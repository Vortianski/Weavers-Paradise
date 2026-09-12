package xox.labvorty.weaversparadise.mixins.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.tuple.MutablePair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xox.labvorty.weaversparadise.data.creative_tab.ExpandableGroup;
import xox.labvorty.weaversparadise.data.creative_tab.ExpandableTabHolder;
import xox.labvorty.weaversparadise.data.creative_tab.ExpansionHelpers;
import xox.labvorty.weaversparadise.items.defined.AdvancedItemOptions;
import xox.labvorty.weaversparadise.util.WPUtilities;

import java.util.*;

/**
 * Порт VortyLib AbstractContainerScreenMixin (клиент): рендер «+»/«−» на иконках групп,
 * затемнение содержимого раскрытых групп, рамки групп (creative_group_borders.png),
 * тултип группы (vortylib.tab.<id>) и клиентский движок AdvancedItemOptions
 * (зажатие клавиши на предмете -> trigger, прогресс-бар в тултипе).
 * Отличия от оригинала: NeoForge-only API заменён — renderTooltip(...) без ItemStack-аргумента
 * (в ванили 1.21.1 оверлоада с ItemStack нет), blit с float u/v (инт-оверлоад с размером
 * атласа в 1.21.1 удалён), instanceof ExpandableCreativeTab -> duck ExpandableTabHolder.
 */
