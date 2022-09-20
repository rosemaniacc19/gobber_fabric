package com.kwpugh.gobber2.enchantments;

import com.kwpugh.gobber2.Gobber2;
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

    @Override
    public boolean isTreasure()
    {
        return Gobber2.CONFIG.GENERAL.enableSummoner;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer()
    {
        return Gobber2.CONFIG.GENERAL.enableSummoner;
    }

    @Override
    public boolean isAvailableForRandomSelection()
    {
        return Gobber2.CONFIG.GENERAL.enableSummoner;
    }
}