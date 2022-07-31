package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.items.arrows.GobberArrowEndEntity;
import com.kwpugh.gobber2.items.arrows.GobberArrowEntity;
import com.kwpugh.gobber2.items.arrows.GobberArrowNetherEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public class EntityInit
{
    public static EntityType<GobberArrowEntity> GOBBER2_ARROW;
    public static EntityType<GobberArrowNetherEntity> GOBBER2_ARROW_NETHER;
    public static EntityType<GobberArrowEndEntity> GOBBER2_ARROW_END;

    public static void registerEntities()
    {
        GOBBER2_ARROW = register("gobber2_arrow", createArrowEntityType(GobberArrowEntity::new));
        GOBBER2_ARROW_NETHER = register("gobber2_arrow_nether", createArrowEntityType(GobberArrowNetherEntity::new));
        GOBBER2_ARROW_END = register("gobber2_arrow_end", createArrowEntityType(GobberArrowEndEntity::new));
    }

    private static <T extends Entity> EntityType<T> register(String s, EntityType<T> entityType)
    {
        return Registry.register(Registry.ENTITY_TYPE, Gobber2.MOD_ID + ":" + s, entityType);
    }

    private static <T extends Entity> EntityType<T> createArrowEntityType(EntityType.EntityFactory<T> factory)
    {
        return FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory).dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeBlocks(4).trackedUpdateRate(20).build();
    }
}
