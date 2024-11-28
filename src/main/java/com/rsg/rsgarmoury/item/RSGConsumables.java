package com.rsg.rsgarmoury.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

import java.util.List;

public class RSGConsumables {

    public static final FoodProperties RSG_FOOD = new FoodProperties.Builder()
            .alwaysEdible()
            .nutrition(3)
            .effect(new MobEffectInstance(MobEffects.HEAL, 1,1), 1)
            .build();

}
