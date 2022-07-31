package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import com.kwpugh.gobber2.util.ProtectionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*
    Need for Armor water breathing
 */

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinBreathing extends Entity
{
    public LivingEntityMixinBreathing(EntityType<?> type, World world)
    {
        super(type, world);
    }

    @Inject(method = "canBreatheInWater", at = @At("HEAD"), cancellable = true)
    public void gobberCanBreatheInWater(CallbackInfoReturnable<Boolean> cir)
    {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if(ProtectionManager.canBreathUnderwater(livingEntity))
        {
            cir.setReturnValue(true);
        }
    }
}