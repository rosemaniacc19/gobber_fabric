package com.kwpugh.gobber2.lists;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.item.FoodComponent;

public class FoodList
{
    public static FoodComponent goo = (new FoodComponent.Builder()).hunger(Gobber2.CONFIG.GENERAL.gooHunger).saturationModifier(Gobber2.CONFIG.GENERAL.gooSaturation).build();
    public static FoodComponent gooeyApple = (new FoodComponent.Builder()).hunger(Gobber2.CONFIG.GENERAL.gooeyAppleHunger).saturationModifier(Gobber2.CONFIG.GENERAL.gooeyAppleSaturation).build();
    public static FoodComponent gooeyBread = (new FoodComponent.Builder()).hunger(Gobber2.CONFIG.GENERAL.gooeyBreadHunger).saturationModifier(Gobber2.CONFIG.GENERAL.gooeyBreadSaturation).build();
    public static FoodComponent gooeyBeef = (new FoodComponent.Builder()).hunger(Gobber2.CONFIG.GENERAL.gooeyBeefHunger).saturationModifier(Gobber2.CONFIG.GENERAL.gooeyBeefSaturation).build();
    public static FoodComponent gooeyBeefstew = (new FoodComponent.Builder()).hunger(Gobber2.CONFIG.GENERAL.gooeyBeefStewHunger).saturationModifier(Gobber2.CONFIG.GENERAL.gooeyBeefStewSaturation).build();
    
    public static FoodComponent gooNether = (new FoodComponent.Builder()).hunger(Gobber2.CONFIG.GENERAL.gooHungerNether).saturationModifier(Gobber2.CONFIG.GENERAL.gooSaturationNether).build();
    public static FoodComponent gooeyAppleNether = (new FoodComponent.Builder()).hunger(Gobber2.CONFIG.GENERAL.gooeyAppleHungerNether).saturationModifier(Gobber2.CONFIG.GENERAL.gooeyAppleSaturationNether).build();
    public static FoodComponent gooeyBreadNether = (new FoodComponent.Builder()).hunger(Gobber2.CONFIG.GENERAL.gooeyBreadHungerNether).saturationModifier(Gobber2.CONFIG.GENERAL.gooeyBreadSaturationNether).build();
    public static FoodComponent gooeyBeefNether = (new FoodComponent.Builder()).hunger(Gobber2.CONFIG.GENERAL.gooeyBeefHungerNether).saturationModifier(Gobber2.CONFIG.GENERAL.gooeyBeefSaturationNether).build();
    public static FoodComponent gooeyBeefstewNether = (new FoodComponent.Builder()).hunger(Gobber2.CONFIG.GENERAL.gooeyBeefStewHungerNether).saturationModifier(Gobber2.CONFIG.GENERAL.gooeyBeefStewSaturationNether).build();
}