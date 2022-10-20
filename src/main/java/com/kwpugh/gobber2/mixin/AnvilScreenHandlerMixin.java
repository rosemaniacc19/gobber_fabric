package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.items.tools.special.EnhancedGobberSword;
import com.kwpugh.gobber2.util.GobberForceManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin
{
    @Shadow @Final private Property levelCost;
    @Shadow private String newItemName;

    @Inject(method = "canTakeOutput", at = @At("HEAD"), cancellable = true)
    public void gobberCanTakeOutput(PlayerEntity player, boolean present, CallbackInfoReturnable<Boolean> cir)
    {
        ForgingScreenHandlerAccessor accessor = (ForgingScreenHandlerAccessor) (Object) this;

        if(accessor.getInput().getStack(1).getItem().equals(ItemInit.GOBBER2_STAFF_CHANNELING))
        {
            cir.setReturnValue(GobberForceManager.getGobberForce(player) > 350);
            cir.cancel();
        }
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    public void emeraldUpdateResult(CallbackInfo ci)
    {
        ForgingScreenHandlerAccessor accessor = (ForgingScreenHandlerAccessor) this;

        EnhancedGobberSword.anvilAction(accessor, levelCost,  newItemName, ci);
    }
}
