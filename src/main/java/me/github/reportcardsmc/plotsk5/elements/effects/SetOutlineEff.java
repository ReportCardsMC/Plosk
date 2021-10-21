package me.github.reportcardsmc.plotsk5.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import me.github.reportcardsmc.plotsk5.utils.PlotSquaredUtil;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class SetOutlineEff extends Effect {
    static {
        Skript.registerEffect(SetOutlineEff.class, "[plotsquared] (set|change) (outline|border) blocks of plot [with id] %string% to %material%");
    }

    private Expression<String> plot;
    private Expression<Material> material;
    @Override
    protected void execute(Event e) {
        Plot p;
        if (plot.getSingle(e) == null || material.getSingle(e) == null || (p = PlotSquaredUtil.getPlot(plot.getSingle(e))) == null) return;
        PlotSquaredUtil.setOutline(p, material.getSingle(e));
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Setting outline of plot " + plot.toString(e, debug) + " to " + material.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        material = (Expression<Material>) exprs[1];
        plot = (Expression<String>) exprs[0];
        return true;
    }
}
