package com.unforgeable.foc.blocks;

import com.unforgeable.foc.Foc;
import com.unforgeable.foc.items.FocItems;
import com.unforgeable.foc.tabs.FocTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class FocBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Foc.MODID);


    //FIRST TESTING BLOCK//
    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2.0F).sound(SoundType.STONE)), FocTabs.FOC_TAB);

    //Registering Block Item of porridge bowl is done in FocItems manually
    //But it may be able to be moved to here
    public static final RegistryObject<Block> PORRIDGE_BOWL = BLOCKS.register("porridge_bowl",
            () -> new PorridgeBowl(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOD)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        FocItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}