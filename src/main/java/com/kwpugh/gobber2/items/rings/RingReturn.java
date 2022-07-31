package com.kwpugh.gobber2.items.rings;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class RingReturn extends BaseRing
{
	public RingReturn(Item.Settings settings)
	{
		super(settings);
	}
		
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);

		if (world.isClient) return TypedActionResult.success(stack);

		if (!player.isSneaking())  // RETURN PLAYER TO BED
		{
			ServerWorld serverWorld = ((ServerWorld) world).getServer().getWorld(World.OVERWORLD);
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

			if(serverPlayer.getSpawnPointPosition() != null) //player bed location not null
			{
				BlockPos bedLoc = serverPlayer.getSpawnPointPosition(); //get player bed position
				serverPlayer.stopRiding();
				serverPlayer.teleport(serverWorld, bedLoc.getX() + 0.5F, bedLoc.getY(), bedLoc.getZ() + 0.5F, serverPlayer.getYaw(), serverPlayer.getPitch());
				world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
				player.sendMessage((Text.translatable("item.gobber2.gobber2_ring_return.tip4")), true);   //Welcome Home!

				TypedActionResult.success(stack);
			}
			else
			{
				player.sendMessage((Text.translatable("item.gobber2.gobber2_ring_return.tip5")), true);  //Set a bed spawn first!

				TypedActionResult.success(stack);
			}
		}
		
		return TypedActionResult.success(stack);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) 
	{
	    tooltip.add(Text.translatable("item.gobber2.gobber2_ring_return.tip1").formatted(Formatting.GREEN));
	    tooltip.add(Text.translatable("item.gobber2.gobber2_ring_return.tip2").formatted(Formatting.YELLOW));
	}   
}