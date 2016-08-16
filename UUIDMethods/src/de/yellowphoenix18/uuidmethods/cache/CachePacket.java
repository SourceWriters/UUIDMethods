package de.yellowphoenix18.uuidmethods.cache;

public class CachePacket {
	
	long time = 0;
	String uuid = "";
	String name = "";
	
	public CachePacket(String uuid, String name) {
		long millis = System.currentTimeMillis();
		this.uuid = uuid;
		this.name = name;
		this.time = millis/1000;
	}
	
	public boolean isPacketAlive() {
		long millis = System.currentTimeMillis();
		long nw_time = millis/1000;
		if(nw_time-time > 60*5) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUUID() {
		return this.uuid;
	}

}
