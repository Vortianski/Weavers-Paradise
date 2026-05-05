package xox.labvorty.weaversparadise.data.loot_modifiers;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class ArmorLootboxModifier extends LootModifier {
    public static final Codec<ArmorLootboxModifier> CODEC = RecordCodecBuilder.create(
            instance -> codecStart(instance).apply(instance, ArmorLootboxModifier::new)
    );

    public ArmorLootboxModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ResourceLocation location = context.getQueriedLootTableId();
        if (location.getPath().startsWith("chests/")) {
            generatedLoot.add(new ItemStack(WeaversParadiseItems.ARMOR_LOOTBOX.get()));
        }

        return generatedLoot;
    }
}