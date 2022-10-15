package com.kwpugh.gobber2.items.staffs;

import com.kwpugh.gobber2.init.EnchantmentInit;
import com.kwpugh.pugh_lib.api.CustomRecipeRemainder;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class StaffTransformation extends BaseStaff implements CustomRecipeRemainder
{
	Random random = new Random();

	public StaffTransformation(Settings properties)
	{
		super(properties);
	}
	
    @Override
    public boolean hasRecipeRemainder()
    {
        return true;
    }

    @Override
	public ItemStack getRecipeRemainder(ItemStack stackIn)
    {   	
    	ItemStack stack = stackIn.copy();
    	stack.setDamage(stack.getDamage() + 1);

    	if(stack.getDamage() >= stack.getMaxDamage())
    	{
    		stack.decrement(1);
    	}

        return stack;
    }

    @Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);
		ItemStack mainHandStack = player.getMainHandStack();
		ItemStack offHandStack = player.getOffHandStack();
		boolean bl = offHandStack.isOf(Items.BOOK);

		if(!world.isClient && bl)
		{
			if(mainHandStack.getDamage() < mainHandStack.getMaxDamage() - 1)
			{
				EnchantmentLevelEntry entry;
				double r = random.nextDouble();  //generate a random double between 0 and 1
				if(r > .90)
				{
					entry = new EnchantmentLevelEntry(EnchantmentInit.QUICKUSE, 1);
				}
				else if(r > .80)
				{
					entry = new EnchantmentLevelEntry(EnchantmentInit.QUIETFEET, 1);
				}
				else if(r > .60)
				{
					entry = new EnchantmentLevelEntry(EnchantmentInit.FASTEROBSIDIAN, 1);
				}
				else if(r > .40)
				{
					entry = new EnchantmentLevelEntry(EnchantmentInit.SOLIDFOOTING, 1);
				}
				else
				{
					entry = new EnchantmentLevelEntry(EnchantmentInit.FLUID_MASTER, 1);
				}
				ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
				EnchantedBookItem.addEnchantment(book, entry);
				offHandStack.decrement(1);
				world.spawnEntity(new ItemEntity(world, player.getX(), player.getY(),player.getZ(),book));

				if(mainHandStack.getDamage() + 100 >= mainHandStack.getMaxDamage())
				{
					mainHandStack.setDamage(mainHandStack.getMaxDamage() -  1);
				}
				else
				{
					mainHandStack.setDamage(mainHandStack.getDamage() + 100);
				}
			}
		}

		return TypedActionResult.success(stack);
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);

		tooltip.add((Text.translatable("item.gobber2.gobber2_staff_transformation.tip1").formatted(Formatting.GREEN)));
		tooltip.add((Text.translatable("item.gobber2.gobber2_staff_transformation.tip2").formatted(Formatting.YELLOW)));
	}
}