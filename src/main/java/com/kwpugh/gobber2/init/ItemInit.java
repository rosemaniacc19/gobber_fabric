package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.items.armor.DragonArmor;
import com.kwpugh.gobber2.items.armor.EndArmor;
import com.kwpugh.gobber2.items.armor.GobberArmor;
import com.kwpugh.gobber2.items.armor.NetherArmor;
import com.kwpugh.gobber2.items.arrows.GobberArrowEndItem;
import com.kwpugh.gobber2.items.arrows.GobberArrowItem;
import com.kwpugh.gobber2.items.arrows.GobberArrowNetherItem;
import com.kwpugh.gobber2.items.food.*;
import com.kwpugh.gobber2.items.fuel.Foo;
import com.kwpugh.gobber2.items.fuel.FooEnd;
import com.kwpugh.gobber2.items.medallions.*;
import com.kwpugh.gobber2.items.other.SpecialItem;
import com.kwpugh.gobber2.items.rings.*;
import com.kwpugh.gobber2.items.staffs.*;
import com.kwpugh.gobber2.items.tools.areatools.ModExcavator;
import com.kwpugh.gobber2.items.tools.areatools.ModHammer;
import com.kwpugh.gobber2.items.tools.areatools.ModTreeAxe;
import com.kwpugh.gobber2.items.tools.basetools.*;
import com.kwpugh.gobber2.items.tools.endtools.*;
import com.kwpugh.gobber2.lists.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.StewItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit
{
	static float gobberAttackSpeed = Gobber2.CONFIG.GENERAL.gobberSwordAttackSpeed;
	static float netherAttackSpeed = Gobber2.CONFIG.GENERAL.gobberNetherSwordAttackSpeed;
	static float endAttackSpeed = Gobber2.CONFIG.GENERAL.gobberEndSwordAttackSpeed;
	static int staffTransformationDurability = Gobber2.CONFIG.GENERAL.staffTransformationDurability;
	static int gobberBowDurability = Gobber2.CONFIG.GENERAL.gobberBowDurability;
	static int netherBowDurability = Gobber2.CONFIG.GENERAL.gobberNetherBowDurability;
	static int endBowDurability = Gobber2.CONFIG.GENERAL.gobberEndBowDurability;

	// Declare material values
	public static final ArmorMaterial GOBBER_ARMOR_MATERIAL = new GobberArmorMaterial();
	public static final ArmorMaterial GOBBER_NETHER_ARMOR_MATERIAL = new NetherArmorMaterial();
	public static final ArmorMaterial GOBBER_END_ARMOR_MATERIAL = new EndArmorMaterial();
	public static final ArmorMaterial GOBBER_DRAGON_ARMOR_MATERIAL = new DragonArmorMaterial();

	public static final ToolMaterial GOBBER_TOOL_MATERIAL = new GobberToolMaterial();
	public static final ToolMaterial GOBBER_NETHER_TOOL_MATERIAL = new NetherToolMaterial();
	public static final ToolMaterial GOBBER_END_TOOL_MATERIAL = new EndToolMaterial();

	public static final Item GOBBER2_GLOBETTE = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GLOBETTE_NETHER = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GLOBETTE_END = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_GLOB = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GLOB_NETHER = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GLOB_END = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_FOO = new Foo((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_FOO_NETHER = new Foo((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_FOO_END = new FooEnd((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_GOO = new Goo((new Item.Settings()).food(FoodList.goo).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GOOEY_APPLE = new GooeyApple((new Item.Settings()).food(FoodList.gooey_apple).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GOOEY_BREAD = new GooeyBread((new Item.Settings()).food(FoodList.gooey_bread).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GOOEY_BEEF = new GooeyBeef((new Item.Settings()).food(FoodList.gooey_beef).group(Gobber2.GOBBER2_GROUP));
	public static final StewItem GOBBER2_GOOEY_BEEFSTEW = new GooeyBeefstew((new Item.Settings()).food(FoodList.gooey_beefstew).maxCount(1).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_GOO_NETHER = new Goo((new Item.Settings()).food(FoodList.goo_nether).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GOOEY_APPLE_NETHER = new GooeyApple((new Item.Settings()).food(FoodList.gooey_apple_nether).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GOOEY_BREAD_NETHER = new GooeyBread((new Item.Settings()).food(FoodList.gooey_bread_nether).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GOOEY_BEEF_NETHER = new GooeyBeef((new Item.Settings()).food(FoodList.gooey_beef_nether).group(Gobber2.GOBBER2_GROUP));
	public static final StewItem GOBBER2_GOOEY_BEEFSTEW_NETHER = new GooeyBeefstew((new Item.Settings()).food(FoodList.gooey_beefstew_nether).maxCount(1).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_INGOT = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_INGOT_NETHER = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_INGOT_END = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_ROD = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_ROD_NETHER = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_ROD_END = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_RING = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_NETHER = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_END = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_MEDALLION = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_NETHER = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_END = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_SWORD = new ModSword(GOBBER_TOOL_MATERIAL, 1, gobberAttackSpeed - 4.0F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_SWORD_NETHER = new ModSword(GOBBER_NETHER_TOOL_MATERIAL, 3, netherAttackSpeed - 4.0F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_SWORD_END = new SwordEnd(GOBBER_END_TOOL_MATERIAL, 6, endAttackSpeed - 4.0F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_SWORD_SNIPER = new SwordEndSniper(GOBBER_END_TOOL_MATERIAL, 6, endAttackSpeed - 4.0F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_BOW = new ModBow((new Item.Settings()).maxDamage(gobberBowDurability).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_BOW_NETHER = new ModBow((new Item.Settings()).maxDamage(netherBowDurability).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_BOW_END = new BowEnd((new Item.Settings()).maxDamage(endBowDurability).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_ARROW = new GobberArrowItem((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_ARROW_NETHER = new GobberArrowNetherItem((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_ARROW_END = new GobberArrowEndItem((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_PICKAXE = new ModPickaxe(GOBBER_TOOL_MATERIAL, -4, -2.4F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_PICKAXE_NETHER = new ModPickaxe(GOBBER_NETHER_TOOL_MATERIAL, -3, -2.3F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_PICKAXE_END = new PickaxeEnd(GOBBER_END_TOOL_MATERIAL, -2, -2.2F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_AXE = new ModAxe(GOBBER_TOOL_MATERIAL, 1.0F, -2.9F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_AXE_NETHER = new ModAxe(GOBBER_NETHER_TOOL_MATERIAL, 3.0F, -2.8F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_AXE_END = new AxeEnd(GOBBER_END_TOOL_MATERIAL, 6.0F, -2.7F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_SHOVEL = new ModShovel(GOBBER_TOOL_MATERIAL, -3.0F, -3.0F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_SHOVEL_NETHER = new ModShovel(GOBBER_NETHER_TOOL_MATERIAL, -2.0F, -2.8F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_SHOVEL_END = new ShovelEnd(GOBBER_END_TOOL_MATERIAL, 0.0F, -2.6F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_HOE = new ModHoe(GOBBER_TOOL_MATERIAL, -9, 2.1F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_HOE_NETHER = new ModHoe(GOBBER_NETHER_TOOL_MATERIAL, -9, 2.2F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_HOE_END = new HoeEnd(GOBBER_END_TOOL_MATERIAL, -9, 2.2F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_PAXEL = new ModPaxel(GOBBER_TOOL_MATERIAL, 0.0F, -2.9F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_PAXEL_NETHER = new ModPaxel(GOBBER_NETHER_TOOL_MATERIAL, 2.0F, -2.8F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_PAXEL_END = new PaxelEnd(GOBBER_END_TOOL_MATERIAL, 5.0F, -2.7F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_PAXEL_STARS = new PaxelEndStars(GOBBER_END_TOOL_MATERIAL, 5.0F, -2.7F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_HAMMER = new ModHammer(GOBBER_TOOL_MATERIAL, -1, -2.4F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_HAMMER_NETHER = new ModHammer(GOBBER_NETHER_TOOL_MATERIAL, 0, -2.3F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_HAMMER_END = new HammerEnd(GOBBER_END_TOOL_MATERIAL, 1, -2.2F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_EXCAVATOR = new ModExcavator(GOBBER_TOOL_MATERIAL, -3.0F, -3.0F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_EXCAVATOR_NETHER = new ModExcavator(GOBBER_NETHER_TOOL_MATERIAL, -2.0F, -2.8F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_EXCAVATOR_END = new ExcavatorEnd(GOBBER_END_TOOL_MATERIAL, 0.0F, -2.6F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_TREE_AXE = new ModTreeAxe(GOBBER_TOOL_MATERIAL, 1.0F, -2.9F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_TREE_AXE_NETHER = new ModTreeAxe(GOBBER_NETHER_TOOL_MATERIAL, 3.0F, -2.8F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_TREE_AXE_END = new TreeAxeEnd(GOBBER_END_TOOL_MATERIAL, 6.0F, -2.7F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_HELMET = new GobberArmor(GOBBER_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_CHESTPLATE = new GobberArmor(GOBBER_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_LEGGINGS = new GobberArmor(GOBBER_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_BOOTS = new GobberArmor(GOBBER_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_HELMET_NETHER = new NetherArmor(GOBBER_NETHER_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_CHESTPLATE_NETHER = new NetherArmor(GOBBER_NETHER_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_LEGGINGS_NETHER = new NetherArmor(GOBBER_NETHER_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_BOOTS_NETHER = new NetherArmor(GOBBER_NETHER_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_LINKS_END = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_HELMET_END = new EndArmor(GOBBER_END_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_CHESTPLATE_END = new EndArmor(GOBBER_END_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_LEGGINGS_END = new EndArmor(GOBBER_END_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_BOOTS_END = new EndArmor(GOBBER_END_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item DRAGON_ELYTRA = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item DRAGON_STAR = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_HELMET_DRAGON = new DragonArmor(GOBBER_DRAGON_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_CHESTPLATE_DRAGON = new DragonArmor(GOBBER_DRAGON_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_LEGGINGS_DRAGON = new DragonArmor(GOBBER_DRAGON_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_BOOTS_DRAGON = new DragonArmor(GOBBER_DRAGON_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_RING_ATTRACTION = new RingAttraction((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_RETURN = new RingReturn((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_SWIFTNESS = new RingSwiftness((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_FARMER = new RingFarmer((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_MINER = new RingMiner((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_ASCENT = new RingAscent((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_SUNSHINE = new RingSunshine((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_ABOVE = new RingAbove((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_RING_CURING = new RingCuring((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_LUCK = new RingLuck((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_VISION = new RingVision((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_STRENGTH = new RingStrength((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_HASTE = new RingHaste((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_PHOENIX = new RingPhoenix((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_REPAIR = new RingRepair((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_AIRWALKING = new RingAirWalking((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_RING_ENDERCHEST = new RingEnderchest((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_EXPLORER = new RingExplorer((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_BLINK = new RingBlink((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_VOID = new RingVoid((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_TELEPORT = new RingTeleport((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_DISMISSAL = new RingDismissal((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_STEALTH = new RingStealth((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_TRAVELER = new RingTraveler((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_MEDALLION_BREATHING = new MedallionBreathing((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_HEALING = new MedallionHealing((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_HERO = new MedallionHero((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_EXP = new MedallionExp((new Item.Settings()));
	public static final Item GOBBER2_MEDALLION_HEALING2 = new MedallionHealing2((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_SHIELDING = new MedallionShielding((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_SEA = new MedallionSea((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_HEALING3 = new MedallionHealing3((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_SUFFERING = new MedallionSuffering((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_STAFF_CLEARING = new StaffClearing((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_TRANSFORMATION = new StaffTransformation((new Item.Settings()).maxDamage(staffTransformationDurability).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_ENSNAREMENT = new StaffEnsnarement((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_FARMER = new StaffFarmer((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_STARS = new StaffStars((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_NATURE = new StaffNature((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_HOSTILE_ENSNAREMENT = new StaffHostileEnsnarement((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_SNIPER = new StaffSniper((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_CHANNELING = new StaffChanneling((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_SPECIAL_ITEM = new SpecialItem((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));


	public static void registerItems()
	{
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_globette"), GOBBER2_GLOBETTE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_globette_nether"), GOBBER2_GLOBETTE_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_globette_end"), GOBBER2_GLOBETTE_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_glob"), GOBBER2_GLOB);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_glob_nether"), GOBBER2_GLOB_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_glob_end"), GOBBER2_GLOB_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_foo"), GOBBER2_FOO);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_foo_nether"), GOBBER2_FOO_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_foo_end"), GOBBER2_FOO_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_goo"), GOBBER2_GOO);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_apple"), GOBBER2_GOOEY_APPLE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_bread"), GOBBER2_GOOEY_BREAD);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_beef"), GOBBER2_GOOEY_BEEF);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_beefstew"), GOBBER2_GOOEY_BEEFSTEW);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_goo_nether"), GOBBER2_GOO_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_apple_nether"), GOBBER2_GOOEY_APPLE_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_bread_nether"), GOBBER2_GOOEY_BREAD_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_beef_nether"), GOBBER2_GOOEY_BEEF_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_beefstew_nether"), GOBBER2_GOOEY_BEEFSTEW_NETHER);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ingot"), GOBBER2_INGOT);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ingot_nether"), GOBBER2_INGOT_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ingot_end"), GOBBER2_INGOT_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_rod"), GOBBER2_ROD);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_rod_nether"), GOBBER2_ROD_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_rod_end"), GOBBER2_ROD_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring"), GOBBER2_RING);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_nether"), GOBBER2_RING_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_end"), GOBBER2_RING_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion"), GOBBER2_MEDALLION);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_nether"), GOBBER2_MEDALLION_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_end"), GOBBER2_MEDALLION_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_sword"), GOBBER2_SWORD);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_sword_nether"), GOBBER2_SWORD_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_sword_end"), GOBBER2_SWORD_END);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_sword_sniper"), GOBBER2_SWORD_SNIPER);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_bow"), GOBBER2_BOW);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_bow_nether"), GOBBER2_BOW_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_bow_end"), GOBBER2_BOW_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_arrow"), GOBBER2_ARROW);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_arrow_nether"), GOBBER2_ARROW_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_arrow_end"), GOBBER2_ARROW_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_pickaxe"), GOBBER2_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_pickaxe_nether"), GOBBER2_PICKAXE_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_pickaxe_end"), GOBBER2_PICKAXE_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_axe"), GOBBER2_AXE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_axe_nether"), GOBBER2_AXE_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_axe_end"), GOBBER2_AXE_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_shovel"), GOBBER2_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_shovel_nether"), GOBBER2_SHOVEL_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_shovel_end"), GOBBER2_SHOVEL_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_hoe"), GOBBER2_HOE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_hoe_nether"), GOBBER2_HOE_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_hoe_end"), GOBBER2_HOE_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_paxel"), GOBBER2_PAXEL);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_paxel_nether"), GOBBER2_PAXEL_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_paxel_end"), GOBBER2_PAXEL_END);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_paxel_stars"), GOBBER2_PAXEL_STARS);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_hammer"), GOBBER2_HAMMER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_hammer_nether"), GOBBER2_HAMMER_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_hammer_end"), GOBBER2_HAMMER_END);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_excavator"), GOBBER2_EXCAVATOR);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_excavator_nether"), GOBBER2_EXCAVATOR_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_excavator_end"), GOBBER2_EXCAVATOR_END);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_tree_axe"), GOBBER2_TREE_AXE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_tree_axe_nether"), GOBBER2_TREE_AXE_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_tree_axe_end"), GOBBER2_TREE_AXE_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_helmet"), GOBBER2_HELMET);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_chestplate"), GOBBER2_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_leggings"), GOBBER2_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_boots"), GOBBER2_BOOTS);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_helmet_nether"), GOBBER2_HELMET_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_chestplate_nether"), GOBBER2_CHESTPLATE_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_leggings_nether"), GOBBER2_LEGGINGS_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_boots_nether"), GOBBER2_BOOTS_NETHER);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_links_end"), GOBBER2_LINKS_END);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_helmet_end"), GOBBER2_HELMET_END);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_chestplate_end"), GOBBER2_CHESTPLATE_END);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_leggings_end"), GOBBER2_LEGGINGS_END);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_boots_end"), GOBBER2_BOOTS_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "dragon_elytra"), DRAGON_ELYTRA);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "dragon_star"), DRAGON_STAR);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_helmet_dragon"), GOBBER2_HELMET_DRAGON);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_chestplate_dragon"), GOBBER2_CHESTPLATE_DRAGON);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_leggings_dragon"), GOBBER2_LEGGINGS_DRAGON);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_boots_dragon"), GOBBER2_BOOTS_DRAGON);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_attraction"), GOBBER2_RING_ATTRACTION);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_return"), GOBBER2_RING_RETURN);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_swiftness"), GOBBER2_RING_SWIFTNESS);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_farmer"), GOBBER2_RING_FARMER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_miner"), GOBBER2_RING_MINER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_ascent"), GOBBER2_RING_ASCENT);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_sunshine"), GOBBER2_RING_SUNSHINE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_above"), GOBBER2_RING_ABOVE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_luck"), GOBBER2_RING_LUCK);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_curing"), GOBBER2_RING_CURING);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_vision"), GOBBER2_RING_VISION);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_strength"), GOBBER2_RING_STRENGTH);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_haste"), GOBBER2_RING_HASTE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_phoenix"), GOBBER2_RING_PHOENIX);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_repair"), GOBBER2_RING_REPAIR);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_airwalking"), GOBBER2_RING_AIRWALKING);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_enderchest"), GOBBER2_RING_ENDERCHEST);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_explorer"), GOBBER2_RING_EXPLORER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_blink"), GOBBER2_RING_BLINK);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_void"), GOBBER2_RING_VOID);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_teleport"), GOBBER2_RING_TELEPORT);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_dismissal"), GOBBER2_RING_DISMISSAL);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_stealth"), GOBBER2_RING_STEALTH);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_traveler"), GOBBER2_RING_TRAVELER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_breathing"), GOBBER2_MEDALLION_BREATHING);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_healing"), GOBBER2_MEDALLION_HEALING);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_hero"), GOBBER2_MEDALLION_HERO);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_exp"), GOBBER2_MEDALLION_EXP);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_healing2"), GOBBER2_MEDALLION_HEALING2);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_shielding"), GOBBER2_MEDALLION_SHIELDING);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_sea"), GOBBER2_MEDALLION_SEA);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_healing3"), GOBBER2_MEDALLION_HEALING3);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_suffering"), GOBBER2_MEDALLION_SUFFERING);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_clearing"), GOBBER2_STAFF_CLEARING);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_transformation"), GOBBER2_STAFF_TRANSFORMATION);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_ensnarement"), GOBBER2_STAFF_ENSNAREMENT);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_farmer"), GOBBER2_STAFF_FARMER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_nature"), GOBBER2_STAFF_NATURE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_stars"), GOBBER2_STAFF_STARS);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_hostile_ensnarement"), GOBBER2_STAFF_HOSTILE_ENSNAREMENT);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_sniper"), GOBBER2_STAFF_SNIPER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_channeling"), GOBBER2_STAFF_CHANNELING);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_special_item"), GOBBER2_SPECIAL_ITEM);
	}
}