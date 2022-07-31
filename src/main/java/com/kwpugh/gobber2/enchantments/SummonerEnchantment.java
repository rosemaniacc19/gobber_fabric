package com.kwpugh.gobber2.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;

public class SummonerEnchantment extends Enchantment
{
    public SummonerEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slots)
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

}