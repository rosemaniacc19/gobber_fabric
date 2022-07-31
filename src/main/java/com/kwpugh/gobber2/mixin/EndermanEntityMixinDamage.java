package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.items.arrows.GobberArrowEndEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixinDamage
{
	@Inject(at = @At("RETURN"), method = "damage", cancellable = true)
	public void gobberDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
	{
		EndermanEntity self = (EndermanEntity) (Object) this;
		Entity entity = source.getSource();

		if(entity instanceof GobberArrowEndEntity)
		{
			self.setHealth(0);
			cir.setReturnValue(true);
		}
	}
}