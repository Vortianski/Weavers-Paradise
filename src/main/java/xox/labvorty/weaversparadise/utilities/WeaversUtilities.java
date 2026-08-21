package xox.labvorty.weaversparadise.utilities;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import xox.labvorty.vortylib.utilities.VortyLibUtilities;
import xox.labvorty.weaversparadise.configs.ClientConfig;
import xox.labvorty.weaversparadise.data.texture.StencilRegistry;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingItem;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingItem;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class WeaversUtilities {
    public static final List<String> dyeTypes = List.of(
            "default",
            "agender",
            "aroace",
            "aromantic",
            "asexual",
            "bisexual",
            "demiboy",
            "demigender",
            "demigirl",
            "gay",
            "genderfluid",
            "genderqueer",
            "intersex",
            "lesbian",
            "nonbinary",
            "pansexual",
            "pride",
            "trans",
            "redstone",
            "lamp",
            "sculk",
            "colored_sculk",
            "hunger",
            "health",
            "day_time",
            "colored_day_time",
            "glowstone",
            "rainbow",
            "biome",
            "ender",
            "speed",
            "height_bedrock",
            "height_sea",
            "invisible",
            "static",
            "crystal",
            "negative",
            "true_negative",
            "nebula",
            "polychromatic",
            "starfall",
            "chromatic_aberration",
            "boykisser",
            "forcefield",
            "hypnotic"
    );
    private static final EquipmentSlot[] ARMOR_SLOTS = {
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.BODY, EquipmentSlot.LEGS, EquipmentSlot.FEET
    };

    public static boolean isRestricted(LivingEntity entity, List<? extends String> restrictorIds) {
        if (restrictorIds.isEmpty()) return false;

        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack stack = entity.getItemBySlot(slot);
            if (!stack.isEmpty() && ClientConfig.containsItem(restrictorIds, stack.getItem())) {
                return true;
            }
        }

        return checkCurios(entity, restrictorIds);
    }

    private static boolean checkCurios(LivingEntity entity, List<? extends String> restrictorIds) {
        Optional<ICuriosItemHandler> curios = CuriosApi.getCuriosInventory(entity);

        if (curios.isEmpty()) return false;

        ICuriosItemHandler handler = curios.get();

        for (var entry : handler.getCurios().entrySet()) {
            var stackHandler = entry.getValue().getStacks();
            for (int i = 0; i < stackHandler.getSlots(); i++) {
                ItemStack stack = stackHandler.getStackInSlot(i);
                if (!stack.isEmpty() && ClientConfig.containsItem(restrictorIds, stack.getItem())) {
                    return true;
                }
            }
        }

        return false;
    }

    public static ItemStack createRandomSingleSidedClothing(SingleSidedClothingItem item) {
        ItemStack stack = new ItemStack(item);

        CompoundTag tag = new CompoundTag();
        Random random = new Random();

        List<String> stencils = StencilRegistry.getStencilsForItem(item);

        List<String> dyeTypes = List.of(
                "default",
                "agender",
                "aroace",
                "aromantic",
                "asexual",
                "bisexual",
                "demiboy",
                "demigender",
                "demigirl",
                "gay",
                "genderfluid",
                "genderqueer",
                "intersex",
                "lesbian",
                "nonbinary",
                "pansexual",
                "pride",
                "trans",
                "redstone",
                "lamp",
                "sculk",
                "colored_sculk",
                "hunger",
                "health",
                "day_time",
                "colored_day_time",
                "glowstone",
                "rainbow",
                "biome",
                "ender",
                "speed",
                "height_bedrock",
                "height_sea",
                "invisible",
                "static",
                "crystal",
                "negative",
                "true_negative",
                "nebula",
                "polychromatic",
                "starfall",
                "chromatic_aberration",
                "boykisser"
        );

        tag.putInt("quality", random.nextInt(11));

        tag.putString("dyeTypeOne", dyeTypes.get(random.nextInt(dyeTypes.size())));
        tag.putString("dyeTypeTwo", dyeTypes.get(random.nextInt(dyeTypes.size())));

        tag.putString("stensilType", stencils.get(random.nextInt(stencils.size())));

        tag.putInt("colorPriRedOne", random.nextInt(256));
        tag.putInt("colorPriGreenOne", random.nextInt(256));
        tag.putInt("colorPriBlueOne", random.nextInt(256));

        tag.putInt("colorPriRedTwo", random.nextInt(256));
        tag.putInt("colorPriGreenTwo", random.nextInt(256));
        tag.putInt("colorPriBlueTwo", random.nextInt(256));

        tag.putInt("colorSecRedOne", random.nextInt(256));
        tag.putInt("colorSecGreenOne", random.nextInt(256));
        tag.putInt("colorSecBlueOne", random.nextInt(256));

        tag.putInt("colorSecRedTwo", random.nextInt(256));
        tag.putInt("colorSecGreenTwo", random.nextInt(256));
        tag.putInt("colorSecBlueTwo", random.nextInt(256));

        tag.putInt("lightValueOne", random.nextInt(16));
        tag.putInt("lightValueTwo", random.nextInt(16));

        VortyLibUtilities.serializeSpecificVector3f(
                tag,
                new org.joml.Vector3f(
                        random.nextFloat(),
                        random.nextFloat(),
                        random.nextFloat()
                ),
                "glintColor"
        );

        stack.set(
                DataComponents.CUSTOM_DATA,
                CustomData.of(tag)
        );

        return stack;
    }

    public static ItemStack createRandomDoubleSidedClothing(DoubleSidedClothingItem item) {
        ItemStack stack = new ItemStack(item);

        CompoundTag tag = new CompoundTag();
        Random random = new Random();

        List<String> stencils = StencilRegistry.getStencilsForItem(item);

        List<String> dyeTypes = List.of(
                "default",
                "agender",
                "aroace",
                "aromantic",
                "asexual",
                "bisexual",
                "demiboy",
                "demigender",
                "demigirl",
                "gay",
                "genderfluid",
                "genderqueer",
                "intersex",
                "lesbian",
                "nonbinary",
                "pansexual",
                "pride",
                "trans",
                "redstone",
                "lamp",
                "sculk",
                "colored_sculk",
                "hunger",
                "health",
                "day_time",
                "colored_day_time",
                "glowstone",
                "rainbow",
                "biome",
                "ender",
                "speed",
                "height_bedrock",
                "height_sea",
                "invisible",
                "static",
                "crystal",
                "negative",
                "true_negative",
                "nebula",
                "polychromatic",
                "starfall",
                "chromatic_aberration",
                "boykisser"
        );

        tag.putInt("quality", random.nextInt(11));

        // LEFT
        tag.putString("dyeTypeLeftOne", dyeTypes.get(random.nextInt(dyeTypes.size())));
        tag.putString("dyeTypeLeftTwo", dyeTypes.get(random.nextInt(dyeTypes.size())));
        tag.putString("stensilTypeLeft", stencils.get(random.nextInt(stencils.size())));

        tag.putInt("colorPriRedLeftOne", random.nextInt(256));
        tag.putInt("colorPriGreenLeftOne", random.nextInt(256));
        tag.putInt("colorPriBlueLeftOne", random.nextInt(256));

        tag.putInt("colorPriRedLeftTwo", random.nextInt(256));
        tag.putInt("colorPriGreenLeftTwo", random.nextInt(256));
        tag.putInt("colorPriBlueLeftTwo", random.nextInt(256));

        tag.putInt("colorSecRedLeftOne", random.nextInt(256));
        tag.putInt("colorSecGreenLeftOne", random.nextInt(256));
        tag.putInt("colorSecBlueLeftOne", random.nextInt(256));

        tag.putInt("colorSecRedLeftTwo", random.nextInt(256));
        tag.putInt("colorSecGreenLeftTwo", random.nextInt(256));
        tag.putInt("colorSecBlueLeftTwo", random.nextInt(256));

        tag.putInt("lightValueLeftOne", random.nextInt(16));
        tag.putInt("lightValueLeftTwo", random.nextInt(16));

        // RIGHT
        tag.putString("dyeTypeRightOne", dyeTypes.get(random.nextInt(dyeTypes.size())));
        tag.putString("dyeTypeRightTwo", dyeTypes.get(random.nextInt(dyeTypes.size())));
        tag.putString("stensilTypeRight", stencils.get(random.nextInt(stencils.size())));

        tag.putInt("colorPriRedRightOne", random.nextInt(256));
        tag.putInt("colorPriGreenRightOne", random.nextInt(256));
        tag.putInt("colorPriBlueRightOne", random.nextInt(256));

        tag.putInt("colorPriRedRightTwo", random.nextInt(256));
        tag.putInt("colorPriGreenRightTwo", random.nextInt(256));
        tag.putInt("colorPriBlueRightTwo", random.nextInt(256));

        tag.putInt("colorSecRedRightOne", random.nextInt(256));
        tag.putInt("colorSecGreenRightOne", random.nextInt(256));
        tag.putInt("colorSecBlueRightOne", random.nextInt(256));

        tag.putInt("colorSecRedRightTwo", random.nextInt(256));
        tag.putInt("colorSecGreenRightTwo", random.nextInt(256));
        tag.putInt("colorSecBlueRightTwo", random.nextInt(256));

        tag.putInt("lightValueRightOne", random.nextInt(16));
        tag.putInt("lightValueRightTwo", random.nextInt(16));

        VortyLibUtilities.serializeSpecificVector3f(
                tag,
                new org.joml.Vector3f(
                        random.nextFloat(),
                        random.nextFloat(),
                        random.nextFloat()
                ),
                "glintColor"
        );

        stack.set(
                DataComponents.CUSTOM_DATA,
                CustomData.of(tag)
        );

        return stack;
    }
}
