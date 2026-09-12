package xox.labvorty.weaversparadise.items.clothing.defined;

import net.minecraft.sounds.SoundEvent;

import java.util.List;

public interface ChokerTrinketInterface {
    default List<SoundEvent> getSounds() {
        return List.of();
    };
}
