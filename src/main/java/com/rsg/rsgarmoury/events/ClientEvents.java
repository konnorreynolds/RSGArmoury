package com.rsg.rsgarmoury.events;

import com.rsg.rsgarmoury.RSGArmoury;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = RSGArmoury.MOD_ID)
public class ClientEvents {

    public static final KeyMapping secondaryForward = new KeyMapping("key.rsgarmooury.spelltag.secondaryforward", GLFW.GLFW_KEY_LEFT_SHIFT, "key.categories.rsgarmoury");
    public static final KeyMapping secondaryReverse = new KeyMapping("key.rsgarmooury.spelltag.secondaryreverse", GLFW.GLFW_KEY_LEFT_ALT, "key.categories.rsgarmoury");

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(secondaryForward);
        event.register(secondaryReverse);
    }
}