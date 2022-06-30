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

public class SetHomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("ezutilities.sethome")) player.sendMessage(FileManager.getMessage("NoPerms"));
        else {
            if (args.length == 1) {
                if (!player.hasPermission("ezutilities.homes.unlimited")) {
                    if (FileManager.cfg.getInt("Homes." + player.getUniqueId() + ".Count") >= (FileManager.cfg.getInt("HomeCount"))) {
                        player.sendMessage(FileManager.getMessage("ToManyHomes"));
                    } else {
                        EzUtilities.getInstance().setHomeLocation(player.getLocation(), player.getUniqueId().toString(), args[0]);
                        player.sendMessage(FileManager.getMessage("SetHome").replace("%home%", args[0]));
                    }
                } else {
                    EzUtilities.getInstance().setHomeLocation(player.getLocation(), player.getUniqueId().toString(), args[0]);
                    player.sendMessage(FileManager.getMessage("SetHome").replace("%home%", args[0]));
                }
                return true;
            }
            player.sendMessage(FileManager.getMessage("HowToUseSetHome"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }
}
