package me.flail.microcommands.mcc.io;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.flail.microcommands.io.Logger;
import me.flail.microcommands.io.Logger.LogType;
import me.flail.microcommands.io.PlayerData;
import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.entity.player.MicroPlayer;

public class PlayerDataHandler implements PlayerData {

	protected MicroCommands plugin = MicroCommands.instance;
	String player = null;

	private String path = plugin.getDataFolder() + "/PlayerData/";

	Logger logger = new ILogger();

	@Override
	public PlayerData player(MicroPlayer player) {
		this.player = player.uuid().toString();
		return this;
	}

	@Override
	public FileConfiguration get() {

		if (player != null) {

			File playerFile = new File(path + player + "/Data.yml");

			boolean isLoaded = plugin.playerFile.containsKey(player);

			if (!isLoaded) {

				FileConfiguration playerConfig = new YamlConfiguration();
				try {
					playerConfig.load(playerFile);
					plugin.playerFile.put(player, playerConfig);

					return plugin.playerFile.get(player);
				} catch (Throwable t) {
					logger.log("Error getting player file for player: " + player, LogType.CONSOLE);
					return playerConfig;
				}

			} else {

				FileConfiguration playerConfig = plugin.playerFile.get(player);

				return playerConfig;

			}

		} else {
			return new YamlConfiguration();
		}

	}

	@Override
	public boolean save(FileConfiguration config) {

		try {

			File playerFile = new File(path + player + "/Data.yml");

			config.save(playerFile);

			plugin.playerFile.put(player, this.get());

			return true;
		} catch (Throwable t) {
			return false;
		}

	}

	@Override
	public Player fromName(String playerName) {

		for (MicroPlayer player : plugin.playerDatabase) {
			String pName = player.player().getName().toLowerCase();
			if (pName.startsWith(playerName.toLowerCase()) || pName.equalsIgnoreCase(playerName)) {
				return player.player();
			}
		}

		return null;
	}



}