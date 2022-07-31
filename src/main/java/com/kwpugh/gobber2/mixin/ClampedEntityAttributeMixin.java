package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*
    Needed to cover the armor max of Gobber armor
 */
@Mixin(ClampedEntityAttribute.class)
abstract class ClampedEntityAttributeMixin
{
    @Final @Shadow private double minValue;

    @Final @Shadow private double maxValue;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void gobberInit(String translationKey, double fallback, double min, double max, CallbackInfo info)
    {
        if(translationKey.equals("attribute.name.generic.armor"))
        {
            Gobber2.LOGGER.info("Gobber Mod Info Message: Max value of " + translationKey + " is currently set to " + this.maxValue);
        }
    }
}