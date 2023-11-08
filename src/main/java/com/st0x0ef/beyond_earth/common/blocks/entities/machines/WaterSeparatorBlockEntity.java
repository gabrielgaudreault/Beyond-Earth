package com.st0x0ef.beyond_earth.common.blocks.entities.machines;

import java.util.List;

import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.common.capabilities.hydrogen.HydrogenStorage;
import com.st0x0ef.beyond_earth.common.capabilities.hydrogen.HydrogenUtil;
import com.st0x0ef.beyond_earth.common.capabilities.hydrogen.IHydrogenStorage;
import com.st0x0ef.beyond_earth.common.capabilities.hydrogen.IHydrogenStorageHolder;
import com.st0x0ef.beyond_earth.common.capabilities.oxygen.IOxygenStorageHolder;
import com.st0x0ef.beyond_earth.common.capabilities.oxygen.OxygenStorage;
import com.st0x0ef.beyond_earth.common.menus.nasaworkbench.StackCacher;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.IEnergyStorage;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.power.NamedComponentRegistry;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.power.PowerSystemEnergyCommon;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.power.PowerSystemRegistry;
import com.st0x0ef.beyond_earth.common.capabilities.energy.EnergyStorageBasic;
import com.st0x0ef.beyond_earth.common.capabilities.oxygen.IOxygenStorage;
import com.st0x0ef.beyond_earth.common.capabilities.oxygen.OxygenUtil;
import com.st0x0ef.beyond_earth.common.config.Config;
import com.st0x0ef.beyond_earth.common.data.recipes.BeyondEarthRecipeType;
import com.st0x0ef.beyond_earth.common.data.recipes.OxygenMakingRecipeAbstract;
import com.st0x0ef.beyond_earth.common.menus.WaterSeparatorMenu;
import com.st0x0ef.beyond_earth.common.registries.BlockEntityRegistry;
import com.st0x0ef.beyond_earth.common.registries.RecipeTypeRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class WaterSeparatorBlockEntity extends AbstractMachineBlockEntity {
    public static final ResourceLocation TANK_INPUT = new ResourceLocation(BeyondEarth.MODID, "input");
    public static final ResourceLocation O2_TANK_OUTPUT = new ResourceLocation(BeyondEarth.MODID, "o2_output");
    public static final ResourceLocation H2_TANK_OUTPUT = new ResourceLocation(BeyondEarth.MODID, "h2_output");
    public static final int DEFAULT_ENERGY_USAGE = 1;
    public static final int SLOT_OUTPUT_SINK_O2 = 2;
    public static final int SLOT_OUTPUT_SOURCE_O2 = 3;
    public static final int SLOT_OUTPUT_SINK_H2 = 4;
    public static final int SLOT_OUTPUT_SOURCE_H2 = 5;

    private FluidTank InputTank;
    private OxygenStorage O2OutputTank;
    private HydrogenStorage H2OutputTank;

    private final StackCacher recipeCacher;
    private OxygenMakingRecipeAbstract cachedRecipe;

    public WaterSeparatorBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.WATER_SEPARATOR_BLOCK_ENTITY.get(), pos, state);

        this.recipeCacher = new StackCacher();
        this.cachedRecipe = null;
    }

    @Override
    protected void tickProcessing() {
        this.drainSources();
        this.consumeIngredients();
        this.fillSinks();
    }

    public boolean consumeIngredients() {
        OxygenMakingRecipeAbstract recipe = this.cacheRecipe();

        if (recipe != null) {
            int oxygen = recipe.getOxygen();
            int hydrogen = recipe.getHydrogen();

            if (this.hasSpaceInOutput(oxygen, hydrogen)) {
                if (this.consumePowerForOperation() != null) {
                    this.getInputTank().drain(recipe.getInput().getAmount(), IFluidHandler.FluidAction.EXECUTE);
                    this.getO2OutputTank().receiveOxygen(oxygen, false);
                    this.getH2OutputTank().receiveHydrogen(hydrogen, false);
                    this.setProcessedInThisTick();
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean hasSpaceInOutput() {
        OxygenMakingRecipeAbstract recipe = this.cacheRecipe();
        return recipe != null && this.hasSpaceInOutput(recipe.getOxygen(), recipe.getHydrogen());
    }

    public boolean hasSpaceInOutput(int oxygen, int hydrogen) {
        return hasSpaceInOutput(oxygen, this.getO2OutputTank(), hydrogen, this.getH2OutputTank());
    }

    public boolean hasSpaceInOutput(int oxygen, IOxygenStorage O2Storage, int hydrogen, IHydrogenStorage H2Storage) {
        return ((oxygen + O2Storage.getOxygen()) <= O2Storage.getMaxCapacity()) && ((hydrogen + H2Storage.getHydrogen()) <= H2Storage.getMaxCapacity());
    }

    public OxygenMakingRecipeAbstract cacheRecipe() {
        FluidStack fluidStack = this.getInputTank().getFluid();

        if (fluidStack.isEmpty()) {
            this.recipeCacher.set(fluidStack);
            this.cachedRecipe = null;
        } else if (!this.recipeCacher.test(fluidStack)) {
            this.recipeCacher.set(fluidStack);
            this.cachedRecipe = this.getRecipeType().findFirst(this.getLevel(), r -> r.test(fluidStack));
        }

        return this.cachedRecipe;
    }

    @Override
    protected void createFluidHandlers(NamedComponentRegistry<IFluidHandler> registry) {
        super.createFluidHandlers(registry);
        this.InputTank = (FluidTank) registry.computeIfAbsent(this.getInputTankName(), this::creatFluidTank);
        this.O2OutputTank = this.createOxygenTank(this.getO2OutputTankName());
        this.H2OutputTank = this.createHydrogenTank(this.getH2OutputTankName());
    }

    protected FluidTank creatFluidTank(ResourceLocation name) {
        return new FluidTank(this.getInitialTankCapacity(name)) {
            @Override
            protected void onContentsChanged() {
                super.onContentsChanged();
                WaterSeparatorBlockEntity.this.setChanged();
            }
        };
    }

    protected OxygenStorage createOxygenTank(ResourceLocation name) {
        OxygenStorage storage = new OxygenStorage((oxygenStorage, oxygenDelta) -> WaterSeparatorBlockEntity.this.setChanged());
        storage.setMaxCapacity(this.getInitialTankCapacity(name));
        return storage;
    }

    protected HydrogenStorage createHydrogenTank(ResourceLocation name) {
        HydrogenStorage storage = new HydrogenStorage((hydrogenStorage, hydrogenDelta) -> WaterSeparatorBlockEntity.this.setChanged());
        storage.setMaxCapacity(this.getInitialTankCapacity(name));
        return storage;
    }

    private void drainSources() {
        OxygenUtil.drainSource(this.getItemHandler(), this.getO2OutputSourceSlot(), this.getO2OutputTank(),
                this.getTransferPerTick());

        HydrogenUtil.drainSource(this.getItemHandler(), this.getH2OutputSourceSlot(), this.getH2OutputTank(),
                this.getTransferPerTick());
    }

    private void fillSinks() {
        OxygenUtil.fillSink(this.getItemHandler(), this.getO2OutputSinkSlot(), this.getO2OutputTank(),
                this.getTransferPerTick());

        HydrogenUtil.fillSink(this.getItemHandler(), this.getH2OutputSinkSlot(), this.getH2OutputTank(),
                this.getTransferPerTick());
    }

    @Override
    protected boolean onCanPlaceItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == this.getO2OutputSourceSlot()) {
            return OxygenUtil.canExtract(stack);
        } else if (index == this.getO2OutputSinkSlot()) {
            return OxygenUtil.canReceive(stack);
        } else if (index == this.getH2OutputSourceSlot()) {
            return HydrogenUtil.canReceive(stack);
        } else if (index == this.getH2OutputSinkSlot()) {
            return HydrogenUtil.canReceive(stack);
        }

        return super.onCanPlaceItemThroughFace(index, stack, direction);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == this.getO2OutputSourceSlot()) {
            return !OxygenUtil.canExtract(stack);
        } else if (index == this.getO2OutputSinkSlot()) {
            return !OxygenUtil.canReceive(stack);
        } else if (index == this.getH2OutputSourceSlot()) {
            return !HydrogenUtil.canReceive(stack);
        } else if (index == this.getH2OutputSinkSlot()) {
            return !HydrogenUtil.canReceive(stack);
        }

        return super.canTakeItemThroughFace(index, stack, direction);
    }

    @Override
    protected void getSlotsForFace(Direction direction, List<Integer> slots) {
        super.getSlotsForFace(direction, slots);
        slots.add(this.getO2OutputSourceSlot());
        slots.add(this.getO2OutputSinkSlot());
        slots.add(this.getH2OutputSourceSlot());
        slots.add(this.getH2OutputSinkSlot());
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new WaterSeparatorMenu.GuiContainer(id, inventory, this);
    }

    @Override
    protected void createEnergyStorages(NamedComponentRegistry<IEnergyStorage> registry) {
        super.createEnergyStorages(registry);
        int capacity = Config.WATER_SEPARATOR_ENERGY_CAPACITY.get();
        int maxTransfer = Config.WATER_SEPARATOR_ENERGY_TRANSFER.get();
        registry.put(new EnergyStorageBasic(this, capacity, maxTransfer, capacity));
    }

    private int getInitialTankCapacity(ResourceLocation name) {
        if (name.equals(this.getInputTankName())) {
            return Config.WATER_SEPARATOR_TANK_FLUID_CAPACITY.get();
        } else if (name.equals(this.getO2OutputTankName())) {
            return Config.WATER_SEPARATOR_TANK_OXYGEN_CAPACITY.get();
        } else if (name.equals(this.getH2OutputTankName())) {
            return Config.WATER_SEPARATOR_TANK_HYDROGEN_CAPACITY.get();
        } else {
            return DEFAULT_TANK_CAPACITY;
        }
    }

    public int getTransferPerTick() {
        return Config.WATER_SEPARATOR_TANK_TRANSFER.get();
    }

    @Override
    protected void createPowerSystems(PowerSystemRegistry map) {
        super.createPowerSystems(map);

        map.put(new PowerSystemEnergyCommon(this) {
            @Override
            public int getBasePowerForOperation() {
                return WaterSeparatorBlockEntity.this.getBasePowerForOperation();
            }
        });
    }

    public int getBasePowerForOperation() {
        return Config.WATER_SEPARATOR_ENERGY_USAGE.get();
    }


    public BeyondEarthRecipeType<? extends OxygenMakingRecipeAbstract> getRecipeType() {
        return RecipeTypeRegistry.WATER_SEPARATOR.get();
    }

    @Override
    protected int getInitialInventorySize() {
        return super.getInitialInventorySize() + 6;
    }

    public int getO2OutputSourceSlot() {
        return SLOT_OUTPUT_SOURCE_O2;
    }

    public int getO2OutputSinkSlot() {
        return SLOT_OUTPUT_SINK_O2;
    }

    public int getH2OutputSourceSlot() {
        return SLOT_OUTPUT_SOURCE_H2;
    }

    public int getH2OutputSinkSlot() {
        return SLOT_OUTPUT_SINK_H2;
    }

    public ResourceLocation getInputTankName() {
        return TANK_INPUT;
    }

    public FluidTank getInputTank() {
        return this.InputTank;
    }

    public ResourceLocation getO2OutputTankName() {
        return O2_TANK_OUTPUT;
    }
    public ResourceLocation getH2OutputTankName() {
        return H2_TANK_OUTPUT;
    }

    public OxygenStorage getO2OutputTank() {
        return this.O2OutputTank;
    }

    public HydrogenStorage getH2OutputTank() {
        return this.H2OutputTank;
    }
}
