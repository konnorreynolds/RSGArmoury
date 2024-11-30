package com.rsg.rsgarmoury.item;

import com.rsg.rsgarmoury.RSGArmoury;
import com.rsg.rsgarmoury.item.custom.ConcealedHeart;
import com.rsg.rsgarmoury.item.custom.ConcentratedDefense;
import com.rsg.rsgarmoury.item.custom.RusticVial;
import com.rsg.rsgarmoury.item.custom.SacrificialDagger;
import com.rsg.rsgarmoury.item.custom.weapons.RSGToolTiers;
import com.rsg.rsgarmoury.item.custom.weapons.SpellTag;
import com.rsg.rsgarmoury.item.custom.weapons.ThunderHammer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class RSGItems {
    // Adds items to the forge registries
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RSGArmoury.MOD_ID);

    // Makes a raw item called 'soul in a bottle'
    public static final RegistryObject<Item> SOUL_IN_A_BOTTLE = ITEMS.register("soul_in_a_bottle",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.soul_in_a_bottle"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final RegistryObject<Item> RSG_FOOD = ITEMS.register("rsg_food",
            () -> new Item(new Item.Properties()
                    .food(RSGConsumables.RSG_FOOD)
                    .stacksTo(16)) {
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.rsg_food"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });



    public static final RegistryObject<Item> SACRIFICIAL_DAGGER = ITEMS.register("sacrificial_dagger",
            () -> new SacrificialDagger(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> CONCEALED_HEART = ITEMS.register("concealed_heart",
            () -> new ConcealedHeart(new Item.Properties()));

    public static final RegistryObject<Item> RUSTIC_VIAL = ITEMS.register("rustic_vial",
            () -> new RusticVial(new Item.Properties()));

    public static final RegistryObject<Item> CONCENTRATED_DEFENSE = ITEMS.register("concentrated_defense",
            () -> new ConcentratedDefense(new Item.Properties()));

    public static final RegistryObject<Item> THUNDER_HAMMER = ITEMS.register("thunder_hammer",
            () -> new ThunderHammer(new Item.Properties()
                    .attributes(SwordItem.createAttributes(RSGToolTiers.RSG_Sword, 12, 1))
                    .stacksTo(1)));

    public static final RegistryObject<Item> SPELL_TAG = ITEMS.register("spell_tag",
            () -> new SpellTag(new Item.Properties()
                    .stacksTo(1)));

    // Calls the event handler i.e., eventBus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
