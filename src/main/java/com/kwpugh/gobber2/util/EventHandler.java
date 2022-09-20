package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EffectsInit;
import com.kwpugh.gobber2.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EventHandler
{
    // Drop tests performed as a result of the break block event
    public static boolean onBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity entity)
    {
        if(player.isCreative() || player.isSpectator()) return false;
        if(world.isClient) return false;

        if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_MEDALLION_EXP) ||
                (PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_EXP) && Gobber2.CONFIG.GENERAL.allowWorkInEnderchest))
        {
            if(state.getBlock() instanceof OreBlock)
            {
                StatusEffectInstance effect = new StatusEffectInstance(EffectsInit.KNOWLEDGE, Gobber2.CONFIG.GENERAL.effectDurationKnowledge, Gobber2.CONFIG.GENERAL.medallionExpAmplifer, true, true);
                player.addStatusEffect(effect);
            }
        }

        return true;
    }
}