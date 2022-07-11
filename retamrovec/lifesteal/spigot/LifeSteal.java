package retamrovec.lifesteal.spigot;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LifeSteal extends JavaPlugin implements Listener {
	
    String pluginversiontype = "1.19";
    String pluginversion = "V6.1";
    String fullpluginversion = pluginversiontype+"-"+pluginversion;
	
	public void onEnable() {
		getLogger().severe(ChatColor.translateAlternateColorCodes('&', "Version " + pluginversion + " (" + pluginversiontype + ")" + " has been enabled."));
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerDeathListener(this), this);
		pm.registerEvents(new CraftItemListener(this), this);
		pm.registerEvents(new PlayerJoinListener(this), this);
		pm.registerEvents(new InventoryClickListener(this, new CustomCraftingGUI()), this);
		pm.registerEvents(this, this);
		getCommand("lifesteal").setExecutor(new HealthManager(this, new CustomCraftingGUI()));
		getCommand("lifesteal").setTabCompleter(new HealthManagerTab(this));
		registerConfig();
		getConfig().options().copyDefaults(true);
		saveConfig();
		getConfig().set("plugin.version", fullpluginversion);
		saveConfig();
		getLogger().info("Config.yml was generated..");
		
		ItemStack goldenapple = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta goldenapplemeta = goldenapple.getItemMeta();
		goldenapplemeta.setDisplayName("Life");
		goldenapplemeta.setLore(List.of(ChatColor.GRAY + "Get one more heart."));
		goldenapple.setItemMeta(goldenapplemeta); 
		{
            NamespacedKey key = new NamespacedKey(this, "goldenapple");
            ShapedRecipe recipe = new ShapedRecipe(key, goldenapple);
            recipe.shape(
                    "GDG",
                    "DND",
                    "GDG");
            recipe.setIngredient('N', Material.NETHERITE_INGOT);
            recipe.setIngredient('D', Material.DIAMOND);
            recipe.setIngredient('G', Material.GOLD_INGOT);

            Bukkit.addRecipe(recipe);
		}
        new UpdateChecker(this, 102599, this).getVersion(version -> {
            if (fullpluginversion.equals(version)) {
                getLogger().info("Plugin version is the latest");
                if (this.getConfig().getBoolean("developer.enable") == true) {
                	getLogger().info("Latest version is " + version);
                	getLogger().info("Your version is " + fullpluginversion);
                }
            } else if (!fullpluginversion.equals(version)){
                getLogger().info("Plugin version is not latest. Please update this plugin.");
                if (this.getConfig().getBoolean("developer.enable") == true) {
                	getLogger().info("Latest version is " + version);
                	getLogger().info("Your version is " + fullpluginversion);
                }
            }
        });
	}
	
	public void onDisable() {
		getLogger().severe("Version " + pluginversion + " (" + pluginversiontype + ")" + " has been disabled.");
		saveConfig();
	}
	
	public void registerConfig() {
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
    		getLogger().info("Generating config.yml... It can take a while.");
            saveDefaultConfig();
        }
    }
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent e) {		
		Player player = e.getPlayer();
		if (player.isOp() || player.hasPermission("lifesteal.admin")) {
	        new UpdateChecker(this, 102599, this).getVersion(version -> {
	            if (!fullpluginversion.equals(version)) {
	                if (this.getConfig().getBoolean("config.notify_op_updates") == true) {
		                player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.update_available_notify")));
	                }
	            }
	        });
		}
	}

}
