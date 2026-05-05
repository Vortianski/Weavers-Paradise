package xox.labvorty.weaversparadise.items.misc;

import com.mojang.authlib.GameProfile;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.renderers.bewlr.CapeRenderer;
import xox.labvorty.weaversparadise.renderers.bewlr.PlushieItemRenderer;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class PlushieItem extends Item implements Plushie {
    public static final String TAG_SKULL_OWNER = "SkullOwner";

    public PlushieItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new PlushieItemRenderer(
                        Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                        Minecraft.getInstance().getEntityModels()
                );
            }
        });

        super.initializeClient(consumer);
    }

    public GameProfile getProfile(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(TAG_SKULL_OWNER, 10)) {
            return NbtUtils.readGameProfile(tag.getCompound(TAG_SKULL_OWNER));
        }

        return new GameProfile(UUID.randomUUID(), "Steve");
    }

    @Override
    public Component getName(ItemStack stack) {
        GameProfile profile = getProfile(stack);
        String name = profile.getName();

        return name != null ? Component.translatable(this.getDescriptionId(), name) : super.getName(stack);
    }

    @Override
    public void verifyTagAfterLoad(CompoundTag tag) {
        super.verifyTagAfterLoad(tag);

        if (tag.contains(TAG_SKULL_OWNER, 8) && !Util.isBlank(tag.getString(TAG_SKULL_OWNER))) {
            GameProfile profile = new GameProfile(null, tag.getString(TAG_SKULL_OWNER));

            SkullBlockEntity.updateGameprofile(profile, resolved -> {
                tag.put(TAG_SKULL_OWNER, NbtUtils.writeGameProfile(new CompoundTag(), resolved));
            });
        }
    }

    public static ItemStack createPreMadePlushieAsync(String playerName, UUID uuid) {
        ItemStack stack = new ItemStack(WeaversParadiseItems.PLAYER_PLUSHIE.get());

        GameProfile profile = new GameProfile(
                uuid != null ? uuid : UUID.randomUUID(),
                playerName
        );

        CompoundTag tag = new CompoundTag();
        tag.put("SkullOwner", NbtUtils.writeGameProfile(new CompoundTag(), profile));
        stack.setTag(tag);

        SkullBlockEntity.updateGameprofile(profile, resolved -> {
            CompoundTag newTag = new CompoundTag();
            newTag.put("SkullOwner", NbtUtils.writeGameProfile(new CompoundTag(), resolved));
            stack.setTag(newTag);
        });

        return stack;
    }
}