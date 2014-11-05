/*
 * Copyright (c) IntellectualCrafters - 2014. You are not allowed to distribute
 * and/or monetize any of our intellectual property. IntellectualCrafters is not
 * affiliated with Mojang AB. Minecraft is a trademark of Mojang AB.
 *
 * >> File = PlayerClaimPlotEvent.java >> Generated by: Citymonstret at
 * 2014-08-09 15:21
 */

package com.intellectualcrafters.plot.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.intellectualcrafters.plot.Flag;
import com.intellectualcrafters.plot.Plot;

/**
 * Called when a Flag is added to a plot
 */
public class PlotFlagAddEvent extends Event implements Cancellable {
    private static HandlerList handlers = new HandlerList();
    private boolean            cancelled;

    private final Plot         plot;
    private final Flag         flag;

    /**
     * PlotFlagAddEvent: Called when a Flag is added to a plot
     *
     * @param flag
     * @param plot
     */
    public PlotFlagAddEvent(final Flag flag, final Plot plot) {
        this.plot = plot;
        this.flag = flag;
    }

    /**
     * Get the plot involved
     *
     * @return Plot
     */
    public Plot getPlot() {
        return this.plot;
    }

    /**
     * Get the flag involved
     *
     * @return Flag
     */
    public Flag getFlag() {
        return this.flag;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean b) {
        this.cancelled = b;
    }
}
