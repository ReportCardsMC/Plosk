package me.github.reportcardsmc.plotsk5.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.plotsquared.bukkit.util.BukkitUtil;
import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import me.github.reportcardsmc.plotsk5.utils.PlotSquaredUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class OwnedPlotsExpr extends SimpleExpression<String> {

    static {
        Skript.registerExpression(OwnedPlotsExpr.class, String.class, ExpressionType.COMBINED, "[plotsquared] owned plots of %offlineplayer%", "[plotsquared] %offlineplayer%['][s] owned plots");
    }

    private Expression<OfflinePlayer> playerExpression;
    @Nullable
    @Override
    protected String[] get(Event e) {
        if (playerExpression.getSingle(e) == null) return null;
        return PlotSquaredUtil.plotAPI.getPlayerPlots(BukkitUtil.getPlayer(playerExpression.getSingle(e))).stream().map(plot -> plot.getId().toString()).toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Checking owned plots of " + playerExpression.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        playerExpression = (Expression<OfflinePlayer>) exprs[0];
        return true;
    }
}
