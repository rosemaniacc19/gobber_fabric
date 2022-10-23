package com.kwpugh.gobber2.events;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EffectsInit;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.GobberForceManager;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerBlockBreakEvent
{
    // actions performed as a result of the break block event
    public static boolean onBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity entity)
    {
        if(Gobber2.CONFIG.GENERAL.medallionExpXPBoost)
        {
            if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_MEDALLION_EXP))
            {
                giveExperienceEffect(player, state);
            }
            else if(Gobber2.CONFIG.GENERAL.allowWorkInEnderchest &&
                    PlayerEquipUtil.hasItemInEnderchest(player, ItemInit.GOBBER2_MEDALLION_EXP))
            {
                giveExperienceEffect(player, state);
            }
        }

        // give GobberForce if mining ore
        if(PlayerEquipUtil.isWearingFullArmor(player) &&
                (state.getBlock() instanceof OreBlock) && Gobber2.CONFIG.GENERAL.enableGobberForce)
        {
            GobberForceManager.addGobberForce(player, Gobber2.CONFIG.GENERAL.forceEarnedFromOre);
        }

        return true;
    }

    public static void giveExperienceEffect(PlayerEntity player, BlockState state)
    {
        if(state.getBlock() instanceof OreBlock || Gobber2.CONFIG.GENERAL.medallionExpFromAnyBlock)
        {
            StatusEffectInstance effect = new StatusEffectInstance(EffectsInit.EXPERIENCE, Gobber2.CONFIG.GENERAL.medallionExpDuration, Gobber2.CONFIG.GENERAL.medallionExpAmplifer, true, true);
            player.addStatusEffect(effect);
        }
    }
}