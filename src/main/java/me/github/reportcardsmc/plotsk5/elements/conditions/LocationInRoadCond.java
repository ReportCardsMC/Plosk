package me.github.reportcardsmc.plotsk5.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class LocationInRoadCond extends Condition {
    static {
        Skript.registerCondition(LocationInRoadCond.class, "[PlotSquared] %location% (1¦is|2¦is(n't| not)) in[side] [the] [plot] road");
    }

    Expression<Location> location;

    @Override
    public boolean check(Event e) {
        Location loc = location.getSingle(e);
        if (loc == null) return isNegated();
        boolean isRoad = new com.plotsquared.core.location.Location(loc.getWorld().toString(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()).isPlotRoad();
        if (isNegated()) return !isRoad;
        return isRoad;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Location in plot world road " + location.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        location = (Expression<Location>) exprs[0];
        setNegated(parseResult.mark == 1);
        return true;
    }
}
