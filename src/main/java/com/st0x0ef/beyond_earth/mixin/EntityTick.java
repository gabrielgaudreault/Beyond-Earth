package com.st0x0ef.beyond_earth.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import com.st0x0ef.beyond_earth.common.events.forge.EntityTickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityTick {

    @Inject(at = @At(value = "HEAD"), method = "tick")
    private void tick(CallbackInfo info) {
        Entity entity = (Entity) ((Object) this);

        MinecraftForge.EVENT_BUS.post(new EntityTickEvent(entity));
    }
}