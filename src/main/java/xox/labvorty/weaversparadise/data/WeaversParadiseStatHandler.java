package xox.labvorty.weaversparadise.data;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import xox.labvorty.weaversparadise.items.*;

import java.util.*;

public class WeaversParadiseStatHandler {

    // Define slot types as constants to avoid magic strings
    private static final String SLOT_LEGWEAR = "legwear";
    private static final String SLOT_GLOVES = "gloves";
    private static final String SLOT_UPPERWEAR = "upperwear";

    // Material types in priority order (cotton > silk > wool)
    private static final String COTTON = "cotton";
    private static final String SILK = "silk";
    private static final String WOOL = "wool";

    public Pair<String, Double> calculateType(LivingEntity livingEntity) {
        return calculateTypeInternal(livingEntity);
    }

    public static Pair<String, Double> calculateStaticType(LivingEntity livingEntity) {
        return calculateTypeInternal(livingEntity);
    }

    private static Pair<String, Double> calculateTypeInternal(LivingEntity livingEntity) {
        MaterialCount counts = new MaterialCount();

        Optional<ICuriosItemHandler> curiosInventory = CuriosApi.getCuriosInventory(livingEntity);

        if (curiosInventory.isPresent()) {
            // Process all slot types
            processSlotType(curiosInventory.get(), livingEntity, SLOT_LEGWEAR, counts);
            processSlotType(curiosInventory.get(), livingEntity, SLOT_GLOVES, counts);
            processSlotType(curiosInventory.get(), livingEntity, SLOT_UPPERWEAR, counts);
        }

        return determineDominantMaterial(counts);
    }

    private static void processSlotType(ICuriosItemHandler handler, LivingEntity livingEntity,
                                        String slotType, MaterialCount counts) {
        ICurioStacksHandler stacksHandler = handler.getCurios().get(slotType);
        if (stacksHandler == null) {
            return;
        }

        for (int i = 0; i < stacksHandler.getSlots(); i++) {
            ItemStack itemStack = stacksHandler.getStacks().getStackInSlot(i);
            if (!itemStack.isEmpty()) {
                classifyItem(itemStack, counts);
            }
        }
    }

    private static void classifyItem(ItemStack itemStack, MaterialCount counts) {
        if (itemStack.getItem() instanceof ThighHighsCotton ||
                itemStack.getItem() instanceof HandWarmersCotton ||
                itemStack.getItem() instanceof ShirtCotton) {
            counts.cotton++;
            counts.total++;
        } else if (itemStack.getItem() instanceof ThighHighsSilk ||
                itemStack.getItem() instanceof HandWarmersSilk ||
                itemStack.getItem() instanceof ShirtSilk) {
            counts.silk++;
            counts.total++;
        } else if (itemStack.getItem() instanceof ThighHighsWool ||
                itemStack.getItem() instanceof HandWarmersWool ||
                itemStack.getItem() instanceof SweaterWool) {
            counts.wool++;
            counts.total++;
        }
    }

    private static Pair<String, Double> determineDominantMaterial(MaterialCount counts) {
        if (counts.total == 0) {
            return new Pair<>("", 0.0);
        }

        // Apply tie-breaking logic: cotton > silk > wool
        if (counts.cotton >= counts.silk && counts.cotton >= counts.wool) {
            // Cotton is dominant or tied for first (cotton wins ties)
            return new Pair<>(COTTON, counts.cotton / counts.total);
        } else if (counts.silk >= counts.wool) {
            // Silk is dominant or tied with wool (silk wins ties)
            return new Pair<>(SILK, counts.silk / counts.total);
        } else {
            // Wool is dominant (no ties to worry about)
            return new Pair<>(WOOL, counts.wool / counts.total);
        }
    }

    // Helper class to track material counts
    private static class MaterialCount {
        double cotton = 0;
        double silk = 0;
        double wool = 0;
        double total = 0;
    }
}