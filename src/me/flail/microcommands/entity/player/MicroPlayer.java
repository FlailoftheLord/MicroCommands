package me.flail.microcommands.entity.player;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.flail.microcommands.mcc.MicroCommands;

/**
 * A modified version of {@link org.bukkit.entity.Player}
 *
 * @author FlailoftheLord
 *
 */
public interface MicroPlayer extends Player {

	MicroPlayer fromId(String pUuid);

	FileConfiguration getDataFile();

	/**
	 * Checks if the player is ignoring @param subject
	 *
	 * @param subject
	 * @return true if subject is found in the players' ignored list, false
	 *         otherwise.
	 *
	 */
	boolean isIgnoring(MicroPlayer player);

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
	boolean isIgnoring(MicroPlayer subject, MicroCommands plugin);

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
