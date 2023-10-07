package com.st0x0ef.beyond_earth.common.events.forge;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Event;
import com.st0x0ef.beyond_earth.common.entities.LanderEntity;

public class TeleportAndCreateLanderEvent extends Event {

    private final LanderEntity landerEntity;
    private final Player player;

    public TeleportAndCreateLanderEvent(LanderEntity landerEntity, Player player) {
        this.landerEntity = landerEntity;
        this.player = player;
    }

    public LanderEntity getLanderEntity() {
        return landerEntity;
    }

    public Player getPlayer() {
        return player;
    }
}
