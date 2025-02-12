package com.thefrozenchiken.cookingmod.datagen;

import com.thefrozenchiken.cookingmod.item.ModItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class ModItemModelGenerators extends ItemModelGenerators {

    public ModItemModelGenerators(ItemModelOutput pItemModelOutput, BiConsumer<ResourceLocation, ModelInstance> pModelOutput) {
        super(pItemModelOutput, pModelOutput);
    }

    @Override
    public void run() {
        generateFlatItem(ModItems.KNIFE.get(), ModelTemplates.FLAT_ITEM);

        if (this.itemModelOutput instanceof ModModelProvider.ModItemInfoCollector collector)
            collector.generateDefaultBlockModels();
    }

}
