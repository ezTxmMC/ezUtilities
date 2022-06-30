package tv.eztxm.ezutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import tv.eztxm.ezutilities.utils.FileManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InvseeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("ezutilities.invsee")) player.sendMessage(FileManager.getMessage("NoPerms"));
        else {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) player.sendMessage(FileManager.getMessage("PlayerNotOnline"));
                else player.openInventory(target.getInventory());
                return true;
            }
            player.sendMessage(FileManager.getMessage("HowToUseInvsee"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> playerNames = new ArrayList<>();
            for (Player players : Bukkit.getOnlinePlayers()) {
                playerNames.add(players.getName());
            }
            return playerNames;
        }
        return Collections.emptyList();
    }
}
