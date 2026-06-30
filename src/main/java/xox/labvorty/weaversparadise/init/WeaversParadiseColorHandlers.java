package xox.labvorty.weaversparadise.init;

import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeDataColor;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeInstance;
import xox.labvorty.weaversparadise.items.dye.BottledDyeItem;
import xox.labvorty.weaversparadise.items.dye.PigmentItem;
import xox.labvorty.weaversparadise.items.materials.ChromaticBloomFruitItem;
import xox.labvorty.weaversparadise.items.materials.ChromaticDustItem;

@EventBusSubscriber(value = Dist.CLIENT)
public class WeaversParadiseColorHandlers {
    @SubscribeEvent
    public static void itemHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((stack, layer) -> {
            if (stack.getItem() instanceof BottledDyeItem dye) {
                CompoundTag compoundTag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                DyeInstance dyeInstance = DyeTypeRegistry.getDyeType(compoundTag.getString("dyeType"));
                return dyeInstance.getColorParser().apply(
                        new DyeDataColor(
                                layer,
                                compoundTag,
                                dye.getItemMainColor(stack),
                                dye.getItemSecondaryColor(stack),
                                dye.getItemLightValue(stack)
                        )
                );
            }

            return -1;
        }, WeaversParadiseItems.BOTTLED_DYE.get());

        event.register((stack, layer) -> {
            if (layer == 0) {
                return -1;
            }

            if (stack.getItem() instanceof ChromaticBloomFruitItem bloomFruit) {
                Minecraft mc = Minecraft.getInstance();
                int ticks = (int)mc.level.getGameTime() + (layer * 2);

                float speed = 0.05F;

                float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
                float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
                float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

                int truered = (int)(red * 255);
                int truegreen = (int)(green * 255);
                int trueblue = (int)(blue * 255);

                return 255 << 24 | truered << 16 | truegreen << 8 | trueblue;
            }

            return -1;
        }, WeaversParadiseItems.CHROMATIC_BLOOM_FRUIT);

        event.register((stack, layer) -> {
            if (stack.getItem() instanceof PigmentItem pureDyeItem) {
                return pureDyeItem.getDyeColor(stack);
            }

            return -1;
        }, WeaversParadiseItems.PURE_DYE);

        event.register((stack, layer) -> {
            if (stack.getItem() instanceof ChromaticDustItem chromaticDustItem) {
                Minecraft mc = Minecraft.getInstance();
                int ticks = (int)mc.level.getGameTime();

                float speed = 0.05F;

                float red = Mth.clamp((float)(Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
                float green = Mth.clamp((float)(Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
                float blue = Mth.clamp((float)(Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

                int truered = (int)(red * 255);
                int truegreen = (int)(green * 255);
                int trueblue = (int)(blue * 255);

                return 255 << 24 | truered << 16 | truegreen << 8 | trueblue;
            }

            return -1;
        }, WeaversParadiseItems.CHROMATIC_DUST);
    }
}
