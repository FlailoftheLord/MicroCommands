package me.flail.microcommands.modules.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.flail.microcommands.inventory.Gui;
import me.flail.microcommands.mcc.MicroCommands;

public abstract class MicroGui implements Gui {
	MicroCommands plugin = MicroCommands.instance;
	public static Map<UUID, Gui> guiDb = new HashMap<>();
	public static Map<UUID, UUID> activeGuiList = new HashMap<>();

	protected Inventory gui;
	protected UUID id;

	public MicroGui(String name, int size) {
		id = UUID.randomUUID();
		gui = Bukkit.createInventory(null, size, name);
	}

	public MicroGui(String name, InventoryType type) {
		id = UUID.randomUUID();
		gui = Bukkit.createInventory(null, type, name);

	}

	@Override
	public UUID id() {
		return id;
	}

	@Override
	public Gui get(UUID playerUuid) {
		return guiDb.get(activeGuiList.get(playerUuid));
	}

	@Override
	public void open(Player player) {
		activeGuiList.put(player.getUniqueId(), id);
		player.openInventory(gui);
	}

	@Deprecated
	@Override
	public void close(Player player) {
		player.closeInventory();
		activeGuiList.remove(player.getUniqueId());
	}

	@Override
	public void delete() {
		for (Player player : plugin.playerDatabase.values()) {
			if (activeGuiList.containsKey(player.getUniqueId())) {
				if (activeGuiList.get(player.getUniqueId()).equals(id())) {
					player.closeInventory();
				}
			}
		}
		guiDb.remove(id());
	}

	@Override
	public void setItem(int slot, ItemStack item) {
		gui.setItem(slot, item);
	}

}
