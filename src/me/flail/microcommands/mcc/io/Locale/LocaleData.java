package me.flail.microcommands.mcc.io.Locale;

import java.util.LinkedHashMap;
import java.util.Map;

public class LocaleData {

	static Map<String, String> config = new LinkedHashMap<>(512);

	public void run() {
		config.put("Flail", "lol, i don't know why i made this...");
	}

}
