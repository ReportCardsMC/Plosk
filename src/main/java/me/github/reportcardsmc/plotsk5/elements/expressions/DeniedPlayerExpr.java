package me.github.reportcardsmc.plotsk5.elements.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.github.reportcardsmc.plotsk5.utils.events.PlayerDeniedFromPlot;
import me.github.reportcardsmc.plotsk5.utils.events.PlayerUndeniedFromPlot;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class DeniedPlayerExpr extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(DeniedPlayerExpr.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] denied(-| )player", "[the] undenied(-| )player");
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        if (!ScriptLoader.isCurrentEvent(PlayerDeniedFromPlot.class, PlayerUndeniedFromPlot.class)) {
            Skript.error("Cannot use 'denied player' outside of a denied/undenied from plot event");
            return false;
        }
        return true;
    }

    @Nullable
    @Override
    protected OfflinePlayer[] get(Event e) {
        return new OfflinePlayer[]{getPlayer(e)};
    }

    @Nullable
    private static OfflinePlayer getPlayer(final @Nullable Event e) {
        if ( e == null ) return null;
        if (e instanceof PlayerDeniedFromPlot) {
            final PlayerDeniedFromPlot main = (PlayerDeniedFromPlot) e;
            return main.getDenied();
        } else if (e instanceof PlayerUndeniedFromPlot) {
            final PlayerUndeniedFromPlot main = (PlayerUndeniedFromPlot) e;
            return main.getUndenied();
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the denied player in a plot denied event";
    }
}
