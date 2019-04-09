package me.flail.microcommands.entity.player;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.modules.homes.Home;

/**
 * A modified version of {@link org.bukkit.entity.Player}
 *
 * @author FlailoftheLord
 *
 */
public interface IMicroPlayer {

	Player player();

	FileConfiguration getDataFile();

	/**
	 * Checks if the player is ignoring @param subject
	 *
	 * @param subject
	 * @return true if subject is found in the players' ignored list, false
	 *         otherwise.
	 *
	 */
	boolean isIgnoring(IMicroPlayer player);

	/**
	 * Checks if the player is ignoring @param subject
	 *
	 * requires the @param plugin to work, as it gets the players' data file.
	 *
	 * @param subject
	 * @param plugin
	 * @return true if subject is found in the players' ignored list, false
	 *         otherwise.
	 *
	 */
	boolean isIgnoring(IMicroPlayer subject, MicroCommands plugin);

	/**
	 * Gets wether this player is allowed to fly or not.
	 *
	 * @return true if the player has permission 'microcommands.fly' false
	 *         otherwise.
	 */
	boolean canFly();

	/**
	 * Opens an anvil inventory for this player
	 */
	void openAnvil();

	/**
	 * Gets a list of the player's homes from their data folder.
	 * 
	 * @return a full list of their homes, will return an empty list if they don't have any homes.
	 */
	List<Home> getHomes();

	public enum property {
		/**
		 * defines a player who is on fire.
		 */
		IS_ON_FIRE,
		/**
		 * defines a vanished player.
		 */
		IS_VANISHED,
		/**
		 * defines a frozen player.
		 */
		IS_FROZEN;
	}

}
