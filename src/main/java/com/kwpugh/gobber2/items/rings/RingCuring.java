package com.kwpugh.gobber2.items.rings;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EnchantmentInit;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class RingCuring extends BaseRing
{
	public RingCuring(Settings settings)
	{
		super(settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if (!world.isClient && Gobber2.CONFIG.GENERAL.enableCuringAlwaysOn)
		{
			if(entity instanceof PlayerEntity player)
			{
				PlayerSpecialAbilities.giveCuringEffect(world, player);
			}
		}
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);

		PlayerSpecialAbilities.giveCuringEffect(world, player);

		if(!(EnchantmentHelper.getLevel(EnchantmentInit.QUICKUSE, player.getEquippedStack(EquipmentSlot.MAINHAND)) > 0) )
		{
			player.getItemCooldownManager().set(this, Gobber2.CONFIG.GENERAL.ringCuringCooldown);
		}

		return TypedActionResult.success(stack);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_curing.tip1").formatted(Formatting.GREEN));

		if(!Gobber2.CONFIG.GENERAL.enableCuringAlwaysOn)
		{
			tooltip.add(Text.translatable("item.gobber2.right_click_activate").formatted(Formatting.YELLOW));
		}
		else
		{
			tooltip.add(Text.translatable("item.gobber2.while_in_inventory").formatted(Formatting.YELLOW));
		}
	}
}