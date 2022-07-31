package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityInteraction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.village.VillageGossipType;
import net.minecraft.village.VillagerGossips;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

/*
	Needed for Ensnarement to bypass normal interact
 */
@Mixin(VillagerEntity.class)
public class VillagerEntityMixinInteract
{
	// Bypasses trading GUI
	@Inject(method = "interactMob", at = @At(value = "HEAD"), cancellable = true)
	public void gobberInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir)
	{
		ItemStack stack = player.getStackInHand(hand);
		if(stack.getItem() == ItemInit.GOBBER2_STAFF_ENSNAREMENT ||
				stack.getItem() == ItemInit.GOBBER2_STAFF_HOSTILE_ENSNAREMENT)
		{
			player.swingHand(hand);

			cir.setReturnValue(ActionResult.PASS);
		}
	}


	// Makes the holder of the medallion really well liked
	@Shadow private VillagerGossips gossip;

	@Inject(method = "onInteractionWith", at = @At(value = "HEAD"), cancellable = true)
	public void gobberOnInteractionWith(EntityInteraction interaction, Entity entity, CallbackInfo ci)
	{
		VillagerEntity self = (VillagerEntity) (Object) this;

		if(entity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entity;

			if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_MEDALLION_HERO))
			{
				if (interaction == EntityInteraction.ZOMBIE_VILLAGER_CURED)
				{
					this.gossip.startGossip(entity.getUuid(), VillageGossipType.MAJOR_POSITIVE, 25);
				}
				else if (interaction == EntityInteraction.TRADE)
				{
					this.gossip.startGossip(entity.getUuid(), VillageGossipType.TRADING, 25);
				}
			}
		}
	}
}