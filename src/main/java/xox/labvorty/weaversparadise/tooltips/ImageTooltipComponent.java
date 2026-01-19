package xox.labvorty.weaversparadise.tooltips;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.resources.ResourceLocation;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ImageTooltipComponent implements TooltipComponent {
    private final List<ResourceLocation> textures;

    private final ResourceLocation textureOne;
    private final String textOne;
    private final String typeOne;
    private final int lightValueOne;
    private final int primaryLeftColorOne;
    private final int secondaryLeftColorOne;
    private final ResourceLocation textureTwo;
    private final String textTwo;
    private final String typeTwo;
    private final int lightValueTwo;
    private final int primaryLeftColorTwo;
    private final int secondaryLeftColorTwo;
    private final ResourceLocation textureThree;
    private final String textThree;
    private final String typeThree;
    private final int lightValueThree;
    private final int primaryRightColorOne;
    private final int secondaryRightColorOne;
    private final ResourceLocation textureFour;
    private final String textFour;
    private final String typeFour;
    private final int lightValueFour;
    private final int primaryRightColorTwo;
    private final int secondaryRightColorTwo;

    public ImageTooltipComponent(
            List<ResourceLocation> textures,
            ResourceLocation textureOne,
            String textOne,
            String typeOne,
            int lightValueOne,
            int primaryLeftColorOne,
            int secondaryLeftColorOne,
            ResourceLocation textureTwo,
            String textTwo,
            String typeTwo,
            int lightValueTwo,
            int primaryLeftColorTwo,
            int secondaryLeftColorTwo,
            ResourceLocation textureThree,
            String textThree,
            String typeThree,
            int lightValueThree,
            int primaryRightColorOne,
            int secondaryRightColorOne,
            ResourceLocation textureFour,
            String textFour,
            String typeFour,
            int lightValueFour,
            int primaryRightColorTwo,
            int secondaryRightColorTwo
    ) {
        this.textures = textures;
        this.textureOne = textureOne;
        this.textOne = textOne;
        this.typeOne = typeOne;
        this.lightValueOne = lightValueOne;
        this.primaryLeftColorOne = primaryLeftColorOne;
        this.secondaryLeftColorOne = secondaryLeftColorOne;
        this.textureTwo = textureTwo;
        this.textTwo = textTwo;
        this.typeTwo = typeTwo;
        this.lightValueTwo = lightValueTwo;
        this.primaryLeftColorTwo = primaryLeftColorTwo;
        this.secondaryLeftColorTwo = secondaryLeftColorTwo;
        this.textureThree = textureThree;
        this.textThree = textThree;
        this.typeThree = typeThree;
        this.lightValueThree = lightValueThree;
        this.primaryRightColorOne = primaryRightColorOne;
        this.secondaryRightColorOne = secondaryRightColorOne;
        this.textureFour = textureFour;
        this.textFour = textFour;
        this.typeFour = typeFour;
        this.lightValueFour = lightValueFour;
        this.primaryRightColorTwo = primaryRightColorTwo;
        this.secondaryRightColorTwo = secondaryRightColorTwo;
    }

    public List<ResourceLocation> getTextures() {
        return textures;
    }

    public ResourceLocation getTexture(String side, int part) {
        if (side.equals("right")) {
            if (part == 1) {
                return this.textureThree;
            } else if (part == 2) {
                return this.textureFour;
            }
        } else if (side.equals("left")) {
            if (part == 1) {
                return this.textureOne;
            } else if (part == 2) {
                return this.textureTwo;
            }
        }

        return this.textureOne;
    }

    public String getText(String side, int part) {
        if (side.equals("right")) {
            if (part == 1) {
                return this.textThree;
            } else if (part == 2) {
                return this.textFour;
            }
        } else if (side.equals("left")) {
            if (part == 1) {
                return this.textOne;
            } else if (part == 2) {
                return this.textTwo;
            }
        }

        return this.textOne;
    }

    public String getType(String side, int part) {
        if (side.equals("right")) {
            if (part == 1) {
                return this.typeThree;
            } else if (part == 2) {
                return this.typeFour;
            }
        } else if (side.equals("left")) {
            if (part == 1) {
                return this.typeOne;
            } else if (part == 2) {
                return this.typeTwo;
            }
        }

        return this.typeOne;
    }

    public int getLightValue(String side, int part) {
        if (side.equals("right")) {
            if (part == 1) {
                return this.lightValueThree;
            } else if (part == 2) {
                return this.lightValueFour;
            }
        } else if (side.equals("left")) {
            if (part == 1) {
                return this.lightValueOne;
            } else if (part == 2) {
                return this.lightValueTwo;
            }
        }

        return this.lightValueOne;
    }

    public int getPrimaryColor(String side, int part) {
        if (side.equals("right")) {
            if (part == 1) {
                return this.primaryRightColorOne;
            } else if (part == 2) {
                return this.primaryRightColorTwo;
            }
        } else if (side.equals("left")) {
            if (part == 1) {
                return this.primaryLeftColorOne;
            } else if (part == 2) {
                return this.primaryLeftColorTwo;
            }
        }

        return this.primaryLeftColorOne;
    }

    public int getSecondaryColor(String side, int part) {
        if (side.equals("right")) {
            if (part == 1) {
                return this.secondaryRightColorOne;
            } else if (part == 2) {
                return this.secondaryRightColorTwo;
            }
        } else if (side.equals("left")) {
            if (part == 1) {
                return this.secondaryLeftColorOne;
            } else if (part == 2) {
                return this.secondaryLeftColorTwo;
            }
        }

        return this.secondaryLeftColorOne;
    }



    public int getWidth() {
        return 8;
    }

    public int getHeight() {
        return 36;
    }
}