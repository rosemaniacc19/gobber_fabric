package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.enchantments.*;
import com.kwpugh.gobber2.enchantments.summoner.SummonerEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EnchantmentInit 
{
	public static final Enchantment SMITHBLADE = new SmithBladeEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
	public static final Enchantment REBUFFING = new RebuffingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
	public static final Enchantment UNTOUCHABLE = new UntouchableEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR_LEGS, new EquipmentSlot[]{EquipmentSlot.LEGS});
	public static final Enchantment FASTEROBSIDIAN = new FasterObsidianEnchantment(Enchantment.Rarity.COMMON, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
	public static final Enchantment QUICKUSE = new QuickUseEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEARABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
	public static final Enchantment BLINDERS = new BlindersEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
	public static final Enchantment SOLIDFOOTING = new SolidFootingEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
	public static final Enchantment APOTROPAIC = new ApotropaicEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
	public static final Enchantment FLUID_MASTER = new FluidMasterEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEARABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
	public static final Enchantment SUMMONER = new SummonerEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
	public static final Enchantment QUIETFEET = new QuietFeetEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});

	public static void registerEnchantments()
    {
		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "smithblade"), SMITHBLADE);
		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "rebuffing"), REBUFFING);
		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "untouchable"), UNTOUCHABLE);
		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "faster_obsidian"), FASTEROBSIDIAN);
		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "quickuse"), QUICKUSE);
		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "blinders"), BLINDERS);
		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "solidfooting"), SOLIDFOOTING);
		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "apotropaic"), APOTROPAIC);
		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "fluid_master"), FLUID_MASTER);
		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "summoner"), SUMMONER);
		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "quietfeet"), QUIETFEET);
    }
}