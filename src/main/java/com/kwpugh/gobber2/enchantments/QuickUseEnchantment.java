package com.kwpugh.gobber2.enchantments;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class QuickUseEnchantment extends Enchantment 
{ 
	public QuickUseEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slots)
	{
		super(weight, target, slots);
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
		return Gobber2.CONFIG.GENERAL.enableQuickUse;
	}

	@Override
	public boolean isAvailableForEnchantedBookOffer()
	{
		return Gobber2.CONFIG.GENERAL.enableQuickUse;
	}

	@Override
	public boolean isAvailableForRandomSelection()
	{
		return Gobber2.CONFIG.GENERAL.enableQuickUse;
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack)
	{
		Item item = stack.getItem();
		if(item == ItemInit.GOBBER2_RING_MINER ||
				item == ItemInit.GOBBER2_RING_ABOVE ||
				item == ItemInit.GOBBER2_RING_BLINK ||
				item == ItemInit.GOBBER2_STAFF_SNIPER ||
				item == ItemInit.GOBBER2_SWORD_SNIPER ||
				item == ItemInit.GOBBER2_STAFF_CLEARING ||
				item == ItemInit.GOBBER2_RING_CURING)
		{
			return true;
		}
	
		return false;
	}
}