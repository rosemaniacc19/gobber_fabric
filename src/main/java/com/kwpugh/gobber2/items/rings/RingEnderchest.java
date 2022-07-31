package com.kwpugh.gobber2.items.rings;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class RingEnderchest extends BaseRing
{
	public RingEnderchest(Item.Settings settings)
	{
		super(settings);
	}
	
	public static final Text TITLE = Text.translatable("item.gobber2.gobber2_ring_enderchest.tip2");
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemStack = player.getStackInHand(hand);
		player.setCurrentHand(hand);
		EnderChestInventory enderChest = player.getEnderChestInventory();
		
		if (enderChest != null)
		{
			if (!world.isClient)
			{
	            player.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
	               return GenericContainerScreenHandler.createGeneric9x3(i, playerInventory, enderChest);
	            }, TITLE));
			}
		}

		return TypedActionResult.success(itemStack);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
	    tooltip.add(Text.translatable("item.gobber2.gobber2_ring_enderchest.tip1").formatted(Formatting.GREEN));
	}   
}