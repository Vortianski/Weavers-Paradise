package xox.labvorty.weaversparadise.compat.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiExclusionArea;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.Bounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.Ingredient;
import xox.labvorty.weaversparadise.gui.screen.StringScreen;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@EmiEntrypoint
public class EmiCompat implements EmiPlugin {
    public static final ResourceLocation CLOTHCRAFTING_SPRITESHEET = ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/gui/emi_simplified_textures.png");
    public static final EmiStack CLOTHCRAFTING_WORKSTATION = EmiStack.of(WeaversParadiseItems.CLOTHCRAFTING_STATION);
    public static final EmiRecipeCategory CLOTHCRAFTING_RECIPE_CATEGORY = new EmiRecipeCategory(
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "clothcrafting"),
            CLOTHCRAFTING_WORKSTATION,
            new EmiTexture(CLOTHCRAFTING_SPRITESHEET, 0, 0, 16, 16)
    );

    public static final ResourceLocation SPINNINGJENNY_SPRITESHEET = ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/gui/emi_simplified_textures.png");
    public static final EmiStack SPINNINGJENNY_WORKSTATION = EmiStack.of(WeaversParadiseItems.SPINNING_JENNY);
    public static final EmiRecipeCategory SPINNINGJENNY_RECIPE_CATEGORY = new EmiRecipeCategory(
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "spinning_jenny"),
            SPINNINGJENNY_WORKSTATION,
            new EmiTexture(SPINNINGJENNY_SPRITESHEET, 0, 0, 16, 16)
    );

    public static final ResourceLocation DYEMAKING_SPRITESHEET = ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/gui/emi_simplified_textures.png");
    public static final EmiStack DYEMAKING_WORKSTATION = EmiStack.of(WeaversParadiseItems.DYEMAKING_BLOCK);
    public static final EmiRecipeCategory DYEMAKING_RECIPE_CATEGORY = new EmiRecipeCategory(
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "dyemaking"),
            DYEMAKING_WORKSTATION,
            new EmiTexture(DYEMAKING_SPRITESHEET, 0, 0, 16, 16)
    );

    @Override
    public void register(EmiRegistry registry) {
        registry.addExclusionArea(StringScreen.class, (screen, consumer) -> {
            int sx = screen.getGuiLeft() + 184 - 8;
            int sy = screen.getGuiTop() + 39 - 16;
            //int ex = screen.getGuiLeft() + 184 + 56 - 8;
            //int ey = screen.getGuiTop() + 39 + 105 - 16;

            consumer.accept(new Bounds(
                    sx,
                    sy,
                    56,
                    105
            ));
        });

        registry.addCategory(CLOTHCRAFTING_RECIPE_CATEGORY);
        registry.addCategory(SPINNINGJENNY_RECIPE_CATEGORY);
        registry.addCategory(DYEMAKING_RECIPE_CATEGORY);

        registry.addWorkstation(CLOTHCRAFTING_RECIPE_CATEGORY, CLOTHCRAFTING_WORKSTATION);
        registry.addWorkstation(SPINNINGJENNY_RECIPE_CATEGORY, SPINNINGJENNY_WORKSTATION);
        registry.addWorkstation(DYEMAKING_RECIPE_CATEGORY, DYEMAKING_WORKSTATION);

        for (int i = 0; i <= 10; i++) {
            final int quality = i;

            List<EmiStack> emiStacks1 = new ArrayList<>();
            ItemStack stack1 = new ItemStack(WeaversParadiseItems.COTTON_CLOTH.get());
            CustomData.update(DataComponents.CUSTOM_DATA, stack1, tag -> tag.putInt("quality", quality));
            emiStacks1.add(EmiStack.of(stack1, 1));

            List<EmiStack> emiStacks2 = new ArrayList<>();
            ItemStack stack2 = new ItemStack(WeaversParadiseItems.SILK_CLOTH.get());
            CustomData.update(DataComponents.CUSTOM_DATA, stack2, tag -> tag.putInt("quality", quality));
            emiStacks2.add(EmiStack.of(stack2, 1));

            List<EmiStack> emiStacks3 = new ArrayList<>();
            ItemStack stack3 = new ItemStack(WeaversParadiseItems.WOOL_CLOTH.get());
            CustomData.update(DataComponents.CUSTOM_DATA, stack3, tag -> tag.putInt("quality", quality));
            emiStacks3.add(EmiStack.of(stack3, 1));

            registry.addDeferredRecipes((deferred) -> {
                deferred.accept(new ClothcraftingEmiRecipe(
                        ResourceLocation.fromNamespaceAndPath("weaversparadise", ("/clothcrafting_cotton_cloth" + "_" + quality)),
                        List.of(EmiIngredient.of(Ingredient.of(WeaversParadiseItems.COTTON_SPOOL.get())).setAmount(6)),
                        emiStacks1
                ));
            });

            registry.addDeferredRecipes((deferred) -> {
                deferred.accept(new ClothcraftingEmiRecipe(
                        ResourceLocation.fromNamespaceAndPath("weaversparadise", ("/clothcrafting_silk_cloth" + "_" + quality)),
                        List.of(EmiIngredient.of(Ingredient.of(WeaversParadiseItems.SILK_SPOOL.get())).setAmount(6)),
                        emiStacks2
                ));
            });

            registry.addDeferredRecipes((deferred) -> {
                deferred.accept(new ClothcraftingEmiRecipe(
                        ResourceLocation.fromNamespaceAndPath("weaversparadise", ("/clothcrafting_wool_cloth" + "_" + quality)),
                        List.of(EmiIngredient.of(Ingredient.of(WeaversParadiseItems.WOOL_SPOOL.get())).setAmount(6)),
                        emiStacks3
                ));
            });
        }

        registry.addDeferredRecipes((deferred) -> {
            deferred.accept(new SpinningJennyEmiRecipe(
                    ResourceLocation.fromNamespaceAndPath("weaversparadise", "/spinning_jenny_cotton_spool"),
                    List.of(
                            EmiIngredient.of(Ingredient.of(WeaversParadiseItems.EMPTY_SPOOL.get())).setAmount(1),
                            EmiIngredient.of(Ingredient.of(WeaversParadiseItems.RAW_COTTON.get())).setAmount(4)
                    ),
                    List.of(
                            EmiStack.of(new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()), 1)
                    )
            ));
        });
        registry.addDeferredRecipes((deferred) -> {
            deferred.accept(new SpinningJennyEmiRecipe(
                    ResourceLocation.fromNamespaceAndPath("weaversparadise", "/spinning_jenny_silk_spool"),
                    List.of(
                            EmiIngredient.of(Ingredient.of(WeaversParadiseItems.EMPTY_SPOOL.get())).setAmount(1),
                            EmiIngredient.of(Ingredient.of(Items.STRING)).setAmount(5)
                    ),
                    List.of(
                            EmiStack.of(new ItemStack(WeaversParadiseItems.SILK_SPOOL.get()), 1)
                    )
            ));
        });
        registry.addDeferredRecipes((deferred) -> {
            deferred.accept(new SpinningJennyEmiRecipe(
                    ResourceLocation.fromNamespaceAndPath("weaversparadise", "/spinning_jenny_wool_spool"),
                    List.of(
                            EmiIngredient.of(Ingredient.of(WeaversParadiseItems.EMPTY_SPOOL.get())).setAmount(1),
                            EmiIngredient.of(Ingredient.of(Items.WHITE_WOOL)).setAmount(1)
                    ),
                    List.of(
                            EmiStack.of(new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()), 1)
                    )
            ));
        });

        List<String> dyeTypes = List.of(
                "agender",
                "aroace",
                "aromantic",
                "asexual",
                "bisexual",
                "demiboy",
                "demigender",
                "demigirl",
                "gay",
                "genderfluid",
                "genderqueer",
                "intersex",
                "lesbian",
                "nonbinary",
                "pansexual",
                "pride",
                "trans",
                "sculk",
                "glowstone",
                "rainbow",
                "biome",
                "ender",
                "speed",
                "height_bedrock",
                "height_sea",
                "redstone",
                "lamp"
        );

        List<ItemStack> dyeCores = new ArrayList<>();
        List<ItemStack> bottledDyes = new ArrayList<>();

        ItemStack baseDyeCore = new ItemStack(WeaversParadiseItems.DYE_CORE.get());
        ItemStack baseBottledDye = new ItemStack(WeaversParadiseItems.BOTTLED_DYE.get());

        for (String entry : dyeTypes) {
            ItemStack dyeCore = baseDyeCore.copy();
            CustomData.update(DataComponents.CUSTOM_DATA, dyeCore, (tag) -> {
                tag.putString("dyeType", entry);
            });
            dyeCores.add(dyeCore);

            ItemStack bottledDye = baseBottledDye.copy();
            CustomData.update(DataComponents.CUSTOM_DATA, bottledDye, (tag) -> {
                tag.putString("dyeType", entry);
            });
            bottledDyes.add(bottledDye);
        }

        for (int i = 0; i < dyeCores.size(); i++) {
            final int j = i;
            registry.addDeferredRecipes((deferred) -> {
                deferred.accept(new DyemakingEmiRecipe(
                        ResourceLocation.fromNamespaceAndPath("weaversparadise", "/dyemaking_" + dyeTypes.get(j)),
                        List.of(
                                EmiIngredient.of(Ingredient.of(dyeCores.get(j))),
                                EmiIngredient.of(Ingredient.of(Items.POTION)),
                                EmiIngredient.of(Ingredient.of(Items.GLASS_BOTTLE)),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes"))))

                        ),
                        List.of(
                                EmiIngredient.of(Ingredient.of(bottledDyes.get(j)))
                        )
                ));
            });
        }
    }
}
