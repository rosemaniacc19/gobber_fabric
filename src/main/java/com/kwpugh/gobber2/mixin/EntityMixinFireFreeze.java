package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EnchantmentInit;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;
import com.kwpugh.gobber2.util.ProtectionManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*
    Need for fire/lava
    and freezing protection
 */

@Mixin(Entity.class)
public abstract class EntityMixinFireFreeze
{
    public EntityMixinFireFreeze(EntityType<?> type, World world)
    {
        super();
    }

    // Fire protection used for armors and Ring of Phoenix. Prevents burning animation on player
    // See also PlayerEntityMixinDamage for other part
    @Inject(method = "isFireImmune", at = @At("HEAD"), cancellable = true)
    public void gobberIsFireImmune(CallbackInfoReturnable<Boolean> cir)
    {
        Entity entity = (Entity) (Object) this;

        if(ProtectionManager.isFireproof(entity))
        {
            cir.setReturnValue(true);
        }
    }

    // Prevent freezing damage from powdered snow and other sources
    // See also PlayerEntityMixinDamage for other part
    @Inject(method = "setFrozenTicks", at = @At("HEAD"), cancellable = true)
    public void setFrozenTicks(int frozenTicks, CallbackInfo ci)
    {
        Entity entity = (Entity) (Object) this;

        if(entity instanceof PlayerEntity player)
        {
            if(PlayerEquipUtil.isWearingGobberArmor(player) ||
                    PlayerEquipUtil.isWearingNetherArmor(player) ||
                    PlayerEquipUtil.isWearingEndArmor(player) ||
                    PlayerEquipUtil.isWearingDragonArmor(player) )
            {
                ci.cancel();
            }
        }
    }

    // Healing used for Ring of Phoenix
    @Inject(method = "baseTick", at = @At("HEAD"))
    public void gobberBaseTick(CallbackInfo ci)
    {
        Entity entity = (Entity) (Object) this;

        if(entity instanceof PlayerEntity player)
        {
            if(player.isInLava() && (PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_RING_PHOENIX) ||
                    PlayerEquipUtil.hasItemInEnderchest(player, ItemInit.GOBBER2_RING_PHOENIX)))
            {
                PlayerSpecialAbilities.giveHealing(player, Gobber2.CONFIG.GENERAL.ringPhoenixHealAmount);
                PlayerSpecialAbilities.giveSaturationEffect(player);
                PlayerSpecialAbilities.giveLesserAbsorption(player);
            }
        }
    }

    // Used for Quietfeet enchantment
    @Inject(method = "occludeVibrationSignals", at = @At("HEAD"), cancellable = true)
    public void gobberOccludeVibrationSignals(CallbackInfoReturnable<Boolean> cir)
    {
        Entity entity = (Entity) (Object) this;

        if (entity instanceof PlayerEntity player)
        {
            if (EnchantmentHelper.getLevel(EnchantmentInit.QUIETFEET, player.getEquippedStack(EquipmentSlot.FEET)) > 0)
            {
                cir.setReturnValue(true);
            }
        }
    }
}
