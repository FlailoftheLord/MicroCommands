package me.flail.microcommands.mcc;

import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;

import me.flail.microcommands.io.Logger;
import me.flail.microcommands.io.Logger.LogType;
import me.flail.microcommands.mcc.commands.CommandProcessor;
import me.flail.microcommands.mcc.io.FileManager;
import me.flail.microcommands.mcc.io.ILogger;
import me.flail.microcommands.mcc.tools.Tools;

public class BootManager {
	private MicroCommands plugin = MicroCommands.instance;
	public Server server = plugin.getServer();
	public ConsoleCommandSender console = server.getConsoleSender();
	public Logger logger = new ILogger();
	public Tools tools = new Tools();
	public PluginManager pluginManager = server.getPluginManager();
	public CommandProcessor commandControl = new CommandProcessor(plugin);
	public FileManager fileManager = new FileManager(plugin);
	private MicroManager manager = new MicroManager();

	public boolean load() {

		try {
			plugin.console = console;
			plugin.logger = logger;
			plugin.fileManager = fileManager;
			plugin.cmdControl = commandControl;
			plugin.plugin = pluginManager;
			plugin.serverName = server.getName();

			// Load up config and other resource files before the plugin is enabled.
			plugin.saveDefaultConfig();
			console.sendMessage("loaded config.yml");
			fileManager.userDataFolder();
			console.sendMessage("loaded player data folder");

			console.sendMessage("================");

			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public void startup() {

		manager.registerCommands();
		logger.log("Loaded commands.", LogType.CONSOLE);
		manager.registerEvents();
		logger.log("Events initiated.", LogType.CONSOLE);

	}

}
