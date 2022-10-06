package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public class EnsnarementUtil
{
    static boolean enableHostileUse = Gobber2.CONFIG.GENERAL.staffEnsnarementHotileMobs;

    // Right-click on entity, if right type, save entity info to tag and delete entity
    public static ActionResult captureMobs(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand)
    {
        if(!player.world.isClient)
        {
            if((enableHostileUse) && (stack.getOrCreateSubNbt("captured_entity").isEmpty()) &&
                    (entity instanceof HostileEntity) && !(entity instanceof WitherEntity))
            {
                if(EnsnarementUtil.saveEntityToStack(entity, stack))
                {
                    player.setStackInHand(hand, stack);
                }

                return ActionResult.SUCCESS;
            }

            if((stack.getOrCreateSubNbt("captured_entity").isEmpty()) &&
                    (entity instanceof AnimalEntity ||
                            entity instanceof MerchantEntity ||
                            entity instanceof GolemEntity ||
                            entity instanceof SquidEntity ||
                            entity instanceof FishEntity ||
                            entity instanceof DolphinEntity ||
                            entity instanceof AllayEntity ||
                            entity instanceof BatEntity))
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

    // Right-click on entity, if right type, save entity info to tag and delete entity
    public static ActionResult captureHostileMobs(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand)
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
                            entity instanceof GhastEntity ||
                            entity instanceof PhantomEntity ||
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

    public static void respawnEntity(ItemUsageContext context, ItemStack stack)
    {
        ServerWorld serverWorld = (ServerWorld) context.getWorld();
        BlockPos pos = context.getBlockPos().offset(context.getSide());
        ServerPlayerEntity player = (ServerPlayerEntity) context.getPlayer();

        NbtCompound entityTag = context.getStack().getSubNbt("captured_entity");   // KEEP

        Optional<Entity> entity = EntityType.getEntityFromNbt(entityTag, serverWorld);

        if(entity.isPresent())
        {
            Entity entity2 = entity.get();
            entity2.updatePositionAndAngles(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, player.getYaw(), player.getPitch());
            serverWorld.spawnEntity(entity2);
        }

        stack.removeSubNbt("name");  //KEEP
        stack.removeSubNbt("captured_entity");  // KEEP

        context.getPlayer().getStackInHand(context.getHand());
    }

    // Method to save an entity to a tag and remove entity from world
    public static boolean saveEntityToStack(Entity entity, ItemStack stack)
    {
        NbtCompound entityTag = new NbtCompound();

        if(!entity.saveSelfNbt(entityTag))
        {
            return false;
        }

        stack.getOrCreateNbt().put("captured_entity", entityTag);
        stack.getOrCreateNbt().putString("name", entity.getDisplayName().getString());
        entity.discard();

        return true;
    }
}