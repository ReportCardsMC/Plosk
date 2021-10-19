package me.github.reportcardsmc.plotsk5.utils.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BukkitPlotEvent extends Event {
    private static HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
