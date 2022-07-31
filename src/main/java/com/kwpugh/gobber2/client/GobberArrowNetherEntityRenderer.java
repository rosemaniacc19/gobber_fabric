package com.kwpugh.gobber2.client;

import com.kwpugh.gobber2.items.arrows.GobberArrowNetherEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class GobberArrowNetherEntityRenderer extends ProjectileEntityRenderer<GobberArrowNetherEntity>
{
    public static final Identifier TEXTURE = new Identifier("gobber2:textures/entity/gobber2_arrow_nether.png");

    public GobberArrowNetherEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(GobberArrowNetherEntity entity)
    {
        return TEXTURE;
    }
}