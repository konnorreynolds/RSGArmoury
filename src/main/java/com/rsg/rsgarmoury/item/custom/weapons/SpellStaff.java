package com.rsg.rsgarmoury.item.custom.weapons;

import com.rsg.rsgarmoury.item.custom.weapons.projectiles.SpellStaffFireball;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static com.rsg.rsgarmoury.events.ClientEvents.secondaryForward;
import static com.rsg.rsgarmoury.events.ClientEvents.secondaryReverse;

public class SpellStaff extends Item {

    ArrayList<Mob> armyList = new ArrayList<>();
    private int currentSpell = 0;

    private int maxSpells = 2;

    public SpellStaff(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        if (!pLevel.isClientSide()) {

            if (secondaryForward.isDown() && currentSpell != maxSpells) {
                currentSpell = currentSpell + 1;
                updateSecondary(pPlayer);

            } else if (secondaryForward.isDown() && currentSpell == maxSpells) {
                currentSpell = 0;
                updateSecondary(pPlayer);

            } else if (secondaryReverse.isDown() && currentSpell != 0) {
                currentSpell = currentSpell - 1;
                updateSecondary(pPlayer);

            } else if (secondaryReverse.isDown() && currentSpell == 0) {
                currentSpell = maxSpells;
                updateSecondary(pPlayer);

            } else {

                getSpell(pLevel, pPlayer, pUsedHand);

            }

        }

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    public int getCurrentSpell() {
        return currentSpell;
    }

    public void getSpell(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ServerLevel serverLevel = (ServerLevel) pLevel;


        if (currentSpell == 0) {

            if (pPlayer.getItemInHand(pUsedHand).getDamageValue() < 80) {   // 100 minus mana taken
                IntStream.rangeClosed(1, 4)
                        .forEach(i -> spawnUndead(serverLevel, pPlayer, pUsedHand));


                pPlayer.getItemInHand(pUsedHand).hurtAndConvertOnBreak(30, this, pPlayer, pPlayer.getEquipmentSlotForItem(pPlayer.getItemInHand(pUsedHand)));

            }

        } else if (currentSpell == 1) {

            if (pPlayer.getItemInHand(pUsedHand).getDamageValue() < 95) {

                pPlayer.heal(5);

                pPlayer.getItemInHand(pUsedHand).hurtAndConvertOnBreak(5, this, pPlayer, pPlayer.getEquipmentSlotForItem(pPlayer.getItemInHand(pUsedHand)));

            }
        } else if (currentSpell == 2) {
            if (pPlayer.getItemInHand(pUsedHand).getDamageValue() < 90) {
                pLevel.playSound(
                        null,
                        pPlayer.getX(),
                        pPlayer.getY(),
                        pPlayer.getZ(),
                        SoundEvents.SNOWBALL_THROW,
                        SoundSource.NEUTRAL,
                        0.5F,
                        0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));

                SpellStaffFireball projectile = new SpellStaffFireball(pLevel, pPlayer);
                projectile.setItem(new ItemStack(Items.FIRE_CHARGE));
                projectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 5F, 0F);
                pLevel.addFreshEntity(projectile);

                pPlayer.getItemInHand(pUsedHand).hurtAndConvertOnBreak(10, this, pPlayer, pPlayer.getEquipmentSlotForItem(pPlayer.getItemInHand(pUsedHand)));
            }
        }
    }

    public void updateSecondary(Player pPlayer) {
        if (currentSpell == 0) {
            pPlayer.sendSystemMessage(Component.literal("Spawn Undead"));
        } else if (currentSpell == 1) {
            pPlayer.sendSystemMessage(Component.literal("Heal"));
        } else if (currentSpell == 2) {
            pPlayer.sendSystemMessage(Component.literal("Fireball"));
        }
    }

    public void spawnUndead(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ServerLevel serverLevel = (ServerLevel) pLevel;

        int min = 1;
        int max = 2;

        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);  // Add one since it starts at 0

        int randomOffset = ThreadLocalRandom.current().nextInt(min, 10 + 1);

        BlockPos spawnPos = pPlayer.getOnPos().offset(randomOffset, 1, randomOffset);


        if (randomNum == 1) {

            Mob skeleton = EntityType.SKELETON.spawn(serverLevel, spawnPos, MobSpawnType.TRIGGERED);

            if (skeleton != null) {
                skeleton.setCustomName(Component.literal("Undead Army"));
                skeleton.setCustomNameVisible(false);
                armyList.add(skeleton);
            }

        } else if (randomNum == 2) {

            Mob zombie = EntityType.ZOMBIE.spawn(serverLevel, spawnPos, MobSpawnType.TRIGGERED);

            if (zombie != null) {
                zombie.setCustomName(Component.literal("Undead Army"));
                zombie.setCustomNameVisible(false);
                armyList.add(zombie);
            }

        }

    }

    public ArrayList<Mob> getArmyList() {
        return armyList;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.spell_staff.shiftdown"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.spell_staff"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

}
