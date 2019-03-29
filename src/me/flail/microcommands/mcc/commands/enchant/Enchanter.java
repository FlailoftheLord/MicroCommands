package me.flail.microcommands.mcc.commands.enchant;

import org.bukkit.inventory.ItemStack;

public abstract class Enchanter extends IEnchantment {
	ItemStack item;

	public Enchanter(ItemStack item) {
		super(item);
	}


}
