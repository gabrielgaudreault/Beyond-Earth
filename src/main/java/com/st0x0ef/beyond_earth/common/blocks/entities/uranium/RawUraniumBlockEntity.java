package com.st0x0ef.beyond_earth.common.blocks.entities.uranium;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import com.st0x0ef.beyond_earth.common.registries.BlockEntityRegistry;

public class RawUraniumBlockEntity extends UraniumBaseBlockEntity {

    public RawUraniumBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.URANIUM_RAW_BLOCK_ENTITY.get(), pos, state);
    }


}
