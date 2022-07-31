package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

/*
    Mixin to alter the armor point cap
 */
@Mixin(EntityAttributes.class)
public class EntityAttributeMixinArmor
{
    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=generic.armor")),
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/attribute/ClampedEntityAttribute;<init>(Ljava/lang/String;DDD)V", ordinal = 0),
            index = 3)

    private static double gobberModifyMax(double original)
    {
        if(Gobber2.CONFIG.GENERAL.enableaArmorCap)
        {
            return 80.0;
        }

        return 30;
    }
}


