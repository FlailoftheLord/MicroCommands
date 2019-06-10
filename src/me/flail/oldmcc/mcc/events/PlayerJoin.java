package me.flail.oldmcc.mcc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import me.flail.oldmcc.mcc.MicroCommands;
import me.flail.oldmcc.mcc.entity.player.MicroPlayer;
import me.flail.oldmcc.mcc.tools.Tools;

public class PlayerJoin implements Listener {

	MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);
	private Tools tools = new Tools();

	@EventHandler
	public void playerLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();

		tools.loadPlayer(new MicroPlayer(player.getUniqueId()));
	}

	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if (player.hasPermission("microcommands.flyjoin")) {
			MicroPlayer mPlayer = new MicroPlayer(player.getUniqueId());
			mPlayer.player().setFlying(!mPlayer.player().getAllowFlight());
		}
	}

}
