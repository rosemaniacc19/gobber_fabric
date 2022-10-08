package com.kwpugh.gobber2.effects;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class Blessing extends StatusEffect
{
	public Blessing()
	{
		super(StatusEffectCategory.BENEFICIAL, // whether beneficial or harmful for entities
				0xa032a8); // color in RGB
	}

	int xpAmount = Gobber2.CONFIG.GENERAL.ExperienceBaseXPPerTick;

	// This method is called every tick to check whether it should apply the status effect or not
	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier)
	{
		// In our case, we just make it return true so that it applies the status effect every tick.
		return true;
	}

	// This method is called when it applies the status effect. We implement custom functionality here.
	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier)
	{
		if (entity instanceof PlayerEntity player)
		{
			if(player.age % Gobber2.CONFIG.GENERAL.effectBlessingDelay == 0)
			{
				player.heal(Gobber2.CONFIG.GENERAL.effectBlessingPoints);

				if(player.getHungerManager().getFoodLevel() < 20)
					player.getHungerManager().setFoodLevel(player.getHungerManager().getFoodLevel() + Gobber2.CONFIG.GENERAL.effectBlessingFood);
			}
		}
	}
}