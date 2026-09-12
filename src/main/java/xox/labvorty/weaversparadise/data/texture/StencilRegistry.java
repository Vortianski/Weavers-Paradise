package xox.labvorty.weaversparadise.data.texture;

import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class StencilRegistry {
    private static final List<Stencil> stencils = new ArrayList<>();

    public static void registerStencil(String name, Item item) {
        registerStencil(new Stencil(name, item));
    }

    public static void registerStencil(Stencil stencil) {
        stencils.add(stencil);
    }

    public static List<Stencil> getStencilsForType(String name) {
        return stencils.stream().filter(l -> l.name().equals(name)).toList();
    }

    public static List<String> getStencilsForItem(Item item) {
        return stencils.stream()
                .filter(l -> l.item().equals(item))
                .map(Stencil::name)
                .toList();
    }

    public static boolean acceptsStencil(String name, Item item) {
        return !stencils.stream().filter(l -> l.name().equals(name)).filter(l -> l.item.equals(item)).toList().isEmpty();
    }

    public record Stencil(String name, Item item) {}

    public static void clear() {
        stencils.clear();
    }
}
