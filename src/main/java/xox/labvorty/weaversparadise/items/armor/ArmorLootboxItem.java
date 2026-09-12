package xox.labvorty.weaversparadise.items.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.List;

public class ArmorLootboxItem extends Item {
    public List<Item> items = List.of(
            WeaversParadiseItems.ASTOLFO_COSMETICS,
            WeaversParadiseItems.BRIDGET_COSMETICS,
            WeaversParadiseItems.FELIX_COSMETICS,
            WeaversParadiseItems.GRIFFITH_COSMETICS,
            WeaversParadiseItems.NIKO_COSMETICS,
            WeaversParadiseItems.GABRIEL_COSMETICS,
            WeaversParadiseItems.GISELLE_COSMETICS,
            WeaversParadiseItems.MIKKELA_COSMETICS,
            WeaversParadiseItems.EXPIE_COSMETICS,
            WeaversParadiseItems.GASTER_COSMETICS,
            WeaversParadiseItems.RALSEI_COSMETICS,
            WeaversParadiseItems.MINOS_PRIME_COSMETICS,
            WeaversParadiseItems.JAYA_UTOMO_COSMETICS
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

            player.getInventory().placeItemBackInInventory(rewardStack);

            if (!player.getAbilities().instabuild) {
                lootboxStack.shrink(1);
            }
        }

        return super.use(level, player, usedHand);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        net.minecraft.client.Minecraft minecraft = net.minecraft.client.Minecraft.getInstance();
        if (minecraft.level == null) {
            return;
        }

        int ticks = (int) minecraft.level.getGameTime();
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
