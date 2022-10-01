package com.kwpugh.gobber2.items.armor;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class EndArmor extends ArmorItem implements FabricElytraItem
{
	public EndArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings)
	{
		super(material, slot, settings);
	}

	boolean unbreakableEndArmor = Gobber2.CONFIG.GENERAL.unbreakableEndArmor;
	boolean enableEndBreathing =  Gobber2.CONFIG.GENERAL.enableEndBreathing;
	boolean enableEndFirePerks = Gobber2.CONFIG.GENERAL.enableEndFirePerk;
	boolean enableEndHealthPerks = Gobber2.CONFIG.GENERAL.enableEndHealthPerks;
	boolean enableEndNoFall = Gobber2.CONFIG.GENERAL.enableEndNoFallDamage;
	int healingPointsEndArmor = Gobber2.CONFIG.GENERAL.healingPointsEndArmor;
	boolean enableEndGliding  =  Gobber2.CONFIG.GENERAL.enableGlidingEndArmor;

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if(!world.isClient && entity instanceof PlayerEntity player)
		{
			if(PlayerEquipUtil.isWearingEndArmor(player))
			{
				if(enableEndHealthPerks)
				{
					if(player.age % 180 == 0)
					{
						PlayerSpecialAbilities.giveGreaterAbsorption(player);
						PlayerSpecialAbilities.giveSaturationEffect(player);
						PlayerSpecialAbilities.giveHealing(player, healingPointsEndArmor);
					}
				}

	    		if(enableEndNoFall)
				{
					player.fallDistance = 0.0F;
				}
	      	}
		}
	}
	
	@Override
	public void onCraft(ItemStack stack, World world, PlayerEntity player) 
	{
		if(unbreakableEndArmor)
		{
			stack.getOrCreateNbt().putBoolean("Unbreakable", true);
		}
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		if(enableEndGliding || enableEndNoFall || enableEndBreathing || enableEndFirePerks || enableEndHealthPerks)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_armor_end.tip1").formatted(Formatting.GREEN));
		}

		if(enableEndFirePerks)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_armor_end.tip2").formatted(Formatting.GREEN));
		}

		if(enableEndBreathing)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_armor_end.tip3").formatted(Formatting.GREEN));
		}

		if(enableEndHealthPerks)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_armor_end.tip4").formatted(Formatting.GREEN));
		}

		if(enableEndNoFall)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_armor_end.tip5").formatted(Formatting.GREEN));
		}

		if(enableEndGliding)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_armor_end.tip6").formatted(Formatting.GREEN));
		}
	}	
}