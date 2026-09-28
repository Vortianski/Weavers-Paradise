package xox.labvorty.weaversparadise.items.misc;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.blocks.entities.PlushieBlockEntity;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.renderers.bewlr.PlushieItemRenderer;

import java.util.UUID;
import java.util.function.Consumer;

public class PlushieItem extends BlockItem implements Plushie {
    public static final String TAG_SKULL_OWNER = "SkullOwner";

    public PlushieItem(Block block) {
        super(block, new Properties()
                .stacksTo(1)
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private BlockEntityWithoutLevelRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (renderer == null) {
                    renderer = new PlushieItemRenderer(
                            Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                            Minecraft.getInstance().getEntityModels()
                    );
                }

                return renderer;
            }
        });
    }

    public GameProfile getProfile(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(TAG_SKULL_OWNER, 10)) {
            return NbtUtils.readGameProfile(tag.getCompound(TAG_SKULL_OWNER));
        }
        return new GameProfile(UUID.randomUUID(), "Steve");
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        String name = getProfile(stack).getName();
        return name != null ? Component.translatable(this.getDescriptionId(), name) : super.getName(stack);
    }

    @Override
    protected boolean updateCustomBlockEntityTag(@NotNull BlockPos pos, @NotNull Level level, @Nullable Player player, @NotNull ItemStack stack, @NotNull BlockState state) {
        boolean result = super.updateCustomBlockEntityTag(pos, level, player, stack, state);

        CompoundTag tag = stack.getTag();
        if (!level.isClientSide
                && tag != null && tag.contains(TAG_SKULL_OWNER, 10)
                && level.getBlockEntity(pos) instanceof PlushieBlockEntity plushie) {
            plushie.setProfile(NbtUtils.readGameProfile(tag.getCompound(TAG_SKULL_OWNER)));
            result = true;
        }

        return result;
    }

    @Override
    public void verifyTagAfterLoad(@NotNull CompoundTag tag) {
        super.verifyTagAfterLoad(tag);

        if (tag.contains(TAG_SKULL_OWNER, 10)) {
            GameProfile profile = NbtUtils.readGameProfile(tag.getCompound(TAG_SKULL_OWNER));
            SkullBlockEntity.updateGameprofile(profile, resolved ->
                    tag.put(TAG_SKULL_OWNER, NbtUtils.writeGameProfile(new CompoundTag(), resolved)));
        }
    }

    public static ItemStack createPlushie(String playerName, UUID uuid) {
        ItemStack stack = new ItemStack(WeaversParadiseItems.PLAYER_PLUSHIE.get());

        GameProfile profile = new GameProfile(uuid != null ? uuid : UUID.randomUUID(), playerName);

        CompoundTag tag = new CompoundTag();
        tag.put(TAG_SKULL_OWNER, NbtUtils.writeGameProfile(new CompoundTag(), profile));
        stack.setTag(tag);

        SkullBlockEntity.updateGameprofile(profile, resolved -> {
            CompoundTag stackTag = stack.getTag();
            if (stackTag != null) {
                stackTag.put(TAG_SKULL_OWNER, NbtUtils.writeGameProfile(new CompoundTag(), resolved));
            }
        });

        return stack;
    }
}