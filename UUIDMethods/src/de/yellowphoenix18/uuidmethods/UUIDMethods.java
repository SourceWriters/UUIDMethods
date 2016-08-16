package de.yellowphoenix18.uuidmethods;

import org.bukkit.plugin.java.JavaPlugin;

import de.yellowphoenix18.uuidmethods.database.MYSQL;
import de.yellowphoenix18.uuidmethods.utils.Utils;

public class UUIDMethods extends JavaPlugin {
	
	public static UUIDMethods m;
	
	public static String ip = "localhost";
	public static int port = 3306;
	public static String username = "username";
	public static String password = "password";
	public static String database = "database";
	public static boolean enabled = false;
	
	public void onEnable() {
		m = this;
		Utils.setUp();
	}
	
	public void onDisable() {
		if(enabled == true) {
			MYSQL.disconnect();
		}
	}
	
	
	
	

}
