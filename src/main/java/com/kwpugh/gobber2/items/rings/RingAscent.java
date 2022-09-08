package com.kwpugh.gobber2.items.rings;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.api.HandRemoveHandler;
import com.kwpugh.gobber2.api.HandTickable;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.EnableUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
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

// Now works in off hand only to avoid dropping issue
public class RingAscent extends BaseRing implements HandRemoveHandler, HandTickable
{
	public RingAscent(Settings settings)
	{
		super(settings);
	}

	// Depends on PlayerEntityMixinHand and HandTickable interface
	@Override
	public void tickHand(ItemStack stack, PlayerEntity player)
	{
		// NO OP
	}

	@Override
	// Depends on PlayerEntityMixinHand and HandTickable interface
	public void onRemoved(PlayerEntity player)
	{
		if (!player.world.isClient)
		{
			if(player.hasStatusEffect(StatusEffects.LEVITATION))
			{
				player.removeStatusEffect(StatusEffects.LEVITATION);
				player.fallDistance = 0.0F;
			}
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if(world.isClient) return;

		if(entity instanceof PlayerEntity player)
		{
			// Remove status effect if player sneaks
			if(!player.isOnGround() &&
					player.hasStatusEffect(StatusEffects.LEVITATION) &&
					player.getOffHandStack().isOf(ItemInit.GOBBER2_RING_ASCENT.asItem()) &&
					player.isSneaking())
			{
				player.removeStatusEffect(StatusEffects.LEVITATION);
			}

			player.fallDistance = 0.0F;
		}
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack mainHandstack = player.getStackInHand(hand);
		ItemStack offHandStack = player.getOffHandStack();

		if(world.isClient) return TypedActionResult.success(mainHandstack);

		if(player.isOnGround() && offHandStack.isOf(ItemInit.GOBBER2_RING_ASCENT))
		{
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.LEVITATION, Gobber2.CONFIG.GENERAL.ringAscentDuration, 1, false, false);

			player.addStatusEffect(effect);
		}

		if(player.isSneaking() && mainHandstack.isOf(ItemInit.GOBBER2_RING_ASCENT))
		{
			EnableUtil.changeEnabled(player, hand);
			player.sendMessage((Text.translatable("Status changed")), true);
		}

		return TypedActionResult.success(mainHandstack);
	}

	@Override
	public boolean hasGlint(ItemStack stack)
	{
		return EnableUtil.isEnabled(stack);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_ascent.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_ascent.tip2").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_ascent.tip3").formatted(Formatting.YELLOW));
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_ascent.tip4").formatted(Formatting.BLUE));
	}
}
