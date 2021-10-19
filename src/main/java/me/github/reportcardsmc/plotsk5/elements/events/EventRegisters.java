package me.github.reportcardsmc.plotsk5.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.plotsquared.core.api.PlotAPI;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotId;
import me.github.reportcardsmc.plotsk5.utils.events.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class EventRegisters {
    public static PlotAPI plotAPI = new PlotAPI();

    public static Plot getPlot(@Nullable String id) {
        if (id == null) return null;
        PlotId plotId = PlotId.fromString(id);
        for (Plot plot : plotAPI.getAllPlots()) {
            if (plot.getId().equals(plotId)) return plot;
        }
        return null;
    }

    static {
        plotAPI.registerListener(new PlotSquaredListener());
        /*
        Plot Enter Event
        Has string value for plot id
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Enter", PlotSkEvent.class, PlayerEnterPlot.class, "[PlotSquared] plot enter[ing]", "[PlotSquared] enter[ing] plot")
                .description("Called when a player goes into a plot")
                .examples("on plot enter:", "\tbroadcast \"%player% has went into plot %event-string%\"");
        EventValues.registerEventValue(PlayerEnterPlot.class, String.class, new Getter<String, PlayerEnterPlot>() {
            public String get(PlayerEnterPlot e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerEnterPlot.class, Player.class, new Getter<Player, PlayerEnterPlot>() {
            public Player get(PlayerEnterPlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Leave Event
        Has string value for plot id
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Exit", PlotSkEvent.class, PlayerLeavePlot.class, "[PlotSquared] plot exit[ing]", "[PlotSquared] exit[ing] plot")
                .description("Called when a player leaves a plot")
                .examples("on plot exit:", "\tbroadcast \"%player% has left plot %event-string%\"");
        EventValues.registerEventValue(PlayerLeavePlot.class, String.class, new Getter<String, PlayerLeavePlot>() {
            public String get(PlayerLeavePlot e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerLeavePlot.class, Player.class, new Getter<Player, PlayerLeavePlot>() {
            public Player get(PlayerLeavePlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Claim Event
        Has string value for plot id
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Claim", PlotSkEvent.class, PlayerClaimPlot.class, "[PlotSquared] [player] plot claim[ed]", "[PlotSquared] [player] claim[ed] plot")
                .description("Called when a player claims a plot")
                .examples("on plot claim:", "\tbroadcast \"%player% has claimed plot %event-string%\"");
        EventValues.registerEventValue(PlayerClaimPlot.class, String.class, new Getter<String, PlayerClaimPlot>() {
            public String get(PlayerClaimPlot e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerClaimPlot.class, Player.class, new Getter<Player, PlayerClaimPlot>() {
            public Player get(PlayerClaimPlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Merge Event
        Has string value for plot id
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Merge", PlotSkEvent.class, PlayerMergePlot.class, "[PlotSquared] [player] plot merge[d]", "[PlotSquared] [player] merge[d] plot")
                .description("Called when a player merges a plot")
                .examples("on plot merge:", "\tbroadcast \"%player% has merged plot %event-string% with another plot\"");
        EventValues.registerEventValue(PlayerMergePlot.class, String.class, new Getter<String, PlayerMergePlot>() {
            public String get(PlayerMergePlot e) {
                return e.getPlotId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerMergePlot.class, Player.class, new Getter<Player, PlayerMergePlot>() {
            public Player get(PlayerMergePlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Delete Event
        Has string value for plot id
         */
        Skript.registerEvent("PlotSquared: Plot Delete", PlotSkEvent.class, PlayerDeletePlot.class, "[PlotSquared] [player] plot delete[d]", "[PlotSquared] [player] delete[d] plot")
                .description("Called when a plot is deleted")
                .examples("on plot delete:", "\tbroadcast \"%event-string% was deleted\"");
        EventValues.registerEventValue(PlayerDeletePlot.class, String.class, new Getter<String, PlayerDeletePlot>() {
            public String get(PlayerDeletePlot e) {
                return e.getPlotId().toString();
            }
        }, 0);
        /*
        Plot Clear Event
        Has string value for plot id
         */
        Skript.registerEvent("PlotSquared: Plot Clear", PlotSkEvent.class, PlayerClearPlot.class, "[PlotSquared] [player] plot clear[ed]", "[PlotSquared] [player] clear[ed] plot")
                .description("Called when a plot is cleared")
                .examples("on plot delete:", "\tbroadcast \"%event-string% was cleared\"");
        EventValues.registerEventValue(PlayerClearPlot.class, String.class, new Getter<String, PlayerClearPlot>() {
            public String get(PlayerClearPlot e) {
                return e.getPlotId().toString();
            }
        }, 0);
        /*
        Plot Rating Event
        Has string value for plot id
        Has integer value for plot rating value
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Rating", PlotSkEvent.class, PlayerRatePlot.class, "[PlotSquared] plot rat(e|ing)", "[PlotSquared] rat(e|ing) plot")
                .description("Called when a player rates a plot")
                .examples("on plot rate:", "\tbroadcast \"%player% has voted %event-integer% on %event-string%\"");
        EventValues.registerEventValue(PlayerRatePlot.class, String.class, new Getter<String, PlayerRatePlot>() {
            public String get(PlayerRatePlot e) {
                return e.getPlotId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerRatePlot.class, Player.class, new Getter<Player, PlayerRatePlot>() {
            public Player get(PlayerRatePlot e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(PlayerRatePlot.class, Number.class, new Getter<Number, PlayerRatePlot>() {
            public Number get(PlayerRatePlot e) {
                return e.getRating();
            }
        }, 0);
        /*
        Plot Denied Event
        Has string value for plot id
        Has player value for player who caused event
        Has "denied player" for player who got denied
         */
        Skript.registerEvent("PlotSquared: Plot Denied", PlotSkEvent.class, PlayerDeniedFromPlot.class, "[PlotSquared] player deny player [from plot]", "[PlotSquared] player den(y|ied) from plot")
                .description("Called when a player denies a player from a plot")
                .examples("on player deny player from plot:", "\tbroadcast \"%player% denied %denied player% from %event-string%\"");
        EventValues.registerEventValue(PlayerDeniedFromPlot.class, String.class, new Getter<String, PlayerDeniedFromPlot>() {
            public String get(PlayerDeniedFromPlot e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerDeniedFromPlot.class, Player.class, new Getter<Player, PlayerDeniedFromPlot>() {
            public Player get(PlayerDeniedFromPlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Undenied Event
        Has string value for plot id
        Has player value for player who caused event
        Has "undenied player" for player who got denied
         */
        Skript.registerEvent("PlotSquared: Plot Undenied", PlotSkEvent.class, PlayerUndeniedFromPlot.class, "[PlotSquared] player undeny player [from plot]", "[PlotSquared] player unden(y|ied) from plot")
                .description("Called when a player undenies a player from a plot")
                .examples("on player undeny player from plot:", "\tbroadcast \"%player% undenied %undenied player% from %event-string%\"");
        EventValues.registerEventValue(PlayerUndeniedFromPlot.class, String.class, new Getter<String, PlayerUndeniedFromPlot>() {
            public String get(PlayerUndeniedFromPlot e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerUndeniedFromPlot.class, Player.class, new Getter<Player, PlayerUndeniedFromPlot>() {
            public Player get(PlayerUndeniedFromPlot e) {
                return e.getPlayer();
            }
        }, 0);
    }
}
