package net.mrscauthd.beyond_earth.common.blocks.entities.machines;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeValueHelper;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.IGaugeValue;
import net.mrscauthd.beyond_earth.common.data.recipes.ItemStackToItemStackRecipe;
import net.mrscauthd.beyond_earth.common.data.recipes.ItemStackToItemStackRecipeType;
import net.mrscauthd.beyond_earth.common.menus.nasaworkbench.StackCacher;

public abstract class ItemStackToItemStackBlockEntity extends AbstractMachineBlockEntity {

    public static final int SLOT_INGREDIENT = 0;
    public static final int SLOT_OUTPUT = 1;

    public static final String KEY_TIMER = "timer";
    public static final String KEY_MAXTIMER = "maxTimer";

    private final StackCacher itemStackCacher;
    private ItemStackToItemStackRecipe cachedRecipe = null;

    public ItemStackToItemStackBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);

        this.itemStackCacher = new StackCacher();
        this.cachedRecipe = null;
    }

    @Override
    public List<IGaugeValue> getDisplayGaugeValues() {
        List<IGaugeValue> list = super.getDisplayGaugeValues();

        if (this.cachedRecipe != null) {
            list.add(this.getCookTimeGaugeValue());
        }

        return list;
    }

    public IGaugeValue getCookTimeGaugeValue() {
        return GaugeValueHelper.getCookTime(this.getTimer(), this.getMaxTimer());
    }

    @Override
    protected int getInitialInventorySize() {
        return super.getInitialInventorySize() + 2;
    }

    @Override
    protected void getSlotsForFace(Direction direction, List<Integer> slots) {
        super.getSlotsForFace(direction, slots);

        if (direction == Direction.UP) {
            slots.add(this.getSlotIngredient());
        } else if (direction == Direction.DOWN) {
            slots.add(this.getSlotOutput());
        }
    }

    @Override
    protected boolean onCanPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        if (index == this.getSlotIngredient() && this.nullOrMatch(direction, Direction.UP)) {
            return this.getRecipeType().findFirst(this.getLevel(), r -> r.test(stack)) != null;
        } else if (index == this.getSlotOutput() && direction == null) {
            return true;
        }

        return super.onCanPlaceItemThroughFace(index, stack, direction);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == this.getSlotOutput() && direction == Direction.DOWN) {
            return true;
        }

        return super.canTakeItemThroughFace(index, stack, direction);
    }

    @Override
    protected void tickProcessing() {
        this.cookIngredient();
    }

    protected ItemStackToItemStackRecipe cacheRecipe() {
        ItemStack itemStack = this.getItem(this.getSlotIngredient());

        if (itemStack == null || itemStack.isEmpty()) {
            this.itemStackCacher.set(itemStack);
            this.cachedRecipe = null;
            this.setMaxTimer(0);
        } else if (!this.itemStackCacher.test(itemStack)) {
            this.itemStackCacher.set(itemStack);
            this.cachedRecipe = this.getRecipeType().findFirst(this.getLevel(), r -> r.test(itemStack));

            if (this.cachedRecipe != null) {
                this.setMaxTimer(this.cachedRecipe.getCookTime());
            } else {
                this.setMaxTimer(0);
            }
        }

        return this.cachedRecipe;
    }

    @Override
    public boolean hasSpaceInOutput() {
        ItemStackToItemStackRecipe cacheRecipe = this.cacheRecipe();
        return cacheRecipe != null && this.hasSpaceInOutput(cacheRecipe.getOutput());
    }

    public boolean hasSpaceInOutput(ItemStack recipeOutput) {
        ItemStack output = this.getItemHandler().getStackInSlot(this.getSlotOutput());
        return hasSpaceInOutput(recipeOutput, output);
    }

    protected boolean resetTimer() {
        if (this.getTimer() > 0) {
            this.setTimer(0);
            return true;
        } else {
            return false;
        }
    }

    public abstract ItemStackToItemStackRecipeType<?> getRecipeType();

    protected void onCooking() {
        this.setTimer(this.getTimer() + 1);
        this.setProcessedInThisTick();
    }

    protected void onCantCooking() {
        this.setTimer(this.getTimer() - 1);
    }

    protected void cookIngredient() {
        ItemStackToItemStackRecipe recipe = this.cacheRecipe();

        if (recipe != null) {
            ItemStack recipeOutput = recipe.getOutput();

            if (this.hasSpaceInOutput(recipeOutput)) {
                if (this.consumePowerForOperation() != null) {
                    this.onCooking();

                    if (this.getTimer() >= this.getMaxTimer()) {
                        IItemHandlerModifiable itemHandler = this.getItemHandler();
                        itemHandler.insertItem(this.getSlotOutput(), recipeOutput.copy(), false);
                        itemHandler.extractItem(this.getSlotIngredient(), 1, false);
                        this.setTimer(0);
                    }
                } else {
                    this.onCantCooking();
                }
            } else {
                this.resetTimer();
            }
        } else {
            this.resetTimer();
        }
    }

    public int getTimer() {
        return this.getPersistentData().getInt(KEY_TIMER);
    }

    public void setTimer(int timer) {
        timer = Math.max(timer, 0);

        if (this.getTimer() != timer) {
            this.getPersistentData().putInt(KEY_TIMER, timer);
            this.setChanged();
        }
    }

    public int getMaxTimer() {
        return this.getPersistentData().getInt(KEY_MAXTIMER);
    }

    public void setMaxTimer(int maxTimer) {
        maxTimer = Math.max(maxTimer, 0);

        if (this.getMaxTimer() != maxTimer) {
            this.getPersistentData().putInt(KEY_MAXTIMER, maxTimer);
            this.setChanged();
        }
    }

    public double getTimerRatio() {
        return (double) this.getTimer() / (double) this.getMaxTimer();
    }

    public int getSlotIngredient() {
        return SLOT_INGREDIENT;
    }

    public int getSlotOutput() {
        return SLOT_OUTPUT;
    }
}
