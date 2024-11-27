package com.rsg.rsgarmoury.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

import static net.minecraft.world.entity.ai.attributes.Attributes.ARMOR;


public class RusticVial extends Item {
    public RusticVial(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        player.getAttribute(ARMOR).setBaseValue(player.getAttribute(ARMOR).getBaseValue() - 2);
        level.playSound(player, player.getOnPos(), SoundEvents.SPLASH_POTION_BREAK, SoundSource.BLOCKS, 4.0F, 0.8F);
        player.getItemInHand(hand).consume(1, player);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.rustic_vial"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

}
