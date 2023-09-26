package net.mrscauthd.beyond_earth.common.blocks.entities.uranium;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.common.registries.BlockEntityRegistry;

public class GlacioUraniumOreEntity extends UraniumBaseBlockEntity {

    public GlacioUraniumOreEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.GLACIO_URANIUM_ORE_BLOCK_ENTITY.get(), pos, state);
    }


}
