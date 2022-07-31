package com.kwpugh.gobber2.client;

import com.kwpugh.gobber2.init.ItemInit;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

/*
 * Needed to produce client side animation of bow pulling with arrow 
 */

public class BowModelPredicates
{
    public static void register()
    {
    ModelPredicateProviderRegistry.register(ItemInit.GOBBER2_BOW, new Identifier("pull"), (stack, world, entity, i) ->
    {
      if (entity == null)
      {
        return 0.0F;
      }
      else
      {
        return entity.getActiveItem() != stack ? 0.0F
            : (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
      }
    });

    ModelPredicateProviderRegistry.register(ItemInit.GOBBER2_BOW, new Identifier("pulling"),
        (stack, world, entity, i) ->
        {
          return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });



    ModelPredicateProviderRegistry.register(ItemInit.GOBBER2_BOW_NETHER, new Identifier("pull"), (stack, world, entity, i) ->
    {
        if (entity == null)
    {
      return 0.0F;
    }
    else
    {
      return entity.getActiveItem() != stack ? 0.0F
          : (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
    }
    });

    ModelPredicateProviderRegistry.register(ItemInit.GOBBER2_BOW_NETHER, new Identifier("pulling"),
      (stack, world, entity, i) ->
    {
        return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
    });



    ModelPredicateProviderRegistry.register(ItemInit.GOBBER2_BOW_END, new Identifier("pull"), (stack, world, entity, i) ->
    {
        if (entity == null)
    {
      return 0.0F;
    }
    else
    {
      return entity.getActiveItem() != stack ? 0.0F
          : (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
    }
    });

    ModelPredicateProviderRegistry.register(ItemInit.GOBBER2_BOW_END, new Identifier("pulling"),
      (stack, world, entity, i) ->
    {
        return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
    });
  }

}