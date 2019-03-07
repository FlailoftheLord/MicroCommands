package me.flail.microcommands.mcc.io;

import java.io.File;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.flail.microcommands.MicroCommands;
import me.flail.microcommands.io.FileLoader;
import me.flail.microcommands.io.Logger.LogType;
import me.flail.microcommands.mcc.tools.ChatUtils;

public class FileManager implements FileLoader {
	MicroCommands plugin;

	private ConsoleCommandSender console;
	private ILogger logger;
	private ChatUtils chatUtils;

	private String path;
	private String pluginPrefix;

	public FileConfiguration config;

	public FileManager(MicroCommands inst) {
		plugin = inst;
		console = plugin.console;
		logger = plugin.logger;
		chatUtils = plugin.tools.chat;

		path = plugin.path;
		pluginPrefix = plugin.pluginPrefix;

		config = plugin.getConfig();
	}

	@Override
	public String getMessage(String path) {
		return this.getFile(plugin, "Messages.yml").get(path).toString();

	}

	@Override
	public void userDataFolder() {

		File PlayerDataFolder = new File(path);

		if (!PlayerDataFolder.exists()) {
			boolean makeFolder = new File(path).mkdirs();

			if (!makeFolder) {
				console.sendMessage(
						chatUtils.chat(pluginPrefix + " &cCould not generate PlayerData folder in path: */" + path));
			}
		}

	}

	@Override
	public FileConfiguration getFile(MicroCommands plugin, String fileName) {

		if (!fileName.endsWith(".yml")) {
			fileName = fileName.concat(".yml");
		}

		File settingsFile = new File(plugin.getDataFolder(), fileName);

		if (!settingsFile.exists()) {
			this.loadFile(plugin, fileName);
		}

		FileConfiguration config = new YamlConfiguration();

		try {
			config.load(settingsFile);
			return config;
		} catch (Throwable e) {
			return null;
		}

	}

	@Override
	public void loadFile(MicroCommands plugin, String fileName) {

		if (!fileName.endsWith(".yml")) {
			fileName = fileName.concat(".yml");
		}

		File settingsFile = new File(plugin.getDataFolder() + "/" + fileName);

		if (!settingsFile.exists()) {
			try {
				plugin.saveResource(fileName, true);
			} catch (Throwable e) {
			}
		} else {
			settingsFile.mkdirs();
		}

		FileConfiguration settingsConfig = new YamlConfiguration();

		try {
			settingsConfig.load(settingsFile);
			settingsConfig.save(settingsFile);
		} catch (Throwable t) {
			logger.log("&4Couldn't load & save file: " + fileName, LogType.CONSOLE);
		}

	}

	@Override
	public void saveFile(MicroCommands plugin, String fileName, FileConfiguration file) {

		if (!fileName.endsWith(".yml")) {
			fileName = fileName.concat(".yml");
		}

		File settingsFile = new File(plugin.getDataFolder(), fileName);

		if (!settingsFile.exists()) {
			this.loadFile(plugin, fileName);
		}

		settingsFile.mkdirs();

		if (file != null) {

			FileConfiguration settingsConfig = file;

			try {
				settingsConfig.save(settingsFile);
			} catch (Throwable e) {
				logger.log("&4Couldn't load & save file: " + fileName, LogType.CONSOLE);
			}

		} else {
			logger.log("&4Invalid or Null YAML file input.", LogType.CONSOLE);
		}

	}

}
