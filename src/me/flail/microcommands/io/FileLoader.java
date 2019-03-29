package me.flail.microcommands.io;

import org.bukkit.configuration.file.FileConfiguration;

import me.flail.microcommands.mcc.MicroCommands;

public interface FileLoader {

	/**
	 * Gets the message from the specified path in the Messages.yml file
	 *
	 * @param path the path of the message inside the messages config file.
	 * @return the string message if found, null otherwise.
	 */
	String getMessage(String path);

	/**
	 * generates the user data folder.
	 */
	void userDataFolder();

	/**
	 * Gets the specified configuration file from the plugins' data folder. If it's
	 * not found, it will return null.
	 *
	 * @param plugin
	 *                     an instance of the main {@link MicroCommands} class
	 * @param fileName
	 *                     the name of the file, with or without the '.yml'
	 * @return the {@link FileConfiguration} if it was found, if not found, it will attempt to create
	 *         it. If it can't create the new file, it will return null.
	 */
	FileConfiguration getFile(String fileName);

	/**
	 * Generates the file if it's not already loaded. Copies the internal contents
	 * to the @param:fileName path if the file doesn't already exist. Also adds the
	 * generated FileConfiguration to the main plugins' live database.
	 *
	 * @param plugin   an instance of the main {@link MicroCommands} class
	 * @param fileName the path to/from which the file should be loaded.
	 */
	boolean loadFile(String fileName);

	/**
	 * Saves the contents of the specified {@linkplain:file} to the pathname of
	 * {@linkplain:fileName}
	 *
	 * @param plugin   instance of the main {@link MicroCommands} class
	 * @param fileName path of the location where the file should be saved to.
	 * @param file     the FileConfiguration to be saved to {@linkplain:fileName}
	 */
	void saveFile(FileConfiguration file);
}
