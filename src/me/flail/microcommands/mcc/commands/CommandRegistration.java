package me.flail.microcommands.mcc.commands;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.MicroManager;
import me.flail.microcommands.mcc.tools.ChatUtils;
import me.flail.tools.DataFile;

public class CommandRegistration {
	MicroCommands plugin = MicroCommands.instance;

	private DataFile cmdFile;

	public CommandRegistration() {
		cmdFile = new DataFile("Commands.yml");
	}

	public PluginCommand[] getCommands() {
		List<PluginCommand> list = new ArrayList<>();

		for (String command : cmdFile.keySet()) {
			list.add(this.command("Commands." + command, plugin));
		}

		return list.toArray(new PluginCommand[] {});
	}

	public void loadCommandsFromFile() {
		ChatUtils chat = new ChatUtils();

		for (PluginCommand cmd : (getCommands() != null) ? getCommands() : new PluginCommand[] {}) {
			String command = "Commands." + cmd.getName();

			List<String> aliases = cmdFile.getList(command + ".aliases");
			List<String> args = new ArrayList<>();
			String description = cmdFile.getValue(command + ".description");

			String permission = cmdFile.getValue(command + ".permission");
			String permMessage = cmdFile.getValue(command + ".no-permission");

			for (String arg : cmdFile.getValue(command + ".usage").split(" ")) {
				args.add(arg);
			}

			cmd.setDescription(chat.chat(description));
			cmd.setPermission(permission);
			cmd.setPermissionMessage(permMessage);
			cmd.setAliases(aliases);
			cmd.setLabel(command);
			cmd.setUsage(chat.chat(cmdFile.getValue(command + ".usage").replace("$command$", command)));

			cmd.setExecutor(plugin);
			cmd.setTabCompleter(plugin);

			MicroManager.registerCommandToServer(cmd);

			new MicroCommand().registerSuggestions(cmd, permission);

		}


	}

	protected PluginCommand command(String name, Plugin plugin) {
		try {
			Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class,
					Plugin.class);
			constructor.setAccessible(true);

			return constructor.newInstance(name, plugin);
		} catch (Throwable t) {
			return null;
		}

	}

}
