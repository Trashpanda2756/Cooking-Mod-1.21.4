package com.thefrozenchiken.cookingmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class ModRecipeDataProvider extends ModRecipeProvider.Runner {

    public ModRecipeDataProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registeries) {
        super(packOutput, registeries);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new ModRecipeProvider(registries, output);
    }
}
