package com.kwpugh.gobber2.items.rings;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class RingAttraction extends BaseRing
{
    static final String ATTRACTION_MODE = "Attraction Mode";
	
    public RingAttraction(Settings settings)
    {
        super(settings);
    }

    static int configRange = Gobber2.CONFIG.GENERAL.ringAttractionRange;
    
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
	    if(entity instanceof PlayerEntity player)
        {
            PlayerSpecialAbilities.doItemAttraction(world, player, stack);
        }
	}
	
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack magnet = player.getStackInHand(hand);
        
        if (!world.isClient && !player.isSneaking())
        {
        	toggleMode(magnet);
        }
        
        return new TypedActionResult<>(ActionResult.SUCCESS, magnet);
    }

    public static boolean isActive(ItemStack magnet)
    {
        return getMagnetMode(magnet).getBoolean();
    }

    private void setMagnetMode(ItemStack magnet, MagnetMode mode)
    {
        checkTag(magnet);
        magnet.getNbt().putBoolean(ATTRACTION_MODE, mode.getBoolean());
    }

    private static MagnetMode getMagnetMode(ItemStack magnet)
    {
        if (!magnet.isEmpty())
        {
            checkTag(magnet);
            
            return magnet.getNbt().getBoolean(ATTRACTION_MODE) ? MagnetMode.ACTIVE : MagnetMode.INACTIVE;
        }
        return MagnetMode.INACTIVE;
    }

    private void toggleMode(ItemStack magnet)
    {
        MagnetMode currentMode = getMagnetMode(magnet);
        
        if (currentMode.getBoolean())
        {
            setMagnetMode(magnet, MagnetMode.INACTIVE);
            
            return;
        }
        
        setMagnetMode(magnet, MagnetMode.ACTIVE);
    }

    private static void checkTag(ItemStack magnet)
    {
        if (!magnet.isEmpty())
        {
            if (!magnet.hasNbt())
            {
                magnet.setNbt(new NbtCompound());
            }
            NbtCompound nbt = magnet.getNbt();
            
            if (!nbt.contains(ATTRACTION_MODE))
            {
                nbt.putBoolean(ATTRACTION_MODE, false);
            }
        }
    }

    public enum MagnetMode
    {
        ACTIVE(true), INACTIVE(false);

        final boolean state;

        MagnetMode(boolean state)
        {
            this.state = state;
        }

        public boolean getBoolean()
        {
            return state;
        }
    }
    
    public boolean hasGlint(ItemStack magnet)
    {
    	return isActive(magnet);
    }
    
    @Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_attraction.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.right_click").formatted(Formatting.YELLOW));
		tooltip.add(Text.translatable("item.gobber2.gobber2_ring_attraction.tip3", configRange).formatted(Formatting.RED));
	} 
}