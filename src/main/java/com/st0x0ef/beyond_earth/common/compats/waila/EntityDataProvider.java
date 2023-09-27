package com.st0x0ef.beyond_earth.common.compats.waila;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.gauge.IGaugeValue;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.gauge.IGaugeValuesProvider;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class EntityDataProvider implements IServerDataProvider<EntityAccessor>, IEntityComponentProvider {

	public static final ResourceLocation Uid = new ResourceLocation(BeyondEarth.MODID, "entity");
	public static final EntityDataProvider INSTANCE = new EntityDataProvider();

	@Override
	public void appendTooltip(ITooltip tooltip, EntityAccessor accessor, IPluginConfig config) {
		WailaPlugin.appendTooltip(tooltip, accessor.getServerData());
	}

	@Override
	public ResourceLocation getUid() {
		return Uid;
	}

	@Override
	public void appendServerData(CompoundTag data, EntityAccessor entity) {
		List<IGaugeValue> list = new ArrayList<>();

		if (entity instanceof IGaugeValuesProvider) {
			((IGaugeValuesProvider) entity).getDisplayGaugeValues().forEach(list::add);
		}

		WailaPlugin.put(data, WailaPlugin.write(list));
	}
}
