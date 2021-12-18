package com.unforgeable.foc.items;

import com.unforgeable.foc.Foc;
import com.unforgeable.foc.blocks.FocBlocks;
import com.unforgeable.foc.tabs.FocTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FocItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Foc.MODID);

    //TEST ITEM//
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item",
            () -> new Item(new Item.Properties().tab(FocTabs.FOC_TAB)));

    public static final RegistryObject<Item> PORRIDGE_BOWL = ITEMS.register("porridge_bowl",
            () -> new BlockItem(FocBlocks.PORRIDGE_BOWL.get(), new Item.Properties().stacksTo(1).tab(FocTabs.FOC_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
