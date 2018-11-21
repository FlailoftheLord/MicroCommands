package me.flail.MicroCommands.Events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.flail.MicroCommands.MicroCommands;
import me.flail.MicroCommands.Tools;

public class PlayerLeave implements Listener {

	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);

	private Tools tools = plugin.tools;

	@EventHandler
	public void playerLeave(PlayerQuitEvent event) {

		FileConfiguration config = plugin.getConfig();

		Player player = event.getPlayer();

		plugin.savePlayer(player);

	}

}
