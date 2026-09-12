package xox.labvorty.weaversparadise.mixins.client;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xox.labvorty.weaversparadise.data.creative_tab.ExpandableGroup;
import xox.labvorty.weaversparadise.data.creative_tab.ExpandableTabHolder;
import xox.labvorty.weaversparadise.data.creative_tab.ExpansionHelpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Прикрепляет к ЛЮБОЙ вкладке Map групп (пустой — на ванильные вкладки не влияет) и
 * повторяет оверрайды VortyLib ExpandableCreativeTab: getDisplayItems() = иконки групп
 * (+ их содержимое, если группа раскрыта) + обычное содержимое; hasAnyItems() = true,
 * если есть группы (оригинал переопределял в true безусловно, в т.ч. для пустой
 * вкладки «Материалы»).
 */
@Mixin(CreativeModeTab.class)
public class CreativeModeTabMixin implements ExpandableTabHolder {
    @Unique
    private Map<String, ExpandableGroup> wp$groups = new LinkedHashMap<>();

    @Override
    public Map<String, ExpandableGroup> wp$getGroups() {
        return this.wp$groups;
    }

    @Override
    public void wp$setGroups(Map<String, ExpandableGroup> groups) {
        this.wp$groups = groups;
    }

    @Inject(method = "getDisplayItems", at = @At("RETURN"), cancellable = true)
    private void wp$expandableDisplayItems(CallbackInfoReturnable<Collection<ItemStack>> cir) {
        if (this.wp$groups.isEmpty()) {
            return;
        }

        Collection<ItemStack> items = new ArrayList<>();

        for (ExpandableGroup group : this.wp$groups.values()) {
            items.add(group.icon.copy());

            if (ExpansionHelpers.isExpanded(group.icon)) {
                for (ItemStack stack : group.items) {
                    items.add(stack.copy());
                }
            }
        }

        items.addAll(cir.getReturnValue());
        cir.setReturnValue(items);
    }

    @Inject(method = "hasAnyItems", at = @At("RETURN"), cancellable = true)
    private void wp$expandableHasAnyItems(CallbackInfoReturnable<Boolean> cir) {
        if (!this.wp$groups.isEmpty()) {
            cir.setReturnValue(true);
        }
    }
}
