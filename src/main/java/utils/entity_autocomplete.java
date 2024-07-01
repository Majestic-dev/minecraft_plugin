package utils;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class entity_autocomplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            return new ArrayList<>();
        }

        Player player = (Player) commandSender;
        World world = player.getWorld();

        List<String> completions = world.getEntities().stream()
                .filter(entity -> !(entity instanceof Player))
                .map(Entity::getName)
                .collect(Collectors.toList());

        return completions.stream()
                .filter(name -> name.toLowerCase().startsWith(strings[0].toLowerCase()))
                .collect(Collectors.toList());
    }
}
