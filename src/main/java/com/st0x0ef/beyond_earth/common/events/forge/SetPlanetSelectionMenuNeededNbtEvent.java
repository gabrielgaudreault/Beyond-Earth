package com.st0x0ef.beyond_earth.common.events.forge;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import com.st0x0ef.beyond_earth.common.entities.RocketEntity;

public class SetPlanetSelectionMenuNeededNbtEvent extends PlayerEvent {

    private final RocketEntity rocket;

    public SetPlanetSelectionMenuNeededNbtEvent(Player player, RocketEntity rocket) {
        super(player);
        this.rocket = rocket;
    }

    public RocketEntity getRocket() {
        return rocket;
    }
}
