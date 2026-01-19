package xox.labvorty.weaversparadise.items;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.CommonHooks;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import xox.labvorty.weaversparadise.gui.menu.StringMenu;
import xox.labvorty.weaversparadise.gui.menu.TestMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseModAttributes;
import xox.labvorty.weaversparadise.tooltips.ImageTooltipComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestItem extends Item implements ICurioItem {
    public TestItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
                .attributes(new ItemAttributeModifiers(List.of(
                        new ItemAttributeModifiers.Entry(
                                WeaversParadiseModAttributes.LIFESTEAL,
                                new AttributeModifier(
                                        ResourceLocation.fromNamespaceAndPath("weaversparadise", "lifesteal"),
                                        0.5D,
                                        AttributeModifier.Operation.ADD_VALUE
                                ),
                                EquipmentSlotGroup.MAINHAND
                        )
                ),true))
        );
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (player instanceof ServerPlayer serverPlayer) {
            BlockPos bPos = BlockPos.containing(player.getX(), player.getY(), player.getZ());
            serverPlayer.openMenu(new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return Component.literal("StringScreen");
                }

                @Override
                public boolean shouldTriggerClientSideContainerClosingOnOpen() {
                    return false;
                }

                @Override
                public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                    return new StringMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(bPos));
                }
            }, bPos);
        }

        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        List<ResourceLocation> list = new ArrayList();
        list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        return Optional.of(
                new ImageTooltipComponent(
                        list,
                        ResourceLocation.fromNamespaceAndPath(
                                "weaversparadise",
                                "textures/tooltips/star_full.png"
                        ),
                        "This is a test text",
                        "default",
                        15,
                        -1,
                        -1,
                        ResourceLocation.fromNamespaceAndPath(
                                "weaversparadise",
                                "textures/tooltips/star_full.png"
                        ),
                        "This is a test text",
                        "default",
                        15,
                        -1,
                        -1
                        ,
                        ResourceLocation.fromNamespaceAndPath(
                                "weaversparadise",
                                "textures/tooltips/star_full.png"
                        ),
                        "This is a test text",
                        "default",
                        15,
                        -1,
                        -1,
                        ResourceLocation.fromNamespaceAndPath(
                                "weaversparadise",
                                "textures/tooltips/star_full.png"
                        ),
                        "This is a test text",
                        "default",
                        15,
                        -1,
                        -1
                )
        );
    }


}