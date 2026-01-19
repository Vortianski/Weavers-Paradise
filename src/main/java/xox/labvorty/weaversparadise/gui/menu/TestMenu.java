package xox.labvorty.weaversparadise.gui.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;
import xox.labvorty.weaversparadise.init.WeaversParadiseInterfaces;

public class TestMenu extends AbstractContainerMenu {
    public final Level level;
    public final Player player;
    public int x, y, z;
    private ContainerLevelAccess access = ContainerLevelAccess.NULL;
    private IItemHandler internal;
    public TestMenu(int id, Inventory inventory, FriendlyByteBuf buf) {
        super(WeaversParadiseInterfaces.TEST_MENU.get(), id);
        this.player = inventory.player;
        this.level = inventory.player.level();
        BlockPos bPos = null;
        if (buf != null) {
            bPos = buf.readBlockPos();
            this.x = bPos.getX();
            this.y = bPos.getY();
            this.z = bPos.getZ();
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }


    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
