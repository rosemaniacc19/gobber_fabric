package com.kwpugh.gobber2.items.tools.endtools;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.pugh_tools.Tools.Bow;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BowEnd extends Bow
{
    public static float speed = Gobber2.CONFIG.GENERAL.projectileSpeed;
    public static float roll = Gobber2.CONFIG.GENERAL.projectileRoll;
    public static float divergence = Gobber2.CONFIG.GENERAL.projectileDivergence;
    public static double extraDamage = Gobber2.CONFIG.GENERAL.projectileExtraDamage;
    public static double powerBonus = Gobber2.CONFIG.GENERAL.projectilePowerDamageBonus;
    public static float zoomMultiplier = Gobber2.CONFIG.GENERAL.bowZoomMultiplier;
    public static int flameBurnSecond = Gobber2.CONFIG.GENERAL.projectileFlameBurnSeconds;
    public static int dropRange = Gobber2.CONFIG.GENERAL.projectileDropRange;

    static boolean unbreakable = Gobber2.CONFIG.GENERAL.unbreakableEndTools;

    public BowEnd(Settings settings)
    {
        super(speed, roll, divergence, extraDamage, powerBonus, zoomMultiplier, flameBurnSecond, dropRange, settings);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player)
    {
        if(world.isClient) return;

        if(unbreakable)
        {
            stack.getOrCreateNbt().putBoolean("Unbreakable", true);
        }
    }
}
