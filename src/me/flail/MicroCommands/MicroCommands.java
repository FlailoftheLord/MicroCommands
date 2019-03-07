/*
 * <Copyright goes here>
 */
package me.flail.microcommands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.microcommands.mcc.commands.CommandProcessor;
import me.flail.microcommands.mcc.entity.MobTags.InvisyTag;
import me.flail.microcommands.mcc.events.ChatListener;
import me.flail.microcommands.mcc.events.PlayerJoin;
import me.flail.microcommands.mcc.events.PlayerQuit;
import me.flail.microcommands.mcc.io.FileManager;
import me.flail.microcommands.mcc.tools.Tools;

public class MicroCommands extends JavaPlugin {

	public Server server;
	public ConsoleCommandSender console;

	public FileManager fileManager;
	public Tools tools;
	public Logger logger;

	public PluginManager plugin;
	public CommandProcessor cmdControl;

	public Map<UUID, Player> playerDatabase = new HashMap<>();
	public Map<String, FileConfiguration> playerFile = new HashMap<>();
	public List<Player> vanishedPlayers = new ArrayList<>();

	public String path = "plugins/MicroCommands/PlayerData/";

	public String version = this.getDescription().getVersion();
	public String serverVersion = this.getServer().getVersion();
	public String serverName;
	public String pluginPrefix = "[" + getDescription().getPrefix() + "] ";

	/**
	 * Main Plugin instance! ;p Proudly & clumsily coded by a wild Flail.
	 */
	public static MicroCommands plugin() {
		return getPlugin(MicroCommands.class);
	}

	@Override
	public void onLoad() {

	}

	@Override
	public void onEnable() {

		// load up the rest of the files and generate player files.
		console.sendMessage(pluginPrefix + " loaded messages files");

		// Register Events
		registerEvents();

		// Register Commands
		registerCommands();

		// Spam the console with useless shit. :>
		tools.consoleSpam();

	}

	@Override
	public void onDisable() {

	}

	public void registerEvents() {

		plugin.registerEvents(new PlayerJoin(), this);
		plugin.registerEvents(new PlayerQuit(), this);

		plugin.registerEvents(new InvisyTag(), this);

		plugin.registerEvents(new ChatListener(), this);

	}

	public void registerCommands() {
		Set<String> commands = this.getDescription().getCommands().keySet();
		for (String cmd : commands) {
			getCommand(cmd).setExecutor(this);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		cmdControl = new CommandProcessor(this);
		Command cmd = command;

		return cmdControl.processCommand(sender, cmd, label, args);
	}

	public Collection<Player> getOnlinePlayers() {
		return playerDatabase.values();
	}

}
