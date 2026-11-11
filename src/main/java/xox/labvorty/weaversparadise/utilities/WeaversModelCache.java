package xox.labvorty.weaversparadise.utilities;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import xox.labvorty.weaversparadise.items.armor.ModelReplacer;

import java.util.*;
import java.util.function.Supplier;

public final class WeaversModelCache {
    private static final String BUNDLED_PACKAGE = "xox.labvorty.weaversparadise.items.armor.";
    private static final ClassValue<Boolean> BUNDLED = new ClassValue<>() {
        @Override
        protected Boolean computeValue(Class<?> type) {
            return type.getName().startsWith(BUNDLED_PACKAGE);
        }
    };
    private static final Map<Key, HumanoidModel<?>> MODELS = new HashMap<>();
    private static final Map<List<? extends String>, Set<Item>> ITEM_SETS = Collections.synchronizedMap(new IdentityHashMap<>());

    private record Key(ModelReplacer replacer, EquipmentSlot slot, boolean additional) {
    }

    public static void register(IEventBus modBus) {
        modBus.addListener(WeaversModelCache::onRegisterReloadListeners);
    }

    private static void onRegisterReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener((net.minecraft.server.packs.resources.ResourceManagerReloadListener) manager -> {
            MODELS.clear();
            ITEM_SETS.clear();
        });
    }

    public static HumanoidModel<?> model(ModelReplacer replacer, LivingEntity entity, EquipmentSlot slot, HumanoidModel<?> parentModel, Supplier<HumanoidModel<?>> build) {
        return cached(new Key(replacer, slot, false), entity, parentModel, build);
    }

    public static HumanoidModel<?> additionalModel(ModelReplacer replacer, LivingEntity entity, EquipmentSlot slot, HumanoidModel<?> parentModel, Supplier<HumanoidModel<?>> build) {
        return cached(new Key(replacer, slot, true), entity, parentModel, build);
    }

    public static boolean containsItem(List<? extends String> ids, Item item) {
        return ITEM_SETS.computeIfAbsent(ids, WeaversModelCache::resolve).contains(item);
    }

    private static HumanoidModel<?> cached(Key key, LivingEntity entity, HumanoidModel<?> parentModel, Supplier<HumanoidModel<?>> build) {
        if (!BUNDLED.get(key.replacer().getClass())) {
            return build.get();
        }
        HumanoidModel<?> model = MODELS.get(key);
        if (model == null) {
            model = build.get();
            if (model != null) {
                MODELS.put(key, model);
            }
            return model;
        }
        model.crouching = entity.isShiftKeyDown();
        model.riding = parentModel.riding;
        model.young = entity.isBaby();
        return model;
    }

    private static Set<Item> resolve(List<? extends String> ids) {
        Set<Item> items = new HashSet<>();
        for (String id : ids) {
            items.add(BuiltInRegistries.ITEM.get(ResourceLocation.parse(id)));
        }
        return items;
    }

    private WeaversModelCache() {
    }
}