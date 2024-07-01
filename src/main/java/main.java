import commands.kill_radius;
import commands.summon_entity;
import events.fishing_event;
import org.bukkit.plugin.java.JavaPlugin;
import utils.entity_autocomplete;

public class main extends JavaPlugin {

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        getCommand("kill_radius").setExecutor(new kill_radius());
        getCommand("kill_radius").setTabCompleter(new entity_autocomplete());
        getCommand("summon_entity").setExecutor(new summon_entity());
        getCommand("summon_entity").setTabCompleter(new entity_autocomplete());
        getServer().getPluginManager().registerEvents(new fishing_event(), this);
    }
}
