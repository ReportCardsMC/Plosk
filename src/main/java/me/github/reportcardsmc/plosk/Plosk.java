package me.github.reportcardsmc.plosk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.plotsquared.core.PlotSquared;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Plosk extends JavaPlugin {

    public static Plosk instance;
    public static SkriptAddon addon;
    public static PlotSquared plot;
    private int metricID = 13147;
    private Metrics metrics;

    @Override
    public void onEnable() {
        metrics = new Metrics(this, metricID);
        // Plugin startup logic
        instance = this;
        plot = PlotSquared.get();
        if (plot == null) {
            getLogger().severe("You don't have plotsquared5 installed.");
            getPluginLoader().disablePlugin(this);
            return;
        } else {
            if (plot.getVersion().version[0] < 5) {
                getLogger().severe("You don't have plotsquared 5 installed, you have " + plot.getVersion().versionString);
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
