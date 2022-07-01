package tv.eztxm.ezutilities.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final File folder = new File("plugins/ezUtilities");
    private static final File config = new File("plugins/ezUtilities/config.yml");
    private static final File messages = new File("plugins/ezUtilities/messages.yml");
    private static final File locations = new File("plugins/ezUtilities/locations.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(config);
    public static YamlConfiguration msg = YamlConfiguration.loadConfiguration(messages);
    public static YamlConfiguration loc = YamlConfiguration.loadConfiguration(locations);
    private static List<String> warps = new ArrayList<>();

    public static void save() {

        try {
            cfg.save(config);
            msg.save(messages);
            loc.save(locations);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setup() {
        if (!folder.exists()) folder.mkdir();
        if (!config.exists()) try {config.createNewFile();} catch (IOException ignored) {}
        if (!messages.exists()) try {messages.createNewFile();} catch (IOException ignored) {}
        if (!locations.exists()) try {locations.createNewFile();} catch (IOException ignored) {}
        cfg.addDefault("Prefix", "&9&lezUtilities &8&l| &7");
        cfg.addDefault("SpawnTeleportJoin", false);
        cfg.addDefault("CustomJoinMessage", false);
        cfg.addDefault("CustomQuitMessage", false);
        cfg.addDefault("DisableJoinMessage", false);
        cfg.addDefault("DisableQuitMessage", false);
        cfg.addDefault("HomeCount", 1);
        cfg.addDefault("Warps", warps);
        cfg.options().copyDefaults(true);
        msg.addDefault("NoPerms", "&cNot enough permissions");
        msg.addDefault("SpawnNotFound", "The Spawn Location can not be found");
        msg.addDefault("FlyOn", "You can be now fly");
        msg.addDefault("FlyOff", "You can be now not more fly");
        msg.addDefault("FlyOnByPlayer", "%target% can be now fly");
        msg.addDefault("FlyOffByPlayer", "%target% can be now not more fly");
        msg.addDefault("HowToUseFly", "/fly <player>");
        msg.addDefault("HowToUseGamemode", "/gamemode <gamemode> <player>");
        msg.addDefault("HowToUseImportWorld", "/importworld <world>");
        msg.addDefault("HowToUseInvsee", "/invsee <player>");
        msg.addDefault("HowToUseSetSpawn", "\n/setspawn\n/setspawn <x> <y> <z>\n/setspawn <x> <y> <z> <yaw> <pitch>");
        msg.addDefault("HowToUseWorld", "/world <world>");
        msg.addDefault("HowToUseSetWarp", "/setwarp <warp>");
        msg.addDefault("HowToUseWarp", "/warp <warp>");
        msg.addDefault("HowToUseDeleteWarp", "/deletewarp <warp>");
        msg.addDefault("HowToUseSkull", "/skull <player>");
        msg.addDefault("HowToUseSetHome", "/sethome <home>");
        msg.addDefault("HowToUseHome", "/home <home>");
        msg.addDefault("HowToUseDeleteHome", "/deletehome <home>");
        msg.addDefault("PlayerNotOnline", "&cPlayer not Online");
        msg.addDefault("PlayerNotExists", "&cPlayer not exists");
        msg.addDefault("ChangeGamemode", "Your Gamemode now %gamemode%");
        msg.addDefault("ChangeGamemodeByPlayer", "%target% Gamemode now %gamemode%");
        msg.addDefault("WorldNotFound", "World can not be found");
        msg.addDefault("SetSpawnLocation", "The Spawn Location has been set");
        msg.addDefault("SpawnTeleport", "You has been teleported to the Spawn Location");
        msg.addDefault("WorldTeleport", "You has been teleported to the World %world%");
        msg.addDefault("SetWarp", "You has been set the Warp %warp%");
        msg.addDefault("Warp", "You has been teleported to the Warp %warp%");
        msg.addDefault("DeleteWarp", "You has been remove the Warp %warp%");
        msg.addDefault("SetHome", "You has been set the Home %home%");
        msg.addDefault("Home", "You has been teleported to the Home %home%");
        msg.addDefault("DeleteHome", "You has been remove the Home %home%");
        msg.addDefault("Warps", "All Warps: %warps%");
        msg.addDefault("Homes", "Your Homes: %homes%");
        msg.addDefault("WarpNotFound", "The Warp can not be found");
        msg.addDefault("HomeNotFound", "The Home can not be found");
        msg.addDefault("ToManyHomes", "You don't can use more homes");
        msg.addDefault("Skull", "You have the skull of %target%");
        msg.addDefault("CustomJoinMessage", "%player% has joined the server");
        msg.addDefault("CustomQuitMessage", "%player% has left the server");
        msg.addDefault("TeleportTo", "You has been teleported to %target%");
        msg.addDefault("TeleportTo2", "%target1% has been teleported to %target2%");
        msg.addDefault("TeleportPlayerTo", "You has been teleported %target1% to %target2%");
        msg.addDefault("NumberFormatNotRight", "&cYou Number Format are not a double");
        msg.options().copyDefaults(true);
        save();
    }

    public static void setLocation(String path, Object value) {
        loc.set(path, value);
        save();
    }

    public static boolean getBoolean(String path) {
        return cfg.getBoolean(path);
    }

    public static String getString(String path) {
        return cfg.getString(path).replace('&', '§');
    }

    public static String getMessage(String path) {
        return getString("Prefix") + msg.getString(path).replace('&', '§');
    }

    public static double getDouble(String path) {
        return loc.getDouble(path);
    }

    public static int getInt(String path) {
        return loc.getInt(path);
    }

    public static String getWorld(String path) {
        return loc.getString(path);
    }
}
