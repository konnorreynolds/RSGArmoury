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

import static net.minecraft.world.entity.ai.attributes.Attributes.ARMOR;


public class ConcentratedDefense extends Item {
    public ConcentratedDefense(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        pPlayer.getAttribute(ARMOR).setBaseValue(pPlayer.getAttribute(ARMOR).getBaseValue() + 2);
        pLevel.playSound(pPlayer, pPlayer.getOnPos(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 5.0F, 2.0F);
        pPlayer.getItemInHand(pUsedHand).consume(1, pPlayer);

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));

    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.concentrated_defense"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

}
