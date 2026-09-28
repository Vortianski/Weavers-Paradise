package xox.labvorty.weaversparadise.items.clothing;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import xox.labvorty.weaversparadise.items.clothing.defined.ChokerTrinketInterface;
import xox.labvorty.weaversparadise.renderers.bewlr.FishRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("deprecation")
public class FishItem extends Item implements ICurioItem, ChokerTrinketInterface {
    public FishItem() {
        super(
                new Properties()
                        .stacksTo(1)
                        .rarity(Rarity.COMMON)
                        .durability(1)
        );
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack itemStack = super.getDefaultInstance();

        CompoundTag compoundTag = new CompoundTag() {{
            putInt("color", 255 << 24 | 255 << 16 | 255 << 8 | 255);
            putString("metalType", "minecraft:iron_ingot");
            putInt("damage", 100);
        }};

        itemStack.setTag(compoundTag);

        return itemStack;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new FishRenderer(
                        Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                        Minecraft.getInstance().getEntityModels()
                );
            }
        });

        super.initializeClient(consumer);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return stack.getOrCreateTag().getInt("damage");
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack itemStack, @NotNull ItemStack repairCandidate) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(compoundTag.getString("metalType")));
        Item pushedCandidate = Items.BEDROCK;
        if (item != Items.AIR && item != Items.STONE) {
            pushedCandidate = item;
        }

        return repairCandidate.is(pushedCandidate);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment holder) {
        return holder == Enchantments.MENDING || holder == Enchantments.UNBREAKING || holder == Enchantments.VANISHING_CURSE || holder == Enchantments.BINDING_CURSE;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return book.getAllEnchantments().keySet().stream().allMatch(
                holder -> {
                    return holder == Enchantments.MENDING || holder == Enchantments.UNBREAKING || holder == Enchantments.VANISHING_CURSE || holder == Enchantments.BINDING_CURSE;
                }
        );
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (livingEntity instanceof Player player && player.isCreative()) {
            return true;
        }

        return stack.getAllEnchantments()
                .keySet()
                .stream()
                .noneMatch(holder -> holder == Enchantments.BINDING_CURSE);
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack itemStack) {
        return true;
    }

    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
        List<Component> components = new ArrayList<>();

        components.add(Component.translatable("curios.modifiers.choker_trinket").withStyle(style -> style.withColor(ChatFormatting.GOLD)));
        components.add(Component.translatable("weaversparadise.tooltip.fish").withStyle(style -> style.withColor(ChatFormatting.BLUE)));

        return components;
    }

    @Override
    public List<SoundEvent> getSounds() {
        return List.of(
                SoundEvents.AXOLOTL_IDLE_AIR
        );
    }
}
