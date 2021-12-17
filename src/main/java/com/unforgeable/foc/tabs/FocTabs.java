package com.unforgeable.foc.tabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class FocTabs {


    public static final CreativeModeTab FOC_TAB =new CreativeModeTab("foc_tab") {
        @Override
        public ItemStack makeIcon () {
            return new ItemStack(Blocks.DIAMOND_BLOCK);
        }
    }

    ;
}











