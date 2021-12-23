package com.unforgeable.foc.client.renderers;

import com.unforgeable.foc.blocks.FocBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class RenderLayers {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        ItemBlockRenderTypes.setRenderLayer(FocBlocks.POHUTUKAWA_FLOWERING_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(FocBlocks.POHUTUKAWA_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(FocBlocks.POHUTUKAWA_TRAPDOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(FocBlocks.POHUTUKAWA_DOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(FocBlocks.POHUTUKAWA_SAPLING.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(FocBlocks.PORRIDGE_BOWL.get(), RenderType.cutoutMipped());
    }

    public static void safeRunClient() {
        final IEventBus eventHandler = FMLJavaModLoadingContext.get().getModEventBus();
        eventHandler.register(RenderLayers.class);
    }
}
