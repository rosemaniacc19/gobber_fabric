package com.kwpugh.gobber2.mixin;

import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Window.class})
public abstract class WindowHintsMixin
{
    // used to improve performance on macos with retina displays
    @Inject(method = "<init>", at = @At("RETURN"))
    private void adjustWindowHints(CallbackInfo callbackInfo)
    {
        // Disable full resolution framebuffer on Retina displays,
        // so that Minecraft can actually keep up.
        GLFW.glfwWindowHint(GLFW.GLFW_COCOA_RETINA_FRAMEBUFFER, GLFW.GLFW_FALSE);
    }
}
