package xox.labvorty.weaversparadise.renderers.helpers;

import net.minecraft.nbt.CompoundTag;
import org.joml.Vector3f;

public record SingleSidedClothingRenderingData(boolean flag, int pCO, int sCO, int pCT, int sCT, String dTO, String dTT, String sT, int lVO, int lVT, String mat, boolean glint, Vector3f glintColor, CompoundTag additionalData) {
}
