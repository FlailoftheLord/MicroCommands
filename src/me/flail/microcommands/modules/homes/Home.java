package me.flail.microcommands.modules.homes;

import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Extends a simple map consisting of the players' UUID converted to a string,
 * and the specified home/section of homes in the players' data file.
 *
 * @author FlailoftheLord
 *
 */
public interface Home extends Map<String, String> {

	/**
	 * Converts the home to a new {@link Home} object
	 *
	 * @param playerUuid
	 * @param name
	 * @return the new Home object, providing the player & home name both exist and
	 *         are valid/not null.
	 */
	Home getHome(String playerUuid, String name);

	/**
	 * @return the name of the home.
	 */
	String name();

	/**
	 * @return the UUID of the owner of the home.
	 */
	String owner();

	/**
	 * Gets all of the specified players' homes from their data file.
	 *
	 * @param player
	 * @return A list of the players' homes. Will also return if the player doesn't
	 *         have any homes and also empty if the player does not exist or is
	 *         null.
	 */
	List<String> getHomes(String playerUuid);

	/**
	 * Checks if the player has the specified home.
	 *
	 * @param playerUuid - uuid of the player.
	 * @param homeName   - name of the home to check for.
	 * @return true if player has the home set in their data file, false otherwise.
	 */
	boolean hasHome(String playerUuid, String homeName);

	/**
	 * Sets the home from the premade Home object.s
	 *
	 * @param location
	 * @param overwrite
	 */
	boolean set(Location location, boolean overwrite);

	/**
	 * Sets the players' home to the specified location.
	 *
	 * @param playerUuid - the players' {@link Player#getUniqueId()} converted to a
	 *                   string.
	 * @param home       - the name of the home to set
	 * @param override   - wether to overwrite the home if it already exists with
	 *                   the same name.
	 * @return true if home was set/overwritten without errors. false otherwise.
	 */
	boolean setHome(String playerUuid, Location location, String name, boolean overwrite);

	/**
	 * Deletes the players' home specified by the {@linkplain @param home}.
	 *
	 * @param playerUuid - the players' {@link Player#getUniqueId()} converted to a
	 *                   string.
	 * @param home       - the name of the home.
	 * @return true if the home was successfully removed, false otherwise.
	 */
	boolean deleteHome(String playerUuid, String home);

	/**
	 * Teleports the specified player to the home specified by the home name.
	 *
	 * @param playerUuid - the players' {@link Player#getUniqueId()} converted to a
	 *                   string.
	 * @param home       - the name of the home.
	 * @return true if player is online and doesn't have the bypass or exempt
	 *         permissions. false otherwise.
	 */
	boolean sendToHome(String playerUuid, String home);

}
