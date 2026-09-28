package xox.labvorty.weaversparadise.items.clothing.defined;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public abstract class DoubleSidedClothingArmorItem extends DoubleSidedClothingItem implements ICurioItem, Equipable {
    private final EquipmentSlot SLOT;

    public DoubleSidedClothingArmorItem(Properties properties, EquipmentSlot equipmentSlot) {
        super(properties);
        SLOT = equipmentSlot;
    }

    @Override
    public @NotNull EquipmentSlot getEquipmentSlot() {
        return SLOT;
    }
}