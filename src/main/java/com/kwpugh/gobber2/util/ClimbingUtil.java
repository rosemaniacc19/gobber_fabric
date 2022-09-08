package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ClimbingUtil
{
    public static boolean canClimb(World world, LivingEntity livingEntity, BlockPos pos)
    {
        boolean value = false;

        if(livingEntity instanceof PlayerEntity player)
        {
            if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_RING_ASCENT))
            {
                BlockPos north = pos.offset(Direction.NORTH, 1);
                BlockPos south = pos.offset(Direction.SOUTH, 1);
                BlockPos east = pos.offset(Direction.EAST, 1);
                BlockPos west = pos.offset(Direction.WEST, 1);

                BlockState stateNorth = world.getBlockState(north);
                BlockState stateSouth = world.getBlockState(south);
                BlockState stateEast = world.getBlockState(east);
                BlockState stateWest = world.getBlockState(west);

                ItemStack ring = PlayerEquipUtil.getItemInInventory(player, ItemInit.GOBBER2_RING_ASCENT);

                if(!EnableUtil.isEnabled(ring)) return value;

                BlockState stateDown = world.getBlockState(pos.offset(Direction.DOWN, 1));

                if(checkBlockBelow(stateDown))
                {
                    if(canBegin(player.world, north, south, east, west, stateNorth, stateSouth, stateEast, stateWest))
                    {
                        value = true;
                    }
                }
                else if(canContinue(stateNorth, stateSouth, stateEast, stateWest))
                {
                    value = true;
                }
            }
        }

        return value;
    }

    public static boolean checkBlockBelow(BlockState state)
    {
        Block block = state.getBlock();
        return block != Blocks.AIR && !state.isIn(BlockTags.REPLACEABLE_PLANTS) && !state.isIn(BlockTags.FLOWERS);
    }

    public static boolean checkContinue(Block block, BlockState state)
    {
        return block != Blocks.AIR && !state.isIn(BlockTags.REPLACEABLE_PLANTS);
    }

    public static boolean checkBegin(Block block, Block blockUp, BlockState state, BlockState stateUp)
    {
        return block != Blocks.AIR && blockUp != Blocks.AIR && !state.isIn(BlockTags.REPLACEABLE_PLANTS) && !stateUp.isIn(BlockTags.REPLACEABLE_PLANTS);
    }

    public static boolean canBegin(World world, BlockPos north, BlockPos south, BlockPos east, BlockPos west, BlockState stateNorth, BlockState stateSouth, BlockState stateEast, BlockState stateWest)
    {
        BlockState stateNorthUp = world.getBlockState(north.offset(Direction.UP, 1));
        BlockState stateSouthUp = world.getBlockState(south.offset(Direction.UP, 1));
        BlockState stateEastUp = world.getBlockState(east.offset(Direction.UP, 1));
        BlockState stateWestUp = world.getBlockState(west.offset(Direction.UP, 1));

        if(checkBegin(stateNorth.getBlock(), stateNorthUp.getBlock(), stateNorth, stateNorthUp))
        {
            return true;
        }
        else if(checkBegin(stateSouth.getBlock(), stateSouthUp.getBlock(), stateSouth, stateSouthUp))
        {
            return true;
        }
        else if(checkBegin(stateEast.getBlock(), stateEastUp.getBlock(), stateEast, stateEastUp))
        {
            return true;
        }
        return checkBegin(stateWest.getBlock(), stateWestUp.getBlock(), stateWest, stateWestUp);
    }

    public static boolean canContinue(BlockState stateNorth, BlockState stateSouth, BlockState stateEast, BlockState stateWest)
    {
        if(checkContinue(stateNorth.getBlock(), stateNorth))
        {
            return true;
        }
        if(checkContinue(stateSouth.getBlock(), stateSouth))
        {
            return true;
        }
        if(checkContinue(stateEast.getBlock(), stateEast))
        {
            return true;
        }
        return checkContinue(stateWest.getBlock(), stateWest);
    }
}