package com.st0x0ef.beyond_earth.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import com.st0x0ef.beyond_earth.common.registries.DamageSourceRegistry;

import java.util.Random;

public class RadiationEffect extends MobEffect {
    public RadiationEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if ((new Random()).nextInt(2) == 0) {
            pLivingEntity.hurt(DamageSourceRegistry.of(pLivingEntity.level(), DamageSourceRegistry.DAMAGE_SOURCE_RADIATIONS), 1.0F);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        int j = 25 >> pAmplifier;
        if (j > 0) {
            return pDuration % j == 0;
        } else {
            return true;
        }
    }

}
