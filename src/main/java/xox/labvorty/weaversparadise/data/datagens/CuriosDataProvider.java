package xox.labvorty.weaversparadise.data.datagens;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import xox.labvorty.weaversparadise.WeaversParadiseMod;

import java.util.concurrent.CompletableFuture;

public class CuriosDataProvider extends top.theillusivec4.curios.api.CuriosDataProvider {
    public CuriosDataProvider(PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(WeaversParadiseMod.MOD_ID, output, fileHelper, registries);
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
        createSlot("choker_trinket")
                .size(0)
                .addCosmetic(false)
                .icon(ResourceLocation.parse("curios:slot/choker_trinket"));
        createSlot("pants")
                .size(1)
                .addCosmetic(false)
                .icon(ResourceLocation.parse("curios:slot/pants"));
        createSlot("cape")
                .size(1)
                .addCosmetic(false)
                .icon(ResourceLocation.parse("curios:slot/cape"));
        createSlot("armor_cosmetics")
                .size(1)
                .addCosmetic(false);
        createEntities("weaversparadise_entities")
                .addPlayer()
                .addSlots("legwear", "gloves", "upperwear", "necklace", "choker_trinket", "pants", "cape", "armor_cosmetics", "head");
    }
}
