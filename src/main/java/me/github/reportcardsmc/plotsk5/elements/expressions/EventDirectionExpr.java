package me.github.reportcardsmc.plotsk5.elements.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.github.reportcardsmc.plotsk5.utils.events.PlayerMergePlot;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EventDirectionExpr extends SimpleExpression<String> {

    static {
        Skript.registerExpression(EventDirectionExpr.class, String.class, ExpressionType.SIMPLE, "[plotsquared] [event(-| )]merge(-| )direction");
    }

    @Nullable
    @Override
    protected String[] get(Event e) {
        if (e instanceof PlayerMergePlot) {
            PlayerMergePlot main = (PlayerMergePlot) e;
            return new String[]{main.getDirection()};
        }
        return null;
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
        return "Direction in plot merge event";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (!ScriptLoader.isCurrentEvent(PlayerMergePlot.class)) {
            Skript.error("Cannot use 'merge direction' outside of a plot merge event");
            return false;
        }
        return true;
    }
}
