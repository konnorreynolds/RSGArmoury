package com.rsg.rsgarmoury.effect;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Timer;

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
