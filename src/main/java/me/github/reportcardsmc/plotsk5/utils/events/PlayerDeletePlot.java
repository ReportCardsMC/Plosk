package me.github.reportcardsmc.plotsk5.utils.events;

import com.plotsquared.bukkit.player.BukkitPlayer;
import com.plotsquared.core.events.PlayerClaimPlotEvent;
import com.plotsquared.core.events.PlotDeleteEvent;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotId;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class PlayerDeletePlot extends BukkitPlotEvent implements Cancellable {
    private PlotDeleteEvent event;

    public PlayerDeletePlot(PlotDeleteEvent event) {
        this.event = event;
    }

    @Override
    public boolean isCancelled() {
        return event.getEventResult() == com.plotsquared.core.events.Result.DENY;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        event.setEventResult(isCancelled ? com.plotsquared.core.events.Result.DENY : com.plotsquared.core.events.Result.ACCEPT);
    }

    public PlotId getPlotId() {
        return event.getPlotId();
    }
}
