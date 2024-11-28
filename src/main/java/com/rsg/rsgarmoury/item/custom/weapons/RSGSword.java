package com.rsg.rsgarmoury.item.custom.weapons;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class RSGSword extends SwordItem {
    public RSGSword(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {

        pAttacker.setHealth(pAttacker.getMaxHealth() + 2);

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
