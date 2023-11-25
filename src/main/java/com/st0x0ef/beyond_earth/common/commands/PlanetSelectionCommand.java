package com.st0x0ef.beyond_earth.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.common.util.Methods;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;

public class PlanetSelectionCommand {

    public PlanetSelectionCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("planetselectionmenu").requires(player -> player.hasPermission(2)).executes((context) -> openPlanetSelection(context.getSource())));
    }

    private int openPlanetSelection(CommandSourceStack source)  throws CommandSyntaxException {
        Player player = source.getPlayer();

        player.getPersistentData().putBoolean(BeyondEarth.MODID + ":planet_selection_menu_open", true);
        player.getPersistentData().putDouble(BeyondEarth.MODID + ":rocket_distance", 1e13);
        Methods.openPlanetGui(player);

        return 1;
    }
}
