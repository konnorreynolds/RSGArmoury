package com.rsg.rsgarmoury.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class PreciseHealEffect extends MobEffect {

    protected PreciseHealEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void onEffectAdded(LivingEntity pLivingEntity, int pAmplifier) {

        pLivingEntity.setHealth(pLivingEntity.getHealth() + pAmplifier);

        super.onEffectAdded(pLivingEntity, pAmplifier);
    }
}
