package me.github.reportcardsmc.plotsk5.utils;

import com.plotsquared.core.api.PlotAPI;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotId;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    public static void setOutline(Plot plot, Material material) {
        BlockType blockType = BlockTypes.get(material.toString());
        if (blockType == null) return;
        plot.getManager().setComponent(plot.getId(), "outline", blockType.getDefaultState());
    }

}
