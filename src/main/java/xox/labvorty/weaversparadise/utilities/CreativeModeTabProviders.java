package xox.labvorty.weaversparadise.utilities;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.phys.Vec3;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreativeModeTabProviders {
    public static List<ItemStack> getPlayerPlushies() {
        return List.of(
                PlushieItem.createPlushie(Optional.of("Vortianski"), Optional.empty()),
                PlushieItem.createPlushie(Optional.of("Pelemeshek"), Optional.empty()),
                PlushieItem.createPlushie(Optional.of("Elifian"), Optional.empty()),
                PlushieItem.createPlushie(Optional.of("_Alazi_"), Optional.empty()),
                PlushieItem.createPlushie(Optional.of("IRON_carat"), Optional.empty())
        );
    }

    public static List<ItemStack> getPigments() {
        List<ItemStack> pigments = new ArrayList<>();
        List<Vec3> colors = List.of(
                new Vec3(0, 0, 0),
                new Vec3(1, 1, 1),
                new Vec3(1, 0, 0),
                new Vec3(0, 1, 0),
                new Vec3(0, 0, 1),
                new Vec3(1, 1, 0),
                new Vec3(1, 0, 1)
        );

        for (Vec3 vec3 : colors) {
            ItemStack pigment = WeaversParadiseItems.PURE_DYE.toStack();

            CustomData.update(DataComponents.CUSTOM_DATA, pigment, (compoundTag) -> {
                compoundTag.putInt("red", (int)(vec3.x * 255));
                compoundTag.putInt("green", (int)(vec3.y * 255));
                compoundTag.putInt("blue", (int)(vec3.z * 255));
            });

            pigments.add(pigment);
        }

        return pigments;
    }

    public static List<ItemStack> getFlags() {
        return List.of(
                WeaversParadiseItems.FLAG_BASIC.toStack(),
                WeaversParadiseItems.FLAG_AGENDER.toStack(),
                WeaversParadiseItems.FLAG_AROACE.toStack(),
                WeaversParadiseItems.FLAG_AROMANTIC.toStack(),
                WeaversParadiseItems.FLAG_ASEXUAL.toStack(),
                WeaversParadiseItems.FLAG_BISEXUAL.toStack(),
                WeaversParadiseItems.FLAG_DEMIBOY.toStack(),
                WeaversParadiseItems.FLAG_DEMIGENDER.toStack(),
                WeaversParadiseItems.FLAG_GAY.toStack(),
                WeaversParadiseItems.FLAG_GENDERFLUID.toStack(),
                WeaversParadiseItems.FLAG_GENDERQUEER.toStack(),
                WeaversParadiseItems.FLAG_INTERSEX.toStack(),
                WeaversParadiseItems.FLAG_LESBIAN.toStack(),
                WeaversParadiseItems.FLAG_NONBINARY.toStack(),
                WeaversParadiseItems.FLAG_PANSEXUAL.toStack(),
                WeaversParadiseItems.FLAG_PRIDE.toStack(),
                WeaversParadiseItems.FLAG_TRANS.toStack()
        );
    }

    public static List<ItemStack> getArmorCosmetics() {
        return List.of(
                WeaversParadiseItems.ASTOLFO_COSMETICS.toStack(),
                WeaversParadiseItems.BRIDGET_COSMETICS.toStack(),
                WeaversParadiseItems.FELIX_COSMETICS.toStack(),
                WeaversParadiseItems.GRIFFITH_COSMETICS.toStack(),
                WeaversParadiseItems.NIKO_COSMETICS.toStack(),
                WeaversParadiseItems.GABRIEL_COSMETICS.toStack(),
                WeaversParadiseItems.GISELLE_COSMETICS.toStack(),
                WeaversParadiseItems.MIKKELA_COSMETICS.toStack(),
                WeaversParadiseItems.EXPIE_COSMETICS.toStack(),
                WeaversParadiseItems.GASTER_COSMETICS.toStack(),
                WeaversParadiseItems.RALSEI_COSMETICS.toStack(),
                WeaversParadiseItems.MINOS_PRIME_COSMETICS.toStack(),
                WeaversParadiseItems.JAYA_UTOMO_COSMETICS.toStack()
        );
    }

    public static List<ItemStack> getStencils() {
        return List.of(
                WeaversParadiseItems.BASIC_STENCIL.toStack(),
                WeaversParadiseItems.HALF_STENCIL.toStack(),
                WeaversParadiseItems.CHECKERS_STENCIL.toStack(),
                WeaversParadiseItems.CHECKERS_SMALL_STENCIL.toStack(),
                WeaversParadiseItems.LINES_VERTICAL_STENCIL.toStack(),
                WeaversParadiseItems.LINES_SMALL_STENCIL.toStack(),
                WeaversParadiseItems.LINES_BIG_STENCIL.toStack(),
                WeaversParadiseItems.CROSS_STENCIL.toStack(),
                WeaversParadiseItems.PAWS_STENCIL.toStack(),
                WeaversParadiseItems.STAR_STENCIL.toStack(),
                WeaversParadiseItems.DIRT_STENCIL.toStack(),
                WeaversParadiseItems.FLOWER_STENCIL.toStack()
        );
    }
}
