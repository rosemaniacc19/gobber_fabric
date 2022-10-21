package com.kwpugh.gobber2.util;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrowingUtil
{
	//  Accelerates growth in area of effect
	public static void growCrops(World world, PlayerEntity player, int regularDelay, int cactusDelay, int horiz, int vertical, boolean extraCrops)
	{
		BlockPos playerPos = new BlockPos(player.getPos());

		for (BlockPos targetPos : BlockPos.iterateOutwards(playerPos, horiz, vertical, horiz))
		{
			BlockState blockstate = world.getBlockState(targetPos);
			Block block = blockstate.getBlock();

			// Fertilizable crops
			if ((block instanceof CropBlock) ||  //Beets Carrots Potatoes
					block instanceof BambooSaplingBlock ||
					block instanceof BambooBlock ||
					block instanceof CocoaBlock ||
					block instanceof StemBlock ||
					block instanceof PumpkinBlock ||
					block instanceof MelonBlock ||
					block instanceof SweetBerryBushBlock ||
					block instanceof FungusBlock ||
					block instanceof SaplingBlock || //all sapling
					block instanceof KelpBlock ||
					block instanceof KelpPlantBlock ||
					block instanceof AzaleaBlock ||
					block instanceof SmallDripleafBlock ||
					block instanceof BigDripleafStemBlock)
			{
				if (player.age % (regularDelay) == 0)
				{
					Fertilizable fertilizable = (Fertilizable) blockstate.getBlock();
					if (fertilizable.isFertilizable(world, targetPos, blockstate, world.isClient))
					{
						if (world instanceof ServerWorld)
						{
							if (fertilizable.canGrow(world, world.random, targetPos, blockstate))
							{
								fertilizable.grow((ServerWorld) world, world.random, targetPos, blockstate);
							}
						}
					}
				}
			}

			// Random tick crops
			if (block instanceof SugarCaneBlock ||
					block instanceof CactusBlock ||
					block instanceof BuddingAmethystBlock ||
					block instanceof NetherWartBlock ||
					block instanceof ChorusFlowerBlock)
			{
				if (world.getTime() % (cactusDelay) == 0)
				{
					if (world instanceof ServerWorld)
					{
						block.randomTick(blockstate, (ServerWorld) world, targetPos, world.random);
					}
				}
			}

			// Random tick for special case
			if (block instanceof CropBlock && extraCrops)
			{
				if (world.getTime() % (cactusDelay) == 0)
				{
					if (world instanceof ServerWorld)
					{
						block.randomTick(blockstate, (ServerWorld) world, targetPos, world.random);
					}
				}
			}

			// Random tick for dripstone
			if (block instanceof PointedDripstoneBlock)
			{
				if (world.getTime() % (regularDelay) == 0)
				{
					if (world instanceof ServerWorld)
					{
						block.randomTick(blockstate, (ServerWorld) world, targetPos, world.random);

						PointedDripstoneBlock.dripTick(blockstate, (ServerWorld) world, targetPos, .9f);
						PointedDripstoneBlock.tryGrow(blockstate, (ServerWorld) world, targetPos, world.random);
					}
				}
			}
		}
	}
}