package me.flail.oldmcc.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.flail.oldmcc.modules.enchant.Enchantment.Enchant;

public class Item extends ItemStack {

	public Item(Material material) {
		super(material);
	}

	public Item(Material material, int quantity) {
		super(material, quantity);
	}

	public void addEnchant(Enchant enchant, int level) {

	}

}
