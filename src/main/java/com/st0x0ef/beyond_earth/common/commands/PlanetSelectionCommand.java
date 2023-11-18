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

        dispatcher.register(Commands.literal("beyond_earth")
                .then(Commands.literal("planetSelection").requires(c -> c.hasPermission(2)).executes((Command) -> {

                    return openPlanetSelection(Command.getSource());
                }))
                .then(Commands.literal("errorGUI").requires(c -> c.hasPermission(2)).executes((Command) -> {

                    return openErrorGui(Command.getSource());
                })));



    }

    private int openPlanetSelection(CommandSourceStack source)  throws CommandSyntaxException {
        Player player = source.getPlayer();

        player.getPersistentData().putBoolean(BeyondEarth.MODID + ":planet_selection_menu_open", true);
        player.getPersistentData().putInt(BeyondEarth.MODID + ":rocket_distance", 100000000);
        Methods.openPlanetGui(player);

        return 0;

    }

    private int openErrorGui(CommandSourceStack source)  throws CommandSyntaxException {
        Methods.openErrorGui(source.getPlayer());

        return 1;

    }

}
