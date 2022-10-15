package com.kwpugh.gobber2.items.staffs;

import com.kwpugh.gobber2.util.GobberForceManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class StaffChanneling extends BaseStaff
{
	PlayerEntity holdingPlayer;

	public StaffChanneling(Settings properties)
	{
		super(properties);
	}
	
    @Override
    public boolean hasRecipeRemainder()
    {
        return true;
    }

    @Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if(entity instanceof PlayerEntity player)
		{
			holdingPlayer = player;
		}
	}

    @Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);
		ItemStack mainHandStack = player.getMainHandStack();
		ItemStack offHandStack = player.getOffHandStack();

		if(!player.world.isClient)
		{
			if(player.isSneaking())
			{
				GobberForceManager.clearGobberForce(player);
				player.sendMessage((Text.translatable("GobberForce cleared!")), true);
			}
			else
			{
				player.sendMessage((Text.translatable("item.gobber2.gobber2_staff_channeling.tip4", GobberForceManager.getGobberForce(player))), true);
			}
		}

		return TypedActionResult.success(stack);
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);

		tooltip.add((Text.translatable("item.gobber2.gobber2_staff_channeling.tip1").formatted(Formatting.GREEN)));
		tooltip.add((Text.translatable("item.gobber2.gobber2_staff_channeling.tip2").formatted(Formatting.YELLOW)));
		tooltip.add((Text.translatable("item.gobber2.gobber2_staff_channeling.tip3").formatted(Formatting.BLUE)));
	}
}