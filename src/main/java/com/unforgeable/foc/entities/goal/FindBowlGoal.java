package com.unforgeable.foc.entities.goal;

import com.unforgeable.foc.entities.Nisse;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.Optional;

public class FindBowlGoal extends Goal {
    protected final double speedModifier;
    protected final Nisse nisse;

    public FindBowlGoal(Nisse nisse, double speedModifier) {
        this.nisse = nisse;
        this.speedModifier = speedModifier;
    }

    @Override
    public boolean canUse() {
        return nisse.getEatTime() <= Nisse.FIND_BOWL_TIME;
    }

    @Override
    public void tick() {
        super.tick();
        Optional<BlockPos> p = Nisse.getPorridgePosition((ServerLevel) nisse.level, nisse.blockPosition());
        if (p.isPresent()) {
            BlockPos position = p.get();
            this.nisse.getNavigation().moveTo(position.getX(), position.getY(), position.getZ(), speedModifier);
        }
    }

    @Override
    public void stop() {
        super.stop();
        this.nisse.getNavigation().stop();
    }
}
