package me.flail.oldmcc.inventory;

import java.util.UUID;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.flail.oldmcc.mcc.entity.player.MicroPlayer;

public interface Gui {

	/**
	 * Gets the specified gui if it's open for the player.
	 * 
	 * @param playerUuid
	 *                       The uuid of the player to get their active inventory for.
	 * @return the {@link Gui} if found, otherwise null.
	 */
	Gui get(UUID playerUuid);

	/**
	 * The Unique Id of this GUi
	 * 
	 * @return the {@link UUID} of this {@link Inventory} type Gui.
	 */
	UUID id();

	/**
	 * Opens this GUI for the specified player.
	 * 
	 * @param player
	 *                   subject to open this GUI for.
	 */
	void open(MicroPlayer player);

	@Deprecated
	/**
	 * Use {@code Gui#delete();} instead.
	 * Closes active gui for player.
	 * 
	 * @param player
	 *                   You can set this to null if you wish, in which case, it will just close the
	 *                   active gui.
	 */
	void close(MicroPlayer player);

	/**
	 * Does the same thing as the method {@code Gui#close(Player player);}
	 * This is the preferred method to close inventories.
	 */
	void delete();

	/**
	 * Sets this itemstack in the specified slot.
	 * If the slot is invalid (the inventory isn't big enough, it will be set in the closest avaliable
	 * slot to that number. (same applies for negative numbers)
	 * 
	 * @param slot
	 *                 The slot in the gui to set this item.
	 * @param item
	 *                 Item to set.
	 */
	void setItem(int slot, ItemStack item);

}
