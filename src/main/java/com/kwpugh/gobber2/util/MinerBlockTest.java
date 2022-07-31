package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.init.TagInit;
import net.minecraft.block.*;
import net.minecraft.tag.BlockTags;

public class MinerBlockTest
{
	public static boolean canBreak(Block block, BlockState state)
	{
		return state.isIn(TagInit.DIRT) ||
				state.isIn(TagInit.SAND) ||
				state.isIn(TagInit.SANDSTONE) ||
				state.isIn(TagInit.NETHERRACK) ||
				state.isIn(TagInit.SOUL_GROUND) ||
				state.isIn(TagInit.COBBLESTONE) ||
				state.isIn(TagInit.STONE) ||
				state.isIn(TagInit.END_STONES) ||
				state.isIn(TagInit.RING_MINER_ADDITIONS) ||
				state.isIn(BlockTags.BASE_STONE_OVERWORLD) ||
				state.isIn(BlockTags.BASE_STONE_NETHER) ||
				state.isIn(BlockTags.WART_BLOCKS) ||
				state.isIn(BlockTags.TERRACOTTA) ||
				state.isIn(BlockTags.SNOW) ||
				state.isIn(BlockTags.NYLIUM) ||
				block instanceof GravelBlock ||
				block instanceof SandBlock ||
				block instanceof MagmaBlock ||
				block == Blocks.MOSSY_COBBLESTONE_SLAB ||
				block == Blocks.MOSSY_COBBLESTONE_STAIRS ||
				block == Blocks.MOSSY_STONE_BRICKS ||
				block == Blocks.MOSSY_STONE_BRICK_STAIRS ||
				block == Blocks.MOSSY_STONE_BRICK_SLAB ||
				block == Blocks.STONE_BRICKS ||
				block == Blocks.STONE_BRICK_STAIRS ||
				block == Blocks.STONE_BRICK_SLAB ||
				block == Blocks.CRACKED_STONE_BRICKS ||
				block == Blocks.INFESTED_CRACKED_STONE_BRICKS ||
				block == Blocks.INFESTED_CHISELED_STONE_BRICKS ||
				block == Blocks.INFESTED_COBBLESTONE ||
				block == Blocks.INFESTED_MOSSY_STONE_BRICKS ||
				block == Blocks.NETHER_BRICKS ||
				block == Blocks.NETHER_BRICK_FENCE ||
				block == Blocks.NETHER_BRICK_STAIRS ||
				block == Blocks.AMETHYST_BLOCK;
	}
}
