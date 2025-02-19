package com.thefrozenchiken.cookingmod.item.custom;

import com.thefrozenchiken.cookingmod.item.ModItems;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.Nullable;

public class KnifeItem extends Item {

    public KnifeItem(ToolMaterial pMaterial, float pAttackDamage, float pAttackSpeed, Item.Properties pProperties) {
        super(pMaterial.applySwordProperties(pProperties, pAttackDamage, pAttackSpeed));
    }

    @Override
    public ItemStack getCraftingRemainder(ItemStack itemStack) {
        ItemStack copyStack = itemStack.copy();
        return copyStack;
    }
}