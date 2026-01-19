package xox.labvorty.weaversparadise.curios;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;
import xox.labvorty.weaversparadise.WeaversParadise;

import java.util.concurrent.CompletableFuture;

public class WeaversParadiseCuriosRegistry extends CuriosDataProvider {
    public WeaversParadiseCuriosRegistry(PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(WeaversParadise.MODID, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        createSlot("legwear")
                .size(1)
                .addCosmetic(false)
                .icon(ResourceLocation.parse("curios:slot/legwear"));
        createSlot("gloves")
                .size(1)
                .addCosmetic(false)
                .icon(ResourceLocation.parse("curios:slot/gloves"));
        createSlot("upperwear")
                .size(1)
                .addCosmetic(false)
                .icon(ResourceLocation.parse("curios:slot/upperwear"));
        createEntities("weavers_entities")
                .addPlayer()
                .addSlots("legwear", "gloves", "upperwear");
    }
}
