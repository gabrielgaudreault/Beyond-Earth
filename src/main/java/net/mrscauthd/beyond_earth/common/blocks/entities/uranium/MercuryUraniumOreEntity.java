package net.mrscauthd.beyond_earth.common.blocks.entities.uranium;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.common.registries.BlockEntityRegistry;

public class MercuryUraniumOreEntity extends UraniumBaseBlockEntity {

    public MercuryUraniumOreEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.MERCURY_URANIUM_ORE_BLOCK_ENTITY.get(), pos, state);
    }


}
