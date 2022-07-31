package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/*
    Need for Armor gliding feature
 */

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinGlide extends Entity 
{
    public LivingEntityMixinGlide(EntityType<?> type, World world)
    {
      super(type, world);
    }

    @ModifyArg(method = "tickFallFlying()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"), index = 1)

    private boolean gobberInitAiMixin(boolean value)
    {

        boolean bl = this.getFlag(7);
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);

        if((bl && !this.onGround && !this.hasVehicle()) &&
                ((itemStack.isOf(ItemInit.GOBBER2_CHESTPLATE_END) && Gobber2.CONFIG.GENERAL.enableGlidingEndArmor)))
        {
            return true;
        }

        return value;
    }
}