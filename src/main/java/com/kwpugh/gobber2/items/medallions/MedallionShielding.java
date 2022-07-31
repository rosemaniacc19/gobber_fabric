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

public class MedallionShielding extends BaseMedallion
{
    static int shieldingHoriz = Gobber2.CONFIG.GENERAL.medallionShieldingHorizRange;
    static int shieldingVert = Gobber2.CONFIG.GENERAL.medallionShieldingVertRange;

    public MedallionShielding(Settings settings)
    {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
    {
        if(entity instanceof PlayerEntity player)
        {
            PlayerSpecialAbilities.giveProjectileShield(world, player, shieldingVert, shieldingHoriz);
        }
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip.add(Text.translatable("item.gobber2.gobber2_medallion_shielding.tip1").formatted(Formatting.GREEN));
        tooltip.add(Text.translatable("item.gobber2.while_in_inventory").formatted(Formatting.YELLOW));
    }
}