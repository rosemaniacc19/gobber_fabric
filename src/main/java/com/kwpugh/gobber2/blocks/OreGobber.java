package com.kwpugh.gobber2.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;

import java.util.List;

public class OreGobber extends BaseOreBlock
{
	public OreGobber(FabricBlockSettings settings) 
	{
		super(settings.requiresTool());
		this.settings.requiresTool();
	}

	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options)
	{
		tooltip.add(Text.translatable("item.gobber2.ore_overworld.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.gobber2_ore.tip2").formatted(Formatting.YELLOW));
	}
}
