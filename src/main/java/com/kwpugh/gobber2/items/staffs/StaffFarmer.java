package com.kwpugh.gobber2.items.staffs;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.GrowingUtil;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class StaffFarmer extends BaseStaff
{
	public StaffFarmer(Settings settings)
	{
		super(settings);
	}

	static int range = Gobber2.CONFIG.GENERAL.staffFarmerHorizRange;
	static int rangeVertical = Gobber2.CONFIG.GENERAL.staffFarmerVertRange;
	static int interval = Gobber2.CONFIG.GENERAL.staffFarmerInterval;
	static int intervalCactus = Gobber2.CONFIG.GENERAL.staffFarmerIntervalCactus;

	static boolean replant = Gobber2.CONFIG.GENERAL.staffFarmerReplant;
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
    	if(!(entity instanceof PlayerEntity) || world.isClient)
        {
            return;
        }

    	PlayerEntity player = (PlayerEntity)entity;
        ItemStack equippedMain = player.getMainHandStack();
        
        if(stack == equippedMain)
        {
        	if (!world.isClient)
        	{
				GrowingUtil.growCrops(world, player, range, rangeVertical, interval, intervalCactus);
        	}	
        }
	}
	
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);
		boolean maxAge;
		
		if(!world.isClient)
		{
			BlockPos playerPos = new BlockPos(player.getPos());
    		
			for (BlockPos targetPos : BlockPos.iterateOutwards(playerPos, range, range, range))
    		{
				Block block = world.getBlockState(targetPos).getBlock();
				BlockState state = world.getBlockState(targetPos);
				BlockState defaultState = block.getDefaultState();
				
				//These plants are simply broken with drops
				if(block instanceof CocoaBlock ||
						block instanceof MelonBlock ||
						block instanceof PumpkinBlock ||
						block instanceof CactusBlock ||
						block instanceof SugarCaneBlock ||
						block instanceof NetherWartBlock ||
						block instanceof BambooBlock)
				{
					world.breakBlock(targetPos, true);
				}
				
				//Crops are harvested, if at max age, and re-planted
				if(block instanceof CropBlock)
				{
					maxAge = state.get(((CropBlock) block).getAgeProperty()) >= ((CropBlock) block).getMaxAge();
					
					if(maxAge)
					{
						world.breakBlock(targetPos, true);
						
						if(replant)
						{
							world.setBlockState(targetPos, defaultState);	
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
		tooltip.add(Text.translatable("item.gobber2.gobber2_staff_farmer.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.gobber2_staff_farmer.tip2").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.gobber2_staff_farmer.tip3", range).formatted(Formatting.YELLOW));
	} 
}