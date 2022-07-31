package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class FuelInit 
{
	public static void registerFuels() 
	{
		FuelRegistry registry = FuelRegistry.INSTANCE;

		registry.add(ItemInit.GOBBER2_FOO, Gobber2.CONFIG.GENERAL.fooBurntime);
		registry.add(ItemInit.GOBBER2_FOO_NETHER, Gobber2.CONFIG.GENERAL.fooNetherBurntime);
		registry.add(ItemInit.GOBBER2_FOO_END, Gobber2.CONFIG.GENERAL.fooEndBurntime);
	}
}