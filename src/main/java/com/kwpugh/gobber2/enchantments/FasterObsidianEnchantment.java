package com.kwpugh.gobber2.enchantments;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FasterObsidianEnchantment extends Enchantment
{
	public FasterObsidianEnchantment(Enchantment.Rarity weight, EnchantmentTarget digger, EquipmentSlot... slotTypes) 
	{
		super(weight, EnchantmentTarget.DIGGER, slotTypes);
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
		return Gobber2.CONFIG.GENERAL.enableFasterObsidian;
	}

	@Override
	public boolean isAvailableForEnchantedBookOffer()
	{
		return Gobber2.CONFIG.GENERAL.enableFasterObsidian;
	}

	@Override
	public boolean isAvailableForRandomSelection()
	{
		return Gobber2.CONFIG.GENERAL.enableFasterObsidian;
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack)
	{
		Item pickaxe = stack.getItem();

		if(pickaxe == ItemInit.GOBBER2_PICKAXE ||
				pickaxe == ItemInit.GOBBER2_PICKAXE_NETHER ||
				pickaxe == ItemInit.GOBBER2_PICKAXE_END ||
				pickaxe == ItemInit.GOBBER2_PAXEL ||
				pickaxe == ItemInit.GOBBER2_PAXEL_NETHER ||
				pickaxe == ItemInit.GOBBER2_PAXEL_END ||
				pickaxe == ItemInit.GOBBER2_PAXEL_STARS ||
				pickaxe == ItemInit.GOBBER2_HAMMER ||
				pickaxe == ItemInit.GOBBER2_HAMMER_NETHER ||
				pickaxe == ItemInit.GOBBER2_HAMMER_END)
		{
			return this.type.isAcceptableItem(stack.getItem());
		}
		else
		{
			return false;
		}

	}
}