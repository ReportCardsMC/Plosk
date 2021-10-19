package me.github.reportcardsmc.plotsk5.elements.conditions;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import me.github.reportcardsmc.plotsk5.utils.PlotSquaredUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class PlayerIsTrustedCond extends Condition {

    // "[plotsquared] %offline player% is trusted in plot [with id] %string%"

    Expression<String> plotID;
    Expression<OfflinePlayer> player;

    @Override
    public boolean check(Event e) {
        Plot plot;
        OfflinePlayer p = player.getSingle(e);
        if (player == null || plotID == null || p == null || (plot = PlotSquaredUtil.getPlot(plotID.getSingle(e))) == null) return false;
        return plot.isOwner(p.getUniqueId()) || plot.getTrusted().stream().anyMatch((v) -> v == p.getUniqueId());
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        player = (Expression<OfflinePlayer>) exprs[0];
        plotID = (Expression<String>) exprs[1];
        return true;
    }
}
