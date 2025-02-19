package com.thefrozenchiken.cookingmod.item;

import com.thefrozenchiken.cookingmod.CookingMod;
import com.thefrozenchiken.cookingmod.item.custom.KnifeItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CookingMod.MOD_ID);


    public static final RegistryObject<KnifeItem> KNIFE = ITEMS.register("knife",
            () -> new KnifeItem(ToolMaterial.IRON, 2.0F, -2.0F,
                    new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("cookingmod:knife")))));


    public static void register(IEventBus eventbus) {
        ITEMS.register(eventbus);
    }

}
