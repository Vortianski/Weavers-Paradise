package xox.labvorty.weaversparadise.items.armor;

import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.List;

public class ArmorLootboxItem extends Item {
    public List<Item> items = List.of(
            WeaversParadiseItems.ASTOLFO_ARMOR_WIG.get(),
            WeaversParadiseItems.ASTOLFO_ARMOR_CHESTPLATE.get(),
            WeaversParadiseItems.ASTOLFO_ARMOR_LEGGINGS.get(),
            WeaversParadiseItems.ASTOLFO_ARMOR_BOOTS.get(),
            WeaversParadiseItems.BRIDGET_ARMOR_HAT.get(),
            WeaversParadiseItems.BRIDGET_ARMOR_JACKET.get(),
            WeaversParadiseItems.BRIDGET_ARMOR_SKIRT.get(),
            WeaversParadiseItems.BRIDGET_ARMOR_BOOTS.get(),
            WeaversParadiseItems.FELIX_ARMOR_HAT.get(),
            WeaversParadiseItems.FELIX_ARMOR_JACKET.get(),
            WeaversParadiseItems.FELIX_ARMOR_SKIRT.get(),
            WeaversParadiseItems.FELIX_ARMOR_BOOTS.get()
    );
    int MAX_ITEMS_PER_PAGE = 6;
    int TICKS_PER_PAGE = 40;

    public ArmorLootboxItem() {
        super(
                new Properties()
                        .rarity(Rarity.EPIC)
                        .stacksTo(4)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack lootboxStack = player.getItemInHand(usedHand);

        if (!level.isClientSide) {
            RandomSource random = level.random;
            Item randomItem = items.get(random.nextInt(items.size()));
            ItemStack rewardStack = new ItemStack(randomItem);

            ItemHandlerHelper.giveItemToPlayer(player, rewardStack);

            if (!player.getAbilities().instabuild) {
                lootboxStack.shrink(1);
            }
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if (context.level() == null) {
            return;
        }

        int ticks = (int) context.level().getGameTime();
        int pages = (int) Math.ceil((double) items.size() / MAX_ITEMS_PER_PAGE);
        float chance = 1.0f / items.size();

        int page = (ticks / TICKS_PER_PAGE) % pages;

        int startIndex = page * MAX_ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + MAX_ITEMS_PER_PAGE, items.size());

        tooltipComponents.add(Component.literal("Possible drops:"));
        for (int i = startIndex; i < endIndex; i++) {
            Item item = items.get(i);
            tooltipComponents.add(Component.literal(" - [").append(item.getName(item.getDefaultInstance())).append(Component.literal("] " + String.format("%.2f%%", chance * 100.0f))));
        }
        tooltipComponents.add(Component.literal("Page " + (page + 1) + "/" + pages));
    }
}
