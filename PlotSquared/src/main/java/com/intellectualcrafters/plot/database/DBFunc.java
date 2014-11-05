/*
 * Copyright (c) IntellectualCrafters - 2014. You are not allowed to distribute
 * and/or monetize any of our intellectual property. IntellectualCrafters is not
 * affiliated with Mojang AB. Minecraft is a trademark of Mojang AB.
 *
 * >> File = DBFunc.java >> Generated by: Citymonstret at 2014-08-09 01:43
 */

package com.intellectualcrafters.plot.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

import org.bukkit.OfflinePlayer;

import com.intellectualcrafters.plot.Flag;
import com.intellectualcrafters.plot.Plot;
import com.intellectualcrafters.plot.PlotComment;
import com.intellectualcrafters.plot.PlotId;

/**
 * @author Citymonstret
 */
public class DBFunc {

    public static AbstractDB dbManager;

    // TODO MongoDB @Brandon

    /**
     * Set Plot owner
     *
     * @param plot
     * @param uuid
     */
    public static void setOwner(final Plot plot, final UUID uuid) {
        dbManager.setOwner(plot, uuid);
    }

    public static void createAllSettingsAndHelpers(final ArrayList<Plot> plots) {
        dbManager.createAllSettingsAndHelpers(plots);
    }

    /**
     * Create a plot
     *
     * @param plots
     */
    public static void createPlots(final ArrayList<Plot> plots) {
        dbManager.createPlots(plots);
    }

    /**
     * Create a plot
     *
     * @param plot
     */
    public static void createPlot(final Plot plot) {
        dbManager.createPlot(plot);
    }

    /**
     * Create tables
     *
     * @throws Exception
     */
    public static void createTables(final String database, final boolean add_constraint) throws Exception {
        dbManager.createTables(database, add_constraint);
    }

    /**
     * Delete a plot
     *
     * @param plot
     */
    public static void delete(final String world, final Plot plot) {
        dbManager.delete(world, plot);
    }

    /**
     * Create plot settings
     *
     * @param id
     * @param plot
     */
    public static void createPlotSettings(final int id, final Plot plot) {
        dbManager.createPlotSettings(id, plot);
    }

    public static int getId(final String world, final PlotId id2) {
        return dbManager.getId(world, id2);
    }

    /**
     * Get a plot id
     *
     * @param plot_id
     * @return
     */
    /*
     * public static int getId(String world, PlotId id2) { Statement stmt =
     * null; try { stmt = connection.createStatement(); ResultSet r =
     * stmt.executeQuery("SELECT `id` FROM `plot` WHERE `plot_id_x` = '" + id2.x
     * + "' AND `plot_id_z` = '" + id2.y + "' AND `world` = '" + world +
     * "' ORDER BY `timestamp` ASC"); int id = Integer.MAX_VALUE;
     * while(r.next()) { id = r.getInt("id"); } stmt.close(); return id; }
     * catch(SQLException e) { e.printStackTrace(); } return Integer.MAX_VALUE;
     * }
     */

    /**
     * @return
     */
    public static LinkedHashMap<String, HashMap<PlotId, Plot>> getPlots() {
        return dbManager.getPlots();
    }

    public static void setMerged(final String world, final Plot plot, final boolean[] merged) {
        dbManager.setMerged(world, plot, merged);
    }

    public static void setFlags(final String world, final Plot plot, final Flag[] flags) {
        dbManager.setFlags(world, plot, flags);
    }

    /**
     * @param plot
     * @param alias
     */
    public static void setAlias(final String world, final Plot plot, final String alias) {
        dbManager.setAlias(world, plot, alias);
    }

    public static void purge(final String world, final PlotId id) {
        dbManager.purge(world, id);
    }

    public static void purge(final String world) {
        dbManager.purge(world);
    }

    /**
     * @param plot
     * @param position
     */
    public static void setPosition(final String world, final Plot plot, final String position) {
        dbManager.setPosition(world, plot, position);
    }

    /**
     * @param id
     * @return
     */
    public static HashMap<String, Object> getSettings(final int id) {
        return dbManager.getSettings(id);
    }

    /**
     *
     */
    public static UUID everyone = UUID.fromString("1-1-3-3-7");

    /**
     * @param plot
     * @param comment
     */
    public static void removeComment(final String world, final Plot plot, final PlotComment comment) {
        dbManager.removeComment(world, plot, comment);
    }

    /**
     * @param plot
     * @param comment
     */
    public static void setComment(final String world, final Plot plot, final PlotComment comment) {
        dbManager.setComment(world, plot, comment);
    }

    /**
     * @param plot
     * @param comment
     */
    public static ArrayList<PlotComment> getCommenst(final String world, final Plot plot, final int tier) {
        return dbManager.getComments(world, plot, tier);
    }

    /**
     * @param plot
     * @param player
     */
    public static void removeHelper(final String world, final Plot plot, final OfflinePlayer player) {
        dbManager.removeHelper(world, plot, player);
    }

    /**
     * @param plot
     * @param player
     */
    public static void removeTrusted(final String world, final Plot plot, final OfflinePlayer player) {
        dbManager.removeTrusted(world, plot, player);
    }

    /**
     * @param plot
     * @param player
     */
    public static void setHelper(final String world, final Plot plot, final OfflinePlayer player) {
        dbManager.setHelper(world, plot, player);
    }

    /**
     * @param plot
     * @param player
     */
    public static void setTrusted(final String world, final Plot plot, final OfflinePlayer player) {
        dbManager.setTrusted(world, plot, player);
    }

    /**
     * @param plot
     * @param player
     */
    public static void removeDenied(final String world, final Plot plot, final OfflinePlayer player) {
        dbManager.removeDenied(world, plot, player);
    }

    /**
     * @param plot
     * @param player
     */
    public static void setDenied(final String world, final Plot plot, final OfflinePlayer player) {
        dbManager.setDenied(world, plot, player);
    }

    public static double getRatings(final Plot plot) {
        return dbManager.getRatings(plot);
    }
}
