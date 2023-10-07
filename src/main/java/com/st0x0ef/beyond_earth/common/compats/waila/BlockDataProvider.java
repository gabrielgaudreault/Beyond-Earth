package com.st0x0ef.beyond_earth.common.compats.waila;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.st0x0ef.beyond_earth.common.compats.mekanism.MekanismCompat;
import com.st0x0ef.beyond_earth.common.compats.mekanism.MekanismHelper;
import mcjty.theoneprobe.api.IElement;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.AbstractMachineBlockEntity;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.gauge.IGaugeValue;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.gauge.IGaugeValuesProvider;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class BlockDataProvider implements IServerDataProvider<BlockAccessor>, IBlockComponentProvider {

	public static final ResourceLocation Uid = new ResourceLocation(BeyondEarth.MODID, "block");
	public static final BlockDataProvider INSTANCE = new BlockDataProvider();

	@Override
	public void appendServerData(CompoundTag data, BlockAccessor blockEntity) {

		List<IGaugeValue> list = new ArrayList<>();

		if (blockEntity instanceof AbstractMachineBlockEntity machineBlockEntity) {
			machineBlockEntity.getFluidHandlers().values().stream().map(machineBlockEntity::getFluidHandlerGaugeValues).flatMap(Collection::stream).forEach(list::add);
		}

		if (blockEntity instanceof IGaugeValuesProvider) {
			((IGaugeValuesProvider) blockEntity).getDisplayGaugeValues().forEach(list::add);
		}

		WailaPlugin.put(data, WailaPlugin.write(list));
	}

	@Override
	public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
		WailaPlugin.appendTooltip(tooltip, accessor.getServerData());
	}

	@Override
	public ResourceLocation getUid() {
		return Uid;
	}
}
