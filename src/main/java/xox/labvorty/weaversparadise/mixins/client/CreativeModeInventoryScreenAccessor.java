package xox.labvorty.weaversparadise.mixins.client;

import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreativeModeInventoryScreen.class)
public interface CreativeModeInventoryScreenAccessor {
    @Accessor("selectedTab")
    static CreativeModeTab wp$getSelectedTab() {
        throw new AssertionError("mixin not applied");
    }

    @Accessor("scrollOffs")
    float wp$getScrollOffs();
}
