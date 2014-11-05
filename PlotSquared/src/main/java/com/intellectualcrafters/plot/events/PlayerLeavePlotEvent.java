package com.intellectualcrafters.plot.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import com.intellectualcrafters.plot.Plot;

/**
 * Created by Citymonstret on 2014-08-16.
 */
public class PlayerLeavePlotEvent extends PlayerEvent {
    private static HandlerList handlers = new HandlerList();

    private final Plot         plot;

    /**
     * PlayerLeavePlotEvent: Called when a player leaves a plot
     *
     * @param player
     * @param plot
     */
    public PlayerLeavePlotEvent(final Player player, final Plot plot) {
        super(player);
        this.plot = plot;
    }

    /**
     * Get the plot involved
     *
     * @return Plot
     */
    public Plot getPlot() {
        return this.plot;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
