package com.kwpugh.gobber2.mixin;

import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kwpugh.gobber2.init.EnchantmentInit;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixinSolidFooting extends LivingEntity 
{
	@Shadow public abstract float getBlockBreakingSpeed(BlockState block);
	
	public PlayerEntityMixinSolidFooting(EntityType<? extends LivingEntity> entityType, World world) 
	{
		super(entityType, world);
	}

	@Inject(at = @At(value="HEAD"), method = "getBlockBreakingSpeed", cancellable = true)
	private void gobberGetBlockBreakingSpeed(BlockState state, CallbackInfoReturnable<Float> cir)
	{		
		PlayerEntity self = (PlayerEntity) (Object) this;
		float speed = self.getInventory().getBlockBreakingSpeed(state);		

		if(EnchantmentHelper.getLevel(EnchantmentInit.SOLIDFOOTING, self.getEquippedStack(EquipmentSlot.FEET)) > 0)
		{
			if(!self.isOnGround())
			{
				if(speed > 1.0f)
				{
					int i = EnchantmentHelper.getEfficiency(this);
					ItemStack itemStack = this.getMainHandStack();

					if(i > 0 && !itemStack.isEmpty())
					{
						speed += (float)(i * i + 1);
					}
				}

				if(StatusEffectUtil.hasHaste(this))
				{
					speed *= 1.0f + (float)(StatusEffectUtil.getHasteAmplifier(this) + 1) * 0.2f;
				}

				cir.setReturnValue(speed);
			}
		}
	}
}