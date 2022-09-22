package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EffectsInit;
import com.kwpugh.gobber2.init.EnchantmentInit;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinKnowledge
{
    @Shadow @Nullable protected PlayerEntity attackingPlayer;

    @Shadow protected abstract void attackLivingEntity(LivingEntity target);

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)
    private void gobberKnowledge(DamageSource source, CallbackInfo ci)
    {
        if(attackingPlayer instanceof ServerPlayerEntity serverPlayer)
        {
            if(serverPlayer.getWorld().isClient) return;

            Hand hand = serverPlayer.getActiveHand();
            ItemStack stack = serverPlayer.getStackInHand(hand);

            if(EnchantmentHelper.getLevel(EnchantmentInit.EXP_BOOST, stack) > 0)
            {
                int level = EnchantmentHelper.getLevel(EnchantmentInit.EXP_BOOST, stack);
                StatusEffectInstance effect = new StatusEffectInstance(EffectsInit.EXPERIENCE, Gobber2.CONFIG.GENERAL.effectDurationExpBoost, level, true, true);
                serverPlayer.addStatusEffect(effect);
                serverPlayer.sendMessage((Text.translatable("XP Boosted by Enchantment")), true);
            }
            else if(Gobber2.CONFIG.GENERAL.medallionExpXPBoost)
            {
                if(PlayerEquipUtil.hasItemInInventory(serverPlayer, ItemInit.GOBBER2_MEDALLION_EXP))
                {
                    StatusEffectInstance effect = new StatusEffectInstance(EffectsInit.EXPERIENCE, Gobber2.CONFIG.GENERAL.effectDurationExpBoost, Gobber2.CONFIG.GENERAL.medallionExpAmplifer, true, true);
                    serverPlayer.addStatusEffect(effect);
                    serverPlayer.sendMessage((Text.translatable("XP Boosted by Medallion")), true);
                }
            }
        }
    }
}