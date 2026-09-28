package xox.labvorty.weaversparadise.data.capabilities;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.init.WeaversParadiseCapabilities;

public class ChromaticShiftProvider implements ICapabilityProvider {
    private final ChromaticShiftData data = new ChromaticShiftData();
    private final LazyOptional<ChromaticShiftData> optional = LazyOptional.of(() -> data);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
        if (capability == WeaversParadiseCapabilities.CHROMATIC_SHIFT_DATA) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }
}