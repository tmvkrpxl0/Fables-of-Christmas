package com.unforgeable.foc.entities.goal;

import com.unforgeable.foc.entities.Nisse;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;

public class NisseRandomStrollGoal extends RandomStrollGoal {

    public NisseRandomStrollGoal(Nisse nisse, double pSpeedModifier) {
        super(nisse, pSpeedModifier, 240, false);
    }

    @Nullable
    @Override
    protected Vec3 getPosition() {
        float probability = this.mob.level.random.nextFloat();
        if (probability < 0.3F) {
            return LandRandomPos.getPos(this.mob, 10, 7);
        } else {
            Optional<BlockPos> pos = Nisse.getPorridgePosition((ServerLevel) this.mob.level, this.mob.blockPosition());
            if (pos.isPresent()) {
                return LandRandomPos.getPosTowards(this.mob, 10, 7, Vec3.atCenterOf(pos.get()));
            } else {
                return LandRandomPos.getPos(this.mob, 10, 7);
            }
        }
    }
}
