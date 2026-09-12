package xox.labvorty.weaversparadise.data.creative_tab;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import xox.labvorty.weaversparadise.init.WeaversParadiseDataComponents;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Порт VortyLib ExpandableCreativeTab. Оригинал наследовал CreativeModeTab и использовал
 * NeoForge-метод CreativeModeTab.Builder#withTabFactory — на Fabric оба пути недоступны
 * (конструктор CreativeModeTab package-private, withTabFactory в ванильном билдере нет),
 * поэтому это фасад над ванильным билдером: build() создаёт обычную вкладку и
 * прикрепляет группы через ExpandableTabHolder (реализуется CreativeModeTabMixin).
 * API (addGroup/title/icon/displayItems) — как в оригинале.
 */
public class ExpandableCreativeTab {

    public static Builder builder() {
        return new Builder(CreativeModeTab.Row.TOP, 0);
    }

    public static Builder builder(CreativeModeTab.Row row, int column) {
        return new Builder(row, column);
    }

    public static class Builder {
        private final CreativeModeTab.Builder inner;
        private final Map<String, ExpandableGroup> groups = new LinkedHashMap<>();

        public Builder(CreativeModeTab.Row row, int column) {
            this.inner = CreativeModeTab.builder(row, column);
        }

        public Builder addGroup(String id, ItemStack icon, List<ItemStack> items) {
            ItemStack taggedIcon = icon.copy();
            taggedIcon.set(WeaversParadiseDataComponents.GROUP_COMPONENT, id);
            taggedIcon.set(WeaversParadiseDataComponents.GROUP_ITEM_COMPONENT, id);

            List<ItemStack> taggedItems = new ArrayList<>();
            for (ItemStack stack : items) {
                ItemStack copy = stack.copy();
                copy.set(WeaversParadiseDataComponents.GROUP_ITEM_COMPONENT, id);
                taggedItems.add(copy);
            }

            groups.put(id, new ExpandableGroup(taggedIcon, taggedItems));

            return this;
        }

        public Builder title(Component title) {
            inner.title(title);
            return this;
        }

        public Builder icon(Supplier<ItemStack> icon) {
            inner.icon(icon);
            return this;
        }

        public Builder displayItems(CreativeModeTab.DisplayItemsGenerator generator) {
            inner.displayItems(generator);
            return this;
        }

        public CreativeModeTab build() {
            CreativeModeTab tab = inner.build();
            ((ExpandableTabHolder) (Object) tab).wp$setGroups(this.groups);
            return tab;
        }
    }
}
