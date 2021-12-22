package com.unforgeable.foc.events;

import com.unforgeable.foc.Foc;
import com.unforgeable.foc.world.gen.FocTreeGen;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Foc.MODID)
public class FocWorldGenEvents {
    @SubscribeEvent
    public static void FocWorldGenEvents(final BiomeLoadingEvent event) {

        FocTreeGen.generateTrees(event);
    }
}