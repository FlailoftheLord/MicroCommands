package me.flail.microcommands.mcc.entity.player;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.flail.microcommands.entity.player.IMicroPlayer;
import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.io.FileManager;

public abstract class AbstractMicroPlayer implements IMicroPlayer {
	protected static OfflinePlayer player;

	MicroCommands plugin = MicroCommands.instance;
	FileManager fm = plugin.fileManager;

	public AbstractMicroPlayer(OfflinePlayer player) {
		AbstractMicroPlayer.player = player;

	}

	public static OfflinePlayer fromId(UUID id) {
		return Bukkit.getOfflinePlayer(id);
	}

	@Override
	public boolean isIgnoring(IMicroPlayer player) {

		return false;
	}

	@Override
	public boolean isIgnoring(IMicroPlayer subject, MicroCommands plugin) {

		return false;
	}


	@Override
	public boolean canFly() {
		// TODO complete these methods soon...
		return false;
	}

	@Override
	public void openAnvil() {
		// TODO complete these methods soon...

	}

}
