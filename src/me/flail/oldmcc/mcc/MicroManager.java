package me.flail.oldmcc.mcc;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;

import me.flail.oldmcc.mcc.commands.CommandRegistration;
import me.flail.oldmcc.mcc.entity.MobTags.InvisyTag;
import me.flail.oldmcc.mcc.events.BlockLogging;
import me.flail.oldmcc.mcc.events.ChatListener;
import me.flail.oldmcc.mcc.events.PlayerJoin;
import me.flail.oldmcc.mcc.events.PlayerQuit;
import me.flail.oldmcc.modules.inventory.GuiManager;

public class MicroManager {
	private MicroCommands plugin = MicroCommands.instance;
	private PluginManager manager = plugin.getServer().getPluginManager();

	public void registerEvents() {

		manager.registerEvents(new PlayerJoin(), plugin);
		manager.registerEvents(new PlayerQuit(), plugin);
		manager.registerEvents(new BlockLogging(), plugin);

		manager.registerEvents(new InvisyTag(), plugin);

		manager.registerEvents(new ChatListener(), plugin);

		manager.registerEvents(new GuiManager(), plugin);

	}

	public void registerCommands() {
		CommandRegistration registry = new CommandRegistration();
		registry.loadCommandsFromFile();

	}


	public boolean reloadPlugin(boolean safe) {
		if (safe) {

		}

		return false;
	}

	public static void registerCommandToServer(Command command) {
		try {
			serverCommandMap().register("m" + command.getName(), command);

		} catch (Throwable t) {
		}
	}

	private static CommandMap serverCommandMap() {
		try {
			if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
				Field f = SimplePluginManager.class.getDeclaredField("commandMap");
				f.setAccessible(true);
				CommandMap commandMap = (CommandMap) f.get(Bukkit.getPluginManager());

				return commandMap;
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return null;
	}

}
