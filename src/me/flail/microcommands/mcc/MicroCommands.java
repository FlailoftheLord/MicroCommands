/*
 * <Copyright goes here>
 */
package me.flail.microcommands.mcc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.microcommands.io.Logger;
import me.flail.microcommands.mcc.commands.CommandProcessor;
import me.flail.microcommands.mcc.commands.MicroCommand;
import me.flail.microcommands.mcc.entity.player.MicroPlayer;
import me.flail.microcommands.mcc.io.FileManager;
import me.flail.microcommands.modules.MicroServer;

public class MicroCommands extends JavaPlugin {

	/**
	 * Main Plugin instance! ;p Proudly & clumsily coded by a wild Flail.
	 */
	public static MicroCommands instance;

	public Server server = new MicroServer(this).get();
	public ConsoleCommandSender console = server.getConsoleSender();

	public FileManager fileManager = new FileManager(this);
	public Logger logger;

	public PluginManager plugin;
	public CommandProcessor cmdControl;

	public Map<UUID, Player> playerDatabase = new HashMap<>();
	public Map<String, FileConfiguration> playerFile = new HashMap<>();
	public List<Player> vanishedPlayers = new ArrayList<>();

	public Map<MicroPlayer, Set<Location>> blockData = new HashMap<>();

	public String version = this.getDescription().getVersion();
	public String serverVersion = this.getServer().getVersion();
	public String serverName;
	public String pluginPrefix = "[" + getDescription().getPrefix() + "] ";

	@Override
	public void onLoad() {
		instance = this;

		boolean load = new BootManager().load();
		if (!load) {
			this.getLogger().severe(
					"MicroCommands hit an error while loading resources.  Disabling plugin safely, please restart your server.");
			this.getServer().getPluginManager().disablePlugin(this);
		}

	}

	@Override
	public void onEnable() {

		new BootManager().startup();

	}

	@Override
	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		cmdControl = new CommandProcessor(this);
		Command cmd = command;

		return cmdControl.processCommand(sender, cmd, label, args);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

		List<String> completion = new ArrayList<>();

		if (command.getName().equalsIgnoreCase("microcommands")) {
			completion = new MicroCommand().getCommandArgs((PluginCommand) command);

		}
		return completion;
	}

	public Collection<Player> getOnlinePlayers() {
		return playerDatabase.values();
	}

}
