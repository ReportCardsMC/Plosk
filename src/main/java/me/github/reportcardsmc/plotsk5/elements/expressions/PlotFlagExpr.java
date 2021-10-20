package me.github.reportcardsmc.plotsk5.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.flag.GlobalFlagContainer;
import me.github.reportcardsmc.plotsk5.utils.PlotSquaredUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class PlotFlagExpr extends SimpleExpression<Object> {
    static {
        Skript.registerExpression(PlotFlagExpr.class, Object.class, ExpressionType.COMBINED, "[PlotSquared] [value of] [the] flag %string% (in|for) plot [with id] %string%");
    }

    private Expression<String> id;
    private Expression<String> flag;

    @Nullable
    @Override
    protected Object[] get(Event e) {
        Plot plot;
        if (id.getSingle(e) == null || flag.getSingle(e) == null || (plot = PlotSquaredUtil.getPlot(id.getSingle(e))) == null) return null;
        return new Object[]{plot.getFlag(GlobalFlagContainer.getInstance().getFlagFromString(flag.getSingle(e)))};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Object> getReturnType() {
        return Object.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Flag " + flag.toString(e, debug) + " of plot: " + id.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        id = (Expression<String>) exprs[1];
        flag = (Expression<String>) exprs[0];
        return true;
    }
}
