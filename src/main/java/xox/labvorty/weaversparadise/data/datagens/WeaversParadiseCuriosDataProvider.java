package xox.labvorty.weaversparadise.data.datagens;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;
import xox.labvorty.weaversparadise.WeaversParadiseMod;

import java.util.concurrent.CompletableFuture;

public class WeaversParadiseCuriosDataProvider extends CuriosDataProvider {
    public WeaversParadiseCuriosDataProvider(PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(WeaversParadiseMod.MOD_ID, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        createSlot("legwear")
                .size(1)
                .addCosmetic(false)
                .icon(new ResourceLocation("curios:slot/legwear"));
        createSlot("gloves")
                .size(1)
                .addCosmetic(false)
                .icon(new ResourceLocation("curios:slot/gloves"));
        createSlot("upperwear")
                .size(1)
                .addCosmetic(false)
                .icon(new ResourceLocation("curios:slot/upperwear"));
        createSlot("choker_trinket")
                .size(0)
                .addCosmetic(false)
                .icon(new ResourceLocation("curios:slot/choker_trinket"));
        createSlot("pants")
                .size(1)
                .addCosmetic(false)
                .icon(new ResourceLocation("curios:slot/pants"));
        createSlot("cape")
                .size(1)
                .addCosmetic(false)
                .icon(new ResourceLocation("curios:slot/cape"));
        createEntities("weaversparadise_entities")
                .addPlayer()
                .addSlots("legwear", "gloves", "upperwear", "necklace", "choker_trinket", "pants", "cape");
    }
}
