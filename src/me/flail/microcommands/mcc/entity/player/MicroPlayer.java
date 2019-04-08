package me.flail.microcommands.mcc.entity.player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.io.PlayerDataHandler;
import me.flail.microcommands.mcc.modules.homes.Home;
import me.flail.microcommands.mcc.modules.homes.HomeLocation;

public class MicroPlayer extends AbstractMicroPlayer {
	private FileConfiguration data;

	public MicroPlayer(OfflinePlayer player) {
		super(player);
		data = new PlayerDataHandler().player(this).get();
	}

	public MicroPlayer(UUID uuid) {
		super(AbstractMicroPlayer.fromId(uuid));
		data = new PlayerDataHandler().player(this).get();
	}

	public static MicroPlayer fromUuid(UUID uuid) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);

		return player.isOnline() ? new MicroPlayer(player.getPlayer()) : new MicroPlayer(uuid);
	}

	public UUID uuid() {
		return player.getUniqueId();
	}

	@Override
	public FileConfiguration getDataFile() {
		return data;
	}

	public void saveDataFile(FileConfiguration config) {
		fm.saveFile(config);
	}

	public Home getHome(String name) {
		ConfigurationSection homeSection = getDataFile().getConfigurationSection("Homes").getConfigurationSection(name);

		HomeLocation location = new HomeLocation(name, plugin.server.getWorld(homeSection.get("Location.World").toString()),
				homeSection.getInt("Location.X"), homeSection.getInt("Location.Y"), homeSection.getInt("Location.Z"),
				homeSection.getInt("Location.Pitch"), homeSection.getInt("Location.Yaw"));

		return new Home(this, location);
	}

	@Override
	public List<Home> getHomes() {

		List<Home> list = new ArrayList<>();

		if (getDataFile().contains("Homes")) {
			ConfigurationSection homes = getDataFile().getConfigurationSection("Homes");

			for (String name : homes.getKeys(false)) {
				list.add(this.getHome(name));
			}

		}

		return list;
	}

	@Override
	public Player player() {
		return player.getPlayer();
	}

	public static Map<UUID, File> playerDB() {
		Map<UUID, File> db = new HashMap<>(128);
		for (File file : userDatabase()) {
			if (file.isDirectory()) {
				String pattern = Pattern.compile(Pattern.UNICODE_CHARACTER_CLASS + "[^0-9a-z&&[\\p-]]").pattern();
				UUID uuid = UUID.fromString(file.getName().replaceAll(pattern, ""));
				db.put(uuid, file);
			}
		}

		return db;
	}

	protected static File[] userDatabase() {
		File folder = new File(MicroCommands.instance.getDataFolder() + "/PlayerData/");
		List<File> playerDataFiles = new ArrayList<>();
		for (String name : folder.list()) {
			File dataFolder = new File(name);
			if (dataFolder.isDirectory()) {
				playerDataFiles.add(dataFolder);
			}
		}

		return playerDataFiles.toArray(new File[] {});
	}
}
