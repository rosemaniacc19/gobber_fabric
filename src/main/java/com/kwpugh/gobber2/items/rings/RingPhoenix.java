package com.kwpugh.gobber2.items.rings;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class RingPhoenix extends BaseRing
{
	public RingPhoenix(Settings settings)
	{
		super(settings);
	}
	 
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_phoenix.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_phoenix.tip2").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.while_in_inventory").formatted(Formatting.YELLOW));

		if(Gobber2.CONFIG.GENERAL.allowWorkInEnderchest)
		{
			tooltip.add(Text.translatable("item.gobber2.while_in_enderchest").formatted(Formatting.AQUA));
		}
	} 
}