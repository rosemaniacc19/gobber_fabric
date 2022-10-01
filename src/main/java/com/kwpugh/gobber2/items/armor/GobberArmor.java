package com.kwpugh.gobber2.items.armor;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;
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

public class GobberArmor extends ArmorItem
{
	public GobberArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings)
	{
		super(material, slot, settings);
	}

	boolean enableGobberBreathing  = Gobber2.CONFIG.GENERAL.enableGobberBreathing;
	boolean enableGobberHealthPerks = Gobber2.CONFIG.GENERAL.enableGobberHealthPerks;
	int healingPointsGobber = Gobber2.CONFIG.GENERAL.healingPointsGobberArmor;

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if(!world.isClient && (entity instanceof PlayerEntity player))
		{
			if(PlayerEquipUtil.isWearingGobberArmor(player))
			{
				if(player.age % 180 == 0)
				{
					if(enableGobberHealthPerks)
					{
						PlayerSpecialAbilities.giveSaturationEffect(player);
						PlayerSpecialAbilities.giveLesserAbsorption(player);
						PlayerSpecialAbilities.giveHealing(player, healingPointsGobber);
					}
				}
			}
		}
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		if(enableGobberBreathing || enableGobberHealthPerks)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_armor.tip1").formatted(Formatting.GREEN));
		}

		if(enableGobberBreathing)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_armor.tip2").formatted(Formatting.GREEN));
		}

		if(enableGobberHealthPerks)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_armor.tip3").formatted(Formatting.GREEN));
		}
	}	
}