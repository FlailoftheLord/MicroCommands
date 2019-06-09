package me.flail.microcommands;

import javax.annotation.Nullable;

import org.bukkit.plugin.java.JavaPlugin;

import me.flail.microcommands.mcc.BootManager;
import me.flail.microcommands.mcc.MicroCommands;

public class MCC extends BootManager {
	@Nullable
	public static MicroCommands get() {
		return JavaPlugin.getPlugin(MicroCommands.class);
	}

}
