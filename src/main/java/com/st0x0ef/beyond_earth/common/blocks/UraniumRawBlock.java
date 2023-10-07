package com.st0x0ef.beyond_earth.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import com.st0x0ef.beyond_earth.common.blocks.entities.uranium.UraniumBaseBlockEntity;
import com.st0x0ef.beyond_earth.common.blocks.entities.uranium.RawUraniumBlockEntity;
import org.jetbrains.annotations.Nullable;

public class UraniumRawBlock extends BaseEntityBlock {
    public UraniumRawBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RawUraniumBlockEntity(pos, state);
    }

    @Override
    public <T2 extends BlockEntity> BlockEntityTicker<T2> getTicker(Level level, BlockState state, BlockEntityType<T2> type) {
        return (l, p, s, e) -> {
            if (e instanceof UraniumBaseBlockEntity entity) {
                entity.tick();
            }
        };
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

}
