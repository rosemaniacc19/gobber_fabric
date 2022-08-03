package com.kwpugh.gobber2.items.rings;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.api.HandRemoveHandler;
import com.kwpugh.gobber2.api.HandTickable;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class RingStrength extends BaseRing implements HandRemoveHandler, HandTickable
{
	static int strengthLevel = Gobber2.CONFIG.GENERAL.ringStrengthLevel;
	static int resistenceLevel = Gobber2.CONFIG.GENERAL.ringStrengthResistenceLevel;

	public RingStrength(Settings settings)
	{
		super(settings);
	}

	@Override
	public void tickHand(ItemStack stack, PlayerEntity playerEntity)
	{
		if (PlayerEquipUtil.hasItemInOffHand(playerEntity, ItemInit.GOBBER2_RING_STRENGTH))
		{
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.STRENGTH, Gobber2.CONFIG.GENERAL.effectDuration, strengthLevel, false, false);
			StatusEffectInstance effect2 = new StatusEffectInstance(StatusEffects.RESISTANCE, Gobber2.CONFIG.GENERAL.effectDuration, resistenceLevel, false, false);

			playerEntity.addStatusEffect(effect);
			playerEntity.addStatusEffect(effect2);
		}
	}

	@Override
	public void onRemoved(PlayerEntity playerEntity)
	{
		playerEntity.removeStatusEffect(StatusEffects.STRENGTH);
		playerEntity.removeStatusEffect(StatusEffects.RESISTANCE);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_strength.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.while_in_off_hand").formatted(Formatting.YELLOW));
		tooltip.add(Text.translatable("item.gobber2.while_in_inventory").formatted(Formatting.YELLOW));

		if(Gobber2.CONFIG.GENERAL.allowWorkInEnderchest)
		{
			tooltip.add(Text.translatable("item.gobber2.while_in_enderchest").formatted(Formatting.AQUA));
		}
	}
}