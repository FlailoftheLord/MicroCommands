package me.flail.microcommands.mcc.tools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.flail.microcommands.io.Logger;
import me.flail.microcommands.io.Logger.LogType;
import me.flail.microcommands.io.PlayerData;
import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.io.ILogger;
import me.flail.microcommands.mcc.io.PlayerDataHandler;

public class Tools {
	MicroCommands plugin = MicroCommands.instance;

	public ChatUtils chat = new ChatUtils();
	public TimeUtils time = new TimeUtils();

	public boolean safeLocation(Player player) {
		boolean reply = false;

		return reply;
	}

	public void consoleSpam() {
		Logger logger = new ILogger();

		startupDump(plugin);

		LogType c = LogType.CONSOLE;

		logger.log(" ", c);
		logger.log(" &aMicroCommands &7v" + plugin.version, c);
		logger.log("   &2by FlailoftheLord.", c);
		logger.log("  &eeverything your server needs!", c);
		logger.log(" ", c);
		logger.log("MicroCommands running under " + plugin.serverVersion, c);
		logger.log("ServerName: " + plugin.serverName, c);
		logger.log(" ", c);

	}

	public void startupDump(MicroCommands plugin) {
		LogType c = LogType.CONSOLE;

		for (Player p : plugin.server.getOnlinePlayers()) {
			plugin.playerDatabase.put(p.getUniqueId(), p);
			loadPlayer(p);
		}
		plugin.logger.log("Loaded up players.", c);

	}

	public boolean loadPlayer(Player player) {

		PlayerData playerFile = new PlayerDataHandler().player(player.getUniqueId().toString());

		FileConfiguration file = playerFile.get();

		String name = player.getName();
		String pUuid = player.getUniqueId().toString();
		String nickName = player.getDisplayName();
		String customName = player.getCustomName();
		String gamemode = player.getGameMode().toString();
		String address = player.getAddress().toString().replace("/", "");

		List<String> previousNames = new ArrayList<>();

		if ((file != null) && file.contains("Usernames")) {
			previousNames = file.getStringList("Usernames");
		}

		file.set("Uuid", pUuid);
		file.set("Name", name);
		file.set("IpAddress", address);

		if (!previousNames.contains(name)) {
			previousNames.add(name);
			file.set("Usernames", previousNames);
		}

		file.set("Nickname", nickName);
		file.set("CustomNameValue", customName);
		file.set("GameMode", gamemode);

		if (player.hasPermission("microcommands.fly")) {
			if (player.hasPermission("microcommands.flylogin")) {
				player.setAllowFlight(true);
				player.setFlying(true);
			}
			file.set("IsFlying", true);
		}

		return playerFile.save(file);

	}

}
