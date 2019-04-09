package me.flail.microcommands.mcc.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.entity.player.MicroPlayer;
import me.flail.microcommands.mcc.io.FileManager;
import me.flail.microcommands.mcc.tools.Tools;

@SuppressWarnings("unused")
public class PlayerQuit implements Listener {

	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);
	private FileManager fm = plugin.fileManager;

	private Tools tools = new Tools();

	@EventHandler
	public void playerLeave(PlayerQuitEvent event) {
		plugin.playerDatabase.remove(new MicroPlayer(event.getPlayer()));
	}

	@EventHandler
	public void playerKick(PlayerKickEvent event) {
		plugin.playerDatabase.remove(new MicroPlayer(event.getPlayer()));

	}

}
