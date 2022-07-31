package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
    Used to prevent the render of the player on Ring of Stealth
 */

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixinStealth
{
    @Inject(method = "renderHitbox", at = @At("HEAD"), cancellable = true)
    private static void renderHitbox(MatrixStack matrices, VertexConsumer vertices, Entity entity, float tickDelta, CallbackInfo ci)
    {
        if(entity instanceof PlayerEntity player)
        {
            if(PlayerEquipUtil.hasItemInOffHand(player, ItemInit.GOBBER2_RING_STEALTH)) ci.cancel();
        }
    }

    @Inject(method = "renderFire", at = @At("HEAD"), cancellable = true)
    private void renderFire(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity, CallbackInfo ci)
    {
        if(Gobber2.CONFIG.GENERAL.enableRingStealthInvisible && entity instanceof PlayerEntity player)
        {
            if(PlayerEquipUtil.hasItemInOffHand(player, ItemInit.GOBBER2_RING_STEALTH)) ci.cancel();
        }
    }

    @Inject(method = "renderShadow", at = @At("HEAD"), cancellable = true)
    private static void renderShadow(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity, float opacity, float tickDelta, WorldView world, float radius, CallbackInfo ci)
    {
        if(entity instanceof PlayerEntity player)
        {
            if(PlayerEquipUtil.hasItemInOffHand(player, ItemInit.GOBBER2_RING_STEALTH)) ci.cancel();
        }
    }
}
