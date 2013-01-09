package me.shock.uberhearts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

	protected FileConfiguration config;
	File file;
	
	public static Main plugin;

	Logger log = Bukkit.getServer().getLogger();
	
	public void onEnable()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new DeathListener(this), (this));
		
		loadConfig();
	}
	
	/**
	 * Copies config from .jar
	 * Doesn't detect changes yet.
	 */
	public void loadConfig()
	{
		PluginDescriptionFile pdfFile = getDescription();
        @SuppressWarnings("unused")
		String version = pdfFile.getVersion();	
		/**
		 * Check to see if there's a config.
		 * If not then create a new one.
		 */
		File config = new File(getDataFolder() + "/config.yml");
		if(!config.exists())
		{
			try{
				getDataFolder().mkdir();
				config.createNewFile();
			} catch (IOException e) {
				log.log(Level.SEVERE, "[UberHearts] Couldn't create config");
			}
			/**
			 * Write the config file here.
			 * New, genius way to write it :)
			 */
			try {
				FileOutputStream fos = new FileOutputStream(new File(getDataFolder() + File.separator + "config.yml"));
				InputStream is = getResource("config.yml");
				byte[] linebuffer = new byte[4096];
				int lineLength = 0;
				while((lineLength = is.read(linebuffer)) > 0)
				{
					fos.write(linebuffer, 0, lineLength);
				}
				fos.close();
				
				log.log(Level.INFO, "[UberHearts] Wrote new config");
				
			} catch (IOException e) {
				log.log(Level.SEVERE, "[UberHearts] Couldn't write config: " + e);
			}	
		}
		else
		{
			log.log(Level.INFO, "[UberHearts] Config found.");
		}
	}
}
