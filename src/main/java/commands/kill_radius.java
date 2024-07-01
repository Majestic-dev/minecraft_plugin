package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class kill_radius implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        if (strings.length != 2) {
            player.sendMessage("Usage: /kill_radius <entity> <radius>");
            return false;
        }

        EntityType entityType;
        try {
            entityType = EntityType.valueOf(strings[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            player.sendMessage("Invalid entity");
            return false;
        }

        int radius;
        try {
            radius = Integer.parseInt(strings[1]);
        } catch (NumberFormatException e) {
            player.sendMessage("The radius must be an integer");
            return false;
        }

        List<Entity> nearbyentities = player.getNearbyEntities(radius, radius, radius);
        int killCount = 0;

        Random random = new Random();

        for (Entity entity : nearbyentities) {
            if (entity.getType() == entityType) {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;

                    if (random.nextBoolean()) {
                        livingEntity.setHealth(0);
                    } else {
                        livingEntity.remove();
                    }

                    killCount++;
                }
            }
        }
        
        player.sendMessage("Killed " + ChatColor.RED + killCount + " " + entityType.name().toLowerCase() + (killCount == 1 ? "" : "s") + " within a radius of " + radius + " blocks");
        Bukkit.broadcastMessage(player.getName() + ChatColor.RED + ChatColor.BOLD + " KILLED " + ChatColor.WHITE + killCount + " " + entityType.name().toLowerCase() + (killCount == 1 ? "" : "s"));
        return true;
    }
}
