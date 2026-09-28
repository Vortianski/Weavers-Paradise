package xox.labvorty.weaversparadise.mixins.compat;

import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.patchouli.client.book.BookPage;
import vazkii.patchouli.client.book.ClientBookRegistry;
import xox.labvorty.weaversparadise.compat.patchouli.DoubleSidedClothingListPage;
import xox.labvorty.weaversparadise.compat.patchouli.SingleSidedClothingListPage;

import java.util.Map;

@Mixin(value = ClientBookRegistry.class, remap = false)
public class ClientBookRegistryMixin {
    @Shadow(remap = false)
    @Final
    public Map<ResourceLocation, Class<? extends BookPage>> pageTypes;

    @Inject(
            at = @At("TAIL"),
            method = "addPageTypes",
            remap = false
    )
    private void weaversparadise$addPageTypes(CallbackInfo ci) {
        pageTypes.put(ResourceLocation.parse("weaversparadise:single_sided_clothing_list"), SingleSidedClothingListPage.class);
        pageTypes.put(ResourceLocation.parse("weaversparadise:double_sided_clothing_list"), DoubleSidedClothingListPage.class);
    }
}
