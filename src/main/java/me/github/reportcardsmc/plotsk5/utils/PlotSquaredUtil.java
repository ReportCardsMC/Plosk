package me.github.reportcardsmc.plotsk5.utils;

import com.plotsquared.core.api.PlotAPI;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotId;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlotSquaredUtil {

    public static PlotAPI plotAPI = new PlotAPI();

    public static Plot getPlot(@Nullable String id) {
        if (id == null) return null;
        PlotId plotId = PlotId.fromString(id);
        for (Plot plot : plotAPI.getAllPlots()) {
            if (plot.getId().equals(plotId)) return plot;
        }
        return null;
    }

    public static String[] getPlotIDs(UUID uuid) {
        List<String> ids = new ArrayList<>();
        for (Plot plot : plotAPI.getAllPlots()) {
            if (plot.getOwner().toString().equals(uuid.toString())) ids.add(plot.getId().toString());
        }
        return ids.toArray(new String[0]);
    }

}
