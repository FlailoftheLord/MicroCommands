package me.flail.microcommands.mcc.entity.player;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import me.flail.microcommands.entity.player.IMicroPlayer;
import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.io.FileManager;
import me.flail.microcommands.mcc.io.PlayerDataHandler;

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
	public IMicroPlayer fromId(String pUuid) {
		return (IMicroPlayer) plugin.playerDatabase.get(UUID.fromString(pUuid));

	}

	@Override
	public FileConfiguration getDataFile() {
		return new PlayerDataHandler().player(player.getUniqueId().toString()).get();
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
