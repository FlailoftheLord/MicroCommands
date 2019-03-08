package me.flail.microcommands.io;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public interface PlayerData {

	/**
	 * loads up the method for the specified player.
	 *
	 * @param player
	 * @return itself... idk lol
	 */
	PlayerData player(String playerUuid);

	/**
	 * Gets the player by their name.
	 *
	 * @param playerName - name of the player to get.
	 * @return the player if they're online, null otherwise.
	 */
	Player fromName(String playerName);

	/**
	 * Gets the players' data file.
	 *
	 */
	FileConfiguration get();

	/**
	 * Saves the players' file using the modified input
	 * {@linkplain FileConfiguration} file
	 * 
	 * @param file
	 * @return true if saved, false otherwise.
	 */
	boolean save(FileConfiguration file);

}
