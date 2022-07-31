package com.kwpugh.gobber2.client;

import com.kwpugh.gobber2.init.BlockInit;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.render.RenderLayer;

public class BlockRenderLayerMap
{
    public static void init()
    {
        BlockRenderLayerMapImpl.INSTANCE.putBlocks(RenderLayer.getCutout(),
                BlockInit.GOBBER2_GLASS,
                BlockInit.GOBBER2_GLASS_NETHER,
                BlockInit.GOBBER2_GLASS_END,
                BlockInit.CLEAR_GLASS
        );
    }
}
