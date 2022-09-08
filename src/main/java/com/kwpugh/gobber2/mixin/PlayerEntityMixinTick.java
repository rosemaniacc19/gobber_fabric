package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.items.rings.RingMiner;
import com.kwpugh.gobber2.items.rings.RingRepair;
import com.kwpugh.gobber2.util.EnableUtil;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stat;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixinTick extends LivingEntity
{
    private static boolean allowEnder = Gobber2.CONFIG.GENERAL.allowWorkInEnderchest;
    private static int strengthLevel = Gobber2.CONFIG.GENERAL.ringStrengthLevel;
    private static int resistenceLevel = Gobber2.CONFIG.GENERAL.ringStrengthResistenceLevel;
    private static int hasteLevel = Gobber2.CONFIG.GENERAL.ringHasteLevel;
    private static int luckLevel = Gobber2.CONFIG.GENERAL.ringLuckLevel;
    private static int speedLevel = Gobber2.CONFIG.GENERAL.ringSwiftnessLevel;
    private static int amount1 = Gobber2.CONFIG.GENERAL.medallionLesserHealingAmount;
    private static int amount2 = Gobber2.CONFIG.GENERAL.medallionHealingAmount;
    private static int amount3 = Gobber2.CONFIG.GENERAL.medallionGreaterHealingAmount;
    private static int shieldingHoriz = Gobber2.CONFIG.GENERAL.medallionShieldingHorizRange;
    private static int shieldingVert = Gobber2.CONFIG.GENERAL.medallionShieldingVertRange;
    private static int rangeSuffering = Gobber2.CONFIG.GENERAL.medallionSufferingRange;
    private static int damageSuffering = Gobber2.CONFIG.GENERAL.medallionSufferingDamage;

    protected PlayerEntityMixinTick(EntityType<? extends LivingEntity> type, World world)
    {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void gobberMinerTick(CallbackInfo ci)
    {
        if(world instanceof ServerWorld)
        {
            PlayerEntity player = (PlayerEntity) (Object) this;

            // process any items in the break list for Ring of Miner
            RingMiner.processList(world, player);

            // repairs damaged items in inventory if Ring of Repair is present
            if(PlayerEquipUtil.hasItemInInventory(player, ItemInit.GOBBER2_RING_REPAIR))
            {
                RingRepair.repairItems(world, player);
            }

            if(allowEnder)
            {
                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_RING_STRENGTH))
                {
                    StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.STRENGTH, Gobber2.CONFIG.GENERAL.effectDuration, strengthLevel, false, false);
                    StatusEffectInstance effect2 = new StatusEffectInstance(StatusEffects.RESISTANCE, Gobber2.CONFIG.GENERAL.effectDuration, resistenceLevel, false, false);

                    player.addStatusEffect(effect);
                    player.addStatusEffect(effect2);
                }

                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_RING_REPAIR))
                {
                    RingRepair.repairItems(world, player);
                }

                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_RING_ATTRACTION))
                {
                    ItemStack stack = PlayerEquipUtil.getItemStackInEnder(player, ItemInit.GOBBER2_RING_ATTRACTION);
                    PlayerSpecialAbilities.doItemAttraction(world, player, stack);
                }

                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_RING_HASTE))
                {
                    ItemStack stack = PlayerEquipUtil.getItemStackInEnder(player, ItemInit.GOBBER2_RING_HASTE);
                    if(EnableUtil.isEnabled(stack))
                    {
                        StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.HASTE, Gobber2.CONFIG.GENERAL.effectDuration, hasteLevel, false, false);
                        player.addStatusEffect(effect);
                    }
                }

                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_RING_LUCK))
                {
                    ItemStack stack = PlayerEquipUtil.getItemStackInEnder(player, ItemInit.GOBBER2_RING_LUCK);
                    if(EnableUtil.isEnabled(stack))
                    {
                        StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.LUCK, Gobber2.CONFIG.GENERAL.effectDuration, luckLevel, false, false);
                        player.addStatusEffect(effect);
                    }
                }

                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_RING_SWIFTNESS))
                {
                    ItemStack stack = PlayerEquipUtil.getItemStackInEnder(player, ItemInit.GOBBER2_RING_SWIFTNESS);
                    if(EnableUtil.isEnabled(stack))
                    {
                        StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.SPEED, Gobber2.CONFIG.GENERAL.effectDuration, speedLevel, false, false);
                        player.addStatusEffect(effect);
                    }
                }

                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_RING_VISION))
                {
                    ItemStack stack = PlayerEquipUtil.getItemStackInEnder(player, ItemInit.GOBBER2_RING_VISION);
                    if(EnableUtil.isEnabled(stack))
                    {
                        StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.NIGHT_VISION, Gobber2.CONFIG.GENERAL.effectDurationNightVision, 0, false, false);
                        player.addStatusEffect(effect);

                        if(player.hasStatusEffect(StatusEffects.DARKNESS))
                        {
                            player.removeStatusEffect(StatusEffects.DARKNESS);
                        }
                    }
                }

                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_HERO))
                {
                    StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 8, 0, false, false);
                    player.addStatusEffect(effect);

                    if(player.hasStatusEffect(StatusEffects.BAD_OMEN));
                    {
                        player.removeStatusEffect(StatusEffects.BAD_OMEN);
                    }
                }

                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_SEA))
                {
                    if(player.isSubmergedInWater())
                    {
                        StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 8, 0, false, false);
                        StatusEffectInstance effect2 = new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 8, 0, false, false);
                        player.addStatusEffect(effect);
                        player.addStatusEffect(effect2);
                    }
                }

                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_HEALING))
                {
                    PlayerSpecialAbilities.giveHealing(player, amount1);
                }

                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_HEALING2))
                {
                    PlayerSpecialAbilities.giveHealing(player, amount2);
                    PlayerSpecialAbilities.giveSaturationEffect(player);
                }

                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_HEALING3))
                {
                    PlayerSpecialAbilities.giveHealing(player, amount3);
                    PlayerSpecialAbilities.giveSaturationEffect(player);
                }

                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_SHIELDING))
                {
                    PlayerSpecialAbilities.giveProjectileShield(world, player, shieldingVert, shieldingHoriz);
                }

                if(PlayerEquipUtil.hasItemInEnder(player, ItemInit.GOBBER2_MEDALLION_SUFFERING))
                {
                    BlockPos pos = player.getBlockPos();
                    PlayerSpecialAbilities.inflictSuffering(world, pos, rangeSuffering, damageSuffering);
                }
            }
        }
    }
}
