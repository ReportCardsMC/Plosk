package me.github.reportcardsmc.plotsk5.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import me.github.reportcardsmc.plotsk5.utils.PlotSquaredUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class PlotBiomeExpr extends SimpleExpression<String> {
    static {
        Skript.registerExpression(PlotBiomeExpr.class, String.class, ExpressionType.COMBINED, "[PlotSquared] [the] biome of plot [with id] %string%");
    }

    private Expression<String> id;

    @Nullable
    @Override
    protected String[] get(Event e) {
        Plot plot;
        if (id.getSingle(e) == null || (plot = PlotSquaredUtil.getPlot(id.getSingle(e))) == null) return null;
        return new String[]{plot.getBiomeSynchronous().toString()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Biome of plot: " + id.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        id = (Expression<String>) exprs[0];
        return true;
    }
}
