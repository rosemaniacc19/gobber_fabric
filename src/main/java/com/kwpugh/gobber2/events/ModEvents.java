package com.kwpugh.gobber2.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;

public class ModEvents
{
    public static void register()
    {
        LootTableEventInit.registerLoot();
        ElytraEvent.init();
        PlayerBlockBreakEvents.AFTER.register(PlayerBlockBreak::onBlockBreak);
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(new AfterKilledOtherEntityEvent());
        UseEntityCallback.EVENT.register(EntityInteractEvent::onUseEntity);
        SummonerEvent.register();
        SummonerManager.init();
    }
}
