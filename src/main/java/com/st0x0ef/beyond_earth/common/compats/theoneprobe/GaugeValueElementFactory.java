package com.st0x0ef.beyond_earth.common.compats.theoneprobe;

import mcjty.theoneprobe.api.IElement;
import mcjty.theoneprobe.api.IElementFactory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import com.st0x0ef.beyond_earth.BeyondEarth;

public class GaugeValueElementFactory implements IElementFactory {

	public static final ResourceLocation ELEMENT_ID = new ResourceLocation(BeyondEarth.MODID, "top_element");
	public static final GaugeValueElementFactory INSTANCE = new GaugeValueElementFactory();

	@Override
	public ResourceLocation getId() {
		return ELEMENT_ID;
	}

	@Override
	public IElement createElement(FriendlyByteBuf buffer) {
		return new GaugeValueElement(buffer);
	}

}
