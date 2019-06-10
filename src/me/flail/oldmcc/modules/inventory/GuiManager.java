package me.flail.oldmcc.modules.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GuiManager implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void playerQuit(PlayerQuitEvent event) {
		MicroGui.guiDb.remove(MicroGui.activeGuiList.get(event.getPlayer().getUniqueId()));
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void inventoryCLose(InventoryCloseEvent event) {
		MicroGui.guiDb.remove(MicroGui.activeGuiList.get(event.getPlayer().getUniqueId()));
	}

}
