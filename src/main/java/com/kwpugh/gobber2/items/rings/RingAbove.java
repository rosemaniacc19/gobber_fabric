package com.kwpugh.gobber2.items.rings;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.List;

public class RingAbove extends BaseRing
{
	public RingAbove(Item.Settings settings)
	{
		super(settings);
	}
	
	static int cooldown = Gobber2.CONFIG.GENERAL.ringAboveCooldown;
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		int bottom = world.getBottomY(); //new
		int top = world.getTopY(); //new

		RegistryKey<World> registryKey = world.getRegistryKey();
		ItemStack stack = player.getStackInHand(hand);

		if (!world.isClient && !(registryKey == World.OVERWORLD))
		{
			player.sendMessage((Text.translatable("item.gobber2.gobber2_ring_above.tip5")), true);   // not in this world
			return TypedActionResult.success(stack);
		}

		if (!world.isClient && (registryKey == World.OVERWORLD))
		{
			ItemStack stack2 = player.getMainHandStack();	
			boolean hasQuickUse = stack2.getEnchantments().toString().contains("quickuse");
			
			// Teleports downward while sneak + right-click
			if(player.isSneaking())  
			{
				//Checking from bottom of world and working upward
				double x = player.getX();
				double y = bottom + 3;  //changed
				double z = player.getZ();
				
				Chunk chunk = world.getChunk((int) player.getX() >> 4, (int)player.getZ() >> 4);


				while (y < top -2)  // changed from 255
				{
		            y++;

		            BlockPos headPos = new BlockPos(x, y+1, z);
		            if (chunk.getBlockState(headPos).getMaterial().equals(Material.AIR))
		            {
		                BlockPos legPos = new BlockPos(x, y, z);
		                if (chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))
		                {
		                    BlockPos groundPos = new BlockPos(x, y-1, z);
		                    if (     (!chunk.getBlockState(groundPos).getBlock().equals(Blocks.LAVA)) &&
		                    		(chunk.getBlockState(groundPos).getMaterial().equals(Material.STONE) ||
		                    		chunk.getBlockState(groundPos).getBlock().equals(Blocks.STONE) ||
		                    		!chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))   )
		                    {
		                    	player.stopRiding();
		                    	player.requestTeleport(x, y, z);
		    	           		player.fallDistance = 0.0F;

								world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

								if(!hasQuickUse)
		    	           		{
		    	           			player.getItemCooldownManager().set(this, cooldown);
		    	           		}
		    	           		
		    	           		return TypedActionResult.success(stack);                     
		                    }
		                }
		            }
		        }				
			}
			else  // teleport upward with right-click
			{	
				//Checking from top of world downward 
				double x = player.getX();
				double y = top -2;  // changed
				double z = player.getZ();
				
				Chunk chunk = world.getChunk((int) player.getX() >> 4, (int)player.getZ() >> 4);

				while (y > bottom + 3) // changed
				{
		            y--;

		            BlockPos groundPos = new BlockPos(x, y-2, z);
		            if (!chunk.getBlockState(groundPos).getMaterial().equals(Material.AIR) &&
		            		!chunk.getBlockState(groundPos).getBlock().equals(Blocks.LAVA))
		            {
		                BlockPos legPos = new BlockPos(x, y-1, z);
		                if (chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))
		                {
		                    BlockPos headPos = new BlockPos(x, y, z);
		                    if (chunk.getBlockState(headPos).getMaterial().equals(Material.AIR))
		                    {	                    	
		                    	player.stopRiding();
		                    	player.requestTeleport(x, y, z);
		    	           		player.fallDistance = 0.0F;

								world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

								if(!hasQuickUse)
		    	           		{
		    	           			player.getItemCooldownManager().set(this, cooldown);
		    	           		}
		    	           		
		    	           		return TypedActionResult.success(stack);                       
		                    }
		                }
		            }
		        }		
			}
		}
		
		return TypedActionResult.success(stack); 
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) 
	{
	    tooltip.add(Text.translatable("item.gobber2.gobber2_ring_above.tip1").formatted(Formatting.GREEN));
	    tooltip.add(Text.translatable("item.gobber2.gobber2_ring_above.tip2").formatted(Formatting.GREEN));
	    tooltip.add(Text.translatable("item.gobber2.gobber2_ring_above.tip3").formatted(Formatting.YELLOW));
	    tooltip.add(Text.translatable("item.gobber2.gobber2_ring_above.tip4").formatted(Formatting.YELLOW));
	}   
}