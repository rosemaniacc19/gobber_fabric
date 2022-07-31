package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
    Used to prevent the render of the player on Ring of Stealth
 */

@Mixin(LivingEntityRenderer.class)
abstract class LivingEntityRendererMixinStealth<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements FeatureRendererContext<T, M>
{
    public LivingEntityRendererMixinStealth(EntityRendererFactory.Context ctx, M model, float shadowRadius)
    {
        super(ctx);
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(T livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci)
    {
        if(Gobber2.CONFIG.GENERAL.enableRingStealthInvisible && livingEntity instanceof PlayerEntity player)
        {
            if(PlayerEquipUtil.hasItemInOffHand(player, ItemInit.GOBBER2_RING_STEALTH)) ci.cancel();
        }
    }
}
