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

public class DeleteHomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("ezutilities.deletehome")) player.sendMessage(FileManager.getMessage("NoPerms"));
        else {
            String uuid = player.getUniqueId().toString();
            List<String> homes = (List<String>) FileManager.cfg.getList("Homes." + uuid + ".List");
            if (homes == null) player.sendMessage(FileManager.getMessage("HomeNotFound"));
            else {
                if (args.length == 1) {
                    if (!homes.contains(args[0])) player.sendMessage(FileManager.getMessage("HomeNotFound"));
                    else {
                        EzUtilities.getInstance().removeHomeLocation(uuid, args[0]);
                        player.sendMessage(FileManager.getMessage("DeleteHome").replace("%home%", args[0]));
                    }
                    return true;
                }
                player.sendMessage(FileManager.getMessage("HowToUseDeleteHome"));
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player player)) return Collections.emptyList();
        String uuid = player.getUniqueId().toString();
        List<String> homes = (List<String>) FileManager.cfg.getList("Homes." + uuid + ".List");
        if (args.length == 1) return homes;
        return Collections.emptyList();
    }
}
