package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.init.ItemInit;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixinGlided
{
    @Inject(method = "wearsGoldArmor", at = @At("HEAD"), cancellable = true)
    private static void gobberWearsGoldArmor(LivingEntity player, CallbackInfoReturnable<Boolean> cir)
    {
        ItemStack itemStack = player.getEquippedStack(EquipmentSlot.CHEST);

        if (itemStack.getItem() == ItemInit.GOBBER2_CHESTPLATE_NETHER)
        {
            cir.setReturnValue(true);
        }
    }
}