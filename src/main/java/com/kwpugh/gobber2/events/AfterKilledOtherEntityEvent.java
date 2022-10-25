package com.kwpugh.gobber2.events;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EffectsInit;
import com.kwpugh.gobber2.init.EnchantmentInit;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.GobberForceManager;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;

public class AfterKilledOtherEntityEvent implements ServerEntityCombatEvents.AfterKilledOtherEntity
{
    @Override
    public void afterKilledOtherEntity(ServerWorld world, Entity entity, LivingEntity killedEntity)
    {
        if(!(entity instanceof LivingEntity)) return;

        if(entity instanceof PlayerEntity player)
        {
            Hand hand = player.getActiveHand();
            ItemStack stack = player.getStackInHand(hand);

            // a sword with Experience Boost overrides a Medallion of Exp to avoid stacking effect
            if(EnchantmentHelper.getLevel(EnchantmentInit.EXP_BOOST, stack) > 0)
            {
                int level = EnchantmentHelper.getLevel(EnchantmentInit.EXP_BOOST, stack);
                StatusEffectInstance effect = new StatusEffectInstance(EffectsInit.EXPERIENCE, Gobber2.CONFIG.GENERAL.effectDurationExpBoost, level, true, true);
                player.addStatusEffect(effect);
            }
            else if(Gobber2.CONFIG.GENERAL.medallionExpXPBoost)
            {
                if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_MEDALLION_EXP))
                {
                    giveExperienceEffect(player);
                }
                else if(Gobber2.CONFIG.GENERAL.allowWorkInEnderchest && PlayerEquipUtil.hasItemInEnderchest(player, ItemInit.GOBBER2_MEDALLION_EXP))
                {
                    giveExperienceEffect(player);
                }
            }

            // give GobberForce if killing a mob
            if(PlayerEquipUtil.isWearingFullArmor(player) &&
                    Gobber2.CONFIG.GENERAL.enableGobberForce &&
                    Gobber2.CONFIG.GENERAL.enableGFKillEarning)
            {
                GobberForceManager.addGobberForce(player, Gobber2.CONFIG.GENERAL.forceEarnedFromKill);
            }
        }
    }

    public void giveExperienceEffect(PlayerEntity player)
    {
        StatusEffectInstance effect = new StatusEffectInstance(EffectsInit.EXPERIENCE, Gobber2.CONFIG.GENERAL.effectDurationExpBoost, Gobber2.CONFIG.GENERAL.medallionExpAmplifer, true, true);
        player.addStatusEffect(effect);
    }
}