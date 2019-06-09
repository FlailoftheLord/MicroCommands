package me.flail.microcommands.mcc.events;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.entity.player.MicroPlayer;
import me.flail.tools.DataFile;

public class BlockLogging implements Listener {
	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);
	protected enum LoadType {
		LOAD, SAVE;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void blockPlace(BlockPlaceEvent event) {
		event.setCancelled(this.logBlock(new MicroPlayer(event.getPlayer().getUniqueId()), event.getBlock()));
	}

	public void loader(LoadType dataType) {
		this.new Manager(dataType, plugin.blockData).runTaskAsynchronously(plugin);
	}

	private boolean logBlock(MicroPlayer player, Block block) {
		try {
			Set<Location> blocks = new HashSet<>();
			blocks.add(block.getLocation());

			if (plugin.blockData.containsKey(player)) {
				blocks.addAll(plugin.blockData.get(player));
			}

			plugin.blockData.put(player, blocks);

			return false;
		} catch (Throwable t) {
			return true;
		}
	}

	protected DataFile setupDataFile(MicroPlayer player) {
		return new DataFile("/PlayerData/" + player.uuid().toString() + "/BlockData.mcc");
	}

	@SuppressWarnings("unchecked")
	protected class Manager extends BukkitRunnable {
		Map<MicroPlayer, Set<Location>> data;
		LoadType type;

		protected Manager(LoadType type, Map<MicroPlayer, Set<Location>> data) {
			this.data = data;
			this.type = type;
		}

		private Map<FileConfiguration, Set<Location>> blockSet(UUID uuid) {
			File file = new File(plugin.getDataFolder() + "/PlayerData/" + uuid.toString() + "/BlockData.mcc");
			FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
			Object unchecked = conf.get("Blocks", new HashSet<>());
			if (unchecked instanceof Set<?>) {
				Map<FileConfiguration, Set<Location>> map = new HashMap<>();
				conf.set("Path", file.getAbsolutePath().toString());
				map.put(conf, (Set<Location>) unchecked);

				return map;
			}

			return new HashMap<>();
		}

		@Override
		public void run() {

			switch (type) {
			case LOAD:
				try {
					Set<Location> blocks = new HashSet<>();
					Map<UUID, File> playerDB = MicroPlayer.playerDB();
					for (UUID playerID : playerDB.keySet()) {
						blocks = this.blockSet(playerID).values().toArray(new Set[] {})[0];

						data.putIfAbsent(new MicroPlayer(playerID), blocks);
					}

				} catch (Throwable t) {
					t.printStackTrace();
				}

				return;
			case SAVE:
				try {
					for (MicroPlayer player : data.keySet()) {
						FileConfiguration conf = this.blockSet(player.uuid()).keySet().toArray(new FileConfiguration[] {})[0];
						Set<Location> blocks = this.blockSet(player.uuid()).get(conf);

						while (data.get(player).iterator().hasNext()) {
							blocks.add(data.get(player).iterator().next());
						}

						conf.set("Blocks", blocks);
						conf.save(new File(conf.get("Path").toString()));
					}

				} catch (Throwable t) {
					t.printStackTrace();
				}

				return;
			}

		}

	}

}
