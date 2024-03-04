package com.st0x0ef.beyond_earth.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import com.st0x0ef.beyond_earth.common.util.Methods;

public class CoalLanternBlock extends LanternBlock {
    public CoalLanternBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HANGING, false).setValue(WATERLOGGED, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.getBlockState(pos).getValue(CoalLanternBlock.HANGING) && !Methods.isSpaceLevelWithoutOxygen(level) && (itemstack.getItem() == Items.FLINT_AND_STEEL || itemstack.getItem() == Items.FIRE_CHARGE)) {
            if (!level.isClientSide) {

                level.setBlock(pos, Blocks.LANTERN.defaultBlockState(), 3);

                this.fireManager(itemstack, player, hand, pos, level);
                return InteractionResult.SUCCESS;
            }
        }

        if (level.getBlockState(pos).getValue(CoalLanternBlock.HANGING) && !Methods.isSpaceLevelWithoutOxygen(level) && (itemstack.getItem() == Items.FLINT_AND_STEEL || itemstack.getItem() == Items.FIRE_CHARGE)) {
            if (!level.isClientSide) {

                level.setBlock(pos, Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true), 3);

                this.fireManager(itemstack, player, hand, pos, level);
                return InteractionResult.SUCCESS;
            }
        }

        if (itemstack.getItem() == Items.FLINT_AND_STEEL || itemstack.getItem() == Items.FIRE_CHARGE) {
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    public void fireManager(ItemStack itemstack, Player playerEntity, InteractionHand hand, BlockPos pos, Level world) {
        if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
            world.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1, 1);

            itemstack.hurtAndBreak(1, playerEntity, (player) -> player.broadcastBreakEvent(hand));
        }

        if (itemstack.getItem() == Items.FIRE_CHARGE) {
            world.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1,1);

            if (!playerEntity.getAbilities().instabuild && !playerEntity.isSpectator()) {
                itemstack.setCount(itemstack.getCount() - 1);
            }
        }
    }
}
