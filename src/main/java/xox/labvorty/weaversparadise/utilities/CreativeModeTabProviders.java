package xox.labvorty.weaversparadise.utilities;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.phys.Vec3;
import oshi.util.tuples.Pair;
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
                PlushieItem.createPlushie(Optional.of("IRON_carat"), Optional.empty()),
                PlushieItem.createPlushie(Optional.of("FIT_FOX"), Optional.empty()),
                PlushieItem.createPlushie(Optional.of("LynxSNT"), Optional.empty())
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

            CustomData.update(DataComponents.CUSTOM_DATA, instance, (tag) -> {
                tag.putString("metalType", type.getA());
                tag.putInt("color", type.getB().getA());
                tag.putInt("damage", type.getB().getB());
            });

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
            ItemStack pigment = new ItemStack(WeaversParadiseItems.PURE_DYE);

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
                new ItemStack(WeaversParadiseItems.FLAG_BASIC),
                new ItemStack(WeaversParadiseItems.FLAG_AGENDER),
                new ItemStack(WeaversParadiseItems.FLAG_AROACE),
                new ItemStack(WeaversParadiseItems.FLAG_AROMANTIC),
                new ItemStack(WeaversParadiseItems.FLAG_ASEXUAL),
                new ItemStack(WeaversParadiseItems.FLAG_BISEXUAL),
                new ItemStack(WeaversParadiseItems.FLAG_DEMIBOY),
                new ItemStack(WeaversParadiseItems.FLAG_DEMIGENDER),
                new ItemStack(WeaversParadiseItems.FLAG_GAY),
                new ItemStack(WeaversParadiseItems.FLAG_GENDERFLUID),
                new ItemStack(WeaversParadiseItems.FLAG_GENDERQUEER),
                new ItemStack(WeaversParadiseItems.FLAG_INTERSEX),
                new ItemStack(WeaversParadiseItems.FLAG_LESBIAN),
                new ItemStack(WeaversParadiseItems.FLAG_NONBINARY),
                new ItemStack(WeaversParadiseItems.FLAG_PANSEXUAL),
                new ItemStack(WeaversParadiseItems.FLAG_PRIDE),
                new ItemStack(WeaversParadiseItems.FLAG_TRANS)
        );
    }

    public static List<ItemStack> getArmorCosmetics() {
        return List.of(
                new ItemStack(WeaversParadiseItems.ASTOLFO_COSMETICS),
                new ItemStack(WeaversParadiseItems.BRIDGET_COSMETICS),
                new ItemStack(WeaversParadiseItems.FELIX_COSMETICS),
                new ItemStack(WeaversParadiseItems.GRIFFITH_COSMETICS),
                new ItemStack(WeaversParadiseItems.NIKO_COSMETICS),
                new ItemStack(WeaversParadiseItems.GABRIEL_COSMETICS),
                new ItemStack(WeaversParadiseItems.GISELLE_COSMETICS),
                new ItemStack(WeaversParadiseItems.MIKKELA_COSMETICS),
                new ItemStack(WeaversParadiseItems.EXPIE_COSMETICS),
                new ItemStack(WeaversParadiseItems.GASTER_COSMETICS),
                new ItemStack(WeaversParadiseItems.RALSEI_COSMETICS),
                new ItemStack(WeaversParadiseItems.MINOS_PRIME_COSMETICS),
                new ItemStack(WeaversParadiseItems.JAYA_UTOMO_COSMETICS)
        );
    }

    public static List<ItemStack> getStencils() {
        return List.of(
                new ItemStack(WeaversParadiseItems.BASIC_STENCIL),
                new ItemStack(WeaversParadiseItems.HALF_STENCIL),
                new ItemStack(WeaversParadiseItems.CHECKERS_STENCIL),
                new ItemStack(WeaversParadiseItems.CHECKERS_SMALL_STENCIL),
                new ItemStack(WeaversParadiseItems.LINES_VERTICAL_STENCIL),
                new ItemStack(WeaversParadiseItems.LINES_SMALL_STENCIL),
                new ItemStack(WeaversParadiseItems.LINES_BIG_STENCIL),
                new ItemStack(WeaversParadiseItems.CROSS_STENCIL),
                new ItemStack(WeaversParadiseItems.PAWS_STENCIL),
                new ItemStack(WeaversParadiseItems.STAR_STENCIL),
                new ItemStack(WeaversParadiseItems.DIRT_STENCIL),
                new ItemStack(WeaversParadiseItems.FLOWER_STENCIL)
        );
    }
}
