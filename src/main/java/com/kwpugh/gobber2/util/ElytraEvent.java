package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.entity.player.PlayerEntity;

public class ElytraEvent
{
    public static void init()
    {
        EntityElytraEvents.CUSTOM.register((entity, tickElytra) -> {
            if(entity instanceof PlayerEntity player)
            {
                if(Gobber2.CONFIG.GENERAL.enableGlidingEndArmor && PlayerEquipUtil.isWearingEndArmor(player))
                {
                    if (tickElytra)
                    {
                        // Optionally consume some resources that are being used up in order to fly, for example damaging an item.
                        // Optionally perform other side effects of elytra flight, for example playing a sound.
                    }
                    // Allow entering/continuing elytra flight with this custom elytra
                    return true;
                }
            }
            // Condition for the custom elytra is not met: don't let players enter or continue elytra flight (unless another elytra is available).
            return false;
        });
    }
}
