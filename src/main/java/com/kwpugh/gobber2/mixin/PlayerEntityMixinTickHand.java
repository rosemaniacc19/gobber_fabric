package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.api.HandRemoveHandler;
import com.kwpugh.gobber2.api.HandTickable;
import com.kwpugh.gobber2.util.ItemUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixinTickHand extends LivingEntity
{
    @Shadow public abstract Iterable<ItemStack> getHandItems();

    protected PlayerEntityMixinTickHand(EntityType<? extends LivingEntity> type, World world)
    {
        super(type, world);
    }

    private final DefaultedList<ItemStack> gobber_handcache = DefaultedList.ofSize(2, ItemStack.EMPTY);

    @Inject(method = "tick", at = @At("HEAD"))
    public void gobberTickHand(CallbackInfo ci)
    {
        // Check if hands contains an item
        int i = 0;
        for (ItemStack stack : getHandItems())
        {
            ItemStack cachedStack = gobber_handcache.get(i);

            if (!ItemUtils.isItemEqual(cachedStack, stack, false, false))
            {
                if (cachedStack.getItem() instanceof HandRemoveHandler)
                {
                    ((HandRemoveHandler) cachedStack.getItem()).onRemoved((PlayerEntity) (Object) this);
                }
                gobber_handcache.set(i, stack.copy());
            }
            i++;

            if (!stack.isEmpty() && stack.getItem() instanceof HandTickable)
            {
                ((HandTickable) stack.getItem()).tickHand(stack, (PlayerEntity) (Object) this);
            }
        }
    }
}
