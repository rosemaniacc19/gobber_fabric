package com.kwpugh.gobber2.events;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EnchantmentInit;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Random;

/*
    NOTE: SummonerManager will handle deleting
    spawned mobs after a defined period of time.
 */
public class SummonerEvent
{
    static boolean summonerGoldGear = Gobber2.CONFIG.GENERAL.summonerGoldGear;
    static boolean bonusStats = Gobber2.CONFIG.GENERAL.summonerEnableBonusStats;
    static double summonerHealthBonus = Gobber2.CONFIG.GENERAL.summonerHealthBonus;
    static double summonerAttackBonus = Gobber2.CONFIG.GENERAL.summonerAttackBonus;
    static double summonerMovementBonus = Gobber2.CONFIG.GENERAL.summonerMovementBonus;
    static double summonerArmorBonus = Gobber2.CONFIG.GENERAL.summonerArmorBonus;
    static boolean summonerRandomMode = Gobber2.CONFIG.GENERAL.summonerEnableRandomMode;
    static float summerRandomChance = Gobber2.CONFIG.GENERAL.summonerRandomModeChance;

    public static void register()
    {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) ->
        {
            if(world.isClient) return ActionResult.PASS;

            if(Gobber2.CONFIG.GENERAL.summonerEventEnable && entity instanceof LivingEntity target)
            {
                if(EnchantmentHelper.getLevel(EnchantmentInit.SUMMONER, player.getEquippedStack(EquipmentSlot.MAINHAND)) > 0)
                {
                    // don't summon new mobs bby hitting existing one
                    if(SummonerManager.isInList(target)) return ActionResult.PASS;

                    int currentLevel = EnchantmentHelper.getLevel(EnchantmentInit.SUMMONER, player.getEquippedStack(EquipmentSlot.MAINHAND));
                    Random random = new Random();
                    int amount = random.nextInt(currentLevel); // random amount of mobs

                    if(summonerRandomMode)
                    {
                        double chance = random.nextDouble(); // random chance to spawn

                        if(chance <= summerRandomChance) // random mode
                        {
                            for(int i = 0; i < amount; i++)
                            {
                                setupMob(world, target, player);
                            }
                        }
                    }
                    else // default mode, all mobs all the time
                    {
                        for(int i = 0; i < currentLevel; i++)
                        {
                            setupMob(world, target, player);
                        }
                    }
                }
            }

            return ActionResult.PASS;
        });
    }

    public static void setupMob(World world, LivingEntity target, PlayerEntity player)
    {
        ZombifiedPiglinEntity mob = EntityType.ZOMBIFIED_PIGLIN.create(world);

        // set their position and have them target the hit enemy
        mob.setPosition(target.getX(), target.getY(), target.getZ());
        mob.setTarget(target);

        // suit themup with gear
        if(summonerGoldGear)
        {
            mob.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
            mob.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET));
            mob.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
            mob.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
            mob.equipStack(EquipmentSlot.FEET, new ItemStack(Items.GOLDEN_BOOTS));
        }

        if(bonusStats)
        {
            mob.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(new EntityAttributeModifier("Summoner Health Bonus", summonerHealthBonus, EntityAttributeModifier.Operation.ADDITION));
            mob.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).addPersistentModifier(new EntityAttributeModifier("Summoner Attack Bonus", summonerAttackBonus, EntityAttributeModifier.Operation.ADDITION));
            mob.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).addPersistentModifier(new EntityAttributeModifier("Summoner Armor Bonus", summonerArmorBonus, EntityAttributeModifier.Operation.ADDITION));
            mob.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).addPersistentModifier(new EntityAttributeModifier("Summoner Movement Bonus", summonerMovementBonus, EntityAttributeModifier.Operation.ADDITION));
        }

        // spawn them
        world.spawnEntity(mob);
        player.sendMessage((Text.translatable("Help has arrived!")), true);

        // add them to the list to be tracked for removal
        SummonerManager.addToList(mob);
    }
}
