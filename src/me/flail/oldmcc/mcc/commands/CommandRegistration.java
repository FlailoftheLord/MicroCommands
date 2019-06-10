package me.flail.oldmcc.mcc.commands;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import me.flail.oldmcc.mcc.MicroCommands;
import me.flail.oldmcc.mcc.MicroManager;
import me.flail.tools.DataFile;
import me.flail.tools.Logger;

public class CommandRegistration extends Logger {
	MicroCommands plugin = MicroCommands.instance;

	private DataFile cmdFile;

	public CommandRegistration() {
		cmdFile = new DataFile("Commands.yml");
	}

	public PluginCommand[] getCommands() {
		List<PluginCommand> list = new ArrayList<>();

		for (String command : cmdFile.keySet("Commands")) {
			list.add(this.command("Commands." + command, plugin));
		}

		return list.toArray(new PluginCommand[] {});
	}

	public void loadCommandsFromFile() {
		for (PluginCommand cmd : (getCommands() != null) ? getCommands() : new PluginCommand[] {}) {
			String command = "Commands." + cmd.getName();

			List<String> aliases = cmdFile.getList(command + ".aliases");
			List<String> args = new ArrayList<>();
			String description = cmdFile.getValue(command + ".description");

			String permission = cmdFile.getValue(command + ".permission");
			String permMessage = cmdFile.getValue(command + ".no-permission");

			if (cmdFile.hasValue(command + ".usage")) {
				for (String arg : cmdFile.getValue(command + ".usage").split(" ")) {
					args.add(arg);
				}

				cmd.setUsage(chat(cmdFile.getValue(command + ".usage").replace("$command$", command)));
			}

			cmd.setDescription(chat(description));
			cmd.setPermission(permission);
			cmd.setPermissionMessage(permMessage);
			cmd.setAliases(aliases);
			cmd.setLabel(command);


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
