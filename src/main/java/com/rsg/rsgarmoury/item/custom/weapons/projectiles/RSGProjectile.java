package com.rsg.rsgarmoury.item.custom.weapons.projectiles;

import com.rsg.rsgarmoury.item.RSGItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class RSGProjectile extends ThrowableItemProjectile {
    public RSGProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public RSGProjectile(Level pLevel) {
        super(EntityType.SNOWBALL, pLevel);
    }

    public RSGProjectile(Level pLevel, LivingEntity pLivingEntity) {
        super(EntityType.SNOWBALL, pLivingEntity, pLevel);
    }


    @Override
    protected Item getDefaultItem() {
        return RSGItems.RSG_PROJECTILE.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {

        pResult.getEntity().hurt(damageSources().magic(), 20);

        super.onHitEntity(pResult);
    }
}
