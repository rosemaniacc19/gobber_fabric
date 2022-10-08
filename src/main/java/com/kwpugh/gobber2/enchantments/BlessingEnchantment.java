package com.kwpugh.gobber2.enchantments;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EffectsInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;

public class BlessingEnchantment extends Enchantment
{
	public BlessingEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slots)
	{
		super(weight, target, slots);
	}

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level)
    {
        StatusEffectInstance effect = new StatusEffectInstance(EffectsInit.BLESSING, Gobber2.CONFIG.GENERAL.effectBlessingDuration, 0, false, true);

        if(user instanceof PlayerEntity player)
        {
            player.addStatusEffect(effect);
        }
    }

    @Override
    public int getMinPower(int level) 
    {
    	return 1 + 10 * (level - 1);
    }

    @Override
    public int getMaxPower(int level) 
    {
    	return super.getMinPower(level) + 50;
    }
     
    @Override
    public int getMaxLevel()
    {
        return 1;
    }

    @Override
    public boolean isTreasure()
    {
        return Gobber2.CONFIG.GENERAL.enableBlessing;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer()
    {
        return Gobber2.CONFIG.GENERAL.enableBlessing;
    }

    @Override
    public boolean isAvailableForRandomSelection()
    {
        return Gobber2.CONFIG.GENERAL.enableBlessing;
    }
}