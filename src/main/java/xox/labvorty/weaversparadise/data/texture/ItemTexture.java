package xox.labvorty.weaversparadise.data.texture;

import net.minecraft.resources.ResourceLocation;

public class ItemTexture {
    private final String clothing_type;
    private final String stencil;
    private final String material;
    private final ResourceLocation textureOne;
    private final ResourceLocation textureTwo;
    private final boolean renderType;

    public ItemTexture(
            String clothing_type,
            String stencil,
            String material,
            ResourceLocation textureOne,
            ResourceLocation textureTwo,
            boolean renderType
    ) {
        this.clothing_type = clothing_type;
        this.stencil = stencil;
        this.material = material;
        this.textureOne = textureOne;
        this.textureTwo = textureTwo;
        this.renderType = renderType;
    }

    public String getClothingType() {
        return clothing_type;
    }

    public String getStencil() {
        return stencil;
    }

    public String getMaterial() {
        return material;
    }

    public ResourceLocation getTextureOne() {
        return textureOne;
    }

    public ResourceLocation getTextureTwo() {
        return textureTwo;
    }

    public boolean getRenderType() {
        return renderType;
    }
}
