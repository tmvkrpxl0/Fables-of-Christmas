package com.unforgeable.foc.world.gen;

import com.unforgeable.foc.world.gen.features.FocFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

public class PohutukawaTreeGrower extends AbstractTreeGrower {
    /**
     * @return a {@link net.minecraft.world.level.levelgen.feature.ConfiguredFeature} of this tree
     */
    protected ConfiguredFeature<?, ?> getConfiguredFeature(Random pRandom, boolean pLargeHive) {
        return FocFeatures.POHUTUKAWA_CONFIG;
    }
}
