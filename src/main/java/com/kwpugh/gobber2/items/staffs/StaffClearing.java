package com.kwpugh.gobber2.items.staffs;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.TagInit;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class StaffClearing extends BaseStaff
{
	public StaffClearing(Settings settings)
	{
		super(settings);
	}

	static int horizRange = Gobber2.CONFIG.GENERAL.staffClearingHorizRange;
	static int verticalRange = Gobber2.CONFIG.GENERAL.staffClearingVertRange;
	static int cooldown = Gobber2.CONFIG.GENERAL.staffClearingCooldown;

	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);
		ItemStack stack2 = player.getMainHandStack();
		boolean hasQuickUse = stack2.getEnchantments().toString().contains("quickuse");
		boolean hasFluidMaster = stack2.getEnchantments().toString().contains("fluid_master");

		if(!world.isClient)
		{
			Block block;
			BlockState state;
			
			for (int x = -horizRange; x <= horizRange; x++)
			{
				for (int y = -verticalRange; y <= verticalRange; y++)
				{
					for (int z = -horizRange; z <= horizRange; z++)
					{
						BlockPos pos = player.getBlockPos().add(x, y, z);

						state = world.getBlockState(pos);
						block = state.getBlock();

						// Clear fluid blocks within range
						if (hasFluidMaster && state.getBlock() instanceof FluidBlock)
						{
							if(player.isSneaking())
							{
								// Logic taken from vanilla SpongeBlock
								world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);

								if(!hasQuickUse)
								{
									player.getItemCooldownManager().set(this, cooldown);
								}

								player.setCurrentHand(hand);
							}
						}

						// Clear vegetation within range
						if (state.isIn(BlockTags.FLOWERS) ||
								block == Blocks.GRASS ||
								block == Blocks.DEAD_BUSH || 
								block == Blocks.TALL_GRASS || 
								block == Blocks.FERN || 
								block == Blocks.LARGE_FERN ||
								state.isIn(TagInit.FLOWERS) ||
								state.isIn(TagInit.BUSHES) ||
								state.isIn(TagInit.GRASS) ||
								block instanceof MushroomBlock || 
								block instanceof FlowerBlock ||
								block instanceof TallFlowerBlock)
						{		
							if(!player.isSneaking())
							{
								world.breakBlock(pos, true);	
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
		tooltip.add(Text.translatable("item.gobber2.gobber2_staff_clearing.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.gobber2_staff_clearing.tip2", horizRange*2, verticalRange*2, horizRange*2).formatted(Formatting.BLUE));
	}
}