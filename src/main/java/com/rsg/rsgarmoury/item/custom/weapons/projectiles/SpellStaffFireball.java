package com.rsg.rsgarmoury.item.custom.weapons.projectiles;

import com.rsg.rsgarmoury.item.RSGItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class SpellStaffFireball extends ThrowableItemProjectile {
    public SpellStaffFireball(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public SpellStaffFireball(Level pLevel) {
        super(EntityType.SNOWBALL, pLevel);
    }

    public SpellStaffFireball(Level pLevel, LivingEntity pLivingEntity) {
        super(EntityType.SNOWBALL, pLivingEntity, pLevel);
    }

    @Override
    protected double getDefaultGravity() {
        return 0.01;
    }

    @Override
    protected Item getDefaultItem() {
        return RSGItems.SPELL_STAFF.get();
    }

    @Override
    protected void onHit(HitResult pResult) {

        Vec3 hitPos = pResult.getLocation();

        level().explode(this, hitPos.x, hitPos.y, hitPos.z, 2, true, Level.ExplosionInteraction.TRIGGER);

        this.kill();

        super.onHit(pResult);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {

        Vec3 hitPos = pResult.getLocation();

        pResult.getEntity().hurt(damageSources().magic(), 20);

        level().explode(this, hitPos.x, hitPos.y, hitPos.z, 2, true, Level.ExplosionInteraction.TRIGGER);

        this.kill();

        super.onHitEntity(pResult);
    }
}
