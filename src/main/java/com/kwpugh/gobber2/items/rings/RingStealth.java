package com.kwpugh.gobber2.items.rings;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

// This item relies on a mixin MobEntityMixinSetTarget
public class RingStealth extends BaseRing
{	
	public RingStealth(Settings settings)
	{
		super(settings);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_stealth.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.while_in_off_hand").formatted(Formatting.YELLOW));
	} 
}