package com.st0x0ef.beyond_earth.common.blocks.entities;

import com.st0x0ef.beyond_earth.common.config.Config;
import com.st0x0ef.beyond_earth.common.registries.MobEffectsRegistry;
import com.st0x0ef.beyond_earth.common.registries.TagRegistry;
import com.st0x0ef.beyond_earth.common.util.Methods;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import com.st0x0ef.beyond_earth.common.registries.BlockEntityRegistry;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class UraniumBlockEntity extends BlockEntity {
    public UraniumBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.URANIUM_BLOCK_ENTITY.get(), pos, state);
    }

    public void tick() {
        AABB area = new AABB(this.getBlockPos()).inflate(Config.RADIOACTIVITY_EFFECT_DISTANCE.get());

        List<LivingEntity> entities = this.level.getEntitiesOfClass(LivingEntity.class, area);
        for (LivingEntity entity : entities) {
            if(!Methods.isLivingInJetSuit(entity) && !entity.getType().is(TagRegistry.ENTITY_RADIATION_INVULNERABLE_TAG)) {
                entity.addEffect(new MobEffectInstance(MobEffectsRegistry.RADIATION.get(), 100));
            }
        }
    }
}
