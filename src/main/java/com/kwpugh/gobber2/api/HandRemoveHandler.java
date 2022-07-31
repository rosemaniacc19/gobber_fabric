package com.kwpugh.gobber2.api;

import net.minecraft.entity.player.PlayerEntity;

public interface HandRemoveHandler
{
    // used to detect if item in hand is removed, refer to PlayerEntityMixinMainhand
    void onRemoved(PlayerEntity playerEntity);
}
