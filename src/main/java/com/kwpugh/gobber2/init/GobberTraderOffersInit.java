package com.kwpugh.gobber2.init;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;

public class GobberTraderOffersInit
{
    public static void register()
    {
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.BOOK), EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantmentInit.QUICKUSE, 1)), 3, 2, 0.05f)));
            factories.add(((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 6), new ItemStack(Items.AMETHYST_SHARD, 4), new ItemStack(ItemInit.GOBBER2_INGOT), 3, 2, 0.05f)));
        });
    }
}