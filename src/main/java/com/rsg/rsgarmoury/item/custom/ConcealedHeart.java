package com.rsg.rsgarmoury.item.custom;

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

import static net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH;


public class ConcealedHeart extends Item {
    public ConcealedHeart(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        pPlayer.getAttribute(MAX_HEALTH).setBaseValue(pPlayer.getMaxHealth() + 2);
        pLevel.playSound(pPlayer, pPlayer.getOnPos(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 5.0F, 2.0F);
        pPlayer.getItemInHand(pUsedHand).consume(1, pPlayer);

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));

    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
            pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.concealed_heart"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
