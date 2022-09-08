package com.kwpugh.gobber2.items.rings;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.api.HandRemoveHandler;
import com.kwpugh.gobber2.api.HandTickable;
import com.kwpugh.gobber2.init.BlockInit;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.List;

public class RingAirWalking extends BaseRing implements HandRemoveHandler, HandTickable
{	
	public RingAirWalking(Settings settings)
	{
		super(settings);
	}

	// Maybe simplify this part using the method in RandomRing Air Walker

	// Depends on PlayerEntityMixinHand and HandTickable interface
	@Override
	public void tickHand(ItemStack stack, PlayerEntity player)
	{
		if (PlayerEquipUtil.hasItemInMainHand(player, ItemInit.GOBBER2_RING_AIRWALKING))
		{
			player.setNoGravity(true);
		}
	}

	@Override
	// Depends on PlayerEntityMixinHand and HandTickable interface
	public void onRemoved(PlayerEntity player)
	{
		player.setNoGravity(false);
	}
	
	@Override   
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		 ItemStack wand = player.getStackInHand(hand);
		
		if(!world.isClient && Gobber2.CONFIG.GENERAL.enableAirWalkingPlaceGlass)
		{			
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
			
			ItemStack stack = serverPlayer.getMainHandStack();

			BlockPos pos = traceForBlock(serverPlayer, 2);
			Block glassBlock = BlockInit.CLEAR_GLASS;
			BlockState glassDefaultState = glassBlock.getDefaultState();	      
      
			if (world.isAir(pos) || !world.getFluidState(pos).isEmpty())
			{
				world.setBlockState(pos, glassDefaultState);
				stack.damage(1, serverPlayer, (p_220038_0_) -> {
			         p_220038_0_.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
			         });
			}	
		}

		return TypedActionResult.success(wand);
   }
	
	private static BlockPos traceForBlock(ServerPlayerEntity player, int range)
	{
	    return player.world.raycast(new RaycastContext(
	            player.getCameraPosVec(1.0f),
	            player.getCameraPosVec(1.0f).add(getLooking(player).multiply(range)),
	            RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player
	    )).getBlockPos();
	}
	
	private static Vec3d getLooking(ServerPlayerEntity player)
	{
	    float f = -MathHelper.sin(player.getYaw() * 0.017453292F) * MathHelper.cos(player.getPitch() * 0.017453292F);
	    float g = -MathHelper.sin(player.getPitch() * 0.017453292F);
	    float h = MathHelper.cos(player.getYaw() * 0.017453292F) * MathHelper.cos(player.getPitch() * 0.017453292F);

	    return new Vec3d(f,g,h);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_airwalking.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.while_in_main hand").formatted(Formatting.YELLOW));
	} 
}