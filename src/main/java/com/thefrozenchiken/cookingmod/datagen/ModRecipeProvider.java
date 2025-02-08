package com.thefrozenchiken.cookingmod.datagen;

import com.google.gson.JsonElement;
import com.thefrozenchiken.cookingmod.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.Nullable;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    protected ModRecipeProvider(HolderLookup.Provider pRegistries) {
        super(pRegistries);
    }

    @Override
    protected void buildRecipes() {

        this.shaped(RecipeCategory.TOOLS, ModItems.KNIFE.get())
                .pattern(" I ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I',Items.IRON_INGOT)
                .unlockedBy(getHasName(ModItems.KNIFE.get()), has(Items.IRON_INGOT)).save(this.output);


    }

}
