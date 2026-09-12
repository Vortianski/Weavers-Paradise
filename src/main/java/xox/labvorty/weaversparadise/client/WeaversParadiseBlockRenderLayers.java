package xox.labvorty.weaversparadise.client;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import xox.labvorty.weaversparadise.init.WeaversParadiseBlocks;

/**
 * В оригинале (NeoForge) render-type задавался ключом "render_type" прямо в JSON моделей —
 * это NeoForge-расширение, ваниль 1.21.1 его игнорирует (прозрачные пиксели рендерятся
 * чёрным в solid-слое). На Fabric регистрируем слои кодом. "solid"-модели не трогаем —
 * solid и так дефолт.
 */
public class WeaversParadiseBlockRenderLayers {

    public static void init() {
        BlockRenderLayerMap.INSTANCE.putBlock(WeaversParadiseBlocks.COTTON_BUSH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WeaversParadiseBlocks.WILD_COTTON_PLANT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WeaversParadiseBlocks.CHROMATIC_BLOOM, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WeaversParadiseBlocks.SPINNING_JENNY, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WeaversParadiseBlocks.CLOTHCRAFTING_STATION, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WeaversParadiseBlocks.STARBLOOM, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WeaversParadiseBlocks.WITCHROOT, RenderType.cutout());
    }
}
