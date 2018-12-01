package me.flail.MicroCommands.Events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.flail.MicroCommands.MicroCommands;
import me.flail.MicroCommands.Tools;

public class PlayerJoin implements Listener {

	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);

	private Tools tools = plugin.tools;

	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		FileConfiguration config = plugin.getConfig();

		plugin.loadPlayer(player);

		FileConfiguration playerData = plugin.getPlayer(player);

		String pName = player.getName();

		String playerIp = player.getAddress().toString().replace("\\", "");

		playerData.set("Name", pName);
		playerData.set("IpAddress", playerIp);

		if (player.hasPermission("microcommands.fly.self") || player.hasPermission("microcommands.fly")) {

			player.setAllowFlight(true);

		}

	}

}
