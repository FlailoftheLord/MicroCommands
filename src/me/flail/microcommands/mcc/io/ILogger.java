package me.flail.microcommands.mcc.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.ChatColor;

import me.flail.microcommands.io.Logger;
import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.tools.TimeUtils;
import me.flail.microcommands.mcc.tools.TimeUtils.Time;
import me.flail.microcommands.mcc.tools.Tools;

public class ILogger implements Logger {
	MicroCommands plugin = MicroCommands.instance;
	Tools tools = new Tools();

	@Override
	public boolean log(String message, LogType type) {

		switch (type) {

		case FILE:
			this.logToFile(tools.chat.chat(message));

			return true;
		case CHAT:

			return true;
		case CONSOLE:
			plugin.console.sendMessage(tools.chat.chat(plugin.pluginPrefix + message));
			return true;
		default:
			return false;

		}

	}

	@Override
	public void logToFile(String msg) {

		BufferedWriter logs = null;

		TimeUtils timeUtils = null;
		try {
			timeUtils = TimeUtils.class.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Time time = timeUtils.new Time();

		try {
			// create a temporary file
			String timeLog = time.monthName(Calendar.MONTH) + " "
					+ new SimpleDateFormat("dd_yyyy").format(Calendar.getInstance().getTime()).toString();

			boolean createFile = new File(plugin.getDataFolder() + "/logs").mkdirs();

			if (createFile || (createFile == false)) {

				File logFile = new File(plugin.getDataFolder() + "/logs/" + timeLog + ".txt");

				logs = new BufferedWriter(new FileWriter(logFile, true));
				logs.newLine();
				logs.write(time.currentDayTime() + " " + ChatColor.stripColor(msg));

				// console.sendMessage("Logging worked!");

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				logs.close();

				// console.sendMessage("logs closed");

			} catch (Exception e) {
			}

		}

	}

	@Override
	public boolean console(String message) {
		return this.log(message, LogType.CONSOLE);
	}

}
