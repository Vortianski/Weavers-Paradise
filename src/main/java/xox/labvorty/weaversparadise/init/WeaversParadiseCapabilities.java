package xox.labvorty.weaversparadise.init;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import xox.labvorty.weaversparadise.data.capabilities.ChromaticShiftData;
import xox.labvorty.weaversparadise.data.capabilities.SculkPulseData;

public class WeaversParadiseCapabilities {
    public static final Capability<ChromaticShiftData> CHROMATIC_SHIFT_DATA = CapabilityManager.get(
            new CapabilityToken<ChromaticShiftData>() {
                @Override
                public String toString() {
                    return super.toString();
                }
            }
    );
    public static final Capability<SculkPulseData> SCULK_PULSE_DATA = CapabilityManager.get(
            new CapabilityToken<SculkPulseData>() {
                @Override
                public String toString() {
                    return super.toString();
                }
            }
    );
}

