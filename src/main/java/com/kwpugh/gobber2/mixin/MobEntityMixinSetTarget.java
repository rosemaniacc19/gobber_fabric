package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.init.ItemInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kwpugh.gobber2.util.PlayerEquipUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

/*
    Need for the Ring of Stealth
 */

@Mixin(MobEntity.class)

public abstract class MobEntityMixinSetTarget extends Entity
{
    @Shadow
    private LivingEntity target;

    public MobEntityMixinSetTarget(EntityType<?> type, World world)
    {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "setTarget", cancellable = true)
    public void gobberSetTarget(LivingEntity target, CallbackInfo ci)
    {
        MobEntity self = (MobEntity) (Object) this;

        if(target instanceof PlayerEntity player)
        {
            if(!this.world.isClient && !player.isCreative())
            {
                if(self instanceof HostileEntity)
                {
                    if(PlayerEquipUtil.hasItemInOffHand(player, ItemInit.GOBBER2_RING_STEALTH))
                    {
                        ci.cancel();
                    }
                }

                if(self instanceof PhantomEntity)
                {
                    if(PlayerEquipUtil.isWearingGobberArmor(player) ||
                            PlayerEquipUtil.isWearingNetherArmor(player) ||
                            PlayerEquipUtil.isWearingEndArmor(player) ||
                            PlayerEquipUtil.isWearingDragonArmor(player) ||
                            PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_RING_STEALTH)
                        )
                    {
                        ci.cancel();
                    }
                }
            }
        }
    }

    @Inject(at = @At("TAIL"), method = "baseTick", cancellable = true)
    public void gobberBaseTick(CallbackInfo ci)
    {
        MobEntity self = (MobEntity) (Object) this;

        if(target instanceof PlayerEntity player)
        {
            if(!this.world.isClient && !player.isCreative())
            {
                if(self instanceof HostileEntity)
                {
                    if(PlayerEquipUtil.hasItemInOffHand(player, ItemInit.GOBBER2_RING_STEALTH))
                    {
                        this.target = null;
                    }
                }

                if(self instanceof PhantomEntity)
                {
                    if(PlayerEquipUtil.isWearingGobberArmor(player) ||
                            PlayerEquipUtil.isWearingNetherArmor(player) ||
                            PlayerEquipUtil.isWearingEndArmor(player) ||
                            PlayerEquipUtil.isWearingDragonArmor(player) ||
                            PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_RING_STEALTH)
                    )
                    {
                        this.target = null;
                    }
                }
            }
        }
    }
}