package com.rsg.rsgarmoury.item;

import com.rsg.rsgarmoury.effect.RSGEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class RSGConsumables {

    public static final FoodProperties RSG_FOOD = new FoodProperties.Builder()
            .effect(new MobEffectInstance(RSGEffects.PRECISE_HEAL.getHolder().get(), 1,1), 1)
            .alwaysEdible()
            .fast()
            .build();
}
