/*
 * Copyright (c) IntellectualCrafters - 2014. You are not allowed to distribute
 * and/or monetize any of our intellectual property. IntellectualCrafters is not
 * affiliated with Mojang AB. Minecraft is a trademark of Mojang AB.
 *
 * >> File = Unlink.java >> Generated by: Citymonstret at 2014-08-09 01:41
 */

package com.intellectualcrafters.plot.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.intellectualcrafters.plot.C;
import com.intellectualcrafters.plot.PlayerFunctions;
import com.intellectualcrafters.plot.Plot;
import com.intellectualcrafters.plot.PlotHelper;
import com.intellectualcrafters.plot.PlotId;
import com.intellectualcrafters.plot.PlotMain;
import com.intellectualcrafters.plot.PlotManager;
import com.intellectualcrafters.plot.PlotWorld;
import com.intellectualcrafters.plot.SetBlockFast;
import com.intellectualcrafters.plot.database.DBFunc;
import com.intellectualcrafters.plot.events.PlotUnlinkEvent;

/**
 * Created by Citymonstret on 2014-08-01.
 */
public class Unlink extends SubCommand {

    public Unlink() {
        super(Command.UNLINK, "Unlink a mega-plot", "unlink", CommandCategory.ACTIONS, true);
    }

    @Override
    public boolean execute(final Player plr, final String... args) {
        if (!PlayerFunctions.isInPlot(plr)) {
            PlayerFunctions.sendMessage(plr, "You're not in a plot.");
            return true;
        }
        final Plot plot = PlayerFunctions.getCurrentPlot(plr);
        if (((plot == null) || !plot.hasOwner() || !plot.getOwner().equals(plr.getUniqueId())) && !PlotMain.hasPermission(plr, "plots.admin")) {
            PlayerFunctions.sendMessage(plr, C.NO_PLOT_PERMS);
            return true;
        }
        if (PlayerFunctions.getTopPlot(plr.getWorld(), plot).equals(PlayerFunctions.getBottomPlot(plr.getWorld(), plot))) {
            PlayerFunctions.sendMessage(plr, C.UNLINK_IMPOSSIBLE);
            return true;
        }
        final World world = plr.getWorld();
        final PlotId pos1 = PlayerFunctions.getBottomPlot(world, plot).id;
        final PlotId pos2 = PlayerFunctions.getTopPlot(world, plot).id;
        final ArrayList<PlotId> ids = PlayerFunctions.getPlotSelectionIds(world, pos1, pos2);

        final PlotUnlinkEvent event = new PlotUnlinkEvent(world, ids);

        Bukkit.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            event.setCancelled(true);
            PlayerFunctions.sendMessage(plr, "&cUnlink has been cancelled");
            return false;
        }

        final PlotManager manager = PlotMain.getPlotManager(world);
        final PlotWorld plotworld = PlotMain.getWorldSettings(world);

        manager.startPlotUnlink(world, plotworld, ids);

        for (final PlotId id : ids) {
            final Plot myplot = PlotMain.getPlots(world).get(id);

            if (plot.helpers != null) {
                myplot.helpers = plot.helpers;
            }
            if (plot.denied != null) {
                myplot.denied = plot.denied;
            }
            myplot.deny_entry = plot.deny_entry;
            myplot.settings.setMerged(new boolean[] { false, false, false, false });
            DBFunc.setMerged(world.getName(), myplot, myplot.settings.getMerged());
        }

        for (int x = pos1.x; x <= pos2.x; x++) {
            for (int y = pos1.y; y <= pos2.y; y++) {
                final boolean lx = x < pos2.x;
                final boolean ly = y < pos2.y;

                final Plot p = PlotHelper.getPlot(world, new PlotId(x, y));

                if (lx) {
                    manager.createRoadEast(plotworld, p);
                    if (ly) {
                        manager.createRoadSouthEast(plotworld, p);
                    }

                }

                if (ly) {
                    manager.createRoadSouth(plotworld, p);
                }

            }
        }
        try {
            if (PlotHelper.canSetFast) {
                SetBlockFast.update(plr);
            }
        }
        catch (final Exception e) {

        }

        manager.finishPlotUnlink(world, plotworld, ids);

        PlayerFunctions.sendMessage(plr, "&6Plots unlinked successfully!");
        return true;
    }
}
