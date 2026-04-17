package xox.labvorty.weaversparadise.mixins;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.items.clothing.PantsJeansItem;

import java.util.Optional;

@Mixin(AbstractArrow.class)
public class AbstractArrowMixin {
    @Inject(
            method = "onHitEntity",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaversparadise$deflectOnHit(EntityHitResult hitResult, CallbackInfo ci) {
        Entity target = hitResult.getEntity();
        if (!(target instanceof Player player)) return;

        Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(player);
        if (handler.isEmpty()) return;

        Optional<SlotResult> slotResult = handler.get().findFirstCurio(stack ->
                stack.getItem() instanceof PantsJeansItem
        );
        if (slotResult.isEmpty()) return;

        ItemStack itemStack = slotResult.get().stack();
        if (itemStack.isEmpty()) return;

        int durabilityLeft = itemStack.getMaxDamage() - itemStack.getDamageValue();
        if (!(durabilityLeft > 1)) return;

        Holder<Enchantment> toughAsNails = player.level()
                .registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(WeaversParadiseEnchantments.TOUGH_AS_NAILS);

        int level = itemStack.getEnchantmentLevel(toughAsNails);
        if (level <= 0) return;

        float chance = level / 30.0f;
        if (player.getRandom().nextFloat() > chance) return;

        AbstractArrow arrow = (AbstractArrow)(Object)this;

        if (!player.level().isClientSide) {
            Vec3 away = arrow.position().subtract(player.position()).normalize();

            double speed = Math.max(arrow.getDeltaMovement().length(), 0.8D) * 0.2;

            Vec3 newMotion = away.scale(speed).add(0.0D, 0.15D, 0.0D);

            arrow.setDeltaMovement(newMotion);
            arrow.hurtMarked = true;

            arrow.setYRot((float)(net.minecraft.util.Mth.atan2(newMotion.x, newMotion.z) * (180F / Math.PI)));
            arrow.setXRot((float)(net.minecraft.util.Mth.atan2(newMotion.y, newMotion.horizontalDistance()) * (180F / Math.PI)));
        }

        ci.cancel();
    }
}
