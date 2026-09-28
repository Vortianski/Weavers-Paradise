package xox.labvorty.weaversparadise.items.clothing;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingArmorItem;
import xox.labvorty.weaversparadise.items.clothing.defined.Upperwear;
import xox.labvorty.weaversparadise.renderers.bewlr.UpperwearRenderer;

import java.util.List;
import java.util.function.Consumer;

public class CottonCropTopLongSleevedItem extends SingleSidedClothingArmorItem implements Upperwear {
    public CottonCropTopLongSleevedItem() {
        super(
                new Properties()
                        .stacksTo(1)
                        .rarity(Rarity.COMMON)
                        .durability(1),
                EquipmentSlot.CHEST
        );
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack itemStack = super.getDefaultInstance();

        itemStack.setTag(obtainDefault());

        return itemStack;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new UpperwearRenderer(
                        Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                        Minecraft.getInstance().getEntityModels()
                );
            }
        });

        super.initializeClient(consumer);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        int quality = stack.getOrCreateTag().getInt("quality");

        return 140 + (10 * quality);
    }

    @Override
    public boolean getFlag(ItemStack itemStack) {
        return itemStack.getOrCreateTag().getBoolean("is_open");
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        CompoundTag compoundTag = stack.getOrCreateTag();
        CompoundTag candidateCompound = repairCandidate.getOrCreateTag();

        int quality = compoundTag.getInt("quality");
        int candidateQuality = candidateCompound.getInt("quality");

        return repairCandidate.is(WeaversParadiseItems.COTTON_CLOTH.get()) && candidateQuality >= quality;
    }

    @Override
    protected List<Enchantment> getSupportedEnchantments() {
        return List.of(
                Enchantments.UNBREAKING,
                Enchantments.VANISHING_CURSE,
                Enchantments.MENDING,
                Enchantments.BINDING_CURSE
        );
    }
}
