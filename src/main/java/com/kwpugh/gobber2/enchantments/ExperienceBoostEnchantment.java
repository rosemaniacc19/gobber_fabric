package com.kwpugh.gobber2.enchantments;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class ExperienceBoostEnchantment extends Enchantment
{
	public ExperienceBoostEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slots)
	{
		super(weight, target, slots);
	}

    public int getMinPower(int level) 
    {
    	return 1 + 10 * (level - 1);
    }

    public int getMaxPower(int level) 
    {
    	return super.getMinPower(level) + 50;
    }
     
    @Override
    public int getMaxLevel()
    {
        return 5;
    }


    @Override
    public boolean isTreasure()
    {
        return Gobber2.CONFIG.GENERAL.enableExpBoost;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer()
    {
        return Gobber2.CONFIG.GENERAL.enableExpBoost;
    }

    @Override
    public boolean isAvailableForRandomSelection()
    {
        return Gobber2.CONFIG.GENERAL.enableExpBoost;
    }
}