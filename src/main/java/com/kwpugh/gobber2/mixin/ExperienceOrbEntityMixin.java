package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrbEntity.class)
public abstract class ExperienceOrbEntityMixin extends Entity 
{
	@Shadow private int amount;
	
	public ExperienceOrbEntityMixin(EntityType<? extends Entity> entityType, World world) 
	{
		super(entityType, world);
	}
	
	private int multiplier = Gobber2.CONFIG.GENERAL.medallionExpMultiplier;

	@Inject(at = @At("HEAD"), method = "onPlayerCollision")
	public void gobberCheckForMedallion(PlayerEntity player, CallbackInfo ci)
	{
		if(!world.isClient)
		{
			if(PlayerEquipUtil.hasItemInOffHand(player, ItemInit.GOBBER2_MEDALLION_EXP) ||
					(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_EXP) && Gobber2.CONFIG.GENERAL.allowRingsTicksInEnderchest))
			{
				if(this.amount < 4)
				{
					this.amount = this.amount * multiplier;
				}
			}			
		}
	}
}