/*
 * <Copyright goes here>
 */
package me.flail.MicroCommands;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.MicroCommands.Events.PlayerJoin;
import me.flail.MicroCommands.Events.PlayerQuit;

public class MicroCommands extends JavaPlugin {

	public ConsoleCommandSender console = Bukkit.getConsoleSender();

	public Server server = this.getServer();

	private PluginManager plugin = server.getPluginManager();
	private CommandProcessor cmdControl;

	public Logger logger = Bukkit.getLogger();

	public Tools tools = new Tools(this);

	private FileConfiguration messagesConfig;
	private File messagesFile;

	private File playerFile;
	private FileConfiguration playerConfig;

	public String dirName = "PlayerData";
	public String path = "plugins/MicroCommands/";

	public String version = this.getDescription().getVersion();
	public String serverVersion = this.getServer().getVersion();
	public String serverName = getServer().getServerName();

	protected void consoleSpam() {

		console.sendMessage(" ");
		console.sendMessage(tools.chat(" &aMicroCommands &7v" + version));
		console.sendMessage(tools.chat("   &2by FlailoftheLord."));
		console.sendMessage(tools.chat("  &eeverything your server needs!"));
		console.sendMessage(" ");
		logger.log(Level.INFO, "MicroCommands running under " + serverVersion);
		logger.log(Level.INFO, "ServerName: " + serverName);
		console.sendMessage(" ");

	}

	@Override
	public void onEnable() {

		// Load up config and other resource files
		saveDefaultConfig();
		userDataFolder();
		loadMessages();

		for (Player p : Bukkit.getOnlinePlayers()) {
			loadPlayer(p);
		}

		// Register Events
		registerEvents();

		// Register Commands
		registerCommands();

		// Spam the console with useless shit. :>
		consoleSpam();
	}

	@Override
	public void onDisable() {

	}

	private void registerEvents() {

		plugin.registerEvents(new PlayerJoin(), this);
		plugin.registerEvents(new PlayerQuit(), this);

	}

	private void registerCommands() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		cmdControl.processCommand(sender, label, args);
		return true;
	}

	private void userDataFolder() {

		File PlayerDataFolder = new File(path + dirName);

		if (!PlayerDataFolder.exists()) {
			boolean makeFolder = new File(path + dirName).mkdirs();

			if (!makeFolder) {
				console.sendMessage(ChatColor.RED + "Could not generate PlayerData folder in path: */" + path);
			}
		}

	}

	public FileConfiguration getMessages() {
		if (messagesConfig == null) {
			loadMessages();
		}
		return messagesConfig;
	}

	private void loadMessages() {
		String msgFile = "Messages.yml";
		messagesFile = new File(getDataFolder(), msgFile);

		if (!messagesFile.exists()) {
			messagesFile.getParentFile().mkdirs();
			saveResource(msgFile, false);
		}

		messagesConfig = new YamlConfiguration();
		try {
			messagesConfig.save(messagesFile);
		} catch (Exception e) {
			logger.log(Level.WARNING, "Could not load file " + msgFile, e);
		}

	}

	public void saveMessages() {
		if (!messagesFile.exists() || (messagesFile == null)) {
			loadMessages();
		}

		try {
			getMessages().save(messagesFile);
		} catch (Exception e) {
			logger.log(Level.WARNING, "Could not save file Messages.yml", e);
		}

	}

	public FileConfiguration getPlayer(Player player) {

		String pUuid = player.getUniqueId().toString();
		File pFile = new File(path + dirName, pUuid + ".yml");
		FileConfiguration pConfig = new YamlConfiguration();

		if (!pFile.exists()) {
			loadPlayer(player);
		}

		try {
			pConfig.save(pFile);
		} catch (Exception e) {
			logger.log(Level.WARNING,
					tools.chat("Could not save player data file for " + player.getName() + " (" + pUuid + ")"));
		}
		return pConfig;
	}

	public void loadPlayer(Player player) {
		String pName = player.getName();
		String puid = player.getUniqueId().toString();
		String pUuid = player.getUniqueId().toString() + ".yml";
		playerFile = new File(path + dirName, pUuid);

		if (!playerFile.exists()) {
			playerFile.getParentFile().mkdirs();
			try {
				playerFile.createNewFile();
			} catch (Exception e) {
				logger.log(Level.WARNING,
						tools.chat("Could not create player data file for " + pName + " (" + puid + ")"));
			}
		}

		playerConfig = new YamlConfiguration();
		try {
			playerConfig.save(playerFile);
		} catch (Exception e) {
			logger.log(Level.WARNING, tools.chat("Could not load player data file for " + pName + " (" + puid + ")"));
		}

	}

	public void savePlayer(Player player) {

	}

}
