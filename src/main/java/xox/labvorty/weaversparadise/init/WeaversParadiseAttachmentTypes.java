package xox.labvorty.weaversparadise.init;

import com.mojang.serialization.Codec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import xox.labvorty.weaversparadise.WeaversParadise;

public class WeaversParadiseAttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, WeaversParadise.MODID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> CHROMATIC_SHIFT_ACTIVE =
            ATTACHMENT_TYPES.register("chromatic_shift_active", () ->
                    AttachmentType.builder(() -> Boolean.FALSE)
                            .serialize(Codec.BOOL)
                            .sync(ByteBufCodecs.BOOL)
                            .build()
            );

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> SCULK_PULSE = ATTACHMENT_TYPES.register(
            "sculk_pulse",
            () -> AttachmentType.builder(() -> 0)
                    .serialize(Codec.INT)
                    .sync(ByteBufCodecs.INT)
                    .build()
    );
}
