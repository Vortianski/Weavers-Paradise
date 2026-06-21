package xox.labvorty.weaversparadise.items.clothing;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.defined.CapeInterface;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingItem;
import xox.labvorty.weaversparadise.renderers.bewlr.CapeRenderer;

import java.util.List;
import java.util.function.Consumer;

public class CapeWoolItem extends SingleSidedClothingItem implements CapeInterface {
    public CapeWoolItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
                .durability(1)
        );
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new CapeRenderer(
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
        int maxdamage = 100 + (25 * quality);

        return maxdamage;
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
                Enchantments.BINDING_CURSE
        );
    }
}
