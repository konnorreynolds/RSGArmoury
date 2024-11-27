package com.rsg.rsgarmoury.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

import static net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH;


public class SacrificialDagger extends Item {
    public SacrificialDagger(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.getAttribute(MAX_HEALTH).setBaseValue(pTarget.getMaxHealth() - 2);
        Level level = pTarget.level();
        level.playSound(pTarget, pTarget.getOnPos(), SoundEvents.ALLAY_HURT, SoundSource.BLOCKS, 10.0F, 1.0F);
        level.playSound(pAttacker, pAttacker.getOnPos(), SoundEvents.ALLAY_HURT, SoundSource.BLOCKS, 10.0F, 1.0F);
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.sacrificial_dagger"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

}
