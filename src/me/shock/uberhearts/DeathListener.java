package me.shock.uberhearts;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathListener implements Listener 
{

	public HashMap<String, Long> lives = new HashMap<String, Long>();
	
    public Main plugin;
	
	public DeathListener(Main instance)
	{
		this.plugin = instance;
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event)
	{
		Player player = event.getPlayer();
		
	}
}
