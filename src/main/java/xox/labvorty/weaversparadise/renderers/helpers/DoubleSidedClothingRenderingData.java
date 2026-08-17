package xox.labvorty.weaversparadise.renderers.helpers;

import net.minecraft.nbt.CompoundTag;
import org.joml.Vector3f;

public record DoubleSidedClothingRenderingData(boolean flag, int pCLO, int sCLO, int pCRO, int sCRO, int pCLT, int sCLT, int pCRT, int sCRT, String dTLO, String dTRO, String dTLT, String dTRT, String sTL, String sTR, int lVLO, int lVLT, int lVRO, int lVRT, String mat, boolean glint, Vector3f glintColor, CompoundTag additionalData) {
}
