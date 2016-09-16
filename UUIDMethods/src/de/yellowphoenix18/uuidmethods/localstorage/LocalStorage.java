package de.yellowphoenix18.uuidmethods.localstorage;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocalStorage {
	
	public static File f = new File("plugins/UUIDDatabse", "storage.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	
	public static void set(String username, String uuid) {
		cfg.set("uuid." + username, uuid);
		cfg.set("username." + uuid, username);
		save();
	}
	
	public static String getUsername(String uuid) {
		String username = null;
		if(cfg.getString("username." + uuid) != null) {
			username = cfg.getString("username." + uuid);
		}
		return username;
	}
	
	public static String getUUID(String username) {
		String uuid = null;
		if(cfg.getString("uuid." + username) != null) {
			uuid = cfg.getString("uuid." + username);
		}
		return uuid;
	}
	
	public static void save() {
		try {
			cfg.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
