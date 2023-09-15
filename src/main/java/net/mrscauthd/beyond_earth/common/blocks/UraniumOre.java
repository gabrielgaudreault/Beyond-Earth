package net.mrscauthd.beyond_earth.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.common.blocks.entities.uranium.UraniumOreEntity;
import org.jetbrains.annotations.Nullable;

public class UraniumOre extends BaseEntityBlock {
    public UraniumOre(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new UraniumOreEntity(pos, state);
    }

    @Override
    public <T2 extends BlockEntity> BlockEntityTicker<T2> getTicker(Level level, BlockState state, BlockEntityType<T2> type) {
        return (l, p, s, e) -> {
            if (e instanceof UraniumOreEntity entity) {
                entity.tick();
            }
        };
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

}
