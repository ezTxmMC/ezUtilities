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

public class SetWarpCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("ezutilities.setwarp")) player.sendMessage(FileManager.getMessage("NoPerms"));
        else {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("spawn")) player.sendMessage(FileManager.getMessage("HowToUseSetSpawn"));
                else {
                    EzUtilities.getInstance().setWarpLocation(player.getLocation(), args[0]);
                    player.sendMessage(FileManager.getMessage("SetWarp").replace("%warp%", args[0]));
                    return true;
                }
                return true;
            }
            player.sendMessage(FileManager.getMessage("HowToUseSetWarp"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }
}
