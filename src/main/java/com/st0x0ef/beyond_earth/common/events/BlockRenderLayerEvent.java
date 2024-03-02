package com.st0x0ef.beyond_earth.common.events;

import com.st0x0ef.beyond_earth.common.registries.BlockRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class BlockRenderLayerEvent {
    public static void renderLayerEvent(final FMLClientSetupEvent event) {
        /** BLOCK */
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.COAL_LANTERN_BLOCK.get(), RenderType.translucent());
    }
}
