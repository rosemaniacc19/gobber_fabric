package com.kwpugh.gobber2.items.staffs;

import com.kwpugh.gobber2.util.EnsnarementUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class StaffEnsnarement extends BaseStaff
{
	public StaffEnsnarement(Settings settings)
	{
		super(settings);
	}

    // Right-click on block, if staff has stored entity set it's position, spawn it in, and remove tags on staff
    @SuppressWarnings("resource")
	public ActionResult useOnBlock(ItemUsageContext context)
    {
    	ItemStack stack = context.getStack();
    	if(!(context.getWorld() instanceof ServerWorld)) return ActionResult.SUCCESS;;
    	if(!context.getWorld().isClient && stack.hasNbt() && stack.getNbt().contains("captured_entity"))
    	{
			EnsnarementUtil.respawnEntity(context, stack);

        	return ActionResult.SUCCESS;
        }

        return ActionResult.SUCCESS;
    }

    @Override
	public boolean hasGlint(ItemStack stack)
	{
		return stack.hasNbt() && !stack.getOrCreateSubNbt("captured_entity").isEmpty();
	}

    @Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_staff_ensnarement.tip1").formatted(Formatting.GREEN));

		if (stack.hasNbt() && !stack.getOrCreateSubNbt("captured_entity").isEmpty())
		{
			tooltip.add((Text.translatable("item.gobber2.gobber2_staff_ensnarement.tip3", stack.getNbt().getString("name")).formatted(Formatting.GREEN)));
		}
	}
}