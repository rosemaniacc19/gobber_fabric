package com.kwpugh.gobber2.enchantments;

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

    /*
     * Makes the enchant only available
     *  from Village librarian
     */
    @Override
    public boolean isAvailableForEnchantedBookOffer()
    {
        return true;
    }

    /*
     * This excludes enchant from enchanting table
     * and loot
     */
    @Override
    public boolean isAvailableForRandomSelection()
    {
        return false;
    }

    /*
     *  So basically this does nothing since the enchantment
     *  table screen handler pulls enchantments by type
     *   in EnchantmentTarget
     */
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