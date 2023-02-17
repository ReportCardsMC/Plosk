package me.github.reportcardsmc.plosk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.plotsquared.core.PlotSquared;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
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
        metrics.addCustomChart(new SimplePie("skript_version", () -> Skript.getVersion().toString()));
        // Plugin startup logic
        instance = this;
        plot = PlotSquared.get();
        if (plot == null) {
            getLogger().severe("You don't have plotsquared 6 installed.");
            getPluginLoader().disablePlugin(this);
            return;
        }
        addon = Skript.registerAddon(this);
        if (!loadClasses()) {
            getLogger().severe("Couldn't load skript classes.");
            getPluginLoader().disablePlugin(this);
            return;
        } else {
            getLogger().info("Classes were registered.");
        }
        getLogger().info("Addon has been enabled");
    }

    private boolean loadClasses() {
        try {
            addon.loadClasses("me.github.reportcardsmc.plosk", "elements");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
