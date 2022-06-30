package tv.eztxm.ezutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import tv.eztxm.ezutilities.utils.FileManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorldCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("ezutilities.world")) player.sendMessage(FileManager.getMessage("NoPerms"));
        else {
            if (args.length == 1) {
                World world = Bukkit.getWorld(args[0]);
                if (world == null) player.sendMessage(FileManager.getMessage("WorldNotFound"));
                else {
                    player.teleport(world.getSpawnLocation());
                    player.sendMessage(FileManager.getMessage("WorldTeleport").replace("%world%", args[0]));
                }
                return true;
            }
            player.sendMessage(FileManager.getMessage("HowToUseWorld"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> worldNames = new ArrayList<>();
        if (args.length == 1) {
            for (World worlds : Bukkit.getWorlds()) {
                worldNames.add(worlds.getName());
            }
            return worldNames;
        }
        return Collections.emptyList();
    }
}
