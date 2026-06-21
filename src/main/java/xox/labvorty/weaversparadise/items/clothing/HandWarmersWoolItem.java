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
import xox.labvorty.weaversparadise.items.clothing.defined.HandWarmersInterface;
import xox.labvorty.weaversparadise.renderers.bewlr.HandWarmersRenderer;

import java.util.List;
import java.util.function.Consumer;

public class HandWarmersWoolItem extends DoubleSidedClothingItem implements HandWarmersInterface {
    public HandWarmersWoolItem() {
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
                return new HandWarmersRenderer(
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

        CompoundTag compoundTag = new CompoundTag() {{
            putInt("quality", 0);
            putString("dyeTypeLeftOne", "default");
            putString("dyeTypeRightOne", "default");
            putString("dyeTypeLeftTwo", "default");
            putString("dyeTypeRightTwo", "default");
            putString("stensilTypeLeft", "default");
            putString("stensilTypeRight", "default");
            putInt("colorPriRedLeftOne", 255);
            putInt("colorPriGreenLeftOne", 255);
            putInt("colorPriBlueLeftOne", 255);

            putInt("colorPriRedLeftTwo", 255);
            putInt("colorPriGreenLeftTwo", 255);
            putInt("colorPriBlueLeftTwo", 255);

            putInt("colorSecRedLeftOne", 255);
            putInt("colorSecGreenLeftOne", 255);
            putInt("colorSecBlueLeftOne", 255);

            putInt("colorSecRedLeftTwo", 255);
            putInt("colorSecGreenLeftTwo", 255);
            putInt("colorSecBlueLeftTwo", 255);

            putInt("colorPriRedRightOne", 255);
            putInt("colorPriGreenRightOne", 255);
            putInt("colorPriBlueRightOne", 255);

            putInt("colorPriRedRightTwo", 255);
            putInt("colorPriGreenRightTwo", 255);
            putInt("colorPriBlueRightTwo", 255);

            putInt("colorSecRedRightOne", 255);
            putInt("colorSecGreenRightOne", 255);
            putInt("colorSecBlueRightOne", 255);

            putInt("colorSecRedRightTwo", 255);
            putInt("colorSecGreenRightTwo", 255);
            putInt("colorSecBlueRightTwo", 255);

            putInt("lightValueLeftOne", 15);
            putInt("lightValueLeftTwo", 15);
            putInt("lightValueRightOne", 15);
            putInt("lightValueRightTwo", 15);
        }};

        itemStack.setTag(compoundTag);

        return itemStack;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        int quality = stack.getOrCreateTag().getInt("quality");

        return 100 + (25 * quality);
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
                WeaversParadiseEnchantments.SOUND_MUFFLER.get()
        );
    }
}
