package com.kwpugh.gobber2.items.tools.special;

import com.kwpugh.gobber2.init.EnchantmentInit;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.items.tools.basetools.ModBow;
import com.kwpugh.gobber2.items.tools.basetools.ModPickaxe;
import com.kwpugh.gobber2.items.tools.basetools.ModSword;
import com.kwpugh.gobber2.mixin.ForgingScreenHandlerAccessor;
import com.kwpugh.gobber2.util.GobberForceManager;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.Property;
import net.minecraft.text.Text;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class EnhancedGobberSword
{
    public static void anvilAction(ForgingScreenHandlerAccessor accessor, Property levelCost, String newItemName, CallbackInfo ci)
    {
        if(accessor.getInput().getStack(1).getItem().equals(ItemInit.GOBBER2_STAFF_CHANNELING))
        {
            ItemStack result = accessor.getInput().getStack(0).copy();
            //result.getOrCreateNbt().putByte("Unbreakable", (byte) 1);

            if(result.getItem() instanceof ModPickaxe)
            {
                result.addEnchantment(Enchantments.FORTUNE, 3);
                result.addEnchantment(Enchantments.EFFICIENCY, 5);
                result.addEnchantment(Enchantments.UNBREAKING, 5);
            }
            else if(result.getItem() instanceof ModSword)
            {
                result.addEnchantment(Enchantments.LOOTING, 3);
                result.addEnchantment(Enchantments.SHARPNESS, 4);
                result.addEnchantment(EnchantmentInit.SMITHBLADE, 1);
                result.addEnchantment(Enchantments.UNBREAKING, 5);
            }
            else if(result.getItem() instanceof ModBow)
            {
                result.addEnchantment(Enchantments.INFINITY, 1);
                result.addEnchantment(Enchantments.POWER, 5);
                result.addEnchantment(Enchantments.PUNCH, 5);
                result.addEnchantment(Enchantments.UNBREAKING, 5);
            }

            if(!StringUtils.isBlank(newItemName))
            {
                result.setCustomName(Text.translatable(newItemName));
            }
            else
            {
                result.removeCustomName();
            }

            accessor.getOutput().setStack(0, result);
            levelCost.set(30);
            ci.cancel();
        }
    }
}
