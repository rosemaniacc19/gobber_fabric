package com.kwpugh.gobber2.enchantments;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FluidMasterEnchantment extends Enchantment
{
    public FluidMasterEnchantment(Enchantment.Rarity weight, EnchantmentTarget target, EquipmentSlot... slotTypes)
    {
        super(weight, target, slotTypes);
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
        return Gobber2.CONFIG.GENERAL.enableFluidMaster;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer()
    {
        return Gobber2.CONFIG.GENERAL.enableFluidMaster;
    }

    @Override
    public boolean isAvailableForRandomSelection()
    {
        return Gobber2.CONFIG.GENERAL.enableFluidMaster;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack)
    {
        Item item = stack.getItem();
        if(item == ItemInit.GOBBER2_STAFF_CLEARING)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}