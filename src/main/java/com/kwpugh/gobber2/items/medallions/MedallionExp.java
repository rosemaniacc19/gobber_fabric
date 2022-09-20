package com.kwpugh.gobber2.items.medallions;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.ExpUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class MedallionExp extends BaseMedallion
{
	public static final int MAX_STORAGE = 1425;
	private final Random random = new Random();

	public MedallionExp(Settings settings)
	{
		super(new Item.Settings().maxDamage(MAX_STORAGE).group(Gobber2.GOBBER2_GROUP));
	}

	// Test code
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);

		// Store the players xp
		if(player.isSneaking() && getXPStored(stack) != MAX_STORAGE)
		{
			int playerXP = ExpUtils.getPlayerXP(player);

			if(playerXP == 0) return TypedActionResult.success(stack);

			int actuallyStored = addXP(stack, playerXP); //try to store all of the player's levels

			ExpUtils.addPlayerXP(player, -actuallyStored);

			if(!world.isClient)
				world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, (random.nextFloat() - random.nextFloat()) * 0.35F + 0.9F);

			return TypedActionResult.success(stack);
		}
		// Give stored xp to player
		else if(!player.isSneaking() && getXPStored(stack) != 0)
		{
			if(getXPStored(stack) > 1401)
			{
				ExpUtils.addPlayerXP(player, 1401);
				setStoredXP(stack, getXPStored(stack)-1401);
			}
			else
			{
				ExpUtils.addPlayerXP(player, getXPStored(stack));
				setStoredXP(stack, 0);
			}

			if(!world.isClient)
			{
				float pitchMultiplier = player.experienceLevel > 30 ? 1.0F : player.experienceLevel / 30.0F;

				world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, pitchMultiplier * 0.75F, 1.0F);
			}

			return TypedActionResult.success(stack);
		}

		return TypedActionResult.success(stack);
	}

	@Override
	public void onCraft(ItemStack stack, World worldIn, PlayerEntity playerIn)
	{
		stack.setDamage(MAX_STORAGE);
	}

	@Override
	public boolean hasGlint(ItemStack stack)
	{
		return getXPStored(stack) > 0;
	}

	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return false;
	}

	@Override
	public boolean canRepair(ItemStack toRepair, ItemStack repair)
	{
		return false;
	}

	public int addXP(ItemStack stack, int amount)
	{
		int stored = getXPStored(stack);

		if(stored + amount > MAX_STORAGE)
		{
			setStoredXP(stack, MAX_STORAGE);
			return MAX_STORAGE - stored;
		}
		else
		{
			setStoredXP(stack, stored + amount);
			return amount;
		}
	}

	public void setStoredXP(ItemStack stack, int amount)
	{
		stack.setDamage(MAX_STORAGE - amount);
	}

	public int getXPStored(ItemStack stack)
	{
		return MAX_STORAGE - stack.getDamage(); //if the damage is 0, the medallion is full on xp
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_medallion_exp.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.while_in_inventory").formatted(Formatting.YELLOW));
		tooltip.add(Text.translatable("item.gobber2.gobber2_medallion_exp.tip2", getXPStored(itemStack)).formatted(Formatting.RED));
		tooltip.add(Text.translatable("item.gobber2.gobber2_medallion_exp.tip3").formatted(Formatting.YELLOW));

		if(Gobber2.CONFIG.GENERAL.allowWorkInEnderchest)
		{
			tooltip.add(Text.translatable("item.gobber2.while_in_enderchest").formatted(Formatting.AQUA));
		}
	} 
}