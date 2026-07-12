package xox.labvorty.weaversparadise.items.stencil;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.utils.ClientDataProvider;

import java.util.List;

public class StencilCheckersItem extends Stencil {
    public StencilCheckersItem() {
        super(new Properties().rarity(Rarity.COMMON).stacksTo(1));
    }

    @Override
    public String getType() {
        return "checkers";
    }
}
