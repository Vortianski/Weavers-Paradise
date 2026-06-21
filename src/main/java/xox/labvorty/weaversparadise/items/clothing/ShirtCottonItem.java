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
import xox.labvorty.weaversparadise.items.clothing.defined.ShirtInterface;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingItem;
import xox.labvorty.weaversparadise.renderers.bewlr.ShirtRenderer;

import java.util.List;
import java.util.function.Consumer;

public class ShirtCottonItem extends SingleSidedClothingItem implements ShirtInterface {
    public ShirtCottonItem() {
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
                return new ShirtRenderer(
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
            putBoolean("is_open", false);
            putString("dyeTypeOne", "default");
            putString("dyeTypeTwo", "default");
            putString("stensilType", "default");
            putInt("colorPriRedOne", 255);
            putInt("colorPriGreenOne", 255);
            putInt("colorPriBlueOne", 255);

            putInt("colorPriRedTwo", 255);
            putInt("colorPriGreenTwo", 255);
            putInt("colorPriBlueTwo", 255);

            putInt("colorSecRedOne", 255);
            putInt("colorSecGreenOne", 255);
            putInt("colorSecBlueOne", 255);

            putInt("colorSecRedTwo", 255);
            putInt("colorSecGreenTwo", 255);
            putInt("colorSecBlueTwo", 255);

            putInt("lightValueOne", 15);
            putInt("lightValueTwo", 15);
        }};

        itemStack.setTag(compoundTag);

        return itemStack;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        int quality = stack.getOrCreateTag().getInt("quality");

        return 100 + (20 * quality);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        CompoundTag stackData = stack.getOrCreateTag();
        CompoundTag candidateData = repairCandidate.getOrCreateTag();

        int stackQuality = stackData.getInt("quality");
        int candidateQuality = candidateData.getInt("quality");

        return repairCandidate.is(WeaversParadiseItems.COTTON_CLOTH.get()) && candidateQuality >= stackQuality;
    }

    @Override
    protected List<Enchantment> getSupportedEnchantments() {
        return List.of(
                Enchantments.MENDING,
                Enchantments.UNBREAKING,
                Enchantments.VANISHING_CURSE,
                Enchantments.BINDING_CURSE,
                WeaversParadiseEnchantments.SOFT_AND_COZY.get()
        );
    }
}
