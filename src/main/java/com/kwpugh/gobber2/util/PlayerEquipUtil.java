package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.init.ItemInit;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public final class PlayerEquipUtil
{
	public static boolean isWearingFullArmor(PlayerEntity player)
	{
		if(isWearingGobberArmor(player) ||
				isWearingNetherArmor(player) ||
				isWearingEndArmor(player) ||
				isWearingDragonArmor(player))
		{
			return true;
		}

		return false;
	}

    public static boolean isWearingGobberArmor(PlayerEntity player)
    {
		ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feet = player.getEquippedStack(EquipmentSlot.FEET);

		//Full Set
		if(		(head.getItem() == ItemInit.GOBBER2_HELMET &&
				chest.getItem() == ItemInit.GOBBER2_CHESTPLATE &&
				legs.getItem() == ItemInit.GOBBER2_LEGGINGS &&
				feet.getItem() == ItemInit.GOBBER2_BOOTS)
			  )
			{
				return true;
			}
    	
    	return false;
    }

	public static boolean isWearingNetherArmor(PlayerEntity player)
	{
		ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feet = player.getEquippedStack(EquipmentSlot.FEET);

		//Full Set
		if(		(head.getItem() == ItemInit.GOBBER2_HELMET_NETHER &&
				chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_NETHER &&
				legs.getItem() == ItemInit.GOBBER2_LEGGINGS_NETHER &&
				feet.getItem() == ItemInit.GOBBER2_BOOTS_NETHER)
			)
		{
			return true;
		}

		return false;
	}

	public static boolean isWearingEndArmor(PlayerEntity player)
	{
		ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feet = player.getEquippedStack(EquipmentSlot.FEET);

		//Full Set
		if(		(head.getItem() == ItemInit.GOBBER2_HELMET_END &&
				chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_END &&
				legs.getItem() == ItemInit.GOBBER2_LEGGINGS_END &&
				feet.getItem() == ItemInit.GOBBER2_BOOTS_END)
			)
		{
			return true;
		}

		return false;
	}

	// Wearing full Dragon Armor
	public static boolean isWearingDragonArmor(PlayerEntity player)
	{
		ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feet = player.getEquippedStack(EquipmentSlot.FEET);

		//Full Set
		if(		(head.getItem() == ItemInit.GOBBER2_HELMET_DRAGON &&
				chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON &&
				legs.getItem() == ItemInit.GOBBER2_LEGGINGS_DRAGON &&
				feet.getItem() == ItemInit.GOBBER2_BOOTS_DRAGON)     )
		{
			return true;
		}

		return false;
	}

	// Generalized check for item in offhand
	public static boolean hasItemInOffHand(PlayerEntity player, Item item)
	{
		ItemStack offHand = player.getOffHandStack();

		if(offHand.getItem() == item)
		{
			return true;
		}

		return false;
	}

	// Generalized check for item in main hand
	public static boolean hasItemInMainHand(PlayerEntity player, Item item)
	{
		ItemStack offHand = player.getMainHandStack();

		if(offHand.getItem() == item)
		{
			return true;
		}

		return false;
	}

	// Generalized check for item in inventory
	public static boolean hasItemInInventory(PlayerEntity player, Item item)
	{
		PlayerInventory inv = player.getInventory();
		int size = inv.size();

		//Is the item in the player inventory?
		for (int slot = 0; slot < size; slot++)
		{
			ItemStack stack = inv.getStack(slot);
			if (stack.getItem() == item)
			{
				return true;
			}
		}

		return false;
	}

	// Return Itemtack for Item in inventory
	public static ItemStack getItemStackInInventory(PlayerEntity player, Item item)
	{
		PlayerInventory inv = player.getInventory();
		int size = inv.size();

		//Is the item in the player inventory?
		for (int slot = 0; slot < size; slot++)
		{
			ItemStack stack = inv.getStack(slot);
			if (stack.getItem() == item)
			{
				return stack;
			}
		}

		return ItemStack.EMPTY;
	}

	// Generalized check for item in ender chest
	public static boolean hasItemInEnderchest(PlayerEntity player, Item item)
	{
		if(!(player instanceof ServerPlayerEntity serverPlayer)) return false;

		EnderChestInventory inv = serverPlayer.getEnderChestInventory();
		int size = inv.size();

		//Is the item in the ender chest inventory?
		for (int slot = 0; slot < size; slot++)
		{
			ItemStack stack = inv.getStack(slot);
			if (stack.getItem() == item)
			{
				return true;
			}
		}

		return false;
	}

	// Return itemstack for item in ender chest
	public static ItemStack getItemStackInEnderchest(PlayerEntity player, Item item)
	{
		if(!(player instanceof ServerPlayerEntity serverPlayer)) return ItemStack.EMPTY;

		EnderChestInventory inv = serverPlayer.getEnderChestInventory();
		int size = inv.size();

		//Is the item in the ender chest inventory?
		for (int slot = 0; slot < size; slot++)
		{
			ItemStack stack = inv.getStack(slot);
			if (stack.getItem() == item)
			{
				return stack;
			}
		}

		return ItemStack.EMPTY;
	}
} 