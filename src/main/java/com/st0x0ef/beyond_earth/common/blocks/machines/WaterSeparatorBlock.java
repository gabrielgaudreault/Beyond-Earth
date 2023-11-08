package com.st0x0ef.beyond_earth.common.blocks.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.WaterSeparatorBlockEntity;

public class WaterSeparatorBlock extends AbstractMachineBlock<WaterSeparatorBlockEntity> {

        public WaterSeparatorBlock(BlockBehaviour.Properties properties) {
                super(properties);
        }

        @Override
        protected boolean useLit() {
                return true;
        }

        @Override
        protected boolean useFacing() {
                return true;
        }

        @Override
        public WaterSeparatorBlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                return new WaterSeparatorBlockEntity(pos, state);
        }
}
