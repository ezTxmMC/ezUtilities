package tv.eztxm.ezutilities.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import tv.eztxm.ezutilities.EzUtilities;
import tv.eztxm.ezutilities.utils.FileManager;

import java.util.Collections;
import java.util.List;

public class SetSpawnCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("ezutilities.setspawn")) player.sendMessage(FileManager.getMessage("NoPerms"));
        else {
            if (args.length == 0) {
                EzUtilities.getInstance().setLocation(player.getLocation(), "Spawn");
                player.sendMessage(FileManager.getMessage("SetSpawnLocation"));
                return true;
            }
            if (args.length == 3) {
                EzUtilities.getInstance().setLocation(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                        player.getLocation(), "Spawn");
                player.sendMessage(FileManager.getMessage("SetSpawnLocation"));
                return true;
            }
            if (args.length == 5) {
                EzUtilities.getInstance().setLocation(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                        Integer.parseInt(args[3]), Integer.parseInt(args[4]), player.getLocation(), "Spawn");
                player.sendMessage(FileManager.getMessage("SetSpawnLocation"));
                return true;
            }
            player.sendMessage(FileManager.getMessage("HowToUseSetSpawn"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }
}
