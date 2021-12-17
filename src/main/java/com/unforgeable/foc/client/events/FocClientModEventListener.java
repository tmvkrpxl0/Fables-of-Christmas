package com.unforgeable.foc.client.events;

import com.unforgeable.foc.Foc;
import com.unforgeable.foc.client.models.NisseModel;
import com.unforgeable.foc.client.renderers.NisseRenderer;
import com.unforgeable.foc.entities.FocEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Foc.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class FocClientModEventListener {

    @SubscribeEvent
    public static void onDefineLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NisseModel.LAYER_LOCATION, NisseModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(FocEntityTypes.NISSE.get(), NisseRenderer::new);
    }
}
