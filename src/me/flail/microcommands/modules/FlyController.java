package me.flail.microcommands.modules;

import org.bukkit.entity.Player;

public interface FlyController {

	/**
	 * Toggles fly mode for specified player.
	 * 
	 * @param player
	 * @return true if their fly was enabled, false otherwise.
	 */
	boolean toggleFly(Player player);

	/**
	 * Toggles fly mode for specified player.
	 * 
	 * @param player
	 *                       the player to change fly status for.
	 * @param persistent
	 *                       wether fly status should be saved if player goes offline.
	 * @return true if fly was enabled, false otherwise.
	 */
	boolean toggleFly(Player player, boolean persistent);

	/**
	 * Toggles fly mode for specified player.
	 * 
	 * @param player
	 *                     the player specified.
	 * @param duration
	 *                     the time that fly should be toggled for, after which, it will be returned to
	 *                     it's original state for this player.
	 * @return true if their fly was enabled, false otherwise.
	 */
	boolean toggleFly(Player player, int duration);

	/**
	 * Toggles fly mode for specified player.
	 * 
	 * @param player
	 *                       the player specified.
	 * @param duration
	 *                       the time that fly should be toggled for, after which, it will be returned
	 *                       to it's original state for this player.
	 * @param persistent
	 *                       wether fly status should be saved if player goes offline.
	 * @return true if their fly was enabled, false otherwise.
	 */
	boolean toggleFly(Player player, int duration, boolean persistent);

}
