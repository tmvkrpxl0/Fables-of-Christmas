package com.unforgeable.foc.world.gen;

import com.unforgeable.foc.world.gen.features.FocFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class FocPlacements {

    public static final PlacedFeature POHUTUKAWA_PLACEMENT = PlacementUtils.register("pohutukawa_placement",
            FocFeatures. POHUTUKAWA_CONFIG.placed(PlacementUtils.countExtra(1, 0.05F, 1),
                    InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(),
                            BlockPos.ZERO)), BiomeFilter.biome()));


    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name,
                                                                                       ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }

}