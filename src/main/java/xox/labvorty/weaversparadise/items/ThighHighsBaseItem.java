package xox.labvorty.weaversparadise.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import xox.labvorty.weaversparadise.data.WeaversParadiseDyeIconHandler;
import xox.labvorty.weaversparadise.tooltips.ImageTooltipComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ThighHighsBaseItem extends Item implements ICurioItem {
    public ThighHighsBaseItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON).durability(1));
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        int quality = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("quality");

        int maxdamage = 100 + (20 * quality);

        return maxdamage;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.sendSystemMessage(Component.literal(player.getItemInHand(usedHand).getMaxDamage() + " " + player.getItemInHand(usedHand).getDamageValue()));

        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        List<ResourceLocation> list = new ArrayList();

        CompoundTag data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        if (data.getInt("quality") >= 2) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 1) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (data.getInt("quality") >= 4) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 3) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (data.getInt("quality") >= 6) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 5) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (data.getInt("quality") >= 8) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 7) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        if (data.getInt("quality") >= 10) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_full.png"));
        } else if (data.getInt("quality") == 9) {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_half.png"));
        } else {
            list.add(ResourceLocation.parse("weaversparadise:textures/tooltips/star_empty.png"));
        }

        return Optional.of(
                new ImageTooltipComponent(
                        list,
                        WeaversParadiseDyeIconHandler.getOrDefault(
                                data.getString("dyeType")
                        ).getTexture(),
                        data.getString("dyeType"),
                        data.getString("dyeType"),
                        data.getInt("lightValue"),
                        getItemMainColor(stack),
                        getItemSecondaryColor(stack),
                        WeaversParadiseDyeIconHandler.getOrDefault(
                                data.getString("dyeType")
                        ).getTexture(),
                        data.getString("dyeType"),
                        data.getString("dyeType"),
                        data.getInt("lightValue"),
                        getItemMainColor(stack),
                        getItemSecondaryColor(stack),
                        WeaversParadiseDyeIconHandler.getOrDefault(
                                data.getString("dyeType")
                        ).getTexture(),
                        data.getString("dyeType"),
                        data.getString("dyeType"),
                        data.getInt("lightValue"),
                        getItemMainColor(stack),
                        getItemSecondaryColor(stack),
                        WeaversParadiseDyeIconHandler.getOrDefault(
                                data.getString("dyeType")
                        ).getTexture(),
                        data.getString("dyeType"),
                        data.getString("dyeType"),
                        data.getInt("lightValue"),
                        getItemMainColor(stack),
                        getItemSecondaryColor(stack)
                )
        );
    }

    public int getItemMainColor(ItemStack stack) {
        int color = 0;

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        int red = tag.getInt("colorRedOne");
        int green = tag.getInt("colorGreenOne");
        int blue = tag.getInt("colorBlueOne");

        color = 255 << 24 | red << 16 | green << 8 | blue;

        return color;
    }

    public int getItemSecondaryColor(ItemStack stack) {
        int color = 0;

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        int red = tag.getInt("colorRedTwo");
        int green = tag.getInt("colorGreenTwo");
        int blue = tag.getInt("colorBlueTwo");

        color = 255 << 24 | red << 16 | green << 8 | blue;

        return color;
    }
}
