package com.kwpugh.gobber2.events;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class LootTableEventInit
{
	static boolean lootEnable = Gobber2.CONFIG.GENERAL.lootEnable;
	static float lootChance = Gobber2.CONFIG.GENERAL.lootChance;

    public static void registerLoot()
    {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(lootEnable)
            {
                if(id.equals(LootTables.DESERT_PYRAMID_CHEST) ||
                        id.equals(LootTables.JUNGLE_TEMPLE_CHEST) ||
                        id.equals(LootTables.IGLOO_CHEST_CHEST) ||
                        id.equals(LootTables.PILLAGER_OUTPOST_CHEST))
                {
                    LootPool GOBBER_RING_RETURN = LootPool.builder()
                            .with(ItemEntry.builder(ItemInit.GOBBER2_RING_RETURN))
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(lootChance).build())
                            .build();

                    tableBuilder.pool(GOBBER_RING_RETURN);
                }

                if(id.equals(LootTables.STRONGHOLD_LIBRARY_CHEST) ||
                        id.equals(LootTables.PILLAGER_OUTPOST_CHEST) ||
                        id.equals(LootTables.NETHER_BRIDGE_CHEST) ||
                        id.equals(LootTables.SHIPWRECK_TREASURE_CHEST))
                {
                    LootPool GOBBER_RING_TELEPORT = LootPool.builder()
                            .with(ItemEntry.builder(ItemInit.GOBBER2_RING_TELEPORT))
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(lootChance).build())
                            .build();

                    tableBuilder.pool(GOBBER_RING_TELEPORT);
                }
            }
        });
    }
}