package com.rsg.rsgarmoury.item.custom.weapons;

import com.rsg.rsgarmoury.item.RSGItems;
import com.rsg.rsgarmoury.item.custom.weapons.projectiles.SpellTagProjectile;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

import static com.rsg.rsgarmoury.events.ClientEvents.secondaryForward;
import static com.rsg.rsgarmoury.events.ClientEvents.secondaryReverse;

public class SpellTag extends Item {

    private int currentSecondary = 0;

    public SpellTag(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        if (!pLevel.isClientSide()) {

            if (secondaryForward.isDown() && currentSecondary != 1) {
                currentSecondary = currentSecondary + 1;
                updateSecondary(pPlayer);

            } else if (secondaryForward.isDown() && currentSecondary == 1) {
                currentSecondary = 0;
                updateSecondary(pPlayer);

            } else if (secondaryReverse.isDown() && currentSecondary != 0) {
                currentSecondary = currentSecondary - 1;
                updateSecondary(pPlayer);

            } else if (secondaryReverse.isDown() && currentSecondary == 0) {
                currentSecondary = 1;
                updateSecondary(pPlayer);

            } else {

                getSecondary(pLevel, pPlayer, pUsedHand);

            }

        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public int getSecondaryValue() {
        return currentSecondary;
    }

    public void getSecondary(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (currentSecondary == 0) {
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
            projectile.setItem(itemstack);
            projectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 5F, 0F);
            pLevel.addFreshEntity(projectile);

            pPlayer.getCooldowns().addCooldown(this, 10);

        } else if (currentSecondary == 1) {
            RSGItems.THUNDER_HAMMER.get().use(pLevel, pPlayer, pUsedHand);
            pPlayer.getCooldowns().addCooldown(this, 8 * 20);
        }
    }

    public void updateSecondary(Player pPlayer) {
        if (currentSecondary == 0) {
            pPlayer.sendSystemMessage(Component.literal("Default"));
        } else if (currentSecondary == 1) {
            pPlayer.sendSystemMessage(Component.literal("ThunderHammer"));
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
}
