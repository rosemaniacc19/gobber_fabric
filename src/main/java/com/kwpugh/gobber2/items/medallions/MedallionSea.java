package com.kwpugh.gobber2.items.medallions;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class MedallionSea extends BaseMedallion
{	
	public MedallionSea(Settings settings)
	{
		super(settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if(entity.isSubmergedInWater() || Gobber2.CONFIG.GENERAL.medallionSeaAlwaysOn)
		{
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 8, 0, false, false);
			StatusEffectInstance effect2 = new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 8, 0, false, false);
			LivingEntity player = (LivingEntity) entity;
			{
				player.addStatusEffect(effect);
				player.addStatusEffect(effect2);
			}
		}	
	}
	  
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_medallion_sea.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.gobber2_medallion_sea.tip2").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.while_in_inventory").formatted(Formatting.YELLOW));

		if(Gobber2.CONFIG.GENERAL.allowRingsTicksInEnderchest)
		{
			tooltip.add(Text.translatable("item.gobber2.while_in_enderchest").formatted(Formatting.AQUA));
		}
	} 
}