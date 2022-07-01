package tv.eztxm.ezutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tv.eztxm.ezutilities.utils.FileManager;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("ezutilities.teleport")) player.sendMessage(FileManager.getMessage("NoPerms"));
        else {
            switch (args.length) {
                case 1 -> {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) player.sendMessage(FileManager.getMessage("PlayerNotOnline"));
                    else {
                        player.teleport(target);
                        player.sendMessage(FileManager.getMessage("TeleportTo").replace("%target%", args[0]));
                    }
                }
                case 2 -> {
                    Player target1 = Bukkit.getPlayer(args[0]);
                    Player target2 = Bukkit.getPlayer(args[1]);
                    if (target1 == null) player.sendMessage(FileManager.getMessage("PlayerNotOnline"));
                    else {
                        if (target2 == null) player.sendMessage(FileManager.getMessage("PlayerNotOnline"));
                        else {
                            target1.teleport(target2);
                            if (player == target1) {
                                player.sendMessage(FileManager.getMessage("TeleportTo").replace("%target%", args[1]));
                                return true;
                            }
                            target1.sendMessage(FileManager.getMessage("TeleportTo2").replace("%target1%", args[0]).replace("%target2%", args[1]));
                            player.sendMessage();
                        }
                    }
                }
                case 3 -> {

                }
                case 4 -> {

                }
                case 5 -> {

                }
                case 6 -> {

                }
            }
            player.sendMessage(FileManager.getMessage("HowToUseTeleport"));
        }
        return true;
    }
}
