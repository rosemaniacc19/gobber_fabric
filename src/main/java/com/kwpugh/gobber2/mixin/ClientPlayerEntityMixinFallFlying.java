package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;
import com.mojang.authlib.GameProfile;

import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.stat.StatHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixinFallFlying extends AbstractClientPlayerEntity 
{
  @Shadow public final ClientPlayNetworkHandler networkHandler;
  @Shadow protected final MinecraftClient client;

    public ClientPlayerEntityMixinFallFlying(MinecraftClient client, ClientWorld world, ClientPlayNetworkHandler networkHandler, StatHandler stats, ClientRecipeBook recipeBook, boolean lastSneaking, boolean lastSprinting)
    {
      super(world, networkHandler.getProfile(), client.getProfileKeys().getPublicKey().get());
      this.networkHandler = networkHandler;
      this.client = client;
    }

    @Inject(method = "tickMovement()V", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"))
    public void gobberTickMovement(CallbackInfo info)
    {
        ItemStack itemStack = this.getEquippedStack(EquipmentSlot.CHEST);
        if (((itemStack.isOf(ItemInit.GOBBER2_CHESTPLATE_END) && Gobber2.CONFIG.GENERAL.enableGlidingEndArmor)) && this.checkFallFlying())
        {
            this.networkHandler.sendPacket(new ClientCommandC2SPacket(this, ClientCommandC2SPacket.Mode.START_FALL_FLYING));
        }
    }
}