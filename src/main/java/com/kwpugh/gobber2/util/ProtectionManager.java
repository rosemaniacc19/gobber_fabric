package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EnchantmentInit;
import com.kwpugh.gobber2.init.ItemInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class ProtectionManager
{
    public static boolean canFreeze(PlayerEntity player, DamageSource source)
    {
        if(source == DamageSource.FREEZE)
        {
            if(PlayerEquipUtil.isWearingGobberArmor(player) ||
                    PlayerEquipUtil.isWearingNetherArmor(player) ||
                    PlayerEquipUtil.isWearingEndArmor(player) ||
                    PlayerEquipUtil.isWearingDragonArmor(player)  )
            {
                return false;
            }
        }

        return true;
    }

    public static boolean canBurn(PlayerEntity player, DamageSource source)
    {
        if(source == DamageSource.HOT_FLOOR ||
                source == DamageSource.IN_FIRE ||
                source == DamageSource.ON_FIRE ||
                source == DamageSource.LAVA ||
                source == DamageSource.LIGHTNING_BOLT)
        {
            if(PlayerEquipUtil.isWearingNetherArmor(player) && Gobber2.CONFIG.GENERAL.enableNetherFirePerk)
            {
                return false;
            }

            if(PlayerEquipUtil.isWearingEndArmor(player) && Gobber2.CONFIG.GENERAL.enableEndFirePerk)
            {
                return false;
            }

            if(PlayerEquipUtil.isWearingDragonArmor(player) && Gobber2.CONFIG.GENERAL.enableDragonFirePerk)
            {
                return false;
            }

            if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_RING_PHOENIX) ||
                    (PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_RING_PHOENIX) && Gobber2.CONFIG.GENERAL.allowWorkInEnderchest))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isFireproof(Entity entity)
    {
        if(entity instanceof PlayerEntity player)
        {
            if(PlayerEquipUtil.isWearingNetherArmor(player) && Gobber2.CONFIG.GENERAL.enableNetherFirePerk)
            {
               return true;
            }

            if(PlayerEquipUtil.isWearingEndArmor(player) && Gobber2.CONFIG.GENERAL.enableEndFirePerk)
            {
                return true;
            }

            if(PlayerEquipUtil.isWearingDragonArmor(player) && Gobber2.CONFIG.GENERAL.enableDragonFirePerk)
            {
                return true;
            }

            if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_RING_PHOENIX) ||
                    (PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_RING_PHOENIX) && Gobber2.CONFIG.GENERAL.allowWorkInEnderchest))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean canDrown(PlayerEntity player, DamageSource source)
    {
        if(source == DamageSource.DROWN)
        {
            if(PlayerEquipUtil.isWearingGobberArmor(player) && Gobber2.CONFIG.GENERAL.enableGobberBreathing)
            {
                return false;
            }

            if(PlayerEquipUtil.isWearingEndArmor(player) && Gobber2.CONFIG.GENERAL.enableEndBreathing)
            {
                return false;
            }

            if(PlayerEquipUtil.isWearingDragonArmor(player) && Gobber2.CONFIG.GENERAL.enableDragonBreathing)
            {
                return false;
            }

            if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_MEDALLION_BREATHING) ||
                    (PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_BREATHING) && Gobber2.CONFIG.GENERAL.allowWorkInEnderchest))
            {
                return false;
            }

            if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_MEDALLION_SEA) ||
                    (PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_SEA) && Gobber2.CONFIG.GENERAL.allowWorkInEnderchest))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean canBreathUnderwater(LivingEntity livingEntity)
    {
        if(livingEntity instanceof PlayerEntity player)
        {
            if(PlayerEquipUtil.isWearingGobberArmor(player) && Gobber2.CONFIG.GENERAL.enableGobberBreathing)
            {
                return true;
            }

            if(PlayerEquipUtil.isWearingEndArmor(player) && Gobber2.CONFIG.GENERAL.enableEndBreathing)
            {
                return true;
            }

            if(PlayerEquipUtil.isWearingDragonArmor(player) && Gobber2.CONFIG.GENERAL.enableDragonBreathing)
            {
                return true;
            }

            if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_MEDALLION_SEA) ||
                    (PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_SEA) && Gobber2.CONFIG.GENERAL.allowWorkInEnderchest))
            {
                return true;
            }

            if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_MEDALLION_BREATHING) ||
                    (PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_BREATHING) && Gobber2.CONFIG.GENERAL.allowWorkInEnderchest))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean isMagicProof(PlayerEntity player, DamageSource source)
    {
        if(EnchantmentHelper.getLevel(EnchantmentInit.APOTROPAIC, player.getEquippedStack(EquipmentSlot.CHEST)) > 0)
        {
            if(source.getName() == "indirectMagic" ||
                    source.getName() == "magic" ||
                    source.getName() == "sonic_boom")
            {
                return true;
            }
        }

        return false;
    }

    public static boolean hasCuring(PlayerEntity player, DamageSource source)
    {
        if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_RING_CURING) ||
                (PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_RING_CURING) && Gobber2.CONFIG.GENERAL.allowWorkInEnderchest))
        {
            return true;
        }

        return false;
    }
}
