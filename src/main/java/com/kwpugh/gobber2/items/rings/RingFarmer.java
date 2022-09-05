package com.kwpugh.gobber2.items.rings;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.GrowingUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class RingFarmer extends BaseRing
{
	static int range = Gobber2.CONFIG.GENERAL.ringFarmerHorizRange;
	static int rangeVertical = Gobber2.CONFIG.GENERAL.ringFarmerVertRange;
	static int interval = Gobber2.CONFIG.GENERAL.ringFarmerInterval;
	static int intervalCactus = Gobber2.CONFIG.GENERAL.ringFarmerIntervalCactus;
	static boolean extraCrops = Gobber2.CONFIG.GENERAL.ringFarmerExtraCrops;

	public RingFarmer(Settings settings)
	{
		super(settings);
	}

	public void inventoryTick(ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {      
    	if(!(entity instanceof PlayerEntity player) || world.isClient) return;

		ItemStack equippedMain = player.getMainHandStack();
        
        if(stack == equippedMain)
        {
        	GrowingUtil.growCrops(world, player, range, rangeVertical, interval, intervalCactus, extraCrops);
        }
    }
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_farmer.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.while_in_main hand").formatted(Formatting.YELLOW));
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_farmer.tip2", range).formatted(Formatting.YELLOW));
	} 
}