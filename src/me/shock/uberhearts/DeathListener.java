package me.shock.uberhearts;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathListener implements Listener 
{

	public HashMap<String, Long> lives = new HashMap<String, Long>();
	String uber = ChatColor.RED + "[" + ChatColor.WHITE + "UberHearts" + ChatColor.RED + "]" + ChatColor.WHITE + ": ";
	
    public Main plugin;
	
	public DeathListener(Main instance)
	{
		this.plugin = instance;
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event)
	{
		Player player = event.getPlayer();
		long maxLives = plugin.getConfig().getLong("max-lives");
		
		/**
		 * Handles when a player has already respawned once.
		 * Handling it in reverse order I guess.
		 */
		if(lives.containsKey(player))
		{
			long formerLives = lives.get(player.getName());
			player.setMaxHealth((int) formerLives - 1);
		}
		else
		{
			lives.put(player.getName(), maxLives -1);
		}
		
		player.sendMessage(uber + "You have " + ChatColor.RED + lives.get(player.getName()) + ChatColor.WHITE + " lives left.");

	}
}
