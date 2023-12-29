package com.st0x0ef.beyond_earth.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import com.st0x0ef.beyond_earth.common.util.Methods;
import com.st0x0ef.beyond_earth.common.registries.BlockRegistry;

public class CoalTorchBlock extends TorchBlock {

    public CoalTorchBlock(Properties p_57491_) {
        super(p_57491_, ParticleTypes.ASH);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (level.getBlockState(pos).getBlock() == BlockRegistry.WALL_COAL_TORCH_BLOCK.get() && !Methods.isSpaceLevelWithoutOxygen(level) && (itemstack.getItem() == Items.FLINT_AND_STEEL || itemstack.getItem() == Items.FIRE_CHARGE)) {
            if (!level.isClientSide) {

                state = level.getBlockState(pos);

                level.setBlock(pos, Blocks.WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, state.getValue(WallCoalTorchBlock.FACING)), 3);

                this.flintManager(itemstack, player, hand, pos, level);
                return InteractionResult.SUCCESS;
            }
        }

        if (level.getBlockState(pos).getBlock() == BlockRegistry.COAL_TORCH_BLOCK.get() && !Methods.isSpaceLevelWithoutOxygen(level) && (itemstack.getItem() == Items.FLINT_AND_STEEL || itemstack.getItem() == Items.FIRE_CHARGE)) {
            if (!level.isClientSide) {

                level.setBlock(pos, Blocks.TORCH.defaultBlockState(), 3);

                this.flintManager(itemstack, player, hand, pos, level);
                return InteractionResult.SUCCESS;
            }
        }

        if (itemstack.getItem() == Items.FLINT_AND_STEEL || itemstack.getItem() == Items.FIRE_CHARGE) {
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    public void flintManager(ItemStack itemstack, Player playerEntity, InteractionHand hand, BlockPos pos, Level world) {
        if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
            world.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1, 1);

            itemstack.hurtAndBreak(1, playerEntity, (player) -> {
                player.broadcastBreakEvent(hand);
            });
        }

        if (itemstack.getItem() == Items.FIRE_CHARGE) {
            world.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1,1);

            if (!playerEntity.getAbilities().instabuild && !playerEntity.isSpectator()) {
                itemstack.setCount(itemstack.getCount() - 1);
            }
        }
    }
}