package me.flail.oldmcc.modules.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.flail.oldmcc.inventory.Gui;
import me.flail.oldmcc.mcc.MicroCommands;
import me.flail.oldmcc.mcc.entity.player.MicroPlayer;

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
	public void open(MicroPlayer player) {
		activeGuiList.put(player.uuid(), id);
		player.player().openInventory(gui);
	}

	@Deprecated
	@Override
	public void close(MicroPlayer player) {
		player.player().closeInventory();
		activeGuiList.remove(player.uuid());
	}

	@Override
	public void delete() {
		for (MicroPlayer player : plugin.playerDatabase) {
			if (activeGuiList.containsKey(player.uuid())) {
				if (activeGuiList.get(player.uuid()).equals(id())) {
					player.player().closeInventory();
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
