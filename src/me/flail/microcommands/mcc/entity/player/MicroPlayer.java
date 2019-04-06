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
import org.bukkit.entity.Player;

import me.flail.microcommands.mcc.MicroCommands;

public class MicroPlayer extends AbstractMicroPlayer {

	public MicroPlayer(Player player) {
		super(player);
	}

	public MicroPlayer(OfflinePlayer player) {
		super(player);
	}

	public MicroPlayer(UUID uuid) {
		super(AbstractMicroPlayer.fromId(uuid));
	}

	public static MicroPlayer fromUuid(UUID uuid) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);

		return player.isOnline() ? new MicroPlayer(player.getPlayer()) : new MicroPlayer(uuid);
	}

	public UUID uuid() {
		return player.getUniqueId();
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
