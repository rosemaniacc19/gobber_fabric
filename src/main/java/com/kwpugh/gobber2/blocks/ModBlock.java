package com.kwpugh.gobber2.blocks;

import com.kwpugh.gobber2.Gobber2;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.List;

public class ModBlock extends Block
{
	private static int blastRes = Gobber2.CONFIG.ORES.blockBlastResistance;

	public ModBlock(Settings settings)
	{
		super(settings);
	}

    @Override
	public float getBlastResistance()
	{
		return Gobber2.CONFIG.ORES.blockBlastResistance;
	}

	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options)
	{
		tooltip.add(Text.translatable("item.gobber2.blast_res.tip1", blastRes).formatted(Formatting.YELLOW));
	}
}
