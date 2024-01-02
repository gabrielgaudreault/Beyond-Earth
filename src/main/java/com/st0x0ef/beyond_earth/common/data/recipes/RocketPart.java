package com.st0x0ef.beyond_earth.common.data.recipes;

import javax.annotation.Nonnull;

import com.st0x0ef.beyond_earth.common.registries.RocketPartsRegistry;

public class RocketPart {

	@Nonnull
	public static final RocketPart EMPTY = new RocketPart(0);

	private final int slots;

	public RocketPart(int slots) {
		this.slots = slots;
	}

	public int getSlots() {
		return this.slots;
	}

	@Override
	public String toString() {
		return RocketPartsRegistry.ROCKET_PARTS_REGISTRY.get().getKey(this).toString();
	}
}
