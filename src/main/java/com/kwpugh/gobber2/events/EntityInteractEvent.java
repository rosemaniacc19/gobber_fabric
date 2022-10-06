package com.kwpugh.gobber2.events;

import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.EnsnarementUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class EntityInteractEvent
{
    public static ActionResult onUseEntity(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult)
    {
        if(entity instanceof LivingEntity livingEntity)
        {
            ItemStack stack = player.getStackInHand(hand);

            if(stack.getItem() == ItemInit.GOBBER2_STAFF_ENSNAREMENT)
            {
                EnsnarementUtil.captureMobs(stack, player, livingEntity, hand);
                player.swingHand(hand);

                return ActionResult.SUCCESS;
            }

            if(stack.getItem() == ItemInit.GOBBER2_STAFF_HOSTILE_ENSNAREMENT)
            {
                EnsnarementUtil.captureHostileMobs(stack, player, livingEntity, hand);
                player.swingHand(hand);

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }
}
