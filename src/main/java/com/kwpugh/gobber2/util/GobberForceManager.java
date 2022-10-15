package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/*
    Collection of methods to
    support GobberForce
 */

public class GobberForceManager
{
    private static int gobberForce;

    // various methods trigger on every player tick
    public static void update(PlayerEntity player)
    {
        if(!player.world.isClient)
        {
            if(PlayerEquipUtil.isWearingGobberArmor(player))
            {
                // Naturally earned planck
                if(getGobberForce(player) < Integer.MAX_VALUE && player.age % Gobber2.CONFIG.GENERAL.forceNaturalRegenDelay == 0)
                {
                    addGobberForce(player, Gobber2.CONFIG.GENERAL.forceEarnedGobberArmor);
                }

                // Provide water breathing
                if(player.getAir() < 2 && getGobberForce(player) > 5)
                {
                    player.setAir(300);
                    subtractGobberForce(player, 5);

                    player.sendMessage((Text.translatable("GobberForce breathing!").formatted(Formatting.AQUA).formatted(Formatting.BOLD)), true);
                }

                // Restore saturation check
                if((player.getHungerManager().getFoodLevel() < 20) && (getGobberForce(player) > 30))
                {
                    player.getHungerManager().setFoodLevel(40);
                    subtractGobberForce(player, 20);

                    player.sendMessage((Text.translatable("GobberForce feeding!").formatted(Formatting.GREEN).formatted(Formatting.BOLD)), true);
                }

                // Restore health check
                if((player.getHealth() < 15) && (getGobberForce(player) > 50))
                {
                    player.setHealth(20.0F);
                    subtractGobberForce(player, 50);

                    player.sendMessage((Text.translatable("GobberForce healing!").formatted(Formatting.RED).formatted(Formatting.BOLD)), true);
                }

                // Give extra health check
                if((player.getHealth() == 20) && (getGobberForce(player) > 70))
                {
                    float current = player.getAbsorptionAmount();
                    if(current < 4)
                    {
                        player.setAbsorptionAmount(current + 1.0F);
                        subtractGobberForce(player, 70);
                        player.sendMessage((Text.translatable("GobberForce extra hearts!").formatted(Formatting.YELLOW).formatted(Formatting.BOLD)), true);
                    }
                }
            }
        }
    }

    public static int getGobberForce(PlayerEntity player)
    {
        return gobberForce;
    }

    public static void addGobberForce(PlayerEntity player, int amount)
    {
        if(!player.world.isClient)
        {
            if(PlayerEquipUtil.isWearingGobberArmor(player))
            {
                if(getGobberForce(player) < Integer.MAX_VALUE)
                {
                    gobberForce = gobberForce + amount;
                }
            }
        }
    }

    public static void subtractGobberForce(PlayerEntity player, int amount)
    {
        if(!player.world.isClient)
        {
            if(amount > getGobberForce(player))
            {
                gobberForce = 0;
            }
            else
            {
                gobberForce = gobberForce - amount;
            }
        }
    }

    public static void clearGobberForce(PlayerEntity player)
    {
        if(!player.world.isClient)
        {
            gobberForce = 0;
        }
    }

    public static void readNbt(NbtCompound nbt)
    {
        if(nbt.contains("gobberForce"))
        {
            gobberForce = nbt.getInt("gobberForce");
        }
    }

    public static void writeNbt(NbtCompound nbt)
    {
        nbt.putInt("gobberForce", gobberForce);
    }
}
