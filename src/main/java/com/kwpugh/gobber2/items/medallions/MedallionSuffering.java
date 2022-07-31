package com.kwpugh.gobber2.items.medallions;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class MedallionSuffering extends BaseMedallion
{
    public MedallionSuffering(Settings settings)
    {
        super(settings);
    }

    static int rangeSuffering = Gobber2.CONFIG.GENERAL.medallionSufferingRange;
    static int damageSuffering = Gobber2.CONFIG.GENERAL.medallionSufferingDamage;

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
    {
        if(world.isClient) return;

        PlayerEntity player = (PlayerEntity)entity;
        BlockPos pos = player.getBlockPos();
        ItemStack equippedOff = player.getOffHandStack();

        if(stack == equippedOff)
        {
            PlayerSpecialAbilities.inflictSuffering(world, pos, rangeSuffering, damageSuffering);
        }
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip.add(Text.translatable("item.gobber2.gobber2_medallion_suffering.tip1").formatted(Formatting.GREEN));
        tooltip.add(Text.translatable("item.gobber2.while_in_off_hand").formatted(Formatting.YELLOW));
    }
}