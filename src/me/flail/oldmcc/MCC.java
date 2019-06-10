package me.flail.oldmcc;

import javax.annotation.Nullable;

import org.bukkit.plugin.java.JavaPlugin;

import me.flail.oldmcc.mcc.BootManager;
import me.flail.oldmcc.mcc.MicroCommands;

public class MCC extends BootManager {
	@Nullable
	public static MicroCommands get() {
		return JavaPlugin.getPlugin(MicroCommands.class);
	}

}
