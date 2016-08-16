package de.yellowphoenix18.uuidmethods.cache;

import java.util.HashMap;

public class Cache {
	
	public static HashMap<String, CachePacket> uuid_list = new HashMap<String, CachePacket>();
	public static HashMap<String, CachePacket> username_list = new HashMap<String, CachePacket>();
	
	public static void saveData(String uuid, String username) {
		CachePacket cp = new CachePacket(uuid, username);
		uuid_list.put(uuid, cp);
		username_list.put(username, cp);
	}
	
	public static String getUsername(String uuid) {
		if(uuid_list.containsKey(uuid)) {
			CachePacket cp = uuid_list.get(uuid);
			if(cp.isPacketAlive()) {
				return cp.getName();
			} else {
				uuid_list.remove(uuid);
			}
		}
		return null;
	}
	
	public static String getUUID(String username) {
		if(username_list.containsKey(username)) {
			CachePacket cp = username_list.get(username);
			if(cp.isPacketAlive()) {
				return cp.getName();
			} else {
				username_list.remove(username);
			}
		}
		return null;
	}

}
