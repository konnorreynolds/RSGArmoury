package com.rsg.rsgarmoury.events;

import com.rsg.rsgarmoury.RSGArmoury;
import com.rsg.rsgarmoury.item.custom.weapons.SpellTag;
import com.rsg.rsgarmoury.item.custom.weapons.ThunderHammer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.rsg.rsgarmoury.item.custom.weapons.ThunderHammer.groundSlam;

@Mod.EventBusSubscriber(modid = RSGArmoury.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RSGEvents {


    @SubscribeEvent
    public static void thunderHammerSlam(LivingFallEvent event) {

        if (event.getEntity() instanceof Player player) {

            if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ThunderHammer) {
                if (event.getDistance() > 6f && !player.isFallFlying()) {

                    if (player instanceof ServerPlayer serverPlayer) {

                        ServerLevel serverLevel = (ServerLevel) player.level();

                        event.setCanceled(true);

                        groundSlam(serverLevel, serverPlayer, player);

                    }
                }
            } else if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof SpellTag spellTag && spellTag.getSecondaryValue() == 1) {
                if (event.getDistance() > 6f && !player.isFallFlying()) {

                    if (player instanceof ServerPlayer serverPlayer) {

                        ServerLevel serverLevel = (ServerLevel) player.level();

                        event.setCanceled(true);

                        groundSlam(serverLevel, serverPlayer, player);

                    }
                }
            }
        }

    }


}
