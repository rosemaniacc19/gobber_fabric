package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.effects.Experience;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EffectsInit 
{	
	public static final StatusEffect EXPERIENCE = new Experience();

    public static void registerEffects()   
    {
    	Registry.register(Registry.STATUS_EFFECT, new Identifier(Gobber2.MOD_ID, "experience"), EXPERIENCE);
    }
}