package fr.aang.gvote.item;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemKey {
	
	public static ItemStack get() {
		ItemStack	customGuy = new ItemStack(Material.TRIPWIRE_HOOK, 1);
		ItemMeta	customM = customGuy.getItemMeta();
		customM.setDisplayName("§bClef de Vote");
		customM.setLore(Arrays.asList("§7▪ §fUtilisez cette clef au §eSpawn", 
									"  §fdans le §bCoffre de Vote"));
		customM.addEnchant(Enchantment.DURABILITY, 250, true);
		customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		customGuy.setItemMeta(customM);
		return customGuy;
	}
	
	public static boolean is(ItemStack item) {
		if (item != null
				&& item.getType() != null
				&& item.getType() == Material.TRIPWIRE_HOOK
				&& item.getItemMeta() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName().equals("§bClef de Vote")
				&& item.getEnchantmentLevel(Enchantment.DURABILITY) == 250)
		{
			return true;
		}
		return false;
	}
}
