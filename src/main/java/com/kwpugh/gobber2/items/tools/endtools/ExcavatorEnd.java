package com.kwpugh.gobber2.items.tools.endtools;

import com.google.common.collect.Sets;
import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.pugh_tools.Tools.AreaUtil;
import com.kwpugh.pugh_tools.Tools.Excavator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Set;

public class ExcavatorEnd extends Excavator
{
    private static final Set<Block> EFFECTIVE_BLOCKS;

    public ExcavatorEnd(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings)
    {
        super(material, attackDamage, attackSpeed, Gobber2.CONFIG.GENERAL.enableFullDamage, settings);
    }

    int radius = 1;
    String radiusText = "3x3";
    boolean enable5x5 = Gobber2.CONFIG.GENERAL.enableEndExcavator5x5;
    static boolean unbreakable = Gobber2.CONFIG.GENERAL.unbreakableEndTools;

    // Used to add tag when pulled from creative tab
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
    {
        NbtCompound tag = stack.getNbt();

        if(!tag.contains("radius"))
        {
            if(unbreakable)
            {
                stack.getOrCreateNbt().putBoolean("Unbreakable", true);
            }

            stack.getOrCreateNbt().putInt("radius", 1);
            stack.getOrCreateNbt().putString("radiusText", "3x3");
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemStack = user.getStackInHand(hand);

        if(enable5x5)
        {
            if (!world.isClient && user.isSneaking())
            {
                // Get tags for change mode checking
                NbtCompound subTags = itemStack.getNbt();
                radius = subTags.getInt("radius");

                if(radius == 1)
                {
                    radius = radius + 1;
                    radiusText = "5x5";
                }
                else
                {
                    radius = radius - 1;
                    radiusText = "3x3";
                }
                user.sendMessage((Text.translatable("Changed to: " + radiusText)), true);

                // Write new values back to tags
                subTags.putInt("radius", radius);
                subTags.putString("radiusText", radiusText);
                itemStack.setNbt(subTags);

                return TypedActionResult.success(itemStack);
            }
        }

        return TypedActionResult.success(itemStack);
    }

    @Override
    public boolean isSuitableFor(BlockState state)
    {
        return EFFECTIVE_BLOCKS.contains(state.getBlock());
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity playerIn)
    {
        ItemStack stack = playerIn.getMainHandStack();
        NbtCompound tag = stack.getNbt();
        radius = tag.getInt("radius");
        int materialMiningLevel = this.getMaterial().getMiningLevel();

        if(isSuitableFor(state))
        {
            if(!playerIn.isSneaking() && playerIn.getMainHandStack().isSuitableFor(world.getBlockState(pos)))
            {
                AreaUtil.attemptBreakNeighbors(world, playerIn, radius, "excavator", false, materialMiningLevel);
            }
        }


        return true;
    }

    static
    {
        EFFECTIVE_BLOCKS = Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.DIRT_PATH, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER, Blocks.SOUL_SOIL);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player)
    {
        stack.getOrCreateNbt().putInt("radius", 1);
        stack.getOrCreateNbt().putString("radiusText", "3x3");

        if(world.isClient) return;

        if(unbreakable)
        {
            stack.getOrCreateNbt().putBoolean("Unbreakable", true);
        }
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        // Get tag values for display in tooltips
        NbtCompound tag = itemStack.getNbt();
        radiusText = tag.getString("radiusText");

        tooltip.add(Text.translatable("item.gobber2.gobber2_excavator.tip1").formatted(Formatting.GREEN));

        if(enable5x5)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_hammer.tip3").formatted(Formatting.YELLOW));
            tooltip.add(Text.translatable("item.gobber2.gobber2_hammer.tip4").formatted(Formatting.YELLOW));
        }

        tooltip.add(Text.translatable("item.gobber2.gobber2_hammer.tip2", radiusText).formatted(Formatting.RED));
    }
}