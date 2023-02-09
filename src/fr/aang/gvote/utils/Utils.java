package fr.aang.gvote.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {
	
	public static ItemStack getItem(Material material, String custom_name, List<String> lore) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(custom_name);
		if (lore != null && !lore.isEmpty())
			itemM.setLore(lore);
		item.setItemMeta(itemM);;
		return item;
	}
	
	public static ItemStack addEnchantEffect(ItemStack item) {
		
		ItemStack new_item = item.clone();
		
		ItemMeta itemM = new_item.getItemMeta();
		itemM.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
		itemM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		new_item.setItemMeta(itemM);
		return new_item;
	}
	
	public static boolean hasAvaliableSlot(Player player,int howmanyclear) {
		
		Inventory inv = player.getInventory();
		int check = 0;
		
		for (int i = 0; i < 36; i++) {
		    ItemStack item = inv.getItem(i);
		    if (item == null || item.getType() == Material.AIR) {
		        check++;
		    }
		}
		if(check >= howmanyclear)
			return true;
		else
			return false;
	}
	
	public static Date	stringToDate(String date_time) {
		
		SimpleDateFormat dateParser = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		try {
			return dateParser.parse(date_time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String dateToString(Date date_time) {

        SimpleDateFormat date_format = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
        
        return date_format.format(date_time);
	}
	
	public static Date getCurrentDate() {
		return new Date(Calendar.getInstance().getTimeInMillis());
	}
	
	public static Date getNextDate(int delay_min) {
		long time = Calendar.getInstance().getTimeInMillis();
		return new Date(time + (delay_min * 60000));
	}
	
	public static String getEquartDate(Date time1, Date time2) {

		int diff_min =  (int)((time1.getTime() - time2.getTime()) / 60000);

		int nbHeures = (diff_min / 60);
		int nbMinutes = (diff_min - (nbHeures * 60));

		return (-nbHeures) + "h" + (-nbMinutes) + "m";
	}
	

}
