package com.kwpugh.gobber2.items.medallions;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.Gobber2Config;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class MedallionBreathing extends BaseMedallion
{
	public MedallionBreathing(Settings settings)
	{
		super(settings);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.while_in_inventory").formatted(Formatting.YELLOW));

		if(Gobber2.CONFIG.GENERAL.allowRingsTicksInEnderchest)
		{
			tooltip.add(Text.translatable("item.gobber2.while_in_enderchest").formatted(Formatting.AQUA));
		}
	} 
}