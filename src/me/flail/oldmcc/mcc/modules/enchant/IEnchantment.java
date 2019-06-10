package me.flail.oldmcc.mcc.modules.enchant;

import org.bukkit.inventory.ItemStack;

import me.flail.oldmcc.modules.enchant.Enchantment;

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
