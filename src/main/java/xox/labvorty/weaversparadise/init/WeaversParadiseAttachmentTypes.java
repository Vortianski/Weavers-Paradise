package xox.labvorty.weaversparadise.init;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.WeaversParadise;

public class WeaversParadiseAttachmentTypes {
    public static final AttachmentType<Boolean> CHROMATIC_SHIFT_ACTIVE = AttachmentRegistry.<Boolean>builder()
            .initializer(() -> Boolean.FALSE)
            .persistent(Codec.BOOL)
            .syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all())
            .buildAndRegister(ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "chromatic_shift_active"));

    public static final AttachmentType<Integer> SCULK_PULSE = AttachmentRegistry.<Integer>builder()
            .initializer(() -> 0)
            .persistent(Codec.INT)
            .syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all())
            .buildAndRegister(ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "sculk_pulse"));

    public static void touch() {
    }
}
