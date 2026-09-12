package xox.labvorty.weaversparadise.util;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketInventory;
import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.TrinketSlot;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/** Replacement for CuriosApi / ICuriosItemHandler / legacy curio helpers. */
public final class WPTrinkets {
    private WPTrinkets() {}

    /** Analog of CurioMatch record from legacy curio helpers. */
    public record CurioMatch<T>(ItemStack stack, T value) {}

    /** Curios slot id -> [group, slot] Trinkets pair. */
    public static String[] trinketsSlot(String curiosId) {
        return switch (curiosId) {
            case "cape" -> new String[]{"chest", "cape"};
            case "necklace" -> new String[]{"chest", "necklace"};
            case "head" -> new String[]{"head", "hat"};
            case "choker_trinket" -> new String[]{"chest", "choker"};
            case "upperwear" -> new String[]{"chest", "upperwear"};
            case "armor_cosmetics" -> new String[]{"chest", "armor_cosmetics"};
            case "gloves" -> new String[]{"hand", "gloves"};
            case "legwear" -> new String[]{"legs", "legwear"};
            case "pants" -> new String[]{"legs", "pants"};
            default -> null;
        };
    }

    /** Replacement for CuriosApi.getCuriosInventory(entity). */
    public static Optional<TrinketComponent> getComponent(LivingEntity entity) {
        return TrinketsApi.getTrinketComponent(entity);
    }

    /** Replacement for handler.isEquipped(predicate). */
    public static boolean isEquipped(LivingEntity entity, Predicate<ItemStack> predicate) {
        return getComponent(entity).map(c -> c.isEquipped(predicate)).orElse(false);
    }

    /** Replacement for handler.findFirstCurio(predicate) — stack only. */
    public static Optional<ItemStack> findFirstCurioStack(LivingEntity entity, Predicate<ItemStack> predicate) {
        return findFirstCurio(entity, predicate).map(Tuple::getB);
    }

    /** Replacement for handler.findFirstCurio(predicate) — (slot ref, stack) tuple. */
    public static Optional<Tuple<SlotReference, ItemStack>> findFirstCurio(LivingEntity entity, Predicate<ItemStack> predicate) {
        return getComponent(entity)
                .map(c -> c.getEquipped(predicate))
                .orElse(List.of())
                .stream()
                .findFirst();
    }

    /** Replacement for handler.findFirstCurio(Item). */
    public static Optional<Tuple<SlotReference, ItemStack>> findFirstCurio(LivingEntity entity, Item item) {
        return findFirstCurio(entity, stack -> stack.is(item));
    }

    /** Replacement for legacy curio helpers.findFirstCurioOfType(entity, clazz). */
    public static <T> Optional<Tuple<SlotReference, ItemStack>> findFirstCurioOfType(LivingEntity entity, Class<T> clazz) {
        return findFirstCurio(entity, stack -> clazz.isInstance(stack.getItem()));
    }

    /** Replacement for legacy curio helpers.findFirstCurioOfType(...).value(). */
    public static <T> Optional<T> findFirstCurioValue(LivingEntity entity, Class<T> clazz) {
        return findFirstCurioOfType(entity, clazz).map(p -> clazz.cast(p.getB().getItem()));
    }

    /** Replacement for legacy curio helpers.findFirstCurioOfType with NULL semantics. */
    @Nullable
    public static <T> CurioMatch<T> findFirstCurioMatch(LivingEntity entity, Class<T> type) {
        for (Tuple<SlotReference, ItemStack> pair : getAllEquipped(entity)) {
            ItemStack stack = pair.getB();
            if (!stack.isEmpty() && type.isInstance(stack.getItem())) {
                return new CurioMatch<>(stack, type.cast(stack.getItem()));
            }
        }
        return null;
    }

    /** Replacement for legacy curio helpers.findAllCuriosOfType. */
    public static <T> List<CurioMatch<T>> findAllCuriosOfType(LivingEntity entity, Class<T> type) {
        List<CurioMatch<T>> results = new ArrayList<>();
        for (Tuple<SlotReference, ItemStack> pair : getAllEquipped(entity)) {
            ItemStack stack = pair.getB();
            if (!stack.isEmpty() && type.isInstance(stack.getItem())) {
                results.add(new CurioMatch<>(stack, type.cast(stack.getItem())));
            }
        }
        return results;
    }

    /** Replacement for legacy curio helpers.modifyFirstCurioOfType. */
    public static <T> boolean modifyFirstCurioOfType(LivingEntity entity, Class<T> type, boolean remove) {
        for (Tuple<SlotReference, ItemStack> pair : getAllEquipped(entity)) {
            ItemStack stack = pair.getB();
            if (!stack.isEmpty() && type.isInstance(stack.getItem())) {
                if (remove) {
                    SlotReference ref = pair.getA();
                    ref.inventory().setItem(ref.index(), ItemStack.EMPTY);
                }
                return true;
            }
        }
        return false;
    }

    /** Replacement for legacy curio helpers.insertIntoFirstAvailableSlotOfType. */
    public static boolean insertIntoFirstAvailableSlotOfType(LivingEntity entity, ItemStack itemStack) {
        if (itemStack == null || itemStack.isEmpty()) return false;
        Optional<TrinketComponent> compOpt = getComponent(entity);
        if (compOpt.isEmpty()) return false;
        TrinketComponent component = compOpt.get();
        for (var groupEntry : component.getInventory().entrySet()) {
            for (var slotEntry : groupEntry.getValue().entrySet()) {
                TrinketInventory inventory = slotEntry.getValue();
                for (int i = 0; i < inventory.getContainerSize(); i++) {
                    if (!inventory.getItem(i).isEmpty()) continue;
                    SlotReference ref = new SlotReference(inventory, i);
                    if (!TrinketSlot.canInsert(itemStack, ref, entity)) continue;
                    int moveCount = Math.min(itemStack.getCount(), Math.max(1, inventory.getMaxStackSize()));
                    ItemStack toInsert = itemStack.copy();
                    toInsert.setCount(moveCount);
                    inventory.setItem(i, toInsert);
                    itemStack.shrink(moveCount);
                    return true;
                }
            }
        }
        return false;
    }

    /** Replacement for handler.setEquippedCurio(identifier, index, stack). */
    public static void setEquipped(SlotReference reference, ItemStack stack) {
        reference.inventory().setItem(reference.index(), stack);
    }

    /** Trinkets slot inventory by group and name. */
    public static Optional<TrinketInventory> getInventory(LivingEntity entity, String group, String slot) {
        return getComponent(entity)
                .map(c -> {
                    var groupMap = c.getInventory().get(group);
                    return groupMap == null ? null : groupMap.get(slot);
                });
    }

    /** Slot inventory by legacy Curios id ("cape", "upperwear", ...). */
    public static Optional<TrinketInventory> getInventoryByCuriosId(LivingEntity entity, String curiosId) {
        String[] gs = trinketsSlot(curiosId);
        if (gs == null) return Optional.empty();
        return getInventory(entity, gs[0], gs[1]);
    }

    /** All equipped stacks. */
    public static List<Tuple<SlotReference, ItemStack>> getAllEquipped(LivingEntity entity) {
        return getComponent(entity).map(TrinketComponent::getAllEquipped).orElse(List.of());
    }

    /** Slot id string "group/name" (analog of slotContext.identifier()). */
    public static String slotId(SlotReference reference) {
        return reference.inventory().getSlotType().getGroup() + "/" + reference.inventory().getSlotType().getName();
    }
}
