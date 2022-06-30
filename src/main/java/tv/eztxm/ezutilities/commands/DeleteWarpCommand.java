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

public class DeleteWarpCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("ezutilities.deletewarp")) player.sendMessage(FileManager.getMessage("NoPerms"));
        else {
            List<String> warps = (List<String>) FileManager.cfg.getList("Warps");
            if (args.length == 1) {
                if (!warps.contains(args[0])) player.sendMessage(FileManager.getMessage("WarpNotFound"));
                else {
                    EzUtilities.getInstance().removeLocation(args[0]);
                    warps.remove(args[0]);
                    FileManager.cfg.set("Warps", warps);
                    FileManager.save();
                    player.sendMessage(FileManager.getMessage("DeleteWarp").replace("%warp%", args[0]));
                }
                return true;
            }
            player.sendMessage(FileManager.getMessage("HowToUseDeleteWarp"));
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
