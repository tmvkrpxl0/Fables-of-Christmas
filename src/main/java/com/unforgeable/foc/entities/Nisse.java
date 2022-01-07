package com.unforgeable.foc.entities;

import com.unforgeable.foc.blocks.FocPorridgeBowl;
import com.unforgeable.foc.entities.goal.FindBowlGoal;
import com.unforgeable.foc.entities.goal.FocPOI;
import com.unforgeable.foc.entities.goal.NisseRandomStrollGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
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
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class Nisse extends AbstractGolem implements NeutralMob {
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int angerTime = 0;
    private UUID angerTarget = null;
    public static final int FIND_BOWL_TIME = 20 * 10;
    private int eatTime = 0;

    public Nisse(EntityType<? extends Nisse> entityType, Level level) {
        super(entityType, level);
        resetEatTime();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.ATTACK_DAMAGE, 6.0D);
    }

    public static Optional<BlockPos> getPorridgePosition(ServerLevel level, BlockPos pos) {
        return level.getPoiManager().findClosest(FocPOI.BOWL.get().getPredicate(), pos, 48, PoiManager.Occupancy.ANY);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            tickDespawn();
            if (this.getHealth() <= 2.0 || this.eatTime <= (FIND_BOWL_TIME)) {
                AABB area = AABB.ofSize(this.position(), 2, 2, 2);
                Optional<BlockPos> pos = BlockPos.betweenClosedStream(area).filter(blockPos -> FocPorridgeBowl.porridgePredicate(this.level, blockPos)).findAny();
                if (pos.isPresent()) {
                    BlockPos bowlPos = pos.get();
                    BlockState bowlState = this.level.getBlockState(bowlPos);
                    int remaining = bowlState.getValue(FocPorridgeBowl.REMAINING_BITES);
                    BlockState newState = bowlState.setValue(FocPorridgeBowl.REMAINING_BITES, remaining - 1);
                    this.level.setBlockAndUpdate(bowlPos, newState);
                    this.setHealth(this.getMaxHealth());
                    resetEatTime();
                }
            }
        }
    }

    private void resetEatTime() {
        int randomInterval = this.getRandom().nextInt() % (60 * 20);
        eatTime = (6 * 60 * 20) + randomInterval;
    }

    private void tickDespawn() {
        if (eatTime > 0) {
            eatTime--;
        } else {
            double x = this.getX();
            double y = this.getY();
            double z = this.getZ();
            ClientboundLevelParticlesPacket packet = new ClientboundLevelParticlesPacket(ParticleTypes.PORTAL, false, x, y, z, 0.5F, 0.5F, 0.5F, 1, 70);
            ((ServerLevel) this.level).players().forEach(player -> player.connection.send(packet));
            this.discard();
        }
    }

    public int getEatTime() {
        return eatTime;
    }

    public void setEatTime(int eatTime) {
        this.eatTime = eatTime;
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

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9, 32F));
        this.goalSelector.addGoal(3, new FindBowlGoal(this, 2.0));
        this.goalSelector.addGoal(4, new NisseRandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (entityToCheck) -> entityToCheck instanceof Enemy));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, true));
    }
}
