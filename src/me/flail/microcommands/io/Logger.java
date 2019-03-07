package me.flail.microcommands.io;

public interface Logger {

	public enum LogType {
		CHAT, CONSOLE, FILE;
	}

	boolean log(String message, LogType type);

	void logToFile(String log);

}
