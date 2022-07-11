package retamrovec.lifesteal.spigot;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
	
	LifeSteal lifesteal;
	public PlayerJoinListener (LifeSteal lifesteal) {
		this.lifesteal = lifesteal;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
						
		if (!player.hasPlayedBefore()) {
			if (!lifesteal.getConfig().contains("player." + player.getName())) {
				lifesteal.getConfig().set("player." + player.getName(), 20);
				lifesteal.saveConfig();
			}
		}
		if (player.hasPlayedBefore()) {
			if (lifesteal.getConfig().contains("player." + player.getName())) {
				
				double playerMaxHealth = Double.valueOf(lifesteal.getConfig().getString("player." + player.getName()));
				
				player.setMaxHealth(playerMaxHealth);
			}
			if (!lifesteal.getConfig().contains("player." + player.getName())) {
				lifesteal.getConfig().set("player." + player.getName(), 20);
				lifesteal.saveConfig();
			}
		}
		
		player.setMaxHealth(lifesteal.getConfig().getInt("player." + player.getName()));
		
	}


}
