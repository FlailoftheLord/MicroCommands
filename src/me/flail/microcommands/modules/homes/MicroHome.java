package me.flail.microcommands.modules.homes;

import org.bukkit.World;

import me.flail.microcommands.mcc.entity.player.MicroPlayer;
import me.flail.microcommands.mcc.modules.homes.AbstractHome;
import me.flail.microcommands.mcc.modules.homes.Home;
import me.flail.microcommands.mcc.modules.homes.HomeLocation;

/**
 * A customizable Home object, containing a player-owner & a unique location (server-wide)
 *
 * @author FlailoftheLord
 */
public interface MicroHome {

	/**
	 * @return The name of this home. From {@link HomeLocation}
	 */
	String name();

	/**
	 * @return The home's location, derived from {@link HomeLocation}
	 */
	HomeLocation location();

	/**
	 * @return The owner of this home, defined in {@link AbstractHome} and publicly displayed at
	 *         {@link Home}
	 */
	MicroPlayer owner();

	/**
	 * Teleports the owner to this home if they're online.
	 */
	void teleport();

	/**
	 * Saves the home to the owner's data file.
	 * 
	 * @param overwrite
	 *                      Wether to overwrite the home if it already exists.
	 */
	void save(boolean overwrite);

	/**
	 * Clears this instance and deletes this home from the player's data file, if it exists.
	 */
	void delete();

	/**
	 * Change the name of this home.
	 * 
	 * @param name
	 *                 The new name of this home.
	 */
	void setName(String name);

	/**
	 * Change the world in which this home is located. (x, y, and z coordinates still stay the same.)
	 * 
	 * @param world
	 *                  The new world to which this home will belong.
	 */
	void setWorld(World world);

	/**
	 * Sets the new location for this home.
	 * 
	 * @param location
	 *                     New {@link HomeLocation} for this home's location.
	 */
	void setLocation(HomeLocation location);

}
