package me.github.reportcardsmc.plotsk5.utils;

import com.plotsquared.core.plot.Plot;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LocationUtil {

//    public static Location[] offset(Location location, Vector[] vectors) {
//        Location[] offset = new Location[vectors.length];
//
//        int i = 0;
//        for (Vector v : vectors) {
//            i++;
//            offset[i] = location.toVector().clone().add(v).toLocation(location.getWorld());
//        }
//
//        return offset;
//    }

//    public static Location[] getLines(Location[] locations, double density) {
//        Vector origin = new Vector(0, 0, 0);
//        Vector vector;
//        ArrayList<Location> line = new ArrayList<>();
//        int add1EveryTime = 0;
//        for (Location l: locations) {
//            add1EveryTime++;
//            int i = locations.length == add1EveryTime ? 0 : add1EveryTime - 1;
//            vector = locations[i].toVector().subtract(l.toVector());
//            line.addAll(Arrays.asList(offset(l, VectorLib.getLine(origin, vector, density))));
//        }
//        return line.toArray(new Location[line.size()]);
//    }


    @Nullable
    public static Block[] getBorder(Plot plot, int borderHeight) {
        List<Block> blocks = new ArrayList<>();
//        com.plotsquared.core.location.Location[] corners = plot.getAllCorners().toArray(new com.plotsquared.core.location.Location[0]);

        List<com.plotsquared.core.location.Location> corners = plot.getAllCorners();
        for (int i = 0; i<corners.size(); i++) {
            com.plotsquared.core.location.Location corner = corners.get(i);

            Location loc = toLocation(corner);
            loc.setY(borderHeight);

            int next = corners.size() == i + 1 ? 0 : i + 1;
            Bukkit.broadcast(Component.text("i: " + i + " next: " + next));

            Location nextLoc = toLocation(corners.get(next));
            nextLoc.setY(borderHeight);
            Bukkit.broadcast(Component.text("loc: " + loc.toVector() + " nextLoc: " + nextLoc.toVector()));

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

//        if (corners.length >= 4) {
//            Location[] cornersDupe = new Location[corners.length];
//            for (int i = 0; i < corners.length; i++) {
//                cornersDupe[i] = new Location(Bukkit.getWorld(corners[i].getWorld()), corners[i].getX(), borderHeight, corners[i].getZ());
//            }
//            for (Location location : getLines(cornersDupe, 3)) {
//                blocks.add(location.getBlock());
//            }
//            return blocks.toArray(new Block[0]);
//        }
        return blocks.toArray(new Block[0]);
    }

    private static Location toLocation(com.plotsquared.core.location.Location loc) {
        return new Location(Bukkit.getWorld(loc.getWorld()), loc.getX(), loc.getY(), loc.getZ());
    }

}
