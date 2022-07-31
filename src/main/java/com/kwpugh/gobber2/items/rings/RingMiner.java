package com.kwpugh.gobber2.items.rings;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.MinerBlockTest;
import com.kwpugh.pugh_tools.util.MinerBlock;
import com.kwpugh.pugh_tools.util.MinerBlockManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RingMiner extends BaseRing
{
	static int minerCooldown = Gobber2.CONFIG.GENERAL.ringMinerCooldown;
	static int minerRange = Gobber2.CONFIG.GENERAL.ringMinerRange;
	static int minerVertical = Gobber2.CONFIG.GENERAL.ringMinerVerticalRange;
	static boolean centralizedBreaking = Gobber2.CONFIG.GENERAL.ringMinerCentralizedBreaking;
	static boolean minerDelayBreak = Gobber2.CONFIG.GENERAL.ringMinerCentralizedBreakingDelay;
	static boolean minerInstantBreak = Gobber2.CONFIG.GENERAL.ringMinerInstantBreak;

	static List<BlockPos> posList = new ArrayList<BlockPos>();
	BlockState state;
	Block block;
	BlockPos pos;
	static BlockPos breakPos;
	static boolean dropBlocks;
	boolean hasQuickUse;

	public RingMiner(Settings settings)
	{
		super(settings);
	}

	//  Called from PlayerEntity.tick()
	public static void processList(World world, PlayerEntity player)
	{
		// By default blocks are added to a List and broken over time with PlayerEntity#tick() tick
		if (!world.isClient)
		{
			if(!posList.isEmpty())
			{
				dropBlocks = false;
				player.sendMessage((Text.translatable("Breaking blocks...")), true);

				if(player.getOffHandStack().getItem() == Items.STICK)
				{
					dropBlocks = true;
				}

				breakPos = posList.get(0);
				world.breakBlock(breakPos, dropBlocks);
				posList.remove(0);
			}
		}
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack mainHand = player.getStackInHand(hand);
		ItemStack offHand = player.getOffHandStack();

		// Sneak + right-click to scan the area around player and store in list
		if(!world.isClient && player.isSneaking())
		{
			dropBlocks = false;
			if(offHand.getItem() == Items.STICK) dropBlocks = true;

			for (int x = minerRange; x >= -minerRange; x--)
			{
				for (int y = minerVertical; y >= 0; y--)
				{
					for (int z = minerRange; z >= -minerRange; z--)
					{
						pos = player.getBlockPos().add(x, y, z);
						state = world.getBlockState(pos);
						block = state.getBlock();

						if(MinerBlockTest.canBreak(block, state))
						{
							if(minerInstantBreak) //break all blocks at once
							{
								player.sendMessage((Text.translatable("Breaking...")), true);
								world.breakBlock(pos, dropBlocks);
							}
							else if(centralizedBreaking) // breaking handled by the block manager, one per server
							{
								player.sendMessage((Text.translatable("Blocks selected, breaking scheduled")), true);
								MinerBlock minerBlock = new MinerBlock(world, block, pos, player, dropBlocks, minerDelayBreak);
								MinerBlockManager.addToList(minerBlock);
							}
							else  // default method if centralized and instant false in config
							{
								player.sendMessage((Text.translatable("Breaking...")), true);
								posList.add(pos);
							}

						}
					}
				}
			}

			// a cooldown applies, unless enchanted with QuickUse
			hasQuickUse = mainHand.getEnchantments().toString().contains("quickuse");
			if(!hasQuickUse)
			{
				player.getItemCooldownManager().set(this, minerCooldown);
			}
		}

		return TypedActionResult.success(mainHand);
	}

	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_miner.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_miner.tip2").formatted(Formatting.YELLOW));
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_miner.tip6", (minerRange*2)+1, minerVertical).formatted(Formatting.BLUE));
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_miner.tip10").formatted(Formatting.BLUE));
	}
}