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

public class WarpCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> warps = (List<String>) FileManager.cfg.getList("Warps");
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("ezutilities.warp")) player.sendMessage(FileManager.getMessage("NoPerms"));
        else {
            if (args.length == 0) {
                player.sendMessage(FileManager.getMessage("Warps").replace("%warps%", warps.toString()));
                return true;
            }
            if (args.length == 1) {
                if (!warps.contains(args[0])) player.sendMessage(FileManager.getMessage("WarpNotFound"));
                else {
                    player.teleport(EzUtilities.getInstance().getLocation(args[0]));
                    player.sendMessage(FileManager.getMessage("Warp").replace("%warp%", args[0]));
                }
                return true;
            }
            player.sendMessage(FileManager.getMessage("HowToUseWarp"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> warps = (List<String>) FileManager.cfg.getList("Warps");
        if (args.length == 1) return warps;
        return Collections.emptyList();
    }
}
