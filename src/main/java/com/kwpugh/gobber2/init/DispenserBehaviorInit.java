package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.items.arrows.GobberArrowEndEntity;
import com.kwpugh.gobber2.items.arrows.GobberArrowEntity;
import com.kwpugh.gobber2.items.arrows.GobberArrowNetherEntity;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class DispenserBehaviorInit
{
    public static void registerBehaviors()
    {
        DispenserBlock.registerBehavior(ItemInit.GOBBER2_ARROW, new ProjectileDispenserBehavior()
        {
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack)
            {
                PersistentProjectileEntity persistentProjectileEntity = new GobberArrowEntity(world, position.getX(), position.getY(), position.getZ());
                persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                return persistentProjectileEntity;
            }
        });

        DispenserBlock.registerBehavior(ItemInit.GOBBER2_ARROW_NETHER, new ProjectileDispenserBehavior()
        {
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack)
            {
                PersistentProjectileEntity persistentProjectileEntity = new GobberArrowNetherEntity(world, position.getX(), position.getY(), position.getZ());
                persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                return persistentProjectileEntity;
            }
        });

        DispenserBlock.registerBehavior(ItemInit.GOBBER2_ARROW_END, new ProjectileDispenserBehavior()
        {
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack)
            {
                PersistentProjectileEntity persistentProjectileEntity = new GobberArrowEndEntity(world, position.getX(), position.getY(), position.getZ());
                persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                return persistentProjectileEntity;
            }
        });
    }
}