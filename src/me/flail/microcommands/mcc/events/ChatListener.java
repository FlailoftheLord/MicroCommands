package me.flail.microcommands.mcc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.microcommands.MicroCommands;
import me.flail.microcommands.mcc.tools.ChatFormatting;

public class ChatListener implements Listener {

	private MicroCommands plugin = JavaPlugin.getPlugin(MicroCommands.class);
	private ChatFormatting chatFormat = new ChatFormatting();

	@EventHandler(priority = EventPriority.HIGH)
	public void playerChat(AsyncPlayerChatEvent event) {

		Player player = event.getPlayer();

		String message = event.getMessage();

		if (player.hasPermission("microcommands.chat.color")) {
			message = chatFormat.chatColor(message, "color");
		}
		if (player.hasPermission("microcommands.chat.format")) {
			message = chatFormat.chatColor(message, "format");
		}
		if (player.hasPermission("microcmmands.chat.magic")) {
			message = chatFormat.chatColor(message, "magic");
		}

		event.setMessage(message);

		event.setFormat(plugin.tools.chat.chat(chatFormat.chatFormat(player)));

	}

}