@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {
    @Unique
    private static final ResourceLocation WP$GROUP_BORDERS = ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/gui/creative_group_borders.png");
    @Unique
    private static final int WP$CELL_SIZE = 18;
    @Unique
    private static final int WP$ATLAS_WIDTH = 18;
    @Unique
    private static final int WP$ATLAS_HEIGHT = 22;
    @Shadow
    @Final
    protected AbstractContainerMenu menu;
    @Shadow
    protected Slot hoveredSlot;
    @Shadow
    protected abstract List<Component> getTooltipFromContainerItem(ItemStack stack);
    @Unique
    protected List<MutablePair<Integer, Item>> wp$itemHold = new ArrayList<>();

    @Inject(method = "renderTooltip", at = @At("HEAD"), cancellable = true)
    private void wp$advancedTooltipItem(GuiGraphics guiGraphics, int x, int y, CallbackInfo ci) {
        if (hoveredSlot != null && !hoveredSlot.getItem().isEmpty()) {
            ItemStack itemStack = hoveredSlot.getItem();

            if (itemStack.getItem() instanceof AdvancedItemOptions advancedItemOptions && advancedItemOptions.useExpander(itemStack) && (ExpansionHelpers.getGroupID(hoveredSlot.getItem()) == null || ExpansionHelpers.getGroupID(hoveredSlot.getItem()).isEmpty())) {
                Item item = itemStack.getItem();

                MutablePair<Integer, Item> pair = wp$itemHold.stream()
                        .filter(p -> p.getValue().equals(item))
                        .findFirst()
                        .orElse(null);

                int ticks = pair != null ? pair.getLeft() : 0;

                List<Component> tooltipList = this.getTooltipFromContainerItem(itemStack);
                tooltipList.add(tooltipList.indexOf(advancedItemOptions.getKeysTooltip()) + 1, WPUtilities.createHoldBar(ticks, 25));

                guiGraphics.renderTooltip(
                        Minecraft.getInstance().font,
                        tooltipList,
                        itemStack.getTooltipImage(),
                        x,
                        y
                );
                ci.cancel();
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void wp$tick(CallbackInfo ci) {
        if (hoveredSlot == null || hoveredSlot.getItem().isEmpty()) {
            wp$fadeHold();
            return;
        }

        ItemStack itemStack = hoveredSlot.getItem();

        if (!(itemStack.getItem() instanceof AdvancedItemOptions advancedItemOptions)) {
            wp$fadeHold();
            return;
        }

        if (!(ExpansionHelpers.getGroupID(hoveredSlot.getItem()) == null || ExpansionHelpers.getGroupID(hoveredSlot.getItem()).isEmpty())) {
            wp$fadeHold();
            return;
        }

        if (!advancedItemOptions.useExpander(itemStack)) {
            wp$fadeHold();
            return;
        }

        Item item = itemStack.getItem();

        MutablePair<Integer, Item> pair = wp$itemHold.stream()
                .filter(p -> p.getValue().equals(item))
                .findFirst()
                .orElse(null);

        if (wp$isKeyOfKeysDown(advancedItemOptions.getKeysToHold())) {
            if (pair != null) {
                pair.setLeft(Math.min(Math.clamp(pair.getLeft() + 1, 0, 26), 100));

                if (pair.getLeft() >= 25) {
                    advancedItemOptions.trigger(((AbstractContainerScreen<?>)(Object)this), itemStack);
                }
            } else {
                wp$itemHold.add(new MutablePair<>(1, item));
            }
        } else {
            if (pair != null) {
                int ticks = pair.getLeft();

                if (ticks > 0) {
                    pair.setLeft(ticks - 1);
                }

                if (pair.getLeft() <= 0) {
                    wp$itemHold.remove(pair);
                }
            }
        }
    }

    @Unique
    private static boolean wp$isKeyOfKeysDown(List<Integer> keys) {
        for (Integer key : keys) {
            if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), key)) {
                return true;
            }
        }

        return false;
    }

    @Unique
    private void wp$fadeHold() {
        Iterator<MutablePair<Integer, Item>> iterator = wp$itemHold.iterator();

        while (iterator.hasNext()) {
            MutablePair<Integer, Item> pair = iterator.next();

            int ticks = pair.getLeft();

            if (ticks <= 0) {
                iterator.remove();
            } else {
                pair.setLeft(ticks - 1);
            }
        }
    }

    @Inject(method = "renderSlot", at = @At("TAIL"))
    private void wp$drawGroupMarker(GuiGraphics guiGraphics, Slot slot, CallbackInfo ci) {
        CreativeModeTab tab = CreativeModeInventoryScreenAccessor.wp$getSelectedTab();
        if (!((Object) tab instanceof ExpandableTabHolder expandableCreativeTab)) {
            return;
        }

        if (!(((AbstractContainerScreen<?>)(Object)this) instanceof CreativeModeInventoryScreen)) {
            return;
        }

        ItemStack itemStack = slot.getItem();
        String groupId = ExpansionHelpers.getGroupID(itemStack);

        if (groupId == null) {
            return;
        }

        boolean expanded = ExpansionHelpers.isExpanded(itemStack);
        PoseStack poseStack = guiGraphics.pose();

        poseStack.pushPose();

        poseStack.translate(slot.x + 10, slot.y + 9, 320);

        guiGraphics.drawString(
                Minecraft.getInstance().font,
                expanded ? "-" : "+",
                0,
                0,
                0xFFFFFF,
                true
        );

        poseStack.popPose();
    }

    @Inject(method = "renderTooltip", at = @At("HEAD"), cancellable = true)
    private void wp$replaceTooltip(GuiGraphics guiGraphics, int x, int y, CallbackInfo ci) {
        CreativeModeTab tab = CreativeModeInventoryScreenAccessor.wp$getSelectedTab();
        if (!((Object) tab instanceof ExpandableTabHolder expandableCreativeTab)) {
            return;
        }

        if (!(((AbstractContainerScreen<?>)(Object)this) instanceof CreativeModeInventoryScreen)) {
            return;
        }

        if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
            ItemStack itemStack = hoveredSlot.getItem();
            String groupId = ExpansionHelpers.getGroupID(itemStack);

            if (groupId == null) {
                return;
            }

            guiGraphics.renderTooltip(Minecraft.getInstance().font, Component.translatable("vortylib.tab." + groupId), x, y);
            ci.cancel();
        }
    }

    @Inject(method = "renderSlot", at = @At("HEAD"))
    private void wp$drawInlinedBackground(GuiGraphics guiGraphics, Slot slot, CallbackInfo ci) {
        CreativeModeTab tab = CreativeModeInventoryScreenAccessor.wp$getSelectedTab();
        if (!((Object) tab instanceof ExpandableTabHolder expandableCreativeTab)) {
            return;
        }

        if (!(((AbstractContainerScreen<?>)(Object)this) instanceof CreativeModeInventoryScreen)) {
            return;
        }

        if (!wp$isCreativeGridSlot(slot)) {
            return;
        }

        String groupId = wp$getExpandedGroupId(expandableCreativeTab, slot.getItem());
        if (groupId == null) {
            return;
        }

        guiGraphics.fill(slot.x, slot.y, slot.x + 16, slot.y + 16, 0x33000000);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;renderLabels(Lnet/minecraft/client/gui/GuiGraphics;II)V"))
    private void wp$drawAllGroupBorders(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        CreativeModeTab tab = CreativeModeInventoryScreenAccessor.wp$getSelectedTab();

        if (!((Object) tab instanceof ExpandableTabHolder expandableCreativeTab)) {
            return;
        }

        Map<Long, String> groupCells = wp$buildGroupCells(expandableCreativeTab, false);
        Map<Long, String> occupiedGroupCells = wp$buildGroupCells(expandableCreativeTab, true);
        int visibleSlots = Math.min(45, this.menu.slots.size());

        guiGraphics.flush();
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0.0F, 0.0F, 100.0F);

        for (int i = 0; i < visibleSlots; i++) {
            Slot slot = this.menu.slots.get(i);
            String groupId = groupCells.get(wp$cellKey(slot.x, slot.y));

            if (groupId != null) {
                wp$drawGroupBorderParts(guiGraphics, slot, groupId, groupCells, occupiedGroupCells, false);
            }
        }

        for (int i = 0; i < visibleSlots; i++) {
            Slot slot = this.menu.slots.get(i);
            String groupId = groupCells.get(wp$cellKey(slot.x, slot.y));

            if (groupId != null) {
                wp$drawGroupBorderParts(guiGraphics, slot, groupId, groupCells, occupiedGroupCells, true);
            }
        }

        guiGraphics.pose().popPose();
        guiGraphics.flush();
    }

    @Unique
    private String wp$getExpandedGroupId(ExpandableTabHolder tab, ItemStack stack) {
        String groupId = ExpansionHelpers.getItemGroupID(stack);

        if (groupId == null) {
            return null;
        }

        ExpandableGroup group = tab.wp$getGroups().get(groupId);

        if (group != null && ExpansionHelpers.isExpanded(group.icon)) {
            return groupId;
        }

        return null;
    }

    @Unique
    private void wp$drawGroupBorderParts(GuiGraphics guiGraphics, Slot slot, String groupId, Map<Long, String> groupCells, Map<Long, String> occupiedGroupCells, boolean innerCorners) {
        int x = slot.x;
        int y = slot.y;
        int step = WP$CELL_SIZE;
        int slotIndex = this.menu.slots.indexOf(slot);
        int col = slotIndex % 9;
        int row = slotIndex / 9;

        boolean north = wp$isSameGroup(groupCells, x, y - step, groupId);
        boolean east = wp$isSameGroup(groupCells, x + step, y, groupId);
        boolean south = wp$isSameGroup(groupCells, x, y + step, groupId);
        boolean west = wp$isSameGroup(groupCells, x - step, y, groupId);

        boolean northWest = wp$isSameGroup(groupCells, x - step, y - step, groupId);
        boolean northEast = wp$isSameGroup(groupCells, x + step, y - step, groupId);
        boolean southEast = wp$isSameGroup(groupCells, x + step, y + step, groupId);
        boolean southWest = wp$isSameGroup(groupCells, x - step, y + step, groupId);

        boolean drawNorth = !north;
        boolean drawEast = !east;
        boolean drawSouth = !south;
        boolean drawWest = !west;

        if (innerCorners) {
            if (north && west && !northWest) {
                wp$blitBorder(guiGraphics, x - 2, y - 2, 9, 9, 3, 3);
            }

            if (north && east && !northEast) {
                wp$blitBorder(guiGraphics, x + 15, y - 2, 4, 9, 3, 3);
            }

            if (south && east && !southEast) {
                wp$blitBorder(guiGraphics, x + 15, y + 15, 4, 4, 3, 3);
            }

            if (south && west && !southWest) {
                wp$blitBorder(guiGraphics, x - 2, y + 15, 9, 4, 3, 3);
            }

            return;
        }

        int horizontalLength = (!east || col == 8) ? 17 : 18;
        int verticalLength   = (!south || row == 4) ? 17 : 18;

        if (drawNorth) {
            wp$blitBorder(guiGraphics, x, y - 1, 0, 2, horizontalLength, 2);
        }

        if (drawSouth) {
            wp$blitBorder(guiGraphics, x, y + 15, 0, 0, horizontalLength, 2);
        }

        if (drawEast) {
            wp$blitBorder(guiGraphics, x + 15, y, 2, 4, 2, verticalLength);
        }

        if (drawWest) {
            wp$blitBorder(guiGraphics, x - 1, y, 0, 4, 2, verticalLength);
        }

        if (drawNorth && drawWest) wp$drawTopLeftCorner(guiGraphics, x, y);
        if (drawNorth && drawEast) wp$drawTopRightCorner(guiGraphics, x, y);
        if (drawSouth && drawEast) wp$drawBottomRightCorner(guiGraphics, x, y);
        if (drawSouth && drawWest) wp$drawBottomLeftCorner(guiGraphics, x, y);
    }

    @Unique
    private Map<Long, String> wp$buildGroupCells(ExpandableTabHolder tab, boolean includeCollapsedIcons) {
        Map<Long, String> groupCells = new HashMap<>();

        if (this.menu instanceof CreativeModeInventoryScreen.ItemPickerMenu itemPickerMenu && (Object) this instanceof CreativeModeInventoryScreenAccessor screenAccessor && !this.menu.slots.isEmpty()) {
            int scrollableRows = Mth.positiveCeilDiv(itemPickerMenu.items.size(), 9) - 5;
            int scrollRow = Math.max((int) (screenAccessor.wp$getScrollOffs() * scrollableRows + 0.5F), 0);
            int originX = this.menu.slots.getFirst().x;
            int originY = this.menu.slots.getFirst().y;

            for (int i = 0; i < itemPickerMenu.items.size(); i++) {
                ItemStack itemStack = itemPickerMenu.items.get(i);
                String itemGroupId = wp$getExpandedGroupId(tab, itemStack);

                if (includeCollapsedIcons && itemGroupId == null) {
                    itemGroupId = ExpansionHelpers.getGroupID(itemStack);
                }

                if (itemGroupId == null) {
                    continue;
                }

                int x = originX + i % 9 * WP$CELL_SIZE;
                int y = originY + (i / 9 - scrollRow) * WP$CELL_SIZE;
                groupCells.put(wp$cellKey(x, y), itemGroupId);
            }

            return groupCells;
        }

        for (int i = 0; i < Math.min(45, this.menu.slots.size()); i++) {
            Slot visibleSlot = this.menu.slots.get(i);
            ItemStack itemStack = visibleSlot.getItem();
            String visibleGroupId = wp$getExpandedGroupId(tab, itemStack);

            if (includeCollapsedIcons && visibleGroupId == null) {
                visibleGroupId = ExpansionHelpers.getGroupID(itemStack);
            }

            if (visibleGroupId != null) {
                groupCells.put(wp$cellKey(visibleSlot.x, visibleSlot.y), visibleGroupId);
            }
        }

        return groupCells;
    }

    @Unique
    private static void wp$drawTopLeftCorner(GuiGraphics guiGraphics, int x, int y) {
        wp$blitBorder(guiGraphics, x - 1, y - 1, 4, 12, 3, 3);
    }

    @Unique
    private static void wp$drawTopRightCorner(GuiGraphics guiGraphics, int x, int y) {
        wp$blitBorder(guiGraphics, x + 14, y - 1, 9, 12, 3, 3);
    }

    @Unique
    private static void wp$drawBottomRightCorner(GuiGraphics guiGraphics, int x, int y) {
        wp$blitBorder(guiGraphics, x + 14, y + 14, 9, 17, 3, 3);
    }

    @Unique
    private static void wp$drawBottomLeftCorner(GuiGraphics guiGraphics, int x, int y) {
        wp$blitBorder(guiGraphics, x - 1, y + 14, 4, 17, 3, 3);
    }

    @Unique
    private static void wp$blitBorder(GuiGraphics guiGraphics, int x, int y, int atlasX, int atlasY, int width, int height) {
        PoseStack poseStack = guiGraphics.pose();

        poseStack.pushPose();

        poseStack.translate(x, y, 0);

        poseStack.scale(1, 1, 1);

        guiGraphics.blit(
                WP$GROUP_BORDERS,
                0,
                0,
                (float) atlasX,
                (float) atlasY,
                width,
                height,
                WP$ATLAS_WIDTH,
                WP$ATLAS_HEIGHT
        );

        poseStack.popPose();
    }

    @Unique
    private static boolean wp$isSameGroup(Map<Long, String> groupCells, int x, int y, String groupId) {
        return groupId.equals(groupCells.get(wp$cellKey(x, y)));
    }

    @Unique
    private static long wp$cellKey(int x, int y) {
        return ((long) x << 32) ^ (y & 0xffffffffL);
    }

    @Unique
    private boolean wp$isCreativeGridSlot(Slot slot) {
        int slotIndex = this.menu.slots.indexOf(slot);
        return slotIndex >= 0 && slotIndex < 45;
    }
}
