package com.intellectualcrafters.plot.uuid;

import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.BiMap;
import com.intellectualcrafters.json.JSONObject;
import com.intellectualcrafters.json.JSONTokener;
import com.intellectualcrafters.plot.PlotMain;
import com.intellectualcrafters.plot.Settings;
import com.intellectualcrafters.plot.StringWrapper;
import com.intellectualcrafters.plot.UUIDHandler;

/**
 * Created by Citymonstret on 2014-10-13.
 */
public class PlotUUIDSaver extends UUIDSaver {

    @Override
    public void globalPopulate() {
        JavaPlugin.getPlugin(PlotMain.class).getServer().getScheduler().runTaskAsynchronously(JavaPlugin.getPlugin(PlotMain.class), new Runnable() {
            @Override
            public void run() {
                final OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();
                final int length = offlinePlayers.length;
                final long start = System.currentTimeMillis();

                String name;
                UUID uuid;
                for (final OfflinePlayer player : offlinePlayers) {
                    uuid = player.getUniqueId();
                    if (!UUIDHandler.uuidExists(uuid)) {
                        name = player.getName();
                        UUIDHandler.add(new StringWrapper(name), uuid);
                    }
                }

                final long time = System.currentTimeMillis() - start;
                final int size = UUIDHandler.getUuidMap().size();
                double ups;
                if ((time == 0l) || (size == 0)) {
                    ups = size;
                }
                else {
                    ups = size / time;
                }

                // Plot Squared Only...
                PlotMain.sendConsoleSenderMessage("&cFinished caching of offline player UUIDs! Took &6" + time + "&cms (&6" + ups + "&c per millisecond), &6" + length + " &cUUIDs were cached" + " and there is now a grand total of &6" + size + " &ccached.");
            }
        });
    }

    @Override
    public void globalSave(final BiMap<StringWrapper, UUID> map) {

    }

    @Override
    public void save(final UUIDSet set) {

    }

    @Override
    public UUID mojangUUID(final String name) throws Exception {
        final URLConnection connection = new URL(Settings.API_URL + "?user=" + name).openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/4.0");
        final JSONTokener tokener = new JSONTokener(connection.getInputStream());
        final JSONObject root = new JSONObject(tokener);
        final String uuid = root.getJSONObject(name).getString("dashed");
        return UUID.fromString(uuid);
    }

    @Override
    public String mojangName(final UUID uuid) throws Exception {
        final URLConnection connection = new URL(Settings.API_URL + "?user=" + uuid.toString().replace("-", "")).openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/4.0");
        final JSONTokener tokener = new JSONTokener(connection.getInputStream());
        final JSONObject root = new JSONObject(tokener);
        return root.getJSONObject(uuid.toString().replace("-", "")).getString("username");
    }

    @Override
    public UUIDSet get(final String name) {
        return null;
    }

    @Override
    public UUIDSet get(final UUID uuid) {
        return null;
    }
}
