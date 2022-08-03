package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin
{
    @Inject(method = "canWalkOnPowderSnow", at = @At("HEAD"), cancellable = true)
    private static void gobberWalkOnSnow(Entity entity, CallbackInfoReturnable<Boolean> cir)
    {
        if (entity instanceof PlayerEntity player)
        {
            if(PlayerEquipUtil.isWearingGobberArmor(player) ||
            PlayerEquipUtil.isWearingNetherArmor(player) ||
            PlayerEquipUtil.isWearingEndArmor(player) ||
            PlayerEquipUtil.isWearingDragonArmor(player))
            {
                cir.setReturnValue(true);
            }
        }
    }
}