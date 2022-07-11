package retamrovec.lifesteal.spigot;

import org.bukkit.Bukkit;
import org.bukkit.BanList.Type;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.md_5.bungee.api.ChatColor;

public class PlayerDeathListener implements Listener {
	
	LifeSteal lifesteal;
	public PlayerDeathListener (LifeSteal lifesteal) {
		this.lifesteal = lifesteal;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onDeath(PlayerDeathEvent e){ 
			Player player = e.getEntity();
			Player killer = player.getKiller();
			
			if (!player.hasPermission("lifesteal.ignore")) {
				
				if (lifesteal.getConfig().contains("player." + player.getName())) {
					
					if (!(lifesteal.getConfig().getInt("player." + player.getName()) == 2)) {
						
						lifesteal.getConfig().set("player." + player.getName(), lifesteal.getConfig().getInt("player." + player.getName()) - 2);
						
						lifesteal.saveConfig();
						
						player.setMaxHealth(lifesteal.getConfig().getInt("player." + player.getName()));
						
					}
					
					if (lifesteal.getConfig().getInt("player." + player.getName()) == 2) {
						
						Bukkit.getBanList(Type.NAME).addBan(player.toString(), ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.zero_health_ban")), null, "CONSOLE");
					}
				} else {
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.player_isnt_registered")));
				}
				if (killer instanceof Player) {
					if (lifesteal.getConfig().contains("player." + killer.getName())) {
													
						if (lifesteal.getConfig().getInt("player." + killer.getName()) > 40) {
							
							lifesteal.getConfig().set("player." + killer.getName(), lifesteal.getConfig().getInt("player." + killer.getName()) + 2);
							
							lifesteal.saveConfig();
								
							player.setMaxHealth(lifesteal.getConfig().getInt("player." + killer.getName()));
							
						}
							
					} else {
						Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.player_isnt_registered")));
					}
				}

			}
		
	}

}
