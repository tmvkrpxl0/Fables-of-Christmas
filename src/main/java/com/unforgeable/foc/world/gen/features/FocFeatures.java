

package com.unforgeable.foc.world.gen.features;

import com.unforgeable.foc.blocks.FocBlocks;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.OptionalInt;

public class FocFeatures {

    public static final ConfiguredFeature<TreeConfiguration, ?>  POHUTUKAWA_CONFIG = FeatureUtils.register("pohutukawa_config",
            Feature.TREE.configured(createTestTree().build()));





    private static TreeConfiguration.TreeConfigurationBuilder createTestTree() {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(FocBlocks.POHUTUKAWA_LOG.get()),
                new ForkingTrunkPlacer(2, 4, 0),

              new  WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(FocBlocks.POHUTUKAWA_LEAVES.get().defaultBlockState(),2)
                        .add(FocBlocks.POHUTUKAWA_FLOWERING_LEAVES.get().defaultBlockState(), 1)),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }

}
