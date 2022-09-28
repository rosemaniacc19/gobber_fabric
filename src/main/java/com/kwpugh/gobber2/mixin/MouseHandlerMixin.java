package com.kwpugh.gobber2.mixin;

import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin({Mouse.class})
public class MouseHandlerMixin
{
    // used to improve performance on macos with retina displays
    //Doubles and longs are counted twice, index 5 is vertical in onMouseScroll.
    @ModifyVariable(method = "onMouseScroll", at = @At("HEAD"), index = 5, argsOnly = true)
    private double scrollFix(double vertical1, long window, double horizontal, double vertical2)
    {
        return vertical1 == 0 ? horizontal : vertical1;
    }
}
