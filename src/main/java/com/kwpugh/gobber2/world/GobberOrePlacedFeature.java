package com.kwpugh.gobber2.world;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.Gobber2Config;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;
import java.util.function.Predicate;

public class GobberOrePlacedFeature
{
    public static final Gobber2Config.Ores CONFIG = Gobber2.CONFIG.ORES;

    public static final RegistryEntry<PlacedFeature> ORE_LUCKY_BLOCK_OVERWORLD;
    public static final RegistryEntry<PlacedFeature> ORE_LUCKY_BLOCK_OVERWORLD_DEEPSLATE;
    public static final RegistryEntry<PlacedFeature> ORE_GOBBER_OVERWORLD;
    public static final RegistryEntry<PlacedFeature> ORE_GOBBER_OVERWORLD_DEEPSLATE;
    public static final RegistryEntry<PlacedFeature> ORE_LUCKY_BLOCK_NETHER;
    public static final RegistryEntry<PlacedFeature> ORE_GOBBER_NETHER;
    public static final RegistryEntry<PlacedFeature> ORE_LUCKY_BLOCK_END;
    public static final RegistryEntry<PlacedFeature> ORE_GOBBER_THEEND;


    static
    {
        ORE_LUCKY_BLOCK_OVERWORLD = PlacedFeatures.register("ore_lucky_block_overworld", GobberOreConfiguredFeature.ORE_LUCKY_BLOCK_OVERWORLD, modifiersWithCount(CONFIG.luckyPerChunk, HeightRangePlacementModifier.uniform(YOffset.fixed(0),YOffset.fixed(CONFIG.luckyMaxLevel)) ));
        ORE_LUCKY_BLOCK_OVERWORLD_DEEPSLATE = PlacedFeatures.register("ore_lucky_block_overworld_deepslate", GobberOreConfiguredFeature.ORE_LUCKY_BLOCK_OVERWORLD_DEEPSLATE, modifiersWithCount(CONFIG.luckyDeepslatePerChunk, HeightRangePlacementModifier.uniform(YOffset.fixed(-64),YOffset.fixed(CONFIG.luckyDeepslateMaxLevel)) ));
        ORE_GOBBER_OVERWORLD = PlacedFeatures.register("ore_gobber_overworld", GobberOreConfiguredFeature.ORE_GOBBER_OVERWORLD, modifiersWithCount(CONFIG.gobberPerChunk, HeightRangePlacementModifier.uniform(YOffset.fixed(0),YOffset.fixed(CONFIG.gobberMaxLevel))  ));
        ORE_GOBBER_OVERWORLD_DEEPSLATE = PlacedFeatures.register("ore_gobber_overworld_deepslate", GobberOreConfiguredFeature.ORE_GOBBER_OVERWORLD_DEEPSLATE, modifiersWithCount(CONFIG.gobberDeepslatePerChunk, HeightRangePlacementModifier.uniform(YOffset.fixed(-64),YOffset.fixed(CONFIG.gobberDeepslateMaxLevel))  ));
        ORE_LUCKY_BLOCK_NETHER = PlacedFeatures.register("ore_lucky_block_nether", GobberOreConfiguredFeature.ORE_LUCKY_BLOCK_NETHER, modifiersWithCount(CONFIG.luckyNetherPerChunk, HeightRangePlacementModifier.uniform(YOffset.fixed(0),YOffset.fixed(CONFIG.luckyNetherMaxLevel))  ));
        ORE_GOBBER_NETHER = PlacedFeatures.register("ore_gobber_nether", GobberOreConfiguredFeature.ORE_GOBBER_NETHER, modifiersWithCount(CONFIG.netherGobberPerChunk, HeightRangePlacementModifier.uniform(YOffset.fixed(0),YOffset.fixed(CONFIG.netherGobberMaxLevel))));
        ORE_LUCKY_BLOCK_END = PlacedFeatures.register("ore_lucky_block_end", GobberOreConfiguredFeature.ORE_LUCKY_BLOCK_END, modifiersWithCount(CONFIG.luckyEndPerChunk, HeightRangePlacementModifier.uniform(YOffset.fixed(0),YOffset.fixed(CONFIG.luckyEndMaxLevel)) ));
        ORE_GOBBER_THEEND = PlacedFeatures.register("ore_gobber_end", GobberOreConfiguredFeature.ORE_GOBBER_THEEND, modifiersWithCount(CONFIG.endGobberPerChunk, HeightRangePlacementModifier.uniform(YOffset.fixed(0),YOffset.fixed(CONFIG.endGobberMaxLevel)) ));
    }


    public static void register()
    {
        // Cast the RegistryEntry to RegistryKey for use with BiomModification API
        RegistryKey<PlacedFeature> overworldLuckyBlock = ORE_LUCKY_BLOCK_OVERWORLD.getKey().get();
        RegistryKey<PlacedFeature> overworldLuckyBlockDeepslate = ORE_LUCKY_BLOCK_OVERWORLD_DEEPSLATE.getKey().get();
        RegistryKey<PlacedFeature> overworldGobberOre = ORE_GOBBER_OVERWORLD.getKey().get();
        RegistryKey<PlacedFeature> overworldGobberOreDeepslate = ORE_GOBBER_OVERWORLD_DEEPSLATE.getKey().get();
        RegistryKey<PlacedFeature> netherLuckyBlock = ORE_LUCKY_BLOCK_NETHER.getKey().get();
        RegistryKey<PlacedFeature> netherGobberOre = ORE_GOBBER_NETHER.getKey().get();
        RegistryKey<PlacedFeature> endLuckyBlock = ORE_LUCKY_BLOCK_END.getKey().get();
        RegistryKey<PlacedFeature> endGobberOre = ORE_GOBBER_THEEND.getKey().get();

        // Inject into Biomes
        BiomeModifications.addFeature(overworldSelector(), GenerationStep.Feature.UNDERGROUND_ORES, overworldLuckyBlock);
        BiomeModifications.addFeature(overworldSelector(), GenerationStep.Feature.UNDERGROUND_ORES, overworldLuckyBlockDeepslate);
        BiomeModifications.addFeature(overworldSelector(), GenerationStep.Feature.UNDERGROUND_ORES, overworldGobberOre);
        BiomeModifications.addFeature(overworldSelector(), GenerationStep.Feature.UNDERGROUND_ORES, overworldGobberOreDeepslate);
        BiomeModifications.addFeature(netherSelector(), GenerationStep.Feature.UNDERGROUND_ORES, netherLuckyBlock);
        BiomeModifications.addFeature(netherSelector(), GenerationStep.Feature.UNDERGROUND_ORES, netherGobberOre);
        BiomeModifications.addFeature(endSelector(), GenerationStep.Feature.UNDERGROUND_ORES, endLuckyBlock);
        BiomeModifications.addFeature(endSelector(), GenerationStep.Feature.UNDERGROUND_ORES, endGobberOre);
    }

    public static Predicate<BiomeSelectionContext> overworldSelector()
    {
        return context -> context.getBiomeRegistryEntry().isIn(BiomeTags.IS_OVERWORLD);
    }

    public static Predicate<BiomeSelectionContext> netherSelector()
    {
        return context -> context.getBiomeRegistryEntry().isIn(BiomeTags.IS_NETHER);
    }

    public static Predicate<BiomeSelectionContext> endSelector()
    {
        return context -> context.getBiomeRegistryEntry().isIn(BiomeTags.IS_END);
    }

    // Used here because the vanilla ones are private
    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}

