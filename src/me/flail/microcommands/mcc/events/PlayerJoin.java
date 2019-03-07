package me.flail.microcommands.mcc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.flail.microcommands.MicroCommands;

public class PlayerJoin implements Listener {

	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);

	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		plugin.tools.loadPlayer(player);

	}

}
