package com.unforgeable.foc.entities.goal;

import com.google.common.collect.Sets;
import com.unforgeable.foc.Foc;
import com.unforgeable.foc.blocks.FocPorridgeBowl;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FocPOI {
    private static final DeferredRegister<PoiType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, Foc.MODID);

    public static final RegistryObject<PoiType> BOWL = POI.register("bowl",
            () -> new PoiType("bowl", Sets.newHashSet(FocPorridgeBowl.ALL_EXCEPT_EMPTY), 1, 1)
    );

    public static void register(IEventBus bus) {
        POI.register(bus);
    }
}
