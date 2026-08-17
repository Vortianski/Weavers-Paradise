package xox.labvorty.weaversparadise.items.misc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.PropertyMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.blocks.PlushieBlock;
import xox.labvorty.weaversparadise.blocks.entities.PlushieBlockEntity;
import xox.labvorty.weaversparadise.init.WeaversParadiseBlocks;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class PlushieItem extends BlockItem implements Plushie {
    public PlushieItem(Block block) {
        super(
                block,
                new Properties()
                        .stacksTo(1)
                        .rarity(Rarity.RARE)
                        .component(DataComponents.PROFILE, new ResolvableProfile(new GameProfile(UUID.randomUUID(), "Steve")))
                        .attributes(createAttributeModifiers())
        );
    }

    public ResolvableProfile getProfile(ItemStack itemStack) {
        return itemStack.getOrDefault(DataComponents.PROFILE, new ResolvableProfile(new GameProfile(UUID.fromString("383b1ab0-ae8f-4342-b94d-b4f3d2cfc9c0"), "Vortianski")));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        ResolvableProfile profile = getProfile(stack);

        String profileName = profile.name().orElse("Steve");

        return Component.translatable(this.getDescriptionId(stack), profileName);
    }

    @Override
    protected boolean updateCustomBlockEntityTag(@NotNull BlockPos pos, @NotNull Level level, @Nullable Player player, @NotNull ItemStack stack, @NotNull BlockState state) {
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof PlushieBlockEntity plushieBlockEntity) {
            ResolvableProfile profile = stack.get(DataComponents.PROFILE);
            if (profile != null) {
                plushieBlockEntity.setProfile(profile);
            }
        }

        return super.updateCustomBlockEntityTag(pos, level, player, stack, state);
    }

    @Override
    public void verifyComponentsAfterLoad(ItemStack stack) {
        ResolvableProfile profile = stack.get(DataComponents.PROFILE);

        if (profile != null && !profile.isResolved()) {
            profile.resolve().thenAcceptAsync(
                    resolved -> stack.set(DataComponents.PROFILE, resolved),
                    SkullBlockEntity.CHECKED_MAIN_THREAD_EXECUTOR
            );
        }
    }

    public static ItemStack createPlushie(Optional<String> playerName, Optional<UUID> uuid) {
        ItemStack itemStack = WeaversParadiseItems.PLAYER_PLUSHIE.get().getDefaultInstance();

        ResolvableProfile profile = new ResolvableProfile(
                playerName,
                uuid,
                new PropertyMap()
        );

        itemStack.set(DataComponents.PROFILE, profile);

        profile.resolve().thenAcceptAsync(
                resolved -> itemStack.set(DataComponents.PROFILE, resolved),
                SkullBlockEntity.CHECKED_MAIN_THREAD_EXECUTOR
        );

        return itemStack;
    }

    public static final ResourceLocation MINING_SPEED_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "base_attack_damage");
    public static ItemAttributeModifiers createAttributeModifiers() {
        return ItemAttributeModifiers.builder()
                .add(Attributes.BLOCK_BREAK_SPEED, new AttributeModifier(MINING_SPEED_MODIFIER_ID, -3.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, -2.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
                .build();
    }
}
