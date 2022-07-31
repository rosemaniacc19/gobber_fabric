package com.kwpugh.gobber2;

import com.kwpugh.gobber2.client.*;


import com.kwpugh.gobber2.init.EntityInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class Gobber2Client implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
		BlockRenderLayerMap.init();
        BowModelPredicates.register();
        EntityRendererRegistry.register(EntityInit.GOBBER2_ARROW, GobberArrowEntityRenderer::new);
        EntityRendererRegistry.register(EntityInit.GOBBER2_ARROW_NETHER, GobberArrowNetherEntityRenderer::new);
        EntityRendererRegistry.register(EntityInit.GOBBER2_ARROW_END, GobberArrowEndEntityRenderer::new);
    }
}