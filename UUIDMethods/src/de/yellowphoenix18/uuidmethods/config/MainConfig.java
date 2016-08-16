package de.yellowphoenix18.uuidmethods.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.yellowphoenix18.uuidmethods.UUIDMethods;

public class MainConfig {
	
	public static File f = new File("plugins/UUIDDatabse", "config.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	
	public static boolean debug = false;
	
	public static void loadConfig() {	
		if(cfg.getString("MYSQL.IP") == null) {	    
		    cfg.set("MYSQL.IP", "localhost");
		}	
		if(cfg.getInt("MYSQL.Port") == 0) {	    
		    cfg.set("MYSQL.Port", 3306);
		}	
		if(cfg.getString("MYSQL.Username") == null) {	    
		    cfg.set("MYSQL.Username", "Username");
		}	
		if(cfg.getString("MYSQL.Password") == null) {	    
		    cfg.set("MYSQL.Password", "Password");
		}	
		if(cfg.getString("MYSQL.Database") == null) {	    
		    cfg.set("MYSQL.Database", "Database");
		}	
		if(cfg.getBoolean("MYSQL.Enabled") == false) {	    
		    cfg.set("MYSQL.Enabled", false);
		}	
		if(cfg.getBoolean("Settings.Debug") == false) {	    
		    cfg.set("Settings.Debug", false);
		}
		
		try {
			cfg.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		UUIDMethods.username = cfg.getString("MYSQL.Username");
		UUIDMethods.ip = cfg.getString("MYSQL.IP");
		UUIDMethods.password = cfg.getString("MYSQL.Password");
		UUIDMethods.database = cfg.getString("MYSQL.Database");
		UUIDMethods.port = cfg.getInt("MYSQL.Port");
		UUIDMethods.enabled = cfg.getBoolean("MYSQL.Enabled");
		debug = cfg.getBoolean("Settings.Debug");
	}

}
