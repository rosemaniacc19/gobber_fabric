package com.kwpugh.gobber2.items.rings;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.EnableUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class RingHaste extends BaseRing
{
	static int hasteLevel = Gobber2.CONFIG.GENERAL.ringHasteLevel;

	public RingHaste(Settings settings)
	{
		super(settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if(EnableUtil.isEnabled(stack))
		{
			if(entity instanceof PlayerEntity)
			{
				LivingEntity player = (LivingEntity) entity;

				StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.HASTE, Gobber2.CONFIG.GENERAL.effectDuration, hasteLevel, false, false);

				player.addStatusEffect(effect);
			}
		}
	}
	
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemStack = player.getStackInHand(hand);

		if (!world.isClient)
		{
			EnableUtil.changeEnabled(player, hand);
			player.sendMessage((Text.translatable("Status changed")), true);
		}

		return TypedActionResult.success(itemStack);
	}

	@Override
	public boolean hasGlint(ItemStack stack)
	{
		return EnableUtil.isEnabled(stack);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_haste.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.right_click").formatted(Formatting.YELLOW));
		tooltip.add(Text.translatable("item.gobber2.while_in_inventory").formatted(Formatting.YELLOW));

		if(Gobber2.CONFIG.GENERAL.allowWorkInEnderchest)
		{
			tooltip.add(Text.translatable("item.gobber2.while_in_enderchest").formatted(Formatting.AQUA));
		}
	} 
}