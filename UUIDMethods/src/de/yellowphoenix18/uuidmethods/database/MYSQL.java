package de.yellowphoenix18.uuidmethods.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class MYSQL {
	
	public static Connection con;
	
	public static Connection connect(String host, int port, String database, String user, String password) {
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", user, password);
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage("§cDatabase-Error:" + e.getMessage());
			e.printStackTrace();
		}
		
		
		return con;
	}
	
	public static void disconnect(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean hasConnection() {
		
		if(con != null) {
			return true;
		}
		
		return false;		
	}
}
