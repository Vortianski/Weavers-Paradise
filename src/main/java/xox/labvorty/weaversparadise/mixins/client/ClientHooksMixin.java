package xox.labvorty.weaversparadise.mixins.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.ClientHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xox.labvorty.vortylib.utilities.VortyLibCurioUtilities;
import xox.labvorty.weaversparadise.items.armor.ModelReplacer;

@Mixin(ClientHooks.class)
public class ClientHooksMixin {
    @Inject(method = "getArmorTexture", at = @At("HEAD"), cancellable = true)
    private static void weaversparadise$replaceTexture(Entity entity, ItemStack itemStack, ArmorMaterial.Layer layer, boolean innerModel, EquipmentSlot equipmentSlot, CallbackInfoReturnable<ResourceLocation> cir) {
        if (!(entity instanceof LivingEntity livingEntity)) return;

        VortyLibCurioUtilities.CurioMatch<ModelReplacer> match = VortyLibCurioUtilities.findFirstCurioOfType(livingEntity, ModelReplacer.class);

        if (match != null) {
            cir.setReturnValue(match.value().getTextureForSlot(equipmentSlot));
        }
    }

    @Inject(method = "getArmorModel", at = @At("HEAD"), cancellable = true)
    private static void weaversparadise$replaceModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> defaultModel, CallbackInfoReturnable<Model> cir) {
        VortyLibCurioUtilities.CurioMatch<ModelReplacer> match = VortyLibCurioUtilities.findFirstCurioOfType(livingEntity, ModelReplacer.class);

        if (match != null) {
            HumanoidModel<?> model = match.value().getModelForSlot(livingEntity, equipmentSlot, defaultModel);
            ClientHooks.copyModelProperties(defaultModel, model);
            cir.setReturnValue(model);
        }
    }
}
