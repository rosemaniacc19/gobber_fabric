package com.kwpugh.gobber2.items.staffs;

import com.kwpugh.gobber2.Gobber2;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class StaffSniper extends BaseStaff
{
	public StaffSniper(Item.Settings settings)
	{
		super(settings);
	}
	
	static int cooldown = Gobber2.CONFIG.GENERAL.staffSniperCooldown;
	static boolean quickUseAllowed = Gobber2.CONFIG.GENERAL.staffSniperEnableQuickUse;

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);
		ItemStack stack2 = player.getMainHandStack();
		boolean hasQuickUse = stack2.getEnchantments().toString().contains("quickuse");
		
        if (!world.isClient)
        {
			if(!hasQuickUse && quickUseAllowed)
			{
				player.getItemCooldownManager().set(this, cooldown);
			}

            ArrowItem itemarrow = (ArrowItem)Items.ARROW;
            PersistentProjectileEntity persistentProjectileEntity = itemarrow.createArrow(world, new ItemStack(Items.ARROW), player);
            float arrowVelocity = 60.0F;
            persistentProjectileEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, arrowVelocity, 0.0F);
            persistentProjectileEntity.setDamage(1);
            world.spawnEntity(persistentProjectileEntity);
            persistentProjectileEntity.pickupType = PickupPermission.DISALLOWED;
        }
		
		return TypedActionResult.success(stack);
	}
	
	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) 
	{
	    tooltip.add(Text.translatable("item.gobber2.gobber2_staff_sniper.tip1").formatted(Formatting.GREEN));

	    if(quickUseAllowed)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_staff_sniper.tip1").formatted(Formatting.YELLOW));
		}
	}   
}