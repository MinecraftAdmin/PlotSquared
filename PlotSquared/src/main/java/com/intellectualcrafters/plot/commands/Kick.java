/*
 * Copyright (c) IntellectualCrafters - 2014. You are not allowed to distribute
 * and/or monetize any of our intellectual property. IntellectualCrafters is not
 * affiliated with Mojang AB. Minecraft is a trademark of Mojang AB.
 *
 * >> File = Kick.java >> Generated by: Citymonstret at 2014-08-09 01:41
 */

package com.intellectualcrafters.plot.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.intellectualcrafters.plot.C;
import com.intellectualcrafters.plot.PlayerFunctions;
import com.intellectualcrafters.plot.Plot;
import com.intellectualcrafters.plot.PlotMain;

/**
 * Created by Citymonstret on 2014-08-01.
 */
public class Kick extends SubCommand {

    public Kick() {
        super(Command.KICK, "Kick a player from your plot", "kick", CommandCategory.ACTIONS, true);
    }

    @Override
    public boolean execute(final Player plr, final String... args) {
        if (!PlayerFunctions.isInPlot(plr)) {
            PlayerFunctions.sendMessage(plr, "You're not in a plot.");
            return false;
        }
        final Plot plot = PlayerFunctions.getCurrentPlot(plr);
        if (((plot == null) || !plot.hasOwner() || !plot.getOwner().equals(plr.getUniqueId())) && !PlotMain.hasPermission(plr, "plots.admin")) {
            PlayerFunctions.sendMessage(plr, C.NO_PLOT_PERMS);
            return false;
        }
        if (args.length != 1) {
            PlayerFunctions.sendMessage(plr, "&c/plot kick <player>");
            return false;
        }
        if (Bukkit.getPlayer(args[0]) == null) {
            PlayerFunctions.sendMessage(plr, C.INVALID_PLAYER.s().replaceAll("%player%", args[0]));
            return false;
        }
        final Player player = Bukkit.getPlayer(args[0]);
        if (!player.getWorld().equals(plr.getWorld()) || !PlayerFunctions.isInPlot(player) || (PlayerFunctions.getCurrentPlot(player) == null) || !PlayerFunctions.getCurrentPlot(player).equals(plot)) {
            PlayerFunctions.sendMessage(plr, C.INVALID_PLAYER.s().replaceAll("%player%", args[0]));
            return false;
        }
        player.teleport(player.getWorld().getSpawnLocation());
        return true;
    }
}
