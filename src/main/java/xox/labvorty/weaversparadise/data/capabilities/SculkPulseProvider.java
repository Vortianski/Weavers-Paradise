package xox.labvorty.weaversparadise.data.capabilities;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.init.WeaversParadiseCapabilities;

public class SculkPulseProvider implements ICapabilityProvider {
    private final SculkPulseData pulseData = new SculkPulseData();
    private final LazyOptional<SculkPulseData> optional = LazyOptional.of(() -> pulseData);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
        if (capability == WeaversParadiseCapabilities.SCULK_PULSE_DATA) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }
}
