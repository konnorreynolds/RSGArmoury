package com.rsg.rsgarmoury.item.custom.weapons;

import com.rsg.rsgarmoury.effect.RSGEffects;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class ThunderHammer extends MaceItem {

    public ThunderHammer(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

                    ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);

                    if (!pLevel.isClientSide) {

                        pPlayer.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 4 * 20, 1));
                        pPlayer.addEffect(new MobEffectInstance(RSGEffects.FAST_FALL.getHolder().get()));

                        pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, pPlayer.getEquipmentSlotForItem(itemstack));
                        pPlayer.getCooldowns().addCooldown(this, 8 * 20);
                    }
                    return InteractionResultHolder.success(itemstack);
                }

                @Override
                public float getAttackDamageBonus(Entity pTarget, float pDamage, DamageSource pDamageSource) {
                    if (pDamageSource.getDirectEntity() instanceof LivingEntity livingentity) {
                        if (!canSmashAttack(livingentity)) {
                            return 0.0F;
                        } else {
                            float f1 = livingentity.fallDistance;
                            float f2;
                            if (f1 <= 3.0F) {
                                f2 = 4.0F * f1;
                            } else if (f1 <= 8.0F) {
                                f2 = 12.0F + 2.0F * (f1 - 3.0F);
                            } else {
                                f2 = 22.0F + f1 - 8.0F;
                            }

                            return livingentity.level() instanceof ServerLevel serverlevel
                                    ? f2 + EnchantmentHelper.modifyFallBasedDamage(serverlevel, livingentity.getWeaponItem(), pTarget, pDamageSource, 0.0F) * f1
                                    : f2;
                        }
                    } else {
                        return 0.0F;
                    }
                }

                public static void groundSlam(Level pLevel, Player pPlayer, Entity pEntity) {
                    if (pPlayer instanceof ServerPlayer serverplayer) {
                        ServerLevel serverlevel = (ServerLevel)pPlayer.level();

                        BlockPos blockPos = serverplayer.getOnPos();

                        Vec3 vec3 = blockPos.getCenter().add(0.0, 0.5, 0.0);

                        serverplayer.setDeltaMovement(serverplayer.getDeltaMovement().with(Direction.Axis.Y, 0.01F));
                        serverplayer.connection.send(new ClientboundSetEntityMotionPacket(serverplayer));

                        serverplayer.removeEffect(RSGEffects.FAST_FALL.getHolder().get());

                        serverlevel.sendParticles(ParticleTypes.FIREWORK, vec3.x, vec3.y, vec3.z, 250,
                                3.5F, 0.3F, 3.5F, 0.15F);

                        serverlevel.playSound(null, serverplayer.getX(), serverplayer.getY(), serverplayer.getZ(), SoundEvents.LIGHTNING_BOLT_THUNDER, serverplayer.getSoundSource(), 0.6f, 1.0F);

                        knockback(serverlevel, serverplayer, pPlayer);
                    }
                }


                public static void knockback(Level pLevel, Player pPlayer, Entity pEntity) {
                    pLevel.levelEvent(2013, pEntity.getOnPos(), 750);


                    pLevel.getEntitiesOfClass(LivingEntity.class, pEntity.getBoundingBox().inflate(3.5), knockbackPredicate(pPlayer, pEntity)).forEach(knockbackPower -> {
                        Vec3 vec3 = knockbackPower.position().subtract(pEntity.position());
                        double d0 = getKnockbackPower(pPlayer, knockbackPower, vec3);
                        Vec3 vec31 = vec3.normalize().scale(d0);
                        if (d0 > 0.0) {
                            knockbackPower.push(vec31.x, 0.7F, vec31.z);
                            knockbackPower.hurt(pLevel.damageSources().fall(), 5);

                            if (knockbackPower instanceof ServerPlayer serverplayer) {
                                serverplayer.connection.send(new ClientboundSetEntityMotionPacket(serverplayer));
                            }
                        }
                    });
                }

                private static Predicate<LivingEntity> knockbackPredicate(Player pPlayer, Entity pEntity) {
                    return p_341576_ -> {
                        boolean flag;
                        boolean flag1;
                        boolean flag2;
                        boolean flag6;
                        label62:
                        {
                            flag = !p_341576_.isSpectator();
                            flag1 = p_341576_ != pPlayer && p_341576_ != pEntity;
                            flag2 = !pPlayer.isAlliedTo(p_341576_);
                            if (p_341576_ instanceof TamableAnimal tamableanimal && tamableanimal.isTame() && pPlayer.getUUID().equals(tamableanimal.getOwnerUUID())) {
                                flag6 = true;
                                break label62;
                            }

                            flag6 = false;
                        }

                        boolean flag3;
                        label55:
                        {
                            flag3 = !flag6;
                            if (p_341576_ instanceof ArmorStand armorstand && armorstand.isMarker()) {
                                flag6 = false;
                                break label55;
                            }

                            flag6 = true;
                        }

                        boolean flag4 = flag6;
                        boolean flag5 = pEntity.distanceToSqr(p_341576_) <= Math.pow(3.5, 2.0);
                        return flag && flag1 && flag2 && flag3 && flag4 && flag5;
                    };
                }

                private static double getKnockbackPower(Player pPlayer, LivingEntity pEntity, Vec3 pEntityPos) {
                    return (3.5 - pEntityPos.length()) * 0.7F * (double) (pPlayer.fallDistance > 5.0F ? 2 : 1) * (1.0 - pEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                }

                public static boolean canSmashAttack(LivingEntity pEntity) {
                    return pEntity.fallDistance > 1.5F && !pEntity.isFallFlying();
                }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.thunder_hammer.shiftdown"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.thunder_hammer"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

            }
