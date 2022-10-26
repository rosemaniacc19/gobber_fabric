package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TagInit 
{
	// Fabric c tags used by Lucky Block extra loot feature
	public static final TagKey<Item> COMMON_LOOT = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "common_loot"));
	public static final TagKey<Item> UNCOOMMON_LOOT = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "uncommon_loot"));
	public static final TagKey<Item> RARE_LOOT = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "rare_loot"));
	public static final TagKey<Item> VERY_RARE_LOOT = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "very_rare_loot"));

	// Fabric c tags used by the Ring of Miner
	public static final TagKey<Block> INFESTED = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "infested"));
	public static final TagKey<Block> COBBLESTONE = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "cobblestones"));
	public static final TagKey<Block> DIRT = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "dirts"));
	public static final TagKey<Block> NETHERRACK = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "netherracks"));
	public static final TagKey<Block> SAND = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "sands"));
	public static final TagKey<Block> SANDSTONE = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "sandstones"));
	public static final TagKey<Block> SOUL_GROUND = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "soul_ground"));
	public static final TagKey<Block> STONE = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "stones"));
	public static final TagKey<Block> END_STONES = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "end_stones"));

	// Fabric c tags used by the Staff of Clearing
	public static final TagKey<Block> FLOWERS = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "flowers"));
	public static final TagKey<Block> GRASS = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "grasses"));
	public static final TagKey<Block> BUSHES = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "bushes"));


	// Gobber tag for air blocks
	public static final TagKey<Block> AIR_BLOCKS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Gobber2.MOD_ID, "air_blocks"));

	// Gobber tag for chest loot
	public static final TagKey<Item> CHEST_LOOT = TagKey.of(Registry.ITEM_KEY, new Identifier(Gobber2.MOD_ID, "chest_loot"));

	// Gobber tag for Ring of Attraction ignore feature
	public static final TagKey<Item> RING_ATTRACTION_IGNORE = TagKey.of(Registry.ITEM_KEY, new Identifier(Gobber2.MOD_ID, "ring_attraction_ignore"));

	// Gobber tag for adding more blocks to Ring of the Miner breaking
	public static final TagKey<Block> RING_MINER_ADDITIONS = TagKey.of(Registry.BLOCK_KEY, new Identifier("gobber2", "ring_miner_additions"));
	
	// Gobber tag for adding items to Ring of Repair blacklist
	public static final TagKey<Item> RING_REPAIR_BLACKLIST = TagKey.of(Registry.ITEM_KEY, new Identifier("gobber2", "ring_repair_blacklist"));
}
