package commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.lang.Math;

public class summon_entity implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        if (strings.length != 5) {
            player.sendMessage("Usage: /summon_entity <entity> <count> <location>");
            return false;
        }

        EntityType entityType;
        try {
            entityType = EntityType.valueOf(strings[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            player.sendMessage("Invalid entity");
            return false;
        }

        int count;
        try {
            count = Integer.parseInt(strings[1]);
        } catch (NumberFormatException e) {
            player.sendMessage("The radius must be an integer");
            return false;
        }

        int count_to_0 = count;
        World world = player.getWorld();

        double x, y, z;
        double x_cur, y_cur, z_cur;
        try {
            x = Double.parseDouble(strings[2]);
            y = Double.parseDouble(strings[3]);
            z = Double.parseDouble(strings[4]);

            x_cur = x;
            y_cur = y;
            z_cur = z;
        } catch (NumberFormatException e) {
            player.sendMessage("Invalid coordinates. Mobs will be summoned at your current location");
            x_cur = player.getLocation().getX();
            y_cur = player.getLocation().getY();
            z_cur = player.getLocation().getZ();
        }

        Location loc = new Location(world, x_cur, y_cur, z_cur);

        while (count_to_0 > 0) {
            world.spawnEntity(loc, entityType);
            count_to_0--;
        }


        player.sendMessage("Summoned " + count + " " + entityType.getName() + "(s) at " + Math.round(x_cur) + " " + Math.round(y_cur) + " " + Math.round(z_cur));
        return true;
    }
}
