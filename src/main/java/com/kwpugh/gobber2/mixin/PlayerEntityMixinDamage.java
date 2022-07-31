package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.ProtectionManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*
    Need for fire/lava
    and freezing protection
 */

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixinDamage extends LivingEntity
{
    public PlayerEntityMixinDamage(EntityType<? extends LivingEntity> entityType, World world)
    {
        super(entityType, world);
    }

    // Prevent freezing damage from powdered snow and other sources, damage from various fire/heat related sources, and damage from drowning
    // Works in conjunction with LivingEntityMixinBreathing and EntityMixinFireFreeze
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void gobberDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if(!ProtectionManager.canFreeze(player, source))
        {
            cir.setReturnValue(false);
        }

        if(!ProtectionManager.canBurn(player, source))
        {
            cir.setReturnValue(false);
        }

        if(!ProtectionManager.canDrown(player, source))
        {
            cir.setReturnValue(false);
        }

        if(ProtectionManager.hasCuring(player, source))
        {
            cir.setReturnValue(false);
        }

        if(ProtectionManager.isMagicProof(player, source) && Gobber2.CONFIG.GENERAL.apotropaicEventEnable)
        {
            cir.setReturnValue(false);
        }
    }
}

