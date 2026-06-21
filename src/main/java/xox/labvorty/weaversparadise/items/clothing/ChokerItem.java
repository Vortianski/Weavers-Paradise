package xox.labvorty.weaversparadise.items.clothing;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.SlotContext;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingItem;
import xox.labvorty.weaversparadise.renderers.bewlr.ChokerRenderer;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class ChokerItem extends DoubleSidedClothingItem {
    public ChokerItem() {
        super(
                new Properties()
                        .stacksTo(1)
                        .rarity(Rarity.COMMON)
                        .durability(1)
        );
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();

        modifiers.put(
                SlotAttribute.getOrCreate("choker_trinket"),
                new AttributeModifier(
                        "weaversparadise:choker",
                        1,
                        AttributeModifier.Operation.ADDITION
                )
        );

        return modifiers;
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack itemStack = super.getDefaultInstance();

        CompoundTag compoundTag = new CompoundTag() {{
            putInt("quality", 10);
            putString("dyeTypeLeftOne", "default");
            putString("dyeTypeRightOne", "default");
            putString("dyeTypeLeftTwo", "default");
            putString("dyeTypeRightTwo", "default");
            putString("stensilTypeLeft", "default");
            putString("stensilTypeRight", "default");
            putInt("colorPriRedLeftOne", 160);
            putInt("colorPriGreenLeftOne", 102);
            putInt("colorPriBlueLeftOne", 0);

            putInt("colorPriRedLeftTwo", 160);
            putInt("colorPriGreenLeftTwo", 102);
            putInt("colorPriBlueLeftTwo", 0);

            putInt("colorSecRedLeftOne", 160);
            putInt("colorSecGreenLeftOne", 102);
            putInt("colorSecBlueLeftOne", 0);

            putInt("colorSecRedLeftTwo", 160);
            putInt("colorSecGreenLeftTwo", 102);
            putInt("colorSecBlueLeftTwo", 0);

            putInt("colorPriRedRightOne", 160);
            putInt("colorPriGreenRightOne", 102);
            putInt("colorPriBlueRightOne", 0);

            putInt("colorPriRedRightTwo", 160);
            putInt("colorPriGreenRightTwo", 102);
            putInt("colorPriBlueRightTwo", 0);

            putInt("colorSecRedRightOne", 160);
            putInt("colorSecGreenRightOne", 102);
            putInt("colorSecBlueRightOne", 0);

            putInt("colorSecRedRightTwo", 160);
            putInt("colorSecGreenRightTwo", 102);
            putInt("colorSecBlueRightTwo", 0);

            putInt("lightValueLeftOne", 15);
            putInt("lightValueLeftTwo", 15);
            putInt("lightValueRightOne", 15);
            putInt("lightValueRightTwo", 15);
        }};

        itemStack.setTag(compoundTag);

        return itemStack;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new ChokerRenderer(
                        Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                        Minecraft.getInstance().getEntityModels()
                );
            }
        });

        super.initializeClient(consumer);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 100;
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack itemStack, ItemStack repairCandidate) {
        return repairCandidate.is(WeaversParadiseItems.COTTON_CLOTH.get()) || repairCandidate.is(Items.LEATHER);
    }

    @Override
    protected List<Enchantment> getSupportedEnchantments() {
        return List.of(
                Enchantments.MENDING,
                Enchantments.UNBREAKING,
                Enchantments.VANISHING_CURSE,
                Enchantments.BINDING_CURSE,
                WeaversParadiseEnchantments.BREATHING_EXERCISE.get()
        );
    }
}
