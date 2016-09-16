package de.yellowphoenix18.uuidmethods.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import de.yellowphoenix18.uuidmethods.UUIDMethods;
import de.yellowphoenix18.uuidmethods.commands.UUIDMethodsCommand;
import de.yellowphoenix18.uuidmethods.config.MainConfig;
import de.yellowphoenix18.uuidmethods.database.MYSQL;
import de.yellowphoenix18.uuidmethods.listener.UUIDListener;
import de.yellowphoenix18.uuidmethods.mcstats.Metrics;

public class Utils {
	
	public static void setUp() {
		loadListener();
		loadCommands();
		MainConfig.loadConfig();
		loadMYSQL();
		loadMCStats();
	}
	
	public static void loadListener() {	
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new UUIDListener(), UUIDMethods.m);
	}
	
	public static void loadCommands() {
		UUIDMethods.m.getCommand("uuidmethods").setExecutor(new UUIDMethodsCommand());
	}
	
	public static void loadMCStats() {
	    try {
	        Metrics metrics = new Metrics(UUIDMethods.m);
	        metrics.start();
	    } catch (IOException e) {
	        System.out.println("Failed to connect to MCStats");
	    }
	}
	
	public static void loadMYSQL() {	
		if(UUIDMethods.enabled == true) {
			MYSQL.connect(UUIDMethods.ip, 3306, UUIDMethods.database, UUIDMethods.username, UUIDMethods.password);
			
			try {
				Statement st = MYSQL.con.createStatement();
				st.executeUpdate("CREATE TABLE IF NOT EXISTS UUIDs(UUID varchar(100),Username varchar(100))");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("ERROR: Bitte schreibe erst die MYSQL-Daten in die config.yml");
			}
		}
	}

}
