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

public class FlyCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("ezutilities.fly")) player.sendMessage(FileManager.getMessage("NoPerms"));
        else {
            if (args.length == 0) {
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.sendMessage(FileManager.getMessage("FlyOff"));
                } else {
                    player.setAllowFlight(true);
                    player.setFlying(false);
                    player.sendMessage(FileManager.getMessage("FlyOn"));
                }
                return true;
            }
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) player.sendMessage(FileManager.getMessage("PlayerNotOnline"));
                else {
                    if (target.getAllowFlight()) {
                        target.setAllowFlight(false);
                        target.setFlying(false);
                        target.sendMessage(FileManager.getMessage("FlyOff"));
                        player.sendMessage(FileManager.getMessage("FlyOffByPlayer").replace("%target%", target.getName()));
                    } else {
                        target.setAllowFlight(true);
                        target.setFlying(false);
                        target.sendMessage(FileManager.getMessage("FlyOn"));
                        player.sendMessage(FileManager.getMessage("FlyOnByPlayer").replace("%target%", target.getName()));
                    }
                }
                return true;
            }
            player.sendMessage(FileManager.getMessage("HowToUseFly"));
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
