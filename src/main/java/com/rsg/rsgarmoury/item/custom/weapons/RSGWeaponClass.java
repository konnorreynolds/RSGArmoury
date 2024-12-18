package com.rsg.rsgarmoury.item.custom.weapons;

import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class RSGWeaponClass extends Item {

    public static double attackSpeedAdditive = 0;     // Meant to be subtracted, typically in ticks
    public static int attackDamageAdditive = 0;    // Flat damage up
    public static double attackSpeedBonus = 5;
    public static int attackDamageBonus = 5;
    public static boolean isSecondaryRemoved = false;
    public String currentUpgrade = "";
    public boolean hasUpgrade = false;
    public boolean secondaryBoostActive = false;
    public boolean secondaryAugmentActive = false;
    public boolean primaryAugmentActive = false;

    public RSGWeaponClass(Properties pProperties) {
        super(pProperties);
    }

    public static ItemAttributeModifiers createAttributes(Tier pTier, int pAttackDamage, float pAttackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double) ((float) pAttackDamage + pTier.getAttackDamageBonus() + attackDamageAdditive), AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double) pAttackSpeed + attackSpeedAdditive, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    public void addUpgrades(String upgrade) {
        if (!hasUpgrade) {
            hasUpgrade = true;

            switch (upgrade) {
                case "Attack Speed" -> {
                    attackSpeedAdditive = attackSpeedBonus;
                    currentUpgrade = "Attack Speed";
                }
                case "Attack Damage" -> {
                    attackDamageAdditive = attackDamageBonus;
                    currentUpgrade = "Attack Damage";
                }
                case "Secondary Boost" -> {
                    secondaryBoostActive = true;
                    currentUpgrade = "Secondary Boost";
                }
                case "Primary Augment" -> {
                    primaryAugmentActive = true;
                    currentUpgrade = "Primary Augment";
                }
                case "Secondary Augment" -> {
                    secondaryAugmentActive = true;
                    currentUpgrade = "Secondary Augment";
                }
            }
        }
    }

    public void removeUpgrade(String upgrade) {
        hasUpgrade = false;
        currentUpgrade = "";

        switch (upgrade) {
            case "Attack Speed" -> {
                attackSpeedAdditive = 0;
            }
            case "Attack Damage" -> {
                attackDamageAdditive = 0;
            }
            case "Secondary Boost" -> {
                secondaryBoostActive = false;
            }
            case "Primary Augment" -> {
                primaryAugmentActive = false;
            }
            case "Secondary Augment" -> {
                secondaryAugmentActive = false;
            }
        }

    }

    public boolean hasUpgrade() {
        return hasUpgrade;
    }

    public String getUpgrade() {
        return currentUpgrade;
    }

    public void toggleSecondary() {
        if (!isSecondaryRemoved) {
            isSecondaryRemoved = true;
        } else if (isSecondaryRemoved) {
            isSecondaryRemoved = false;
        }
    }

    public boolean getIsSecondaryRemoved() {
        return isSecondaryRemoved;
    }

}
