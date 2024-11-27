package com.rsg.rsgarmoury.item.custom;

import net.minecraft.client.gui.screens.Screen;
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

import static net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH;


public class ConcealedHeart extends Item {
    public ConcealedHeart(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        player.getAttribute(MAX_HEALTH).setBaseValue(player.getMaxHealth() + 2);
        level.playSound(player, player.getOnPos(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 5.0F, 2.0F);
        player.getItemInHand(hand).consume(1, player);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.concealed_heart.shift_down"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.concealed_heart"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
