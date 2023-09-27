package com.st0x0ef.beyond_earth.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import com.st0x0ef.beyond_earth.common.registries.BlockRegistry;

public class PermafrostGrass extends SnowyDirtBlock {
    public PermafrostGrass(Properties properties) {
        super(properties);
    }

    private static boolean canBeGrass(BlockState state, LevelReader reader, BlockPos pos) {
        BlockPos above = pos.above();
        BlockState blockstate = reader.getBlockState(above);
        if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        } else if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LightEngine.getLightBlockInto(reader, state, pos, blockstate, above, Direction.UP, blockstate.getLightBlock(reader, above));
            return i < reader.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState state, LevelReader reader, BlockPos pos) {
        BlockPos above = pos.above();
        return canBeGrass(state, reader, pos) && !reader.getFluidState(above).is(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockPos above = pos.above();
        if (!level.isClientSide && !level.isAreaLoaded(pos, 3)) {
            if (!level.getBlockState(above).isAir()) {
                level.setBlockAndUpdate(pos, BlockRegistry.PERMAFROST_DIRT.get().defaultBlockState());
            }
        }
        else {
            if (!level.isAreaLoaded(pos, 3)) return;
            if (level.getMaxLocalRawBrightness(above) >= 9) {
                BlockState blockstate = this.defaultBlockState();

                for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (level.getBlockState(blockpos).is(BlockRegistry.PERMAFROST_DIRT.get()) && canPropagate(blockstate, level, blockpos)) {
                        level.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, Boolean.valueOf(level.getBlockState(blockpos.above()).is(Blocks.SNOW))));
                    }
                }
            }

        }
    }

}
