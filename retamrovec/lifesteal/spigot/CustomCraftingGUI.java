package retamrovec.lifesteal.spigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CustomCraftingGUI {
	
	public Inventory CreateInventory() {
		Inventory inv = Bukkit.createInventory(null, InventoryType.WORKBENCH, ChatColor.translateAlternateColorCodes('&', "&cHeart &arecipe"));
		
		// Gold_Ingot | Diamond         | Gold_Ingot
		// Diamond    | Netherite_Ingot | Diamond
		// Gold_Ingot | Diamond         | Gold_Ingot
		
		inv.setItem(1, new ItemStack(Material.GOLD_INGOT));
		
		inv.setItem(2, new ItemStack(Material.DIAMOND));
		
		inv.setItem(3, new ItemStack(Material.GOLD_INGOT));
		
		inv.setItem(4, new ItemStack(Material.DIAMOND));
		
		inv.setItem(5, new ItemStack(Material.NETHERITE_INGOT));
		
		inv.setItem(6, new ItemStack(Material.DIAMOND));
		
		inv.setItem(7, new ItemStack(Material.GOLD_INGOT));
		
		inv.setItem(8, new ItemStack(Material.DIAMOND));
		
		inv.setItem(9, new ItemStack(Material.GOLD_INGOT));
		
		return inv;
	}
	
	public void OpenInventory(Player p) {
		p.openInventory(CreateInventory());
	}

}
