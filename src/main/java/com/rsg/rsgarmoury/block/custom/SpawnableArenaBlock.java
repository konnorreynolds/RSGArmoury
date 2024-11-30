package com.rsg.rsgarmoury.block.custom;

import com.rsg.rsgarmoury.block.RSGBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class SpawnableArenaBlock extends Block {
    public SpawnableArenaBlock(Properties properties) {
        super(properties);
    }

    private static List<Double[]> generateSphere(int radius, int numPoints) {
        List<Double[]> coordinates = new ArrayList<>();

        for (int i = 0; i < numPoints; i++) {
            double phi = Math.acos(-1.0 + (2.0 * i) / numPoints);
            double theta = Math.sqrt(numPoints * Math.PI) * phi;

            double x = radius * Math.sin(phi) * Math.cos(theta);
            double z = radius * Math.sin(phi) * Math.sin(theta);
            double y = radius * Math.cos(phi);

            coordinates.add(new Double[]{x, y, z});

        }
        return coordinates;
    }

    public void replaceUnlessAir(Level pLevel, BlockPos pPos, int blockX, int blockY, int blockZ) {

        BlockPos wallPos = new BlockPos(pPos.getX() + blockX, pPos.getY() + blockY, pPos.getZ() + blockZ);

        if (!pLevel.isClientSide()) {
            ServerLevel serverWorld = (ServerLevel) pLevel;
            if (serverWorld.getBlockState(wallPos).isAir()) {
                serverWorld.setBlockAndUpdate(wallPos, RSGBlocks.SPAWNABLE_ARENA_WALL.get().defaultBlockState());
            }
        }
    }

    public void replaceIfWall(Level pLevel, BlockPos pPos, int blockX, int blockY, int blockZ) {

        BlockPos wallPos = new BlockPos(pPos.getX() + blockX, pPos.getY() + blockY, pPos.getZ() + blockZ);

        if (!pLevel.isClientSide()) {
            ServerLevel serverWorld = (ServerLevel) pLevel;
            if (serverWorld.getBlockState(wallPos).is(RSGBlocks.SPAWNABLE_ARENA_WALL.get())) {
                serverWorld.setBlockAndUpdate(wallPos, Blocks.AIR.defaultBlockState());
            }
        }
    }

    @Override
    protected void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);

        List<Double[]> coordinates = generateSphere(25, 15000);

        if (!pLevel.isClientSide()) {
            for (Double[] coord : coordinates) {
                int x = (int) Math.round(coord[0]);
                int y = (int) Math.round(coord[1]);
                int z = (int) Math.round(coord[2]);

                replaceUnlessAir(pLevel, pPos, x, y, z);
            }

            pLevel.playSound(null, pPos, SoundEvents.RAID_HORN.get(), SoundSource.BLOCKS);
        }

    }

    @Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);

        if (!pLevel.isClientSide()) {
            List<Double[]> coordinates = generateSphere(25, 15000);

            for (Double[] coord : coordinates) {
                int x = (int) Math.round(coord[0]);
                int y = (int) Math.round(coord[1]);
                int z = (int) Math.round(coord[2]);

                replaceIfWall(pLevel, pPos, x, y, z);
            }

            pLevel.playSound(null, pPos, SoundEvents.GHAST_SCREAM, SoundSource.BLOCKS);

        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury.spawnable_arena_block"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
