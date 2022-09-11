package com.kwpugh.gobber2.enchantments;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class BlindersEnchantment extends Enchantment
{
	public BlindersEnchantment(Enchantment.Rarity weight, EnchantmentTarget digger, EquipmentSlot... slotTypes) 
	{
		super(weight, EnchantmentTarget.ARMOR_HEAD, slotTypes);
	}

	public int getMinPower(int level) 
	{
		return 20;
	}

	public int getMaxPower(int level)
	{
		return 50;
	}

	@Override
	public int getMaxLevel()
	{
	    return 1;
	}

	@Override
	public boolean isTreasure()
	{
		return Gobber2.CONFIG.GENERAL.enableBlinders;
	}

	@Override
	public boolean isAvailableForEnchantedBookOffer()
	{
		return Gobber2.CONFIG.GENERAL.enableBlinders;
	}

	@Override
	public boolean isAvailableForRandomSelection()
	{
		return Gobber2.CONFIG.GENERAL.enableBlinders;
	}
}