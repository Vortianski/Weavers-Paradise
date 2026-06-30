package xox.labvorty.weaversparadise.init;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xox.labvorty.weaversparadise.data.tooltip_components.ClothingTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.QualityTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.client.ClothingClientTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.client.DyeClientTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.client.QualityClientTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeDataColor;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeInstance;
import xox.labvorty.weaversparadise.items.dye.BottledDyeItem;
import xox.labvorty.weaversparadise.items.dye.PigmentItem;
import xox.labvorty.weaversparadise.items.materials.ChromaticBloomFruitItem;
import xox.labvorty.weaversparadise.items.materials.ChromaticDustItem;

import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaversParadiseClientRegistry {
    @SubscribeEvent
    public static void registerTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ClothingTooltipComponent.class, data -> new ClothingClientTooltipComponent(
                data.getQualityTextures(),
                data.getEntries()
        ));
        event.register(DyeTooltipComponent.class, data -> new DyeClientTooltipComponent(data.getDyeIcon(), data.getText(), data.getType(), data.getLightValue(), data.getPrimaryColor(), data.getSecondaryColor()));
        event.register(QualityTooltipComponent.class, data -> new QualityClientTooltipComponent(data.getTextures()));
    }

    @SubscribeEvent
    public static void itemHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((stack, layer) -> {
            if (stack.getItem() instanceof BottledDyeItem dye) {
                CompoundTag compoundTag = stack.getOrCreateTag();

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
            if (stack.getItem() instanceof PigmentItem pureDyeItem) {
                return pureDyeItem.getDyeColor(stack);
            }

            return -1;
        }, WeaversParadiseItems.PIGMENT.get());

        event.register((stack, layer) -> {
            if (layer == 0) {
                return -1;
            }

            if (stack.getItem() instanceof ChromaticBloomFruitItem bloomFruit) {
                Minecraft mc = Minecraft.getInstance();
                int ticks = (int)mc.level.getGameTime() + (layer * 2);

                float speed = 0.05F;

                float red = Mth.clamp((float) (Math.sin(ticks * speed) * 0.5 + 0.5), 0, 1);
                float green = Mth.clamp((float) (Math.sin(ticks * speed + 2 * Math.PI / 3) * 0.5 + 0.5), 0, 1);
                float blue = Mth.clamp((float) (Math.sin(ticks * speed + 4 * Math.PI / 3) * 0.5 + 0.5), 0, 1);

                int truered = (int) (red * 255);
                int truegreen = (int) (green * 255);
                int trueblue = (int) (blue * 255);

                return 255 << 24 | truered << 16 | truegreen << 8 | trueblue;
            }

            return -1;
        }, WeaversParadiseItems.CHROMATIC_BLOOM_FRUIT.get());

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
        }, WeaversParadiseItems.CHROMATIC_DUST.get());
    }

    public static int blendColors(int c1, int c2, float ratio) {
        // Clamp ratio between 0 and 1
        ratio = Math.min(1.0f, Math.max(0.0f, ratio));

        int r1 = (c1 >> 16) & 0xFF;
        int g1 = (c1 >> 8) & 0xFF;
        int b1 = c1 & 0xFF;

        int r2 = (c2 >> 16) & 0xFF;
        int g2 = (c2 >> 8) & 0xFF;
        int b2 = c2 & 0xFF;

        int r = (int)(r1 * ratio + r2 * (1 - ratio));
        int g = (int)(g1 * ratio + g2 * (1 - ratio));
        int b = (int)(b1 * ratio + b2 * (1 - ratio));

        return 255 << 24 | (r << 16) | (g << 8) | b;
    }

    public static int getCycledColor(List<Integer> colors, int ticks) {
        if (colors == null || colors.isEmpty()) return 0xFFFFFF;
        if (colors.size() == 1) return colors.get(0);

        int holdTime = 20;
        int fadeTime = 20;
        int segmentTime = holdTime + fadeTime;
        int totalCycle = colors.size() * segmentTime;

        int time = ticks % totalCycle;
        int index = time / segmentTime;
        int local = time % segmentTime;

        int current = colors.get(index);
        int next = colors.get((index + 1) % colors.size());

        float t = 0f;
        if (local >= holdTime) {
            t = (local - holdTime) / (float) fadeTime;
        }

        return lerpColor(current, next, t);
    }

    private static int lerpColor(int c1, int c2, float t) {
        int r1 = (c1 >> 16) & 0xFF;
        int g1 = (c1 >> 8) & 0xFF;
        int b1 = c1 & 0xFF;

        int r2 = (c2 >> 16) & 0xFF;
        int g2 = (c2 >> 8) & 0xFF;
        int b2 = c2 & 0xFF;

        int r = (int)(r1 + (r2 - r1) * t);
        int g = (int)(g1 + (g2 - g1) * t);
        int b = (int)(b1 + (b2 - b1) * t);

        return 255 << 24 | (r << 16) | (g << 8) | b;
    }
}
