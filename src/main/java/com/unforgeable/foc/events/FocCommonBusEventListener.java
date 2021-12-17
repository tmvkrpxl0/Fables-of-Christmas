package com.unforgeable.foc.events;

import com.unforgeable.foc.Foc;
import com.unforgeable.foc.entities.FocEntityTypes;
import com.unforgeable.foc.entities.Nisse;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Foc.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FocCommonBusEventListener {

    @SubscribeEvent
    public static void onRegisterAttributes(EntityAttributeCreationEvent event) {
        event.put(FocEntityTypes.NISSE.get(), Nisse.createAttributes().build());
    }
}
