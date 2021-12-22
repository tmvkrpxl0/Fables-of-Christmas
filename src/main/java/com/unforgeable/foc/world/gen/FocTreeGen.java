package com.unforgeable.foc.world.gen;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Set;

public class FocTreeGen {


    public static void generateTrees(final BiomeLoadingEvent event) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);


        //HOT  OR WARM BIOMES?//

        if (event.getCategory() == Biome.BiomeCategory.SAVANNA || event.getCategory() == Biome.BiomeCategory.PLAINS) {
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FocPlacements.POHUTUKAWA_PLACEMENT);

        }

    }
}