package com.unforgeable.foc.entities;

import com.unforgeable.foc.Foc;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FocEntityTypes {
    private static final DeferredRegister<EntityType<? extends Entity>> ENTITY
            = DeferredRegister.create(ForgeRegistries.ENTITIES, Foc.MODID);

    public static void register(IEventBus bus) {
        ENTITY.register(bus);
    }
}
