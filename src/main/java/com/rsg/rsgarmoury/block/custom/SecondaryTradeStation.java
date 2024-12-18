package com.rsg.rsgarmoury.block.custom;

import com.mojang.serialization.MapCodec;
import com.rsg.rsgarmoury.block.entity.RSGBlockEntities;
import com.rsg.rsgarmoury.block.entity.SecondaryTradeStationEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SecondaryTradeStation extends BaseEntityBlock {

    public SecondaryTradeStation(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }


    @Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {

        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof SecondaryTradeStationEntity) {
                ((SecondaryTradeStationEntity) blockEntity).drops();
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        // IF: Code is executing on logical client.
        if (pLevel.isClientSide) {
            // Do nothing.
            return InteractionResult.SUCCESS;
        } else {

            // Get block entity at the block location.
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);

            // IF: block entity is matching.
            if (blockEntity instanceof SecondaryTradeStationEntity) {

                // Cast player to ServerPlayer.
                ServerPlayer serverPlayer = (ServerPlayer) pPlayer;

                // Cast block entity to SecondaryTradeStation.
                SecondaryTradeStationEntity secondaryTradeStationEntity = (SecondaryTradeStationEntity) blockEntity;

                // Open menu.
                serverPlayer.openMenu(secondaryTradeStationEntity, pPos);
            }

            return InteractionResult.CONSUME;
        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SecondaryTradeStationEntity(pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {

        if (pLevel.isClientSide) {
            return null;
        }

        return createTickerHelper(pBlockEntityType, RSGBlockEntities.SECONDARY_TRADE_BE.get(),
                ((pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1)));
    }
}
