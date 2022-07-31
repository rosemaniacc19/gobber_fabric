package com.kwpugh.gobber2.items.armor;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class NetherArmor extends ArmorItem
{
	public NetherArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings)
	{
		super(material, slot, settings);
	}

	boolean enablNetherFirePerk = Gobber2.CONFIG.GENERAL.enableNetherFirePerk;
	boolean enableNetherHealthPerks = Gobber2.CONFIG.GENERAL.enableNetherHealthPerks;
	int healingPointsNetherArmor = Gobber2.CONFIG.GENERAL.healingPointsNetherArmor;

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{				
		if(!world.isClient && entity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entity;
			if(PlayerEquipUtil.isWearingNetherArmor(player))
	      	{
	    		if(player.age % 180 == 0)
				{
					if(enableNetherHealthPerks)
					{
						PlayerSpecialAbilities.giveLesserAbsorption(player);
						PlayerSpecialAbilities.giveSaturationEffect(player);
						PlayerSpecialAbilities.giveHealing(player, healingPointsNetherArmor);
					}
				}
	      	}
		}
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		if(enablNetherFirePerk || enableNetherHealthPerks)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_armor_nether.tip1").formatted(Formatting.GREEN));
		}

		if(enablNetherFirePerk)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_armor_nether.tip2").formatted(Formatting.GREEN));
		}

		if(enableNetherHealthPerks)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_armor_nether.tip3").formatted(Formatting.GREEN));
		}
	}
}