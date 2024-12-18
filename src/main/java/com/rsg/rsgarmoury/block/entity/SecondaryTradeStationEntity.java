package com.rsg.rsgarmoury.block.entity;

import com.rsg.rsgarmoury.item.RSGItems;
import com.rsg.rsgarmoury.item.custom.weapons.RSGWeaponClass;
import com.rsg.rsgarmoury.item.custom.weapons.SpellTag;
import com.rsg.rsgarmoury.item.custom.weapons.ThunderHammer;
import com.rsg.rsgarmoury.screen.SecondaryTradeStationMenu;
import com.rsg.rsgarmoury.util.RSGTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Timer;

public class SecondaryTradeStationEntity extends BlockEntity implements MenuProvider {

    private static final int TOP_SLOT = 0;
    private static final int BOTTOM_SLOT = 1;
    protected final ContainerData data;
    private final ItemStackHandler itemHandler = new ItemStackHandler(2);
    private final Timer craftTimer = new Timer();
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private int progress = 0;
    private int maxProgress = 78;


    public SecondaryTradeStationEntity(BlockPos pPos, BlockState pBlockState) {
        super(RSGBlockEntities.SECONDARY_TRADE_BE.get(), pPos, pBlockState);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> SecondaryTradeStationEntity.this.progress;
                    case 1 -> SecondaryTradeStationEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {

                switch (pIndex) {
                    case 0 -> SecondaryTradeStationEntity.this.progress = pValue;
                    case 1 -> SecondaryTradeStationEntity.this.maxProgress = pValue;
                }

            }

            @Override
            public int getCount() {
                return 2;
            }
        };

    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.rsgarmoury.secondary_trade_station");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new SecondaryTradeStationMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {

        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("progress", progress);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("progress");

    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {

        if (hasRecipe()) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if (hasProgressFinished()) {
                tradeSecondaries();
                resetProgress();
            }
        } else {
            resetProgress();
        }

    }

    private void resetProgress() {
        progress = 0;
    }

    private void tradeSecondaries() {

        // SpellTag Transfer
        if (itemHandler.getStackInSlot(BOTTOM_SLOT).getItem() instanceof SpellTag spellTag) {
            if (itemHandler.getStackInSlot(TOP_SLOT).getItem() instanceof ThunderHammer thunderHammer) {
                spellTag.toggleThunderHammer();
                thunderHammer.toggleSecondary();
            }

        }

        // Upgrade Install
        else if (itemHandler.getStackInSlot(TOP_SLOT).is(RSGTags.Items.RSG_UPGRADES) && itemHandler.getStackInSlot(BOTTOM_SLOT).getItem() instanceof RSGWeaponClass weapon) {
            if (!weapon.hasUpgrade) {

                if (itemHandler.getStackInSlot(TOP_SLOT).is(RSGItems.RED_SHARD.get())) {
                    itemHandler.extractItem(TOP_SLOT, 1, false);
                    weapon.addUpgrades("Attack Damage");
                }
                if (itemHandler.getStackInSlot(TOP_SLOT).is(RSGItems.GREEN_SHARD.get())) {
                    itemHandler.extractItem(TOP_SLOT, 1, false);
                    weapon.addUpgrades("Attack Speed");
                }
                if (itemHandler.getStackInSlot(TOP_SLOT).is(RSGItems.BLUE_SHARD.get())) {
                    itemHandler.extractItem(TOP_SLOT, 1, false);
                    weapon.addUpgrades("Secondary Boost");
                }
                if (itemHandler.getStackInSlot(TOP_SLOT).is(RSGItems.WHITE_SCALE.get())) {
                    itemHandler.extractItem(TOP_SLOT, 1, false);
                    weapon.addUpgrades("Primary Augment");
                }
                if (itemHandler.getStackInSlot(TOP_SLOT).is(RSGItems.BLACK_SCALE.get())) {
                    itemHandler.extractItem(TOP_SLOT, 1, false);
                    weapon.addUpgrades("Secondary Augment");
                }
            }
        }


        // Remove Upgrade
        else if (itemHandler.getStackInSlot(BOTTOM_SLOT).isEmpty() && itemHandler.getStackInSlot(TOP_SLOT).getItem() instanceof RSGWeaponClass weapon && weapon.hasUpgrade) {

            if (weapon.getUpgrade().equals("Attack Damage")) {
                weapon.removeUpgrade("Attack Damage");
                itemHandler.insertItem(BOTTOM_SLOT, new ItemStack(RSGItems.RED_SHARD.get()), false);
            }
            if (weapon.getUpgrade().equals("Attack Speed")) {
                weapon.removeUpgrade("Attack Speed");
                itemHandler.insertItem(BOTTOM_SLOT, new ItemStack(RSGItems.GREEN_SHARD.get()), false);
            }
            if (weapon.getUpgrade().equals("Secondary Boost")) {
                weapon.removeUpgrade("Secondary Boost");
                itemHandler.insertItem(BOTTOM_SLOT, new ItemStack(RSGItems.BLUE_SHARD.get()), false);
            }
            if (weapon.getUpgrade().equals("Primary Augment")) {
                weapon.removeUpgrade("Primary Augment");
                itemHandler.insertItem(BOTTOM_SLOT, new ItemStack(RSGItems.WHITE_SCALE.get()), false);
            }
            if (weapon.getUpgrade().equals("Secondary Augment")) {
                weapon.removeUpgrade("Secondary Augment");
                itemHandler.insertItem(BOTTOM_SLOT, new ItemStack(RSGItems.BLACK_SCALE.get()), false);
            }
        }

    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {

        boolean secondaryTransfer = this.itemHandler.getStackInSlot(BOTTOM_SLOT).getItem() == RSGItems.SPELL_TAG.get() &&
                this.itemHandler.getStackInSlot(TOP_SLOT).is(RSGTags.Items.RSG_WEAPONS);

        boolean upgradeInstall = this.itemHandler.getStackInSlot(TOP_SLOT).is(RSGTags.Items.RSG_UPGRADES) &&
                this.itemHandler.getStackInSlot(BOTTOM_SLOT).getItem() instanceof RSGWeaponClass weapon && !weapon.hasUpgrade;

        boolean upgradeRemove = this.itemHandler.getStackInSlot(TOP_SLOT).getItem() instanceof RSGWeaponClass weapon && weapon.hasUpgrade &&
                this.itemHandler.getStackInSlot(BOTTOM_SLOT).isEmpty();

        return secondaryTransfer || upgradeInstall || upgradeRemove;
    }
}
