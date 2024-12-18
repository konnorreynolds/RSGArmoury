package com.rsg.rsgarmoury.item.custom.weapons;

import com.rsg.rsgarmoury.item.custom.weapons.projectiles.SpellTagProjectile;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

import static com.rsg.rsgarmoury.events.ClientEvents.secondaryForward;
import static com.rsg.rsgarmoury.events.ClientEvents.secondaryReverse;

public class SpellTag extends RSGWeaponClass {

    public final List<String> secondaryList = new ArrayList<String>();
    private final int baseProjectileDamage;
    private final int baseProjectileCooldown;
    private int currentSecondary = 0;


    public SpellTag(Properties pProperties) {
        super(pProperties);

        baseProjectileDamage = 5;
        attackDamageBonus = 2;

        baseProjectileCooldown = 10; // Ticks
        attackSpeedBonus = 5;

        secondaryList.add("Default");
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        if (!pLevel.isClientSide()) {

            int maxSecondaries = secondaryList.size() - 1;

            if (secondaryForward.isDown() && currentSecondary != maxSecondaries) {
                currentSecondary = currentSecondary + 1;
                updateSecondary(pPlayer);

            } else if (secondaryForward.isDown() && currentSecondary == maxSecondaries) {
                currentSecondary = 0;
                updateSecondary(pPlayer);

            } else if (secondaryReverse.isDown() && currentSecondary != 0) {
                currentSecondary = currentSecondary - 1;
                updateSecondary(pPlayer);

            } else if (secondaryReverse.isDown() && currentSecondary == 0) {
                currentSecondary = maxSecondaries;
                updateSecondary(pPlayer);

            } else {

                getSecondary(pLevel, pPlayer, pUsedHand);

            }


        }

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }


    public int getSecondaryValue() {
        return currentSecondary;
    }

    public void getSecondary(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (secondaryList.get(currentSecondary).equals("Default")) {
            ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
            pLevel.playSound(
                    null,
                    pPlayer.getX(),
                    pPlayer.getY(),
                    pPlayer.getZ(),
                    SoundEvents.SNOWBALL_THROW,
                    SoundSource.NEUTRAL,
                    0.5F,
                    0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));

            SpellTagProjectile projectile = new SpellTagProjectile(pLevel, pPlayer);

            projectile.changeDamage(baseProjectileDamage + attackDamageAdditive);

            projectile.setItem(itemstack);
            projectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 5F, 0F);
            pLevel.addFreshEntity(projectile);

            int speedAdditive = attackDamageAdditive;

            pPlayer.getCooldowns().addCooldown(this, baseProjectileCooldown - speedAdditive);

            // Thunder Hammer
        } else if (secondaryList.get(currentSecondary).equals("Thunder Hammer")) {
            ThunderHammer.getSecondary(pPlayer, pUsedHand, 4 * 20);
            pPlayer.getCooldowns().addCooldown(this, 8 * 20);
        }
    }

    public void updateSecondary(Player pPlayer) {
        pPlayer.sendSystemMessage(Component.literal(secondaryList.get(currentSecondary)));
    }

    public void toggleThunderHammer() {
        if (secondaryList.contains("Thunder Hammer")) {
            secondaryList.remove("Thunder Hammer");
        } else {
            secondaryList.add("Thunder Hammer");
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.spell_tag.shiftdown"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.spell_tag"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }

}
