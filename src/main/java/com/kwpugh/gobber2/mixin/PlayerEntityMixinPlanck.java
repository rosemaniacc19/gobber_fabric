package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.GobberForceManager;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixinPlanck extends LivingEntity
{
    @Shadow public abstract void readCustomDataFromNbt(NbtCompound nbt);
    @Shadow public abstract void writeCustomDataToNbt(NbtCompound nbt);

    protected PlayerEntityMixinPlanck(EntityType<? extends LivingEntity> type, World world)
    {
        super(type, world);
    }

    // run various tasks each player tick
    @Inject(method = "tick", at = @At("TAIL"))
    public void gobberForceTick(CallbackInfo ci)
    {
        if(world instanceof ServerWorld && Gobber2.CONFIG.GENERAL.enableGobberForce)
        {
            PlayerEntity player = (PlayerEntity) (Object) this;
            GobberForceManager.update(player);
        }
    }

    // subtract planck when damaged by mobs
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void gobberForceDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        if(world instanceof ServerWorld && Gobber2.CONFIG.GENERAL.enableGobberForce)
        {
            PlayerEntity player = (PlayerEntity) (Object) this;
            int damageAmount = (int) amount;

            if(GobberForceManager.getGobberForce(player) > 100 && damageAmount < 100)
            {
                GobberForceManager.subtractGobberForce(player, damageAmount);
                player.sendMessage((Text.translatable("Player damage absorbed by GobberForce: " + damageAmount).formatted(Formatting.GOLD).formatted(Formatting.BOLD)), true);
                cir.setReturnValue(false);
            }
            else if(GobberForceManager.getGobberForce(player) > damageAmount)
            {
                player.sendMessage((Text.translatable("Your GobberForce amount reduced by: " + damageAmount).formatted(Formatting.DARK_PURPLE).formatted(Formatting.BOLD)), true);
                GobberForceManager.subtractGobberForce(player, damageAmount);
            }
        }
    }

    // cancel exhaustion
    @Inject(method = "addExhaustion", at = @At("HEAD"), cancellable = true)
    public void gobberForceAddExhaustion(float exhaustion, CallbackInfo ci)
    {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if(GobberForceManager.getGobberForce(player) > 10)
        {
            GobberForceManager.subtractGobberForce(player, 5);
            ci.cancel();
        }
    }

    // add writeNbt to player class
    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeNbtPlanck(NbtCompound nbt, CallbackInfo ci)
    {
        if(world instanceof ServerWorld && Gobber2.CONFIG.GENERAL.enableGobberForce)
        {
            GobberForceManager.writeNbt(nbt);
        }
    }

    // add readNbt to player class
    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readNbtPlanck(NbtCompound nbt, CallbackInfo ci)
    {
        if(world instanceof ServerWorld && Gobber2.CONFIG.GENERAL.enableGobberForce)
        {
            if(nbt.contains("gobberForce"))
            {
                GobberForceManager.readNbt(nbt);
            }
        }
    }
}
