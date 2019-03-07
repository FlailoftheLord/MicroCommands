package me.flail.microcommands.mcc.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.flail.microcommands.MicroCommands;
import me.flail.microcommands.mcc.io.FileManager;
import me.flail.microcommands.mcc.tools.Tools;

@SuppressWarnings("unused")
public class PlayerQuit implements Listener {

	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);
	private FileManager fm = plugin.fileManager;

	private Tools tools = plugin.tools;

	@EventHandler
	public void playerLeave(PlayerQuitEvent event) {

	}

}
