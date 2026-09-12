package xox.labvorty.weaversparadise.mixins.client;

import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xox.labvorty.weaversparadise.data.creative_tab.ExpandableGroup;
import xox.labvorty.weaversparadise.data.creative_tab.ExpandableTabHolder;
import xox.labvorty.weaversparadise.data.creative_tab.ExpansionHelpers;

import java.util.Collection;

/**
 * Порт VortyLib CreativeModeInventoryScreenMixin: клик по иконке группы раскрывает/сворачивает
 * её (toggle флага на иконке) и обновляет содержимое вкладки. Проверка instanceof заменена
 * с ExpandableCreativeTab (наследование невозможно на Fabric) на duck-интерфейс.
 */
@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeModeInventoryScreenMixin {
    @Shadow
    private static CreativeModeTab selectedTab;

    @Shadow
    protected abstract void refreshCurrentTabContents(Collection<ItemStack> items);

    @Inject(
            method = "slotClicked",
            at = @At("HEAD"),
            cancellable = true
    )
    private void wp$onSlotClicked(Slot slot, int slotId, int mouseButton, ClickType clickType, CallbackInfo ci) {
        if (selectedTab == null || !((Object) selectedTab instanceof ExpandableTabHolder holder)) {
            return;
        }

        if (slot == null) {
            return;
        }

        ItemStack itemStack = slot.getItem();
        String groupId = ExpansionHelpers.getGroupID(itemStack);
        ExpandableGroup expandableGroup = holder.wp$getGroups().get(groupId);

        if (expandableGroup != null) {
            ExpansionHelpers.toggleExpanded(expandableGroup.icon);
            refreshCurrentTabContents(selectedTab.getDisplayItems());
            ci.cancel();
        }
    }
}
