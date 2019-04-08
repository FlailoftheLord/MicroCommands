package me.flail.microcommands.mcc.modules.homes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.microcommands.io.PlayerData;
import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.io.PlayerDataHandler;
import me.flail.microcommands.modules.homes.MccHome;

public abstract class AbstractHome implements MccHome {

	private MicroCommands plugin = JavaPlugin.getPlugin(MicroCommands.class);
	private PlayerData pData = new PlayerDataHandler();

	protected AbstractHome() {
	}

	@Override
	public String name() {
		String[] values = this.values().toArray(new String[0]);
		return values[0];
	}

	@Override
	public String owner() {
		String[] keys = this.keySet().toArray(new String[0]);
		return keys[0];
	}

	@Override
	public MccHome getHome(String playerUuid, String name) {

		FileConfiguration playerConfig = pData.player(playerUuid).get();

		if (playerConfig.contains("Homes." + name.toLowerCase())) {

			MccHome home = MccHome.class.cast(new HashMap<String, String>());

			home.put(playerUuid.trim(), name.toLowerCase());

			return home;
		} else {
			return null;
		}

	}

	@Override
	public List<String> getHomes(String playerUuid) {

		FileConfiguration playerConfig = pData.player(playerUuid).get();

		if (playerConfig.contains("Homes")) {
			List<String> homes = new ArrayList<>();

			for (String h : playerConfig.getConfigurationSection("Homes").getKeys(false)) {
				homes.add(h.toLowerCase());
			}

			return homes;
		}

		return new ArrayList<>();
	}

	@Override
	public boolean hasHome(String playerUuid, String homeName) {
		PlayerData player = pData.player(playerUuid);

		FileConfiguration playerConfig = player.get();
		if (playerConfig.contains("Homes." + homeName.toLowerCase())) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean set(Location location, boolean overwrite) {
		PlayerData player = pData.player(this.owner());

		FileConfiguration playerConfig = player.get();
		if (!overwrite && playerConfig.contains("Homes." + this.name())) {
			return false;
		} else {
			playerConfig.set("Homes." + this.name() + ".Location.World", location.getWorld().getName());
			playerConfig.set("Homes." + this.name() + ".Location.X", location.getBlockX());
			playerConfig.set("Homes." + this.name() + ".Location.Y", location.getBlockY());
			playerConfig.set("Homes." + this.name() + ".Location.Z", location.getBlockZ());
			playerConfig.set("Homes." + this.name() + ".Location.Pitch", location.getPitch());
			playerConfig.set("Homes." + this.name() + ".Location.Yaw", location.getYaw());

			return player.save(playerConfig);
		}
	}

	@Override
	public boolean setHome(String playerUuid, Location location, String name, boolean overwrite) {
		MccHome home = this.getHome(playerUuid, name);

		return home.set(location, overwrite);
	}

	@Override
	public boolean deleteHome(String playerUuid, String home) {
		try {
			PlayerData player = pData.player(playerUuid);

			FileConfiguration playerConfig = player.get();
			playerConfig.set("Homes." + home.toLowerCase(), null);

			return player.save(playerConfig);
		} catch (Throwable t) {
			return false;
		}
	}

	@Override
	public boolean sendToHome(String playerUuid, String homeName) {
		try {
			PlayerData player = pData.player(playerUuid);
			FileConfiguration playerConfig = player.get();

			ConfigurationSection home = playerConfig.getConfigurationSection("Homes." + homeName.toLowerCase());

			if (home != null) {

				World world = plugin.server.getWorld(home.get("Location.World", "world").toString());
				int x = home.getInt("Location.X", 0);
				int y = home.getInt("Location.Y", 64);
				int z = home.getInt("Location.Z", 0);
				float pitch = (float) home.getDouble("Location.Pitch", 90);
				float yaw = (float) home.getDouble("Location.Yaw", 0);

				Location homeLocation = new Location(world, x, y, z, yaw, pitch);
				UUID pUuid = UUID.fromString(playerUuid);
				if (plugin.playerDatabase.containsKey(pUuid)) {
					Player homeOwner = plugin.playerDatabase.get(pUuid);

					return homeOwner.teleport(homeLocation);
				}

			}

			return false;
		} catch (Throwable t) {
			return false;
		}
	}

}
