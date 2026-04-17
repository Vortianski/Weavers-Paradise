package xox.labvorty.weaversparadise.mixins.client;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import xox.labvorty.weaversparadise.mixin_helpers.PlayerModelInterface;

@Mixin(PlayerModel.class)
public class PlayerModelMixin implements PlayerModelInterface {
    @Shadow
    private final ModelPart cloak;

    public PlayerModelMixin(ModelPart cloak) {
        this.cloak = cloak;
    }

    @Unique
    public ModelPart getCloak() {
        return cloak;
    }
}
