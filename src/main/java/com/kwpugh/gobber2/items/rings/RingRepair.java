package com.kwpugh.gobber2.items.rings;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.TagInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class RingRepair extends BaseRing
{	
	public RingRepair(Settings settings)
	{
		super(settings);
	}

	static int repairInterval = Gobber2.CONFIG.GENERAL.ringRepairInterval;

	public static void repairItems(World world, PlayerEntity player)
	{
		if(!world.isClient)
		{
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

			if(serverPlayer.age % repairInterval == 0) // create an delay interval
			{
				for(int i = 0; i < serverPlayer.getInventory().size(); i++)
				{
					ItemStack stack2 = serverPlayer.getInventory().getStack(i);

					if(!stack2.isIn(TagInit.RING_REPAIR_BLACKLIST))
					{
						if(!stack2.isEmpty())
						{
							if (!(stack2 == serverPlayer.getMainHandStack()))
							{
								if (stack2.isDamaged())
								{
									stack2.setDamage(stack2.getDamage() - 1);
									break;
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_repair.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_repair.tip2").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.while_in_inventory").formatted(Formatting.YELLOW));

		if(Gobber2.CONFIG.GENERAL.allowRingsTicksInEnderchest)
		{
			tooltip.add(Text.translatable("item.gobber2.while_in_enderchest").formatted(Formatting.AQUA));
		}
	} 
}