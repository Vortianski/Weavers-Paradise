package xox.labvorty.weaversparadise.mixins.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeInstance;
import xox.labvorty.weaversparadise.items.dye.BottledDyeItem;
import xox.labvorty.weaversparadise.items.dye.DyeCoreItem;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {
    @Inject(
            method = "renderItem(Lnet/minecraft/world/item/ItemStack;III)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaversparadise$replaceRenderItem(ItemStack itemStack, int x, int y, int seed, CallbackInfo ci) {
        weaversparadise$tryReplace(itemStack, x, y, ci);
    }

    @Inject(
            method = "renderFakeItem(Lnet/minecraft/world/item/ItemStack;III)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaversparadise$replaceRenderFakeItem(ItemStack itemStack, int x, int y, int seed, CallbackInfo ci) {
        weaversparadise$tryReplace(itemStack, x, y, ci);
    }

    @Unique
    private void weaversparadise$tryReplace(ItemStack itemStack, int x, int y, CallbackInfo ci) {
        if (itemStack.isEmpty() || !(itemStack.getItem() instanceof BottledDyeItem || itemStack.getItem() instanceof DyeCoreItem)) {
            return;
        }

        if (!InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
            return;
        }

        CompoundTag compound = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        GuiGraphics self = (GuiGraphics) (Object) this;

        if (!compound.contains("dyeType")) {
            self.renderItem(new ItemStack(Items.BARRIER), x, y);
            ci.cancel();
            return;
        }

        DyeInstance dyeInstance = DyeTypeRegistry.getDyeType(compound.getString("dyeType"));
        if (dyeInstance == null) {
            self.renderItem(new ItemStack(Items.BARRIER), x, y);
            ci.cancel();
            return;
        }

        dyeInstance.getDyeIcon().renderScaled(self, x, y, 16, 16);
        ci.cancel();
    }
}