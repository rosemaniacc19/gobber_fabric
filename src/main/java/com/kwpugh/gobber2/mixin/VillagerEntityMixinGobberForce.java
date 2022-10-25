package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.GobberForceManager;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixinGobberForce extends MerchantEntity
{
    public VillagerEntityMixinGobberForce(EntityType<? extends MerchantEntity> entityType, World world)
    {
        super(entityType, world);
    }

    /*
        Gobber Force - Charisma feature:
         used to reduce the cost of trading
         with villagers, but better than
         Hero of the Village
     */
    @Inject(method = "prepareOffersFor", at = @At("TAIL"))
    private void gobberForcePrepareOffersFor(PlayerEntity player, CallbackInfo ci)
    {
        if (Gobber2.CONFIG.GENERAL.enableGobberForce &&
                Gobber2.CONFIG.GENERAL.enableGFChrisma &&
                PlayerEquipUtil.isWearingFullArmor(player) &&
                (GobberForceManager.getGobberForce(player) > Gobber2.CONFIG.GENERAL.forceCharismaLevel))
        {

            int j = offerScale(GobberForceManager.getGobberForce(player));

            Iterator var = this.getOffers().iterator();

            while(var.hasNext())
            {
                TradeOffer tradeOffer2 = (TradeOffer)var.next();
                double d = 0.3D + 0.0625D * (double)j;
                int k = (int)Math.floor(d * (double)tradeOffer2.getOriginalFirstBuyItem().getCount());
                tradeOffer2.increaseSpecialPrice(-Math.max(k, 1));
            }

            GobberForceManager.subtractGobberForce(player, Gobber2.CONFIG.GENERAL.forceCharismaCost);
        }
    }

    public int offerScale(int gobberForce)
    {
        int offerLevel = 0;

        if(gobberForce >= 50 && gobberForce < 100)
        {
            offerLevel = 3;
        }
        else if(gobberForce >= 100 && gobberForce < 300)
        {
            offerLevel = 6;
        }
        else if(gobberForce >= 300 && gobberForce < 500)
        {
            offerLevel = 9;
        }
        else
        {
            offerLevel = 12;
        }

        return offerLevel;
    }
}
