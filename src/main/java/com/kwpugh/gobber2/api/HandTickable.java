package com.kwpugh.gobber2.api;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface HandTickable
{
    // used to tick hand, refer to PlayerEntityMixinMainhand
    void tickHand(ItemStack stack, PlayerEntity playerEntity);
}