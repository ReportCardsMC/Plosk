package me.github.reportcardsmc.plosk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import me.github.reportcardsmc.plosk.utils.LocationUtil;
import me.github.reportcardsmc.plosk.utils.PlotSquaredUtil;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Plot Border Blocks")
@Description("List of blocks that border the plot (inner border)")
@Examples({"command /setborder:", "    trigger:", "        set border blocks of plot plot at player at height 70 to bedrock"})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class PlotBorderExpr extends SimpleExpression<Block> {

    static {
        Skript.registerExpression(PlotBorderExpr.class, Block.class, ExpressionType.COMBINED, "[PlotSquared] [all] [inner] border blocks of plot [with id] %string% (at|with) height %number%");
    }

    private Expression<Number> height;
    private Expression<String> plot;

    @Nullable
    @Override
    protected Block[] get(Event e) {
        Plot p;
        Number h = height.getSingle(e);
        if (plot.getSingle(e) == null || h == null || (p = PlotSquaredUtil.getPlot(plot.getSingle(e))) == null)
            return null;
        Block[] blocks = LocationUtil.getBorder(p, h.intValue());
        //        for (Block block : blocks) {
//            Bukkit.getLogger().warning(block.getLocation().toString());
//        }
        return blocks;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Block> getReturnType() {
        return Block.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Get border of plot: " + plot.toString(e, debug) + " at height" + height.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        height = (Expression<Number>) exprs[1];
        plot = (Expression<String>) exprs[0];
        return true;
    }
}
