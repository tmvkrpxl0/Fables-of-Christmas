package com.unforgeable.foc.tabs;

import com.unforgeable.foc.blocks.FocBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class FocTabs {


    public static final CreativeModeTab FOC_TAB =new CreativeModeTab("foc_tab") {
        @Override
        public ItemStack makeIcon () {
            return new ItemStack(FocBlocks.POHUTUKAWA_DOOR.get());
        }
    }

    ;
}











