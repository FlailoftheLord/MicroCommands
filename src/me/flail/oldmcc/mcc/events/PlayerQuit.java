package me.flail.oldmcc.mcc.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.flail.oldmcc.mcc.MicroCommands;
import me.flail.oldmcc.mcc.entity.player.MicroPlayer;
import me.flail.oldmcc.mcc.tools.Tools;

@SuppressWarnings("unused")
public class PlayerQuit implements Listener {

	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);

	private Tools tools = new Tools();

	@EventHandler
	public void playerLeave(PlayerQuitEvent event) {
		plugin.playerDatabase.remove(new MicroPlayer(event.getPlayer().getUniqueId()));
	}

	@EventHandler
	public void playerKick(PlayerKickEvent event) {
		plugin.playerDatabase.remove(new MicroPlayer(event.getPlayer().getUniqueId()));

	}

}
