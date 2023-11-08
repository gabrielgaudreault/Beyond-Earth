package com.st0x0ef.beyond_earth.common.items;

import com.st0x0ef.beyond_earth.common.registries.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class SpaceBaliseItem extends Item {
    Boolean coordsSet = false;
    int baliseX = 0;
    int baliseY = 0;
    int baliseZ = 0;
    String baliseLevel = "";

    public SpaceBaliseItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);

        if (blockState.is(BlockRegistry.ROCKET_LAUNCH_PAD.get())) {
            ItemStack stack = context.getItemInHand();

            CompoundTag coords = stack.getOrCreateTagElement("coords");
            coords.putInt("x", blockPos.getX());
            coords.putInt("y", blockPos.getY());
            coords.putInt("z", blockPos.getZ());
            coords.putString("level", level.dimension().location().toString());
            coordsSet = true;

            baliseX = coords.getInt("x");
            baliseY = coords.getInt("y");
            baliseZ = coords.getInt("z");
            baliseLevel = coords.getString("level");

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        CompoundTag coords = stack.getTagElement("coords");

        if (coords == null) {
            player.sendSystemMessage(Component.literal("No coords found"));
            return InteractionResultHolder.fail(stack);
        }
        else if (!level.getBlockState(new BlockPos(coords.getInt("x"), coords.getInt("y"), coords.getInt("z"))).is(BlockRegistry.ROCKET_LAUNCH_PAD.get())) {
            player.sendSystemMessage(Component.translatable("message.beyond_earth.space_balise.no_launch_pad", coords.getInt("x"), coords.getInt("y"), coords.getInt("z"), coords.getString("level")));
            coordsSet = false;
        }
        else {
            player.sendSystemMessage(Component.translatable("message.beyond_earth.space_balise.launch_pad_coordinates", baliseX, baliseY, baliseZ, baliseLevel));

        }

        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        if (!coordsSet) {
            tooltipComponents.add(Component.translatable("message.beyond_earth.space_balise.right_click"));

        } else {
            tooltipComponents.add(Component.translatable("message.beyond_earth.space_balise.launch_pad_coordinates", baliseX, baliseY, baliseZ, baliseLevel));
        }
    }
}