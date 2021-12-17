package com.unforgeable.foc.entities;

import com.unforgeable.foc.Foc;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FocEntityTypes {
    private static final DeferredRegister<EntityType<?>> ENTITY
            = DeferredRegister.create(ForgeRegistries.ENTITIES, Foc.MODID);

    public static final RegistryObject<EntityType<Nisse>> NISSE = ENTITY.register("nisse",
            () -> EntityType.Builder.of(Nisse::new, MobCategory.CREATURE)
                    .sized(0.45F, 0.9F)
                    .build(new ResourceLocation(Foc.MODID, "nisse").toString())
    );

    public static void register(IEventBus bus) {
        ENTITY.register(bus);
    }
}
