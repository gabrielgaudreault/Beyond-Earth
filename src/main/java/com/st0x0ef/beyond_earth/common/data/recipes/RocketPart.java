package com.st0x0ef.beyond_earth.common.data.recipes;

import javax.annotation.Nonnull;

import com.st0x0ef.beyond_earth.common.registries.RocketPartsRegistry;

public record RocketPart(int slots) {

	@Nonnull
	public static final RocketPart EMPTY = new RocketPart(0);


	@Override
	public String toString() {
		return RocketPartsRegistry.ROCKET_PARTS_REGISTRY.get().getKey(this).toString();
	}
}
