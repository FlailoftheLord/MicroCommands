package me.flail.microcommands.mcc.commands.enchant;

import org.bukkit.inventory.ItemStack;

import me.flail.microcommands.modules.enchant.Enchantment;

public class IEnchantment implements Enchantment {
	ItemStack item;

	public IEnchantment(ItemStack item) {
		this.item = item;
	}

	@Override
	public ItemStack get() {
		return item;
	}

	@Override
	public void addEnchant(Enchantment type) {

	}

	@Override
	public Enchant enchants() {
		return new Enchant();
	}

}
