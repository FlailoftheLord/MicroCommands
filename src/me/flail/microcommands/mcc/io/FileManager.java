package me.flail.microcommands.mcc.io;

import java.io.File;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.flail.microcommands.io.FileLoader;
import me.flail.microcommands.io.Logger;
import me.flail.microcommands.io.Logger.LogType;
import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.tools.ChatUtils;

public class FileManager implements FileLoader {
	private MicroCommands plugin;

	private ConsoleCommandSender console;
	private Logger logger;
	private ChatUtils chatUtils = new ChatUtils();

	private String path;
	private String pluginPrefix;

	public FileConfiguration config;

	public FileManager(MicroCommands inst) {
		plugin = inst;
		console = inst.console;
		logger = inst.logger;

		path = MicroCommands.path;
		pluginPrefix = inst.pluginPrefix;

		config = inst.getConfig();
	}

	@Override
	public String getMessage(String path) {
		return this.getFile("Messages.yml").get(path).toString();

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
	public FileConfiguration getFile(String fileName) {

		if (!fileName.endsWith(".yml")) {
			fileName = fileName.concat(".yml");
		}

		File settingsFile = new File(plugin.getDataFolder(), fileName);

		if (!settingsFile.exists()) {
			this.loadFile(fileName);
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
	public void loadFile(String fileName) {

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
	public void saveFile(FileConfiguration file) {
		String fileName = file.getName();

		if (!fileName.endsWith(".yml")) {
			fileName = fileName.concat(".yml");
		}

		File settingsFile = new File(plugin.getDataFolder(), fileName);

		if (!settingsFile.exists()) {
			this.loadFile(fileName);
		}

		settingsFile.mkdirs();

		FileConfiguration settingsConfig = file;

		try {
			settingsConfig.save(settingsFile);
		} catch (Throwable e) {
			logger.log("&4Couldn't load & save file: " + fileName, LogType.CONSOLE);
		}

	}

}
