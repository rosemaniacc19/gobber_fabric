package com.kwpugh.gobber2.client;

import com.kwpugh.gobber2.items.arrows.GobberArrowEndEntity;
import com.kwpugh.gobber2.items.arrows.GobberArrowNetherEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class GobberArrowEndEntityRenderer extends ProjectileEntityRenderer<GobberArrowEndEntity>
{
    public static final Identifier TEXTURE = new Identifier("gobber2:textures/entity/gobber2_arrow_end.png");

    public GobberArrowEndEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(GobberArrowEndEntity entity)
    {
        return TEXTURE;
    }
}