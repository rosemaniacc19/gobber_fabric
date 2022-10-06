package com.kwpugh.gobber2.init;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;

public class TraderOffersInit
{
    public static void registerOffers()
    {
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 8), new ItemStack(Items.BOOK), EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantmentInit.QUICKUSE, 1)), 3, 2, 0.05f)));
            factories.add(((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 6), new ItemStack(Items.BOOK), EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantmentInit.SMITHBLADE, 1)), 3, 2, 0.05f)));
            factories.add(((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.BOOK), EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantmentInit.REBUFFING, 1)), 3, 2, 0.05f)));
            factories.add(((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.BOOK), EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantmentInit.SOLIDFOOTING, 1)), 3, 2, 0.05f)));
            factories.add(((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.BOOK), EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EnchantmentInit.EXP_BOOST, 1)), 3, 2, 0.05f)));
            factories.add(((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 6), new ItemStack(Items.AMETHYST_SHARD, 4), new ItemStack(ItemInit.GOBBER2_INGOT), 3, 2, 0.05f)));
        });
    }
}