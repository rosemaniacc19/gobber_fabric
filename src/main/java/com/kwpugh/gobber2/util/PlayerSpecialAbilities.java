package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EffectsInit;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.items.rings.RingAttraction;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class PlayerSpecialAbilities
{
	static boolean enableNetherSwordPerks = Gobber2.CONFIG.GENERAL.enableNetherSwordPerks;
	static int configRange = Gobber2.CONFIG.GENERAL.ringAttractionRange;
	static boolean enableCoalBlock = Gobber2.CONFIG.GENERAL.ringAttractionEnableCoal;

	//Increases the player's health amount to max on tick update, based on inputs
	public static void giveHealing(PlayerEntity player, int amount)
	{
		if(player.age % 180 == 0)
		{
			if(player.getHealth() < player.getMaxHealth())
			{
				player.heal(amount);
			}		
		}
	}

	//Set player saturation/food level to max on tick update
	public static void giveSaturationEffect(PlayerEntity player)
	{
		if(player.age % 180 == 0)
		{
			if(player.getHungerManager().getFoodLevel() < 20)
				player.getHungerManager().setFoodLevel(player.getHungerManager().getFoodLevel() + 1);
		}
	}

	//Lesser yellow hearts on tick update
	public static void giveLesserAbsorption(PlayerEntity player)
	{
		float current = player.getAbsorptionAmount();

		if(player.getHealth() < 20 || current >= 10)
		{
			return;
		}

		if(current >= 9)
		{
			if (player.age % 180 == 0)
			{
				player.setAbsorptionAmount(10);
			}


			return;
		}

		if(current < 9)
		{
			if (player.age % 180 == 0)
			{
				player.setAbsorptionAmount(current + 0.33F);
			}
		}
	}

	//Greater yellow hearts on tick update
	public static void giveGreaterAbsorption(PlayerEntity player)
	{
		float current = player.getAbsorptionAmount();

		if(player.getHealth() < 20 || current >= 20)
		{
			return;
		}

		if(current >= 19)
		{
			if (player.age % 180 == 0)
			{
				player.setAbsorptionAmount(20);
			}

			return;
		}
		if(current < 19)
		{
			if (player.age % 180 == 0)
			{
				player.setAbsorptionAmount(current + 1.0F);
			}
		}
	}

	public static void giveProjectileShield(World world, PlayerEntity player, int vRadius, int hRadius)
	{
		ItemStack drop;

		// Scan for hostile mobs in defined range
		Box mobBoxLoot = (new Box(player.getBlockPos())).expand(hRadius, vRadius, hRadius);
		List<Entity> listShield = world.getNonSpectatingEntities(Entity.class, mobBoxLoot);
		Iterator<Entity> iteratorShield = listShield.iterator();
		Entity targetEntity;

		while(iteratorShield.hasNext())
		{
			targetEntity = iteratorShield.next();

			// Have some fun with incoming projectiles
			if(targetEntity instanceof ProjectileEntity projectile)
			{
				drop = Items.FEATHER.getDefaultStack(); // deals with unknown projectile type

				boolean arrow = targetEntity instanceof ArrowEntity;
				boolean fireball = targetEntity instanceof FireballEntity;
				boolean small_fireball = targetEntity instanceof SmallFireballEntity;
				boolean shulker = targetEntity instanceof ShulkerBulletEntity;
				boolean llamaSpit = targetEntity instanceof LlamaSpitEntity;
				boolean trident = targetEntity instanceof TridentEntity;
				boolean witherSkull = targetEntity instanceof WitherSkullEntity;
				boolean dragonball = targetEntity instanceof DragonFireballEntity;

				if(arrow) drop = Items.ARROW.getDefaultStack();
				if(fireball || small_fireball) drop = Items.FIRE_CHARGE.getDefaultStack();
				if(shulker) drop = Items.POPPED_CHORUS_FRUIT.getDefaultStack();
				if(llamaSpit) drop = Items.BONE_MEAL.getDefaultStack();
				if(trident) drop = Items.PRISMARINE_SHARD.getDefaultStack();
				if(witherSkull) drop = Items.WITHER_SKELETON_SKULL.getDefaultStack();
				if(dragonball) break;

				if(arrow || fireball || small_fireball || shulker || llamaSpit || trident)
				{
					world.spawnEntity(new ItemEntity(world, projectile.getX(), projectile.getY(), projectile.getZ(), drop));
					projectile.remove(Entity.RemovalReason.DISCARDED);
				}

				if(witherSkull && Gobber2.CONFIG.GENERAL.medallionShieldingAgainstWither)
				{
					world.spawnEntity(new ItemEntity(world, projectile.getX(), projectile.getY(), projectile.getZ(), drop));
					projectile.remove(Entity.RemovalReason.DISCARDED);
				}
			}

			// Deal with any splash potions
			if(targetEntity instanceof PotionEntity potionEntity)
			{
				drop = Items.POPPY.getDefaultStack();

				world.spawnEntity(new ItemEntity(world, potionEntity.getX(), potionEntity.getY(), potionEntity.getZ(), drop));
				potionEntity.remove(Entity.RemovalReason.DISCARDED);
			}
		}
	}

	// Checks if player has negative effects and removes them, used on Dragon Armor
	public static void giveCuringEffect(World world, PlayerEntity player)
	{
		if (!world.isClient)
		{
			if(player.hasStatusEffect(StatusEffects.BLINDNESS))
			{
				player.removeStatusEffect(StatusEffects.BLINDNESS);
			}

			if(player.hasStatusEffect(StatusEffects.HUNGER))
			{
				player.removeStatusEffect(StatusEffects.HUNGER);
			}

			if(player.hasStatusEffect(StatusEffects.MINING_FATIGUE))
			{
				player.removeStatusEffect(StatusEffects.MINING_FATIGUE);
			}

			if(player.hasStatusEffect(StatusEffects.NAUSEA))
			{
				player.removeStatusEffect(StatusEffects.NAUSEA);
			}

			if(player.hasStatusEffect(StatusEffects.POISON))
			{
				player.removeStatusEffect(StatusEffects.POISON);
			}

			if(player.hasStatusEffect(StatusEffects.SLOWNESS))
			{
				player.removeStatusEffect(StatusEffects.SLOWNESS);
			}

			if(player.hasStatusEffect(StatusEffects.UNLUCK))
			{
				player.removeStatusEffect(StatusEffects.UNLUCK);
			}

			if(player.hasStatusEffect(StatusEffects.WEAKNESS))
			{
				player.removeStatusEffect(StatusEffects.WEAKNESS);
			}

			if(player.hasStatusEffect(StatusEffects.WITHER))
			{
				player.removeStatusEffect(StatusEffects.WITHER);
			}

			if(player.hasStatusEffect(StatusEffects.INSTANT_DAMAGE))
			{
				player.removeStatusEffect(StatusEffects.INSTANT_DAMAGE);
			}
		}
	}

	public static void inflictSuffering(World world, BlockPos pos, int range, int damage)
	{
		// Scan for hostile mobs
		Box mobBox = (new Box(pos)).expand(range, 2, range);
		List<Entity> list2 = world.getNonSpectatingEntities(Entity.class, mobBox);
		Iterator<Entity> iterator2 = list2.iterator();

		Entity targetEntity;

		// Cycle through and effect entities
		while(iterator2.hasNext())
		{
			targetEntity = (Entity)iterator2.next();

			// Exclude some of the harder mobs
			if(targetEntity instanceof WitherEntity ||
					targetEntity instanceof GuardianEntity ||
					targetEntity instanceof BlazeEntity ||
					targetEntity instanceof VexEntity)
				continue;

			if(targetEntity instanceof HostileEntity || targetEntity instanceof HoglinEntity)
			{
				targetEntity.damage(DamageSource.GENERIC, damage);
			}
		}
	}

	public static void giveNetherSwordPerk(DamageSource source, WitherSkeletonEntity self)
	{
		PlayerEntity player = (PlayerEntity) source.getAttacker();
		ItemStack mainHand = player.getEquippedStack(EquipmentSlot.MAINHAND);

		if(enableNetherSwordPerks)
		{
			if(mainHand.getItem().equals(ItemInit.GOBBER2_SWORD_NETHER) && player.hasStatusEffect(StatusEffects.WITHER))
			{
				player.removeStatusEffect(StatusEffects.WITHER);
			}

			if(mainHand.getItem().equals(ItemInit.GOBBER2_SWORD_NETHER))
			{
				self.dropItem(Items.WITHER_SKELETON_SKULL);
			}
		}

		// Give Knowledge Boost even without enchantment, if has medallion of exp
		if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_MEDALLION_EXP))
		{
			StatusEffectInstance effect = new StatusEffectInstance(EffectsInit.EXPERIENCE, Gobber2.CONFIG.GENERAL.effectDurationExpBoost, Gobber2.CONFIG.GENERAL.netherSwordXPPerTick, true, true);
			player.addStatusEffect(effect);
		}
	}

	public static void doItemAttraction(World world, PlayerEntity player, ItemStack stack)
	{
		if(!world.isClient && RingAttraction.isActive(stack))
		{
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
			BlockPos playerPos = new BlockPos(player.getPos());

			// Check for a particular block that stops the attraction
			if(enableCoalBlock)
			{
				for(BlockPos targetPos : BlockPos.iterateOutwards(playerPos, configRange, 5, configRange))
				{
					BlockState blockstate = world.getBlockState(targetPos);

					if((blockstate.getBlock() == Blocks.COAL_BLOCK))
					{
						return;
					}
				}
			}

			// Scan and collect items
			List<ItemEntity> entityItems = serverPlayer.getWorld().getEntitiesByClass(ItemEntity.class, serverPlayer.getBoundingBox().expand(configRange), EntityPredicates.VALID_ENTITY);
			for (ItemEntity entityItemNearby : entityItems)
			{
				entityItemNearby.onPlayerCollision(serverPlayer);
			}

			// Scan and collect XP orbs
			List<ExperienceOrbEntity> entityXP = serverPlayer.getWorld().getEntitiesByClass(ExperienceOrbEntity.class, serverPlayer.getBoundingBox().expand(configRange), EntityPredicates.VALID_ENTITY);
			for (Entity entityXPNearby : entityXP)
			{
				entityXPNearby.onPlayerCollision(serverPlayer);
			}
		}
	}
}