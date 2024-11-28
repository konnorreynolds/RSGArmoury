package com.rsg.rsgarmoury.effect;

import com.rsg.rsgarmoury.RSGArmoury;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class RSGEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, RSGArmoury.MOD_ID);

    public static final RegistryObject<MobEffect> PRECISE_HEAL = MOB_EFFECTS.register("precise_heal",
            () -> new PreciseHealEffect(MobEffectCategory.BENEFICIAL, 0x36ebab));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
