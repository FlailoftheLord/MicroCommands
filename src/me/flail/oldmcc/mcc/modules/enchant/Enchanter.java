package me.flail.oldmcc.mcc.modules.enchant;

import org.bukkit.inventory.ItemStack;

public abstract class Enchanter extends IEnchantment {
	ItemStack item;

	public Enchanter(ItemStack item) {
		super(item);
	}


}
