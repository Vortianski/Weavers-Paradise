package xox.labvorty.weaversparadise.data.generators;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;
import xox.labvorty.weaversparadise.WeaversParadise;

import java.util.concurrent.CompletableFuture;

public class CurioRegistry extends CuriosDataProvider {
    public CurioRegistry(PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
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
        createEntities("weavers_entities")
                .addPlayer()
                .addEntities(EntityType.ARMOR_STAND)
                .addSlots("legwear", "gloves", "upperwear", "necklace", "choker_trinket", "pants", "cape", "armor_cosmetics", "head");
    }
}
