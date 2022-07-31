package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.items.medallions.BaseMedallion;
import com.kwpugh.gobber2.items.rings.BaseRing;
import com.kwpugh.gobber2.items.staffs.BaseStaff;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// QuickUse enchantment target
@Mixin(targets = "net/minecraft/enchantment/EnchantmentTarget$2")
public class EnchantmentTargetMixin
{
    @Inject(method = "isAcceptableItem(Lnet/minecraft/item/Item;)Z", at = @At("HEAD"), cancellable = true)
    private void gobberIsAcceptableItem(Item item, CallbackInfoReturnable<Boolean> cir)
    {
        if(item instanceof BaseRing && Gobber2.CONFIG.GENERAL.enableRingEnchanting)
        {
            cir.setReturnValue(true);
        }

        if(item instanceof BaseMedallion && Gobber2.CONFIG.GENERAL.enableMedallionEnchanting)
        {
            cir.setReturnValue(true);
        }

        if(item instanceof BaseStaff && Gobber2.CONFIG.GENERAL.enableStaffEnchanting)
        {
            cir.setReturnValue(true);
        }
    }
}