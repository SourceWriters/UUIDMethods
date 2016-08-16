package de.yellowphoenix18.uuidmethods.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UUIDDatabase {
	
	public static void setData(String uuid, String username) throws SQLException {
		
		Statement st = null;
		
			st = MYSQL.con.createStatement();
		
		if(UUIDDatabase.isInUUIDDatabaseTable(uuid)) {
			st.executeUpdate("UPDATE UUIDs SET Username='" + username + "'WHERE UUID='" + uuid +"'");
		} else {
			st.executeUpdate("INSERT INTO UUIDs(UUID,Username) VALUES ('" + uuid + "','" + username + "')");
		}
	}
	
	public static String getUsername(String uuid) throws SQLException {		
		String username = null;
		
		Statement st = MYSQL.con.createStatement();
		
		ResultSet rs = null;
		
		rs = st.executeQuery("SELECT * FROM UUIDs WHERE UUID='" + uuid + "'");
		
		while(rs.next()) {
			username = rs.getString(2);
		}
		
		return username;
	}
	
	public static String getUUID(String username) throws SQLException {		
		String uuid = null;
		
		Statement st = MYSQL.con.createStatement();
		
		ResultSet rs = null;
		
		rs = st.executeQuery("SELECT * FROM UUIDs WHERE Username='" + username + "'");
		
		while(rs.next()) {
			uuid = rs.getString(1);
		}
		
		return uuid;
	}
	
	public static boolean isInUUIDDatabaseTable(String uuid) {
		try {
			Statement st = MYSQL.con.createStatement();
			
			ResultSet rs = null;
			
			rs = st.executeQuery("SELECT * FROM UUIDs WHERE UUID='"+ uuid +"'");
			
			while(rs.next()) {
			
			if(rs != null) {
				return true;
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

}
