package de.yellowphoenix18.uuidmethods.methods;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import de.yellowphoenix18.uuidmethods.UUIDMethods;
import de.yellowphoenix18.uuidmethods.cache.Cache;
import de.yellowphoenix18.uuidmethods.database.UUIDDatabase;
import de.yellowphoenix18.uuidmethods.fetcher.OldUsernameFetcher;
import de.yellowphoenix18.uuidmethods.fetcher.UUIDFetcher;
import de.yellowphoenix18.uuidmethods.localstorage.LocalStorage;
import de.yellowphoenix18.uuidmethods.status.MojangStatus;


public class UUIDAPI {
	
	public static String getUsernameDatabase(String UUID) {
		String Username = null;		
		try {
			Username = UUIDDatabase.getUsername(UUID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Username;
	}
	
	public static String getUsernameLocal(String UUID) {
		String username = null;
		if(UUIDMethods.storelocal == true) {
			username = LocalStorage.getUsername(UUID);
		}
		return username;
	}
	
	public static String getUsernameMojang(String UUID) {
		String Username = null;		
		UUIDFetcher fetcher = new UUIDFetcher(Arrays.asList("xxx"));
		try {
			Username = fetcher.getUsername(UUID);
			UUIDDatabase.setData(UUID, Username);
			if(UUIDMethods.storelocal == true) {
				LocalStorage.set(Username, UUID);
			}
			OldUsernameFetcher.storeData(UUID);
			return Username;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getUsername(String UUID) {
		String Username = null;
		if(Cache.getUsername(UUID) != null) {
			return Cache.getUsername(UUID);
		} else {
			try {
				if(MojangOnline() == false) {
					Username = getUsernameDatabase(UUID);
					if(Username == null) {
						if(UUIDMethods.storelocal == true) {
							Username = LocalStorage.getUsername(UUID);
						}
					}
				} else {
					Username = getUsernameMojang(UUID);
					UUIDDatabase.setData(UUID, Username);
					OldUsernameFetcher.storeData(UUID);
				}
			} catch (Exception e) {
				Username = getUsernameDatabase(UUID);
			}
			if(Username != null) {
				Cache.saveData(UUID, Username);
			}
			return Username;
		}
	}
	
	public static String getUsernameWithoutCache(String UUID) {
		String Username = null;
			try {
				if(MojangOnline() == false) {
					Username = getUsernameDatabase(UUID);
					if(Username == null) {
						if(UUIDMethods.storelocal == true) {
							Username = LocalStorage.getUsername(UUID);
						}
					}
				} else {
					Username = getUsernameMojang(UUID);
					UUIDDatabase.setData(UUID, Username);
					OldUsernameFetcher.storeData(UUID);
				}
			} catch (Exception e) {
				Username = getUsernameDatabase(UUID);
			}
			if(Username != null) {
				Cache.saveData(UUID, Username);
			}
			return Username;
	}
	
	public static String getUUIDLocal(String Username) {
		String uuid = null;
		if(UUIDMethods.storelocal == true) {
			uuid = LocalStorage.getUUID(Username);
		}
		return uuid;
	}
	
	public static String getUUIDMojang(String Username) {
		String UUID = null;
		UUIDFetcher fetcher = new UUIDFetcher(Arrays.asList(Username));
		Map<String, UUID> response = null;
		try {
			response = fetcher.call();
			if(response.containsKey(Username)) {
				UUID = response.get(Username).toString();
				if(UUIDMethods.storelocal == true) {
					LocalStorage.set(Username, UUID);
				}
				UUIDDatabase.setData(UUID, Username);
				OldUsernameFetcher.storeData(UUID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(UUID == null) {
			try {
				UUID = OldUsernameFetcher.getUUID(Username);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return UUID;
	}
	
	public static String getUUIDDatabase(String Username) {
		String UUID = null;
		
		try {
			UUID = UUIDDatabase.getUUID(Username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(UUID == null) {
			try {
				UUID = OldUsernameFetcher.getUUID(Username);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return UUID;
	}
	
	public static boolean MojangOnline() throws Exception {
        if(MojangStatus.getStatus() != null) {
        	if(MojangStatus.getStatus().equalsIgnoreCase("OK")) {
                return true;
        	} else {
                return false;
        	}
        } else {
            return false;
        }
	}
	
	public static String getUUID(String Username) {
		String UUID = null;
		if(Cache.getUsername(UUID) != null) {
			return Cache.getUsername(UUID);
		} else {
			try {
				if(MojangOnline() == false) {
					UUID = getUUIDDatabase(Username);
					if(UUID == null) {
						if(UUIDMethods.storelocal == true) {
							UUID = LocalStorage.getUUID(Username);
						}
					}
				} else {
					UUID = getUUIDMojang(Username);
					UUIDDatabase.setData(UUID, Username);
					OldUsernameFetcher.storeData(UUID);
				}
			} catch (Exception e) {
				UUID = getUUIDDatabase(Username);
			}
			
			if(UUID == null) {
				try {
					UUID = OldUsernameFetcher.getUUID(Username);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(Username != null) {
				Cache.saveData(UUID, Username);
			}
			
			return UUID;
		}
	}

	
	public static String getUUIDWithoutCache(String Username) {
		String UUID = null;
			try {
				if(MojangOnline() == false) {
					UUID = getUUIDDatabase(Username);
					if(UUID == null) {
						if(UUIDMethods.storelocal == true) {
							UUID = LocalStorage.getUUID(Username);
						}
					}
				} else {
					UUID = getUUIDMojang(Username);
					UUIDDatabase.setData(UUID, Username);
					OldUsernameFetcher.storeData(UUID);
				}
			} catch (Exception e) {
				UUID = getUUIDDatabase(Username);
			}
			
			if(UUID == null) {
				try {
					UUID = OldUsernameFetcher.getUUID(Username);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(Username != null) {
				Cache.saveData(UUID, Username);
			}
			
			return UUID;
		}
}