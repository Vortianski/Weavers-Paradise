package xox.labvorty.weaversparadise.items.clothing;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingItem;
import xox.labvorty.weaversparadise.items.clothing.defined.ThighHighsInterface;
import xox.labvorty.weaversparadise.renderers.bewlr.ThighHighsRenderer;

import java.util.List;
import java.util.function.Consumer;

public class ThighHighsSilkItem extends DoubleSidedClothingItem implements ThighHighsInterface {
    public ThighHighsSilkItem() {
        super(
                new Properties()
                        .stacksTo(1)
                        .defaultDurability(1)
        );
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new ThighHighsRenderer(
                        Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                        Minecraft.getInstance().getEntityModels()
                );
            }
        });

        super.initializeClient(consumer);
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack itemStack = super.getDefaultInstance();

        itemStack.setTag(obtainDefault());

        return itemStack;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        int quality = stack.getOrCreateTag().getInt("quality");

        return 100 + (15 * quality);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        CompoundTag stackData = stack.getOrCreateTag();
        CompoundTag candidateData = repairCandidate.getOrCreateTag();

        int stackQuality = stackData.getInt("quality");
        int candidateQuality = candidateData.getInt("quality");

        return repairCandidate.is(WeaversParadiseItems.WOOL_CLOTH.get()) && candidateQuality >= stackQuality;
    }

    @Override
    protected List<Enchantment> getSupportedEnchantments() {
        return List.of(
                Enchantments.MENDING,
                Enchantments.UNBREAKING,
                Enchantments.VANISHING_CURSE,
                Enchantments.BINDING_CURSE,
                WeaversParadiseEnchantments.WATER_STRIDER.get()
        );
    }
}
