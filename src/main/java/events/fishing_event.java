package events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class fishing_event implements Listener {

    @EventHandler
    public void onPlayerFishing(PlayerFishEvent event) {
        Player player = event.getPlayer();

        if (event.getCaught() != null) {
            player.sendMessage("You caught a " + ChatColor.YELLOW + ChatColor.BOLD + event.getCaught().getName());
        }
    }
}
