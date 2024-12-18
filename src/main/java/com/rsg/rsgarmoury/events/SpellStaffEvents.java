package com.rsg.rsgarmoury.events;

import com.rsg.rsgarmoury.RSGArmoury;
import com.rsg.rsgarmoury.item.custom.weapons.SpellStaff;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = RSGArmoury.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpellStaffEvents {


    @SubscribeEvent
    public static void regenMana(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            Player player = event.player;
            if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof SpellStaff spellStaff && player.getRandom().nextFloat() < spellStaff.getManaRegen()) {
                player.getItemInHand(InteractionHand.MAIN_HAND).hurtAndBreak(-1, player, player.getEquipmentSlotForItem(player.getItemInHand(InteractionHand.MAIN_HAND)));
            }
        }
    }


    @SubscribeEvent
    public static void undeadArmyAttack(LivingDamageEvent event) {

        if (event.getEntity() instanceof Player player) {
            if (event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof SpellStaff spellStaff) {
                    Iterator<Mob> tamedArmy = spellStaff.getArmyList().iterator();
                    while (tamedArmy.hasNext()) {
                        Mob mob = tamedArmy.next();
                        mob.setTarget(attacker);
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void undeadArmyIgnore(LivingChangeTargetEvent event) {
        if (Objects.equals(event.getEntity().getCustomName(), Component.literal("Undead Army"))) {
            Component playerName = Component.literal("Undead Army");
            if (event.getNewTarget() instanceof Player player) {
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof SpellStaff) {
                    playerName = player.getName();
                }
                if (player.getName().equals(playerName)) {
                    event.setNewTarget(null);
                }
            }
        }
    }
}
