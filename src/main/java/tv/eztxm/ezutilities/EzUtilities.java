package tv.eztxm.ezutilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tv.eztxm.ezutilities.commands.*;
import tv.eztxm.ezutilities.listeners.JoinListener;
import tv.eztxm.ezutilities.listeners.QuitListener;
import tv.eztxm.ezutilities.utils.FileManager;

import java.util.ArrayList;
import java.util.List;

public final class EzUtilities extends JavaPlugin {
    private static EzUtilities instance;

    @Override
    public void onEnable() {
        instance = this;
        FileManager.setup();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void registerCommands() {
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("world").setExecutor(new WorldCommand());
        getCommand("importworld").setExecutor(new ImportWorldCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("deletewarp").setExecutor(new DeleteWarpCommand());
        getCommand("skull").setExecutor(new SkullCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("deletehome").setExecutor(new DeleteHomeCommand());
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
    }

    public void setWarpLocation(Location location, String locationName) {
        List<String> warps = (List<String>) FileManager.cfg.getList("Warps");
        FileManager.setLocation("Location." + locationName + ".X", location.getX());
        FileManager.setLocation("Location." + locationName + ".Y", location.getY());
        FileManager.setLocation("Location." + locationName + ".Z", location.getZ());
        FileManager.setLocation("Location." + locationName + ".Yaw", location.getYaw());
        FileManager.setLocation("Location." + locationName + ".Pitch", location.getPitch());
        FileManager.setLocation("Location." + locationName + ".World", location.getWorld().getName());
        if (!warps.contains(locationName)) warps.add(locationName);
        FileManager.setLocation("Warps", warps);
        FileManager.save();
        reloadConfig();
    }

    public void setHomeLocation(Location location, String uuid, String locationName) {
        List<String> homes = (List<String>) FileManager.cfg.getList("Homes." + uuid + ".List");
        if (homes == null) homes = new ArrayList<>();
        FileManager.setLocation("Home." + uuid + "." + locationName + ".X", location.getX());
        FileManager.setLocation("Home." + uuid + "." + locationName + ".Y", location.getY());
        FileManager.setLocation("Home." + uuid + "." + locationName + ".Z", location.getZ());
        FileManager.setLocation("Home." + uuid + "." + locationName + ".Yaw", location.getYaw());
        FileManager.setLocation("Home." + uuid + "." + locationName + ".Pitch", location.getPitch());
        FileManager.setLocation("Home." + uuid + "." + locationName + ".World", location.getWorld().getName());
        if (!homes.contains(locationName)) homes.add(locationName);
        FileManager.cfg.set("Homes." + uuid + ".List", homes);
        FileManager.cfg.set("Homes." + uuid + ".Count", FileManager.cfg.getInt("Home." + uuid + ".Count") + 1);
        FileManager.save();
        reloadConfig();
    }

    public void setLocation(Location location, String locationName) {
        FileManager.setLocation("Location." + locationName + ".X", location.getX());
        FileManager.setLocation("Location." + locationName + ".Y", location.getY());
        FileManager.setLocation("Location." + locationName + ".Z", location.getZ());
        FileManager.setLocation("Location." + locationName + ".Yaw", location.getYaw());
        FileManager.setLocation("Location." + locationName + ".Pitch", location.getPitch());
        FileManager.setLocation("Location." + locationName + ".World", location.getWorld().getName());
    }

    public void setLocation(double x, double y, double z, Location location, String locationName) {
        FileManager.setLocation("Location." + locationName + ".X", x);
        FileManager.setLocation("Location." + locationName + ".Y", y);
        FileManager.setLocation("Location." + locationName + ".Z", z);
        FileManager.setLocation("Location." + locationName + ".Yaw", location.getYaw());
        FileManager.setLocation("Location." + locationName + ".Pitch", location.getPitch());
        FileManager.setLocation("Location." + locationName + ".World", location.getWorld().getName());
    }

    public void setLocation(double x, double y, double z, float yaw, float pitch, Location location, String locationName) {
        FileManager.setLocation("Location." + locationName + ".X", x);
        FileManager.setLocation("Location." + locationName + ".Y", y);
        FileManager.setLocation("Location." + locationName + ".Z", z);
        FileManager.setLocation("Location." + locationName + ".Yaw", yaw);
        FileManager.setLocation("Location." + locationName + ".Pitch", pitch);
        FileManager.setLocation("Location." + locationName + ".World", location.getWorld().getName());
    }

    public void removeLocation(String locationName) {
        FileManager.setLocation("Location." + locationName, null);
    }

    public void removeHomeLocation(String uuid, String locationName) {
        FileManager.setLocation("Home." + uuid + "." + locationName, null);
    }

    public Location getLocation(String locationName) {
        double x = FileManager.getDouble("Location." + locationName + ".X");
        double y = FileManager.getDouble("Location." + locationName + ".Y");
        double z = FileManager.getDouble("Location." + locationName + ".Z");
        float yaw = FileManager.getInt("Location." + locationName + ".Yaw");
        float pitch = FileManager.getInt("Location." + locationName + ".Pitch");
        String worldName = FileManager.getWorld("Location." + locationName + ".World");
        World world = Bukkit.getWorld(worldName);
        return new Location(world, x, y, z, yaw, pitch);
    }

    public Location getHomeLocation(String locationName) {
        double x = FileManager.getDouble("Home." + locationName + ".X");
        double y = FileManager.getDouble("Home." + locationName + ".Y");
        double z = FileManager.getDouble("Home." + locationName + ".Z");
        float yaw = FileManager.getInt("Home." + locationName + ".Yaw");
        float pitch = FileManager.getInt("Home." + locationName + ".Pitch");
        String worldName = FileManager.getWorld("Home." + locationName + ".World");
        World world = Bukkit.getWorld(worldName);
        return new Location(world, x, y, z, yaw, pitch);
    }

    public ItemStack getSkull(String username) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(username);
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    public static EzUtilities getInstance() {
        return instance;
    }
}
