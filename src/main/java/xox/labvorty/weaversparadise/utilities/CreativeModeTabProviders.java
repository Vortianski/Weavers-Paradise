package xox.labvorty.weaversparadise.utilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import oshi.util.tuples.Pair;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;

import java.util.ArrayList;
import java.util.List;

public class CreativeModeTabProviders {
    public static List<ItemStack> getPlushies() {
        return List.of(
                PlushieItem.createPlushie("Vortianski", null),
                PlushieItem.createPlushie("Pelemeshek", null),
                PlushieItem.createPlushie("Elifian", null),
                PlushieItem.createPlushie("_Alazi_", null)
        );
    }

    public static List<ItemStack> getArmorCosmetics() {
        return List.of(
                WeaversParadiseItems.ASTOLFO_COSMETICS.get().getDefaultInstance(),
                WeaversParadiseItems.BRIDGET_COSMETICS.get().getDefaultInstance(),
                WeaversParadiseItems.FELIX_COSMETICS.get().getDefaultInstance(),
                WeaversParadiseItems.GRIFFITH_COSMETICS.get().getDefaultInstance(),
                WeaversParadiseItems.NIKO_COSMETICS.get().getDefaultInstance(),
                WeaversParadiseItems.GABRIEL_COSMETICS.get().getDefaultInstance(),
                WeaversParadiseItems.GISELLE_COSMETICS.get().getDefaultInstance(),
                WeaversParadiseItems.MIKKELA_COSMETICS.get().getDefaultInstance(),
                WeaversParadiseItems.EXPIE_COSMETICS.get().getDefaultInstance(),
                WeaversParadiseItems.GASTER_COSMETICS.get().getDefaultInstance(),
                WeaversParadiseItems.RALSEI_COSMETICS.get().getDefaultInstance(),
                WeaversParadiseItems.MINOS_PRIME_COSMETICS.get().getDefaultInstance(),
                WeaversParadiseItems.JAYA_UTOMO_COSMETICS.get().getDefaultInstance()
        );
    }

    private static final List<Pair<String, Pair<Integer, Integer>>> metalTypes = List.of(
            new Pair<>("minecraft:iron_ingot", new Pair<>(-1, 100)),
            new Pair<>("minecraft:gold_ingot", new Pair<>(-1, 25)),
            new Pair<>("minecraft:copper_ingot", new Pair<>(-34267, 50))
    );

    public static List<ItemStack> getTrinketList(Item item) {
        List<ItemStack> itemStacks = new ArrayList<>();

        for (Pair<String, Pair<Integer, Integer>> type : metalTypes) {
            ItemStack instance = new ItemStack(item);

            CompoundTag tag = instance.getOrCreateTag();

            tag.putString("metalType", type.getA());
            tag.putInt("color", type.getB().getA());
            tag.putInt("damage", type.getB().getB());

            instance.setTag(tag);

            itemStacks.add(instance);
        }

        return itemStacks;
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
            ItemStack pigment = WeaversParadiseItems.PIGMENT.get().getDefaultInstance();

            CompoundTag compoundTag = pigment.getOrCreateTag();
            compoundTag.putInt("red", (int)(vec3.x * 255));
            compoundTag.putInt("green", (int)(vec3.y * 255));
            compoundTag.putInt("blue", (int)(vec3.z * 255));

            pigment.setTag(compoundTag);

            pigments.add(pigment);
        }

        return pigments;
    }

    public static List<ItemStack> getFlags() {
        return List.of(
                WeaversParadiseItems.FLAG_BASIC.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_AGENDER.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_AROACE.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_AROMANTIC.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_ASEXUAL.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_BISEXUAL.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_DEMIBOY.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_DEMIGENDER.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_DEMIGIRL.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_GAY.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_GENDERFLUID.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_GENDERQUEER.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_INTERSEX.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_LESBIAN.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_NONBINARY.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_PANSEXUAL.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_PRIDE.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_TRANS.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_TRANSFEMININE.get().getDefaultInstance(),
                WeaversParadiseItems.FLAG_TRANSMASCULINE.get().getDefaultInstance()
        );
    }

    public static List<ItemStack> getStencils() {
        return List.of(
                WeaversParadiseItems.BASIC_STENCIL.get().getDefaultInstance(),
                WeaversParadiseItems.HALF_STENCIL.get().getDefaultInstance(),
                WeaversParadiseItems.CHECKERS_STENCIL.get().getDefaultInstance(),
                WeaversParadiseItems.CHECKERS_SMALL_STENCIL.get().getDefaultInstance(),
                WeaversParadiseItems.LINES_VERTICAL_STENCIL.get().getDefaultInstance(),
                WeaversParadiseItems.LINES_SMALL_STENCIL.get().getDefaultInstance(),
                WeaversParadiseItems.LINES_BIG_STENCIL.get().getDefaultInstance(),
                WeaversParadiseItems.CROSS_STENCIL.get().getDefaultInstance(),
                WeaversParadiseItems.PAWS_STENCIL.get().getDefaultInstance(),
                WeaversParadiseItems.STAR_STENCIL.get().getDefaultInstance(),
                WeaversParadiseItems.DIRT_STENCIL.get().getDefaultInstance(),
                WeaversParadiseItems.FLOWER_STENCIL.get().getDefaultInstance()
        );
    }
}
