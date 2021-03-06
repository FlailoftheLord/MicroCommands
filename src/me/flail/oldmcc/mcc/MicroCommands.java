/*
 * Copyright (C) 2019 FlailoftheLord.
 * Read the LICENSE file under this plugin's root directory for the full license details.
 */
package me.flail.oldmcc.mcc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.oldmcc.mcc.commands.CommandProcessor;
import me.flail.oldmcc.mcc.entity.player.MicroPlayer;
import me.flail.oldmcc.mcc.tools.TabCompleter;
import me.flail.oldmcc.modules.MicroServer;
import me.flail.tools.DataFile;
import me.flail.tools.Logger;
import me.flail.user.User;

public class MicroCommands extends JavaPlugin {

	/**
	 * Main Plugin instance! ;p Proudly & clumsily coded by a wild Flail.
	 */
	public static MicroCommands instance;

	public Server server = new MicroServer(this).get();
	public ConsoleCommandSender console = server.getConsoleSender();

	public DataFile config;
	public DataFile messages = new DataFile("Messages.yml");

	public Logger logger;

	public PluginManager plugin;
	public CommandProcessor cmdControl;

	public Set<MicroPlayer> playerDatabase = new HashSet<>(32);
	public Map<UUID, User> players = new HashMap<>(4);
	public Map<String, UUID> offlinePlayers = new LinkedHashMap<>(256);
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

		config = new DataFile("config.yml");

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

		return new TabCompleter(sender, command).get(args);
	}

	public Set<MicroPlayer> getOnlinePlayers() {
		return playerDatabase;
	}

}
