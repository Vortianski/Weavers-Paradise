package xox.labvorty.weaversparadise.items.stencil;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.data.texture.StencilRegistry;

import java.util.List;

public abstract class Stencil extends Item {
    int MAX_ITEMS_PER_PAGE = 6;
    int TICKS_PER_PAGE = 40;

    public Stencil(Properties properties) {
        super(properties);
    }

    public String getType() {
        return "default";
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        if (level == null) {
            return;
        }

        List<StencilRegistry.Stencil> stencils = StencilRegistry.getStencilsForType(getType());

        int ticks = (int) level.getGameTime();
        int pages = (int) Math.ceil((double)stencils.size() / MAX_ITEMS_PER_PAGE);

        int page = (ticks / TICKS_PER_PAGE) % pages;

        int startIndex = page * MAX_ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + MAX_ITEMS_PER_PAGE, stencils.size());

        tooltipComponents.add(Component.translatable("weaversparadise.tooltip.applicable_to").withStyle(ChatFormatting.GRAY));
        for (int i = startIndex; i < endIndex; i++) {
            StencilRegistry.Stencil stencil = stencils.get(i);
            tooltipComponents.add(Component.literal(" - ").append(stencil.item().getName(new ItemStack(stencil.item()))));
        }
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.literal("Page " + (page + 1) + "/" + pages));

        super.appendHoverText(itemStack, level, tooltipComponents, tooltipFlag);
    }
}
