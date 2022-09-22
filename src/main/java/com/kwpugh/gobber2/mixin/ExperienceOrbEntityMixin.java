package com.kwpugh.gobber2.mixin;

//import com.kwpugh.gobber2.Gobber2;
//import com.kwpugh.gobber2.init.EnchantmentInit;
//import com.kwpugh.gobber2.init.ItemInit;
//import com.kwpugh.gobber2.util.ExpUtils;
//import com.kwpugh.gobber2.util.PlayerEquipUtil;
//import net.minecraft.enchantment.EnchantmentHelper;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.EquipmentSlot;
//import net.minecraft.entity.ExperienceOrbEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.world.World;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(ExperienceOrbEntity.class)
//public abstract class ExperienceOrbEntityMixin extends Entity
//{
//	@Shadow private int amount;
//
//	@Shadow private int health;
//
//	public ExperienceOrbEntityMixin(EntityType<? extends Entity> entityType, World world)
//	{
//		super(entityType, world);
//	}
//
//	@Inject(at = @At("HEAD"), method = "onPlayerCollision")
//	public void gobberCheckForMedallion(PlayerEntity player, CallbackInfo ci)
//	{
//		if(!world.isClient)
//		{
//			int multiplier = Gobber2.CONFIG.GENERAL.medallionExpMultiplier;
//
//			// Check for Knowledge Boost on swords
//			if(player.getEquippedStack(EquipmentSlot.MAINHAND) != null)
//			{
//				if(EnchantmentHelper.getLevel(EnchantmentInit.KNOWLEDGE, player.getEquippedStack(EquipmentSlot.MAINHAND)) > 0)
//				{
//					int level = EnchantmentHelper.getLevel(EnchantmentInit.KNOWLEDGE, player.getEquippedStack(EquipmentSlot.MAINHAND));
//
//					System.out.println("level: " + level);
//					System.out.println(" enchant amount before: " + this.amount);
//
////					 multiplier = ExpUtils.getMultiplier(amount, level);
////					 this.amount = this.amount * (level + multiplier);
//
//
////					if(this.amount < 4)
//					{
//						this.amount = this.amount + (level * multiplier);
//					}
//
//					System.out.println("enchant amount after: " + this.amount);
//				}
//			}
//			// or extra xp if player has Medallion of Exp
//			else if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_MEDALLION_EXP) ||
//					(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_EXP) && Gobber2.CONFIG.GENERAL.allowWorkInEnderchest))
//			{
//				System.out.println("medallion amount before: " + this.amount);
//
//				if(this.amount < 4)
//				{
//					this.amount = this.amount + multiplier;
//				}
//
//				System.out.println("medallion amount after: " + this.amount);
//			}
//		}
//	}
//
//
//}