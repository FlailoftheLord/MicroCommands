package me.flail.microcommands.mcc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.entity.player.MicroPlayer;
import me.flail.microcommands.mcc.tools.Tools;

public class PlayerJoin implements Listener {

	MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);
	private Tools tools = new Tools();

	@EventHandler
	public void playerLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();

		tools.loadPlayer(new MicroPlayer(player));
	}

	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if (player.hasPermission("microcommands.flyjoin")) {
			MicroPlayer mPlayer = new MicroPlayer(player);
			mPlayer.player().setFlying(!mPlayer.player().getAllowFlight());
		}
	}

}
