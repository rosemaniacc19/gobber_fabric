package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EffectsInit;
import com.kwpugh.gobber2.init.EnchantmentInit;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixinKnowledge
{
    @Shadow @Nullable protected PlayerEntity attackingPlayer;

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)
    private void onDeathWisdom(DamageSource source, CallbackInfo ci)
    {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        if(attackingPlayer.getEquippedStack(EquipmentSlot.MAINHAND) != null)
        {
            if(EnchantmentHelper.getLevel(EnchantmentInit.KNOWLEDGE, attackingPlayer.getEquippedStack(EquipmentSlot.MAINHAND)) > 0)
            {
                int level = EnchantmentHelper.getLevel(EnchantmentInit.KNOWLEDGE, attackingPlayer.getEquippedStack(EquipmentSlot.MAINHAND));
                StatusEffectInstance effect = new StatusEffectInstance(EffectsInit.KNOWLEDGE, Gobber2.CONFIG.GENERAL.effectDurationKnowledge, level, true, true);
                attackingPlayer.addStatusEffect(effect);
            }
        }

        if(PlayerEquipUtil.hasItemInInventory(attackingPlayer, ItemInit.GOBBER2_MEDALLION_EXP) ||
                (PlayerEquipUtil.hasItemInEnder(attackingPlayer, ItemInit.GOBBER2_MEDALLION_EXP) && Gobber2.CONFIG.GENERAL.allowWorkInEnderchest))
        {
            StatusEffectInstance effect = new StatusEffectInstance(EffectsInit.KNOWLEDGE, Gobber2.CONFIG.GENERAL.effectDurationKnowledge, Gobber2.CONFIG.GENERAL.medallionExpAmplifer, true, true);
            attackingPlayer.addStatusEffect(effect);
        }
    }
}