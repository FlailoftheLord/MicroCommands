package me.flail.microcommands.mcc.entity.player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.modules.homes.Home;
import me.flail.microcommands.mcc.modules.homes.HomeLocation;
import me.flail.tools.DataFile;
import me.flail.user.User;

public class MicroPlayer extends User {

	private DataFile data;

	public MicroPlayer(UUID uuid) {
		super(uuid);
		data = getDataFile();
	}

	public Home getHome(String name) {
		DataFile data = this.getDataFile();
		String key = "Homes.Location";

		HomeLocation location = new HomeLocation(name, plugin.server.getWorld(data.getValue(key + ".World")),
				data.getNumber(key + ".X"), data.getNumber(key + ".Y"), data.getNumber(key + ".Z"),
				data.getNumber(key + ".Pitch"), data.getNumber(key + ".Yaw"));

		return new Home(this, location);
	}

	public List<Home> getHomes() {

		List<Home> list = new ArrayList<>();

		if (getDataFile().hasValue("Homes")) {

			for (String name : data.keySet("Homes")) {
				list.add(this.getHome(name));
			}

		}

		return list;
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

	public static File[] userDatabase() {
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
