package com.kwpugh.gobber2.items.medallions;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class MedallionExp extends BaseMedallion
{
	public MedallionExp(Settings settings)
	{
		super(settings);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_medallion_exp.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.while_in_off_hand").formatted(Formatting.YELLOW));

		if(Gobber2.CONFIG.GENERAL.allowRingsTicksInEnderchest)
		{
			tooltip.add(Text.translatable("item.gobber2.while_in_enderchest").formatted(Formatting.AQUA));
		}
	} 
}