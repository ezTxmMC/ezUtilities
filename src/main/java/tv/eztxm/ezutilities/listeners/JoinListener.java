package tv.eztxm.ezutilities.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tv.eztxm.ezutilities.EzUtilities;
import tv.eztxm.ezutilities.utils.FileManager;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (FileManager.getBoolean("SpawnTeleportJoin")) {
            if (EzUtilities.getInstance().getLocation("Spawn") != null) player.teleport(EzUtilities.getInstance().getLocation("Spawn"));
            else player.sendMessage(FileManager.getMessage("SpawnNotFound"));
        }
        if (FileManager.getBoolean("CustomJoinMessage")) {
            event.setJoinMessage(FileManager.getMessage("CustomJoinMessage")
                    .replace("%player%", player.getName()));
        }
    }
}
