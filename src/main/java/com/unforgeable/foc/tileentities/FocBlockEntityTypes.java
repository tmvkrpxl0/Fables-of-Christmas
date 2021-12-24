package com.unforgeable.foc.tileentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FocBlockEntityTypes extends SignBlockEntity {


    public FocBlockEntityTypes(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return FocBlockEntities.SIGN_BLOCK_ENTITIES.get();
    }
}
