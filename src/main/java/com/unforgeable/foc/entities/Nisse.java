package com.unforgeable.foc.entities;

import com.unforgeable.foc.blocks.FocBlocks;
import com.unforgeable.foc.entities.goal.StayAroundPosGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class Nisse extends AbstractGolem implements NeutralMob {
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    protected static final EntityDataAccessor<Optional<BlockPos>> POINT = SynchedEntityData.defineId(Nisse.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);
    private int angerTime = 0;
    private UUID angerTarget = null;

    public Nisse(EntityType<? extends Nisse> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.ATTACK_DAMAGE, 6.0D);
    }

    @Override
    public void tick() {
        super.tick();
        Optional<BlockPos> optional = getPoint();
        if (optional.isPresent()) {
            BlockPos point = optional.get();
            if (this.level.getBlockState(point).getBlock() != FocBlocks.PORRIDGE_BOWL.get()) {
                setPoint(null);
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(POINT, Optional.empty());
    }

    public Optional<BlockPos> getPoint() {
        return this.entityData.get(POINT);
    }

    public void setPoint(@Nullable BlockPos point) {
        this.entityData.set(POINT, Optional.ofNullable(point));
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9, 32F));
        this.goalSelector.addGoal(2, new StayAroundPosGoal(this, 0.6, false, this::getPoint));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (entityToCheck) -> entityToCheck instanceof Enemy));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.angerTime;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerLevel) this.level, true);
        }
    }

    @Override
    public void setRemainingPersistentAngerTime(int time) {
        this.angerTime = time;
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.angerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID target) {
        this.angerTarget = target;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }
}
