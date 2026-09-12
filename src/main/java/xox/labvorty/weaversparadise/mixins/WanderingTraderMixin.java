package xox.labvorty.weaversparadise.mixins;

import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

/** Редкая сделка бродячего торговца: ArmorLootbox за 32 изумруда, 1 использование. */
@Mixin(WanderingTrader.class)
public abstract class WanderingTraderMixin {

    @Inject(method = "updateTrades", at = @At("TAIL"))
    private void weaversparadise$addRareTrade(CallbackInfo ci) {
        WanderingTrader trader = (WanderingTrader) (Object) this;
        trader.getOffers().add(new MerchantOffer(
                new ItemCost(Items.EMERALD, 32),
                new ItemStack(WeaversParadiseItems.ARMOR_LOOTBOX),
                1, 1, 0.05F
        ));
    }
}
