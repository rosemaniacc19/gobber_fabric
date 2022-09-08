package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.util.ClimbingUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinClimbing extends Entity
{
    public LivingEntityMixinClimbing(EntityType<?> type, World world)
    {
        super(type, world);
    }

    @Inject(at = @At("RETURN"), method = "isClimbing", cancellable = true)
    public void gobberClimbing(CallbackInfoReturnable<Boolean> cir)
    {
        if(!cir.getReturnValue())
        {
            LivingEntity livingEntity = (LivingEntity) (Object) this;
            boolean value = ClimbingUtil.canClimb(world, livingEntity, livingEntity.getBlockPos());
            cir.setReturnValue(value);
        }
    }
}