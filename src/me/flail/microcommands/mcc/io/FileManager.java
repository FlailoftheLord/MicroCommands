package me.flail.microcommands.mcc.io;

import java.io.File;
import java.io.InputStream;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.flail.microcommands.io.FileLoader;
import me.flail.microcommands.io.Logger;
import me.flail.microcommands.io.Logger.LogType;
import me.flail.microcommands.lang.Locale.Locale;
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
		String defaultValue = new Locale().message(path);

		return this.getFile("Locale.yml").get(path, defaultValue).toString();
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
	public boolean loadFile(String fileName) {

		if (!fileName.endsWith(".yml")) {
			fileName = fileName.concat(".yml");
		}

		File settingsFile = new File(plugin.getDataFolder(), fileName);

		if (!settingsFile.exists()) {
			try {
				return this.loadStream(settingsFile, plugin.getResource(fileName));
			} catch (Throwable e) {
			}

		} else {
			settingsFile.mkdirs();
		}

		return false;
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

	protected boolean loadStream(File file, InputStream stream) {
		if (stream == null) {
			try {
				file.createNewFile();
			} catch (Throwable t) {
			}
			return true;
		}

		try {
			return true;
		} catch (Throwable t) {
			return false;
		}

	}

}
