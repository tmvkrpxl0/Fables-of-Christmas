package com.unforgeable.foc.entities.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public class StayAroundPosGoal extends RandomStrollGoal {
    private static final int MAX_XZ_DIST = 15;
    private static final int MAX_Y_DIST = 7;
    private final Supplier<Optional<BlockPos>> posGetter;


    public StayAroundPosGoal(PathfinderMob pMob, double pSpeedModifier, boolean pCheckNoActionTime, Supplier<Optional<BlockPos>> posGetter) {
        super(pMob, pSpeedModifier, 10, pCheckNoActionTime);
        this.posGetter = posGetter;
    }

    @Override
    public boolean canUse() {
        Optional<BlockPos> pos = posGetter.get();
        if (pos.isEmpty()) return false;
        SectionPos pointSection = SectionPos.of(pos.get());
        SectionPos posSection = SectionPos.of(this.mob.blockPosition());
        return !pointSection.equals(posSection) && super.canUse();
    }

    @Nullable
    @Override
    protected Vec3 getPosition() {
        Optional<BlockPos> pos = posGetter.get();
        if (pos.isEmpty()) return null;
        BlockPos blockpos = this.mob.blockPosition();
        SectionPos posSection = SectionPos.of(blockpos);
        SectionPos pointSection = SectionPos.of(pos.get());
        return pointSection != posSection ? DefaultRandomPos.getPosTowards(this.mob, MAX_XZ_DIST, MAX_Y_DIST, Vec3.atBottomCenterOf(pointSection.center()), (float) Math.PI / 2F) : null;
    }
}
