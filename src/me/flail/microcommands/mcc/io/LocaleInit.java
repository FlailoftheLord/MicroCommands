package me.flail.microcommands.mcc.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import me.flail.microcommands.mcc.MicroCommands;

public class LocaleInit extends FileManager {

	MicroCommands plugin = MicroCommands.instance;

	public LocaleInit(MicroCommands plugin) {
		super(plugin);
	}

	protected void setupLocale(String version) {

		FileInputStream messagesStreamIn = (FileInputStream) plugin.getResource("messages.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(messagesStreamIn.toString()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
