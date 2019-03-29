package me.flail.microcommands.mcc.entity.player;

import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.flail.microcommands.entity.player.MicroPlayer;
import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.io.FileManager;
import me.flail.microcommands.mcc.io.PlayerDataHandler;

public abstract class AbstractMicroPlayer implements MicroPlayer {

	MicroCommands plugin = MicroCommands.instance;
	FileManager fm = plugin.fileManager;

	public MicroPlayer(Player player) {

	}

	@Override
	public MicroPlayer fromId(String pUuid) {
		return (MicroPlayer) plugin.playerDatabase.get(UUID.fromString(pUuid));

	}

	@Override
	public FileConfiguration getDataFile() {
		return new PlayerDataHandler().player(this.getUniqueId().toString()).get();
	}

	@Override
	public boolean isIgnoring(MicroPlayer player) {

		return false;
	}

	@Override
	public boolean isIgnoring(MicroPlayer subject, MicroCommands plugin) {

		return false;
	}

}
