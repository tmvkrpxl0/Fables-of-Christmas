package com.unforgeable.foc.tileentities;

import com.unforgeable.foc.Foc;
import com.unforgeable.foc.blocks.FocBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FocBlockEntities {

    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Foc.MODID);



    public static final RegistryObject<BlockEntityType<FocSignBlockEntity>> SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("pohutukawa_sign", () -> BlockEntityType.Builder.of(FocSignBlockEntity::new,
                    FocBlocks.POHUTUKAWA_STANDING_SIGN.get(),
                    FocBlocks.POHUTUKAWA_WALL_SIGN.get()
            ).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
