package com.thefrozenchiken.cookingmod.datagen;


import com.thefrozenchiken.cookingmod.CookingMod;
import com.thefrozenchiken.cookingmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.*;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.crafting.*;


import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ModRecipeProvider extends RecipeProvider implements DataProvider {

    protected ModRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
        super(pRegistries, pOutput);
    }

    @Override
    protected void buildRecipes() {
        this.output.includeRootAdvancement();
        this.generateForEnabledBlockFamilies(FeatureFlagSet.of(FeatureFlags.VANILLA));

//        Knife Recipe
        this.shaped(RecipeCategory.TOOLS, ModItems.KNIFE.get())
                .pattern("I")
                .pattern("S")
                .define('S', Items.STICK)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(ModItems.KNIFE.get()), has(Items.IRON_INGOT))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, new ItemStack(Items.CHICKEN, 5))
                .requires(ModItems.KNIFE.get())
                .requires(Items.CHICKEN)
                .unlockedBy(getHasName(Items.CHICKEN), has(Items.COOKED_CHICKEN))
                .save(this.output);
    }

    @Override
    protected void generateForEnabledBlockFamilies(FeatureFlagSet pEnabledFeatures) {
        ModBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach(p_358446_ -> this.generateRecipes(p_358446_, pEnabledFeatures));
    }

    protected void oreSmelting(List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        this.oreCooking(RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected void oreBlasting(List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        this.oreCooking(RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");

    }

    private <T extends AbstractCookingRecipe> void oreCooking(
            RecipeSerializer<T> pSerializer,
            AbstractCookingRecipe.Factory<T> pRecipeFactory,
            List<ItemLike> pIngredients,
            RecipeCategory pCategory,
            ItemLike pResult,
            float pExperience,
            int pCookingTime,
            String pGroup,
            String pSuffix
    ) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pSerializer, pRecipeFactory)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), this.has(itemlike))
                    .save(this.output, CookingMod.MOD_ID + ":" + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }
    }

    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput p_365932_, CompletableFuture<HolderLookup.Provider> p_363203_) {
            super(p_365932_, p_363203_);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
            return new ModRecipeProvider(pRegistries, pOutput);
        }

        @Override
        public String getName() {
            return "Cooking Mod Recipes";
        }
    }
}
