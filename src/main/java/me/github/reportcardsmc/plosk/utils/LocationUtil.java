package me.github.reportcardsmc.plosk.utils;

import com.plotsquared.core.plot.Plot;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationUtil {

    @Nullable
    public static Block[] getBorder(Plot plot, int borderHeight) {
        List<Block> blocks = new ArrayList<>();

        List<com.plotsquared.core.location.Location> corners = Arrays.asList(plot.getCorners());
        for (int i = 0; i < corners.size(); i++) {
            com.plotsquared.core.location.Location corner = corners.get(i);

            Location loc = toLocation(corner);
            loc.setY(borderHeight);

            int next = corners.size() == i + 1 ? 0 : i + 1;

            Location nextLoc = toLocation(corners.get(next));
            nextLoc.setY(borderHeight);

            double distance = loc.distance(nextLoc);
            Vector p1 = loc.toVector();
            Vector p2 = nextLoc.toVector();
            Vector vector = p2.clone().subtract(p1).normalize();
            double length = 0;
            for (; length < distance; p1.add(vector)) {
                blocks.add(p1.toLocation(loc.getWorld()).getBlock());
                length += 1;
            }
        }

        return blocks.toArray(new Block[0]);
    }

    private static Location toLocation(com.plotsquared.core.location.Location loc) {
        return new Location(Bukkit.getWorld(loc.getWorld().getName()), loc.getX(), loc.getY(), loc.getZ());
    }

}
