package me.github.reportcardsmc.plotsk5;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.plotsquared.core.PlotSquared;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class PlotSk5 extends JavaPlugin {

    public static PlotSk5 instance;
    public static SkriptAddon addon;
    public static PlotSquared plot;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        plot = PlotSquared.get();
        if (plot == null) {
            getLogger().severe("You don't have plotsquared5 installed.");
            getPluginLoader().disablePlugin(this);
            return;
        } else {
            if (plot.getVersion().version[0] != 5) {
                getLogger().severe("You don't have plotsquared 5 installed.");
                getPluginLoader().disablePlugin(this);
                return;
            }

        }
        addon = Skript.registerAddon(this);
        if (!loadClasses()) {
            getLogger().severe("Couldn't load skript classes.");
            getPluginLoader().disablePlugin(this);
            return;
        }
        getLogger().info("Addon has been enabled");
    }

    private boolean loadClasses() {
        try {
            addon.loadClasses("me.github.reportcardsmc.plotsk5", "elements");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
