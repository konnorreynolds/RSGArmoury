package com.rsg.rsgarmoury.item.custom.weapons.projectiles;

import com.rsg.rsgarmoury.item.RSGItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class SpellTagProjectile extends ThrowableItemProjectile {
    public SpellTagProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public SpellTagProjectile(Level pLevel) {
        super(EntityType.SNOWBALL, pLevel);
    }

    public SpellTagProjectile(Level pLevel, LivingEntity pLivingEntity) {
        super(EntityType.SNOWBALL, pLivingEntity, pLevel);
    }

    @Override
    protected double getDefaultGravity() {
        return 0;
    }

    @Override
    protected Item getDefaultItem() {
        return RSGItems.SPELL_TAG.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {

        pResult.getEntity().hurt(damageSources().magic(), 20);

        EntityType.LIGHTNING_BOLT.spawn((ServerLevel) pResult.getEntity().level(), pResult.getEntity().getOnPos(), MobSpawnType.TRIGGERED);

        super.onHitEntity(pResult);
    }
}
