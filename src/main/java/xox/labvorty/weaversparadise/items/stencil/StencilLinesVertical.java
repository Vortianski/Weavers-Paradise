package xox.labvorty.weaversparadise.items.stencil;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class StencilLinesVertical extends Item {
    public StencilLinesVertical() {
        super(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (tooltipFlag.hasShiftDown()) {
            tooltipComponents.add(
                    Component.translatable("weaversparadise.tooltip.applicable_to").withStyle(ChatFormatting.GRAY)
            );
            tooltipComponents.add(
                    Component.translatable("item.weaversparadise.thigh_highs_cotton")
            );
            tooltipComponents.add(
                    Component.translatable("item.weaversparadise.thigh_highs_silk")
            );
            tooltipComponents.add(
                    Component.translatable("item.weaversparadise.thigh_highs_wool")
            );
            tooltipComponents.add(
                    Component.translatable("item.weaversparadise.hand_warmers_cotton")
            );
            tooltipComponents.add(
                    Component.translatable("item.weaversparadise.hand_warmers_silk")
            );
            tooltipComponents.add(
                    Component.translatable("item.weaversparadise.hand_warmers_wool")
            );
            tooltipComponents.add(
                    Component.translatable("item.weaversparadise.shirt_cotton")
            );
            tooltipComponents.add(
                    Component.translatable("item.weaversparadise.shirt_silk")
            );
            tooltipComponents.add(
                    Component.translatable("item.weaversparadise.sweater_wool")
            );
        } else {
            tooltipComponents.add(
                    Component.translatable("weaversparadise.tooltip.hold", "SHIFT").withStyle(ChatFormatting.GRAY)
            );
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
