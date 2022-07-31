package com.kwpugh.gobber2.world;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.Gobber2Config;
import com.kwpugh.gobber2.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class GobberOreConfiguredFeature

{
	public static final Gobber2Config.Ores CONFIG = Gobber2.CONFIG.ORES;

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_LUCKY_BLOCK_OVERWORLD;
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_LUCKY_BLOCK_OVERWORLD_DEEPSLATE;
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_GOBBER_OVERWORLD;
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_GOBBER_OVERWORLD_DEEPSLATE;
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_LUCKY_BLOCK_NETHER;
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_GOBBER_NETHER;
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_GOBBER_THEEND;

    static
    {
        ORE_LUCKY_BLOCK_OVERWORLD = ConfiguredFeatures.register("ore_lucky_block_overworld", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, BlockInit.GOBBER2_LUCKY_BLOCK.getDefaultState())), CONFIG.luckyVeinSize));
        ORE_LUCKY_BLOCK_OVERWORLD_DEEPSLATE = ConfiguredFeatures.register("ore_lucky_block_overworld_deepslate", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.GOBBER2_LUCKY_BLOCK_DEEPSLATE.getDefaultState())), CONFIG.luckyDeepslateVeinSize));
        ORE_GOBBER_OVERWORLD = ConfiguredFeatures.register("ore_gobber_overworld", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, BlockInit.GOBBER2_ORE.getDefaultState())), CONFIG.gobberVeinSize));
        ORE_GOBBER_OVERWORLD_DEEPSLATE = ConfiguredFeatures.register("ore_gobber_overworld_deepslate", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.GOBBER2_ORE_DEEPSLATE.getDefaultState())), CONFIG.gobberDeepslateVeinSize));
        ORE_LUCKY_BLOCK_NETHER = ConfiguredFeatures.register("ore_lucky_block_nether", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.NETHERRACK, BlockInit.GOBBER2_LUCKY_BLOCK_NETHER.getDefaultState())), CONFIG.luckyNetherVeinSize));
        ORE_GOBBER_NETHER = ConfiguredFeatures.register("ore_gobber_nether", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.NETHERRACK, BlockInit.GOBBER2_ORE_NETHER.getDefaultState())), CONFIG.netherGobberVeinSize));
        ORE_GOBBER_THEEND = ConfiguredFeatures.register("ore_gobber_end", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.END_STONE), BlockInit.GOBBER2_ORE_END.getDefaultState())), CONFIG.endGobberVeinSize));
    }
}

