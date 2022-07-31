package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.init.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixinBlinders
{
	@Inject(at = @At("RETURN"), method = "isPlayerStaring(Lnet/minecraft/entity/player/PlayerEntity;)Z", cancellable = true)
	public void gobberIsPlayerStaring(PlayerEntity player, CallbackInfoReturnable<Boolean> cir)
	{
		if(EnchantmentHelper.getLevel(EnchantmentInit.BLINDERS, player.getEquippedStack(EquipmentSlot.HEAD)) > 0)
		{
			cir.setReturnValue(false);
		}
	}
}