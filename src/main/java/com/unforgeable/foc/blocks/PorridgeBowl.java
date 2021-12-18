package com.unforgeable.foc.blocks;

import com.unforgeable.foc.entities.FocEntityTypes;
import com.unforgeable.foc.entities.Nisse;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class PorridgeBowl extends Block {
    public static final int MAX_BITES = 8;
    public static final IntegerProperty REMAINING_BITES = IntegerProperty.create("porridge_bite", 0, MAX_BITES);
    public static VoxelShape BOWL_SHAPE = Shapes.or(
            Block.box(4.0, 0.0, 4.0, 12.0, 1.0, 12.0), //Base

            Block.box(3.0, 1.0, 4.0, 4.0, 2.0, 12.0), //Sides
            Block.box(12.0, 1.0, 4.0, 13.0, 2.0, 12.0), //Sides

            Block.box(4.0, 1.0, 3.0, 12.0, 2.0, 4.0),
            Block.box(4.0, 1.0, 12.0, 12.0, 2.0, 13.0)

    );
    public static VoxelShape[] SHAPES = new VoxelShape[]{
            Shapes.or(BOWL_SHAPE, Shapes.empty()),
            Shapes.or(BOWL_SHAPE, Block.box(11.0, 1.0, 4.0, 12.0, 2.0, 12.0)),
            Shapes.or(BOWL_SHAPE, Block.box(10.0, 1.0, 4.0, 12.0, 2.0, 12.0)),
            Shapes.or(BOWL_SHAPE, Block.box(9.0, 1.0, 4.0, 12.0, 2.0, 12.0)),
            Shapes.or(BOWL_SHAPE, Block.box(8.0, 1.0, 4.0, 12.0, 2.0, 12.0)),
            Shapes.or(BOWL_SHAPE, Block.box(7.0, 1.0, 4.0, 12.0, 2.0, 12.0)),
            Shapes.or(BOWL_SHAPE, Block.box(6.0, 1.0, 4.0, 12.0, 2.0, 12.0)),
            Shapes.or(BOWL_SHAPE, Block.box(5.0, 1.0, 4.0, 12.0, 2.0, 12.0)),
            Shapes.or(BOWL_SHAPE, Block.box(4.0, 1.0, 4.0, 12.0, 2.0, 12.0)),
    };

    private final int spawnRange = 3;
    private final int maxNearby = 5;

    public PorridgeBowl(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(REMAINING_BITES, 8));
    }

    public static InteractionResult eat(LevelAccessor level, BlockPos pos, BlockState state, Player player) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            player.getFoodData().eat(2, 0.1F);
            int bites = state.getValue(REMAINING_BITES);
            level.gameEvent(player, GameEvent.EAT, pos);
            if (bites > 0) {
                level.setBlock(pos, state.setValue(REMAINING_BITES, bites - 1), 3);
            }
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (level.isClientSide) {
            if (eat(level, pos, state, player).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (itemStack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return eat(level, pos, state, player);
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos pFacifacingPosgPos) {
        return facing == Direction.DOWN && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, pFacifacingPosgPos);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        return !level.getBlockState(pos.below()).getMaterial().isSolid();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(REMAINING_BITES);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        //resetShape();
        return SHAPES[state.getValue(REMAINING_BITES)];
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return state.getValue(REMAINING_BITES) > 0;
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel serverLevel, @NotNull BlockPos pos, @NotNull Random random) {
        int remaining = state.getValue(REMAINING_BITES);
        if (remaining <= 0) return;
        double x = pos.getX() + (random.nextDouble() - random.nextDouble() * this.spawnRange) + 0.5;
        double y = pos.getY();
        double z = pos.getZ() + (random.nextDouble() - random.nextDouble() * this.spawnRange) + 0.5;
        int nearBy;
        {
            AABB area = new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1).inflate(this.spawnRange);
            nearBy = serverLevel.getEntitiesOfClass(Nisse.class, area).size();
        }
        Nisse nisse = new Nisse(FocEntityTypes.NISSE.get(), serverLevel);
        if (nearBy < maxNearby) {
            nisse.moveTo(x, y, z, serverLevel.random.nextFloat() * 360.0F, 0.0F);
            boolean succeed = serverLevel.tryAddFreshEntityWithPassengers(nisse);//Not sure if it's necessary, but it's used by Spawner
            if (succeed) {
                serverLevel.setBlock(pos, state.setValue(REMAINING_BITES, remaining - 1), 3);
                ClientboundLevelParticlesPacket packet = new ClientboundLevelParticlesPacket(ParticleTypes.PORTAL, false, x, y, z, 0.5F, 0.5F, 0.5F, 1, 70);
                serverLevel.players().forEach(player -> player.connection.send(packet));
            }
        }
    }
}
