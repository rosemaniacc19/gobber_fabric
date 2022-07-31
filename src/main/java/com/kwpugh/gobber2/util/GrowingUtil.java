package com.kwpugh.gobber2.util;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;

public class GrowingUtil
{
	//  Accelerates growth in area of effect
	public static void growCrops(World world, PlayerEntity player, int baseTickDelay, int cactusTickDelay, int radius, int height)
	{
		BlockPos playerPos = new BlockPos(player.getPos());

		if (player.age % (baseTickDelay) == 0)
		{
			for (BlockPos targetPos : BlockPos.iterateOutwards(playerPos, radius, height, radius))
			{
				BlockState blockstate = world.getBlockState(targetPos);
				Block block = blockstate.getBlock();

				if (   (block instanceof CropBlock) ||  //Beets Carrots Potatoes
						block instanceof BambooSaplingBlock ||
						block instanceof BambooBlock ||
						block instanceof CocoaBlock ||
						block instanceof StemBlock ||
						block instanceof SweetBerryBushBlock ||
						block instanceof FungusBlock ||
						block instanceof SaplingBlock  || //all sapling
						block instanceof KelpBlock ||
						block instanceof KelpPlantBlock ||
						block instanceof AzaleaBlock ||
						block instanceof SmallDripleafBlock ||
						block instanceof BigDripleafStemBlock
				)
				{
					Fertilizable fertilizable = (Fertilizable)blockstate.getBlock();
					if (fertilizable.isFertilizable(world, targetPos, blockstate, world.isClient))
					{
						if (world instanceof ServerWorld)
						{
							if (fertilizable.canGrow(world, world.random, targetPos, blockstate))
							{
								fertilizable.grow((ServerWorld)world, world.random, targetPos, blockstate);
							}
						}
					}
				}
			}
		}

		if (world.getTime() % (cactusTickDelay) == 0)
		{
			for (BlockPos tickTarget : BlockPos.iterateOutwards(playerPos, radius, height, radius))
			{
				BlockState blockstate2 = world.getBlockState(tickTarget);
				Block blockToTick = blockstate2.getBlock();

				if(blockToTick instanceof SugarCaneBlock ||
						blockToTick instanceof CactusBlock ||
						blockToTick instanceof BuddingAmethystBlock ||
						blockToTick instanceof NetherWartBlock ||
						blockToTick instanceof ChorusFlowerBlock)
				{
					if(world instanceof ServerWorld)
					{
						blockToTick.randomTick(blockstate2, (ServerWorld) world, tickTarget, world.random);
					}
				}
			}
		}

		if (world.getTime() % (baseTickDelay) == 0)
		{
			for (BlockPos tickTarget : BlockPos.iterateOutwards(playerPos, radius, height, radius))
			{
				BlockState blockstate2 = world.getBlockState(tickTarget);
				Block blockToTick = blockstate2.getBlock();

				if(blockToTick instanceof PointedDripstoneBlock)
				{
					if(world instanceof ServerWorld)
					{
						blockToTick.randomTick(blockstate2, (ServerWorld) world, tickTarget, world.random);

						PointedDripstoneBlock.dripTick(blockstate2, (ServerWorld) world, tickTarget,.9f);
						PointedDripstoneBlock.tryGrow(blockstate2, (ServerWorld) world, tickTarget, world.random);
					}
				}
			}
		}
	}
}