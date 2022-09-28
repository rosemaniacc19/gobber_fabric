package com.kwpugh.gobber2.items.staffs;

import com.kwpugh.gobber2.util.EnsnarementUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

/*
 * This item also relies on mixins
 * into VillagerEntity, AbstractDonkeyEntity, and
 * WanderingTraderEntity to change interactMob
 * methods and bypass usually GUIs
 *
 */
public class StaffHostileEnsnarement extends BaseStaff
{
    public StaffHostileEnsnarement(Settings settings)
    {
        super(settings);
    }

    // Right-click on entity, if right type, save entity info to tag and delete entity
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand)
    {
        if(!player.world.isClient)
        {
            if((stack.getOrCreateSubNbt("captured_entity").isEmpty()) &&
                    (entity instanceof AnimalEntity ||
                            entity instanceof MerchantEntity ||
                            entity instanceof GolemEntity ||
                            entity instanceof SquidEntity ||
                            entity instanceof FishEntity ||
                            entity instanceof DolphinEntity ||
                            entity instanceof AllayEntity ||
                            entity instanceof BatEntity ||
                            ((entity instanceof HostileEntity) && !(entity instanceof WitherEntity))))
            {
                if(EnsnarementUtil.saveEntityToStack(entity, stack))
                {
                    player.setStackInHand(hand, stack);
                }

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.SUCCESS;
    }

    // Right-click on block, if staff has stored entity set it's position, spawn it in, and remove tags on staff
    @SuppressWarnings("resource")
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        ItemStack stack = context.getStack();
        if(!(context.getWorld() instanceof ServerWorld)) return ActionResult.SUCCESS;;
        if(!context.getWorld().isClient && stack.hasNbt() && stack.getNbt().contains("captured_entity"))
        {
            EnsnarementUtil.respawnEntity(context, stack);

            return ActionResult.SUCCESS;
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public boolean hasGlint(ItemStack stack)
    {
        return stack.hasNbt() && !stack.getOrCreateSubNbt("captured_entity").isEmpty();
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip.add(Text.translatable("item.gobber2.gobber2_staff_hostile_ensnarement.tip1").formatted(Formatting.GREEN));

        if (stack.hasNbt() && !stack.getOrCreateSubNbt("captured_entity").isEmpty())
        {
            tooltip.add((Text.translatable("item.gobber2.gobber2_staff_ensnarement.tip3", stack.getNbt().getString("name")).formatted(Formatting.GREEN)));
        }
    }
}