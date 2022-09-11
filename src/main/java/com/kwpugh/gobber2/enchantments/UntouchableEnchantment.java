package com.kwpugh.gobber2.enchantments;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class UntouchableEnchantment extends Enchantment
{	
	public UntouchableEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes)
	{
		super(weight, target, slotTypes);
	}
	
	public int getMinPower(int level) 
	{
		return 10 + 20 * (level - 1);
	}

	public int getMaxPower(int level)
	{
		return super.getMinPower(level) + 50;
	}

	@Override
	public int getMaxLevel()
	{
	    return Gobber2.CONFIG.GENERAL.untouchableMaxLevel;
	}

	@Override
	public void onUserDamaged(LivingEntity user, Entity attacker, int level)
	{
		if(user.getRandom().nextFloat() <= (Gobber2.CONFIG.GENERAL.untouchableAttackChance + (level / 10)))
		{
			if(attacker != null)
			{
				attacker.damage(DamageSource.thorns(user), Gobber2.CONFIG.GENERAL.untouchableBaseDamage + (level * 1.5F));
			}
		}
	}

	@Override
	public boolean isTreasure()
	{
		return Gobber2.CONFIG.GENERAL.enableUntouchable;
	}

	@Override
	public boolean isAvailableForEnchantedBookOffer()
	{
		return Gobber2.CONFIG.GENERAL.enableUntouchable;
	}

	@Override
	public boolean isAvailableForRandomSelection()
	{
		return Gobber2.CONFIG.GENERAL.enableUntouchable;
	}
}