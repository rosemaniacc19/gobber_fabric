package com.kwpugh.gobber2.items.medallions;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class MedallionHealing3 extends BaseMedallion
{
    public MedallionHealing3(Settings settings)
    {
        super(settings);
    }

    static int amount = Gobber2.CONFIG.GENERAL.medallionGreaterHealingAmount;

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
    {
        PlayerEntity player = (PlayerEntity) entity;

        PlayerSpecialAbilities.giveHealing(player, amount);
        PlayerSpecialAbilities.giveSaturationEffect(player);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip.add(Text.translatable("item.gobber2.while_in_inventory").formatted(Formatting.YELLOW));

        if(Gobber2.CONFIG.GENERAL.allowWorkInEnderchest)
        {
            tooltip.add(Text.translatable("item.gobber2.while_in_enderchest").formatted(Formatting.AQUA));
        }
    }
}