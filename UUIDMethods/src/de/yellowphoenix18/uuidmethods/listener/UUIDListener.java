package de.yellowphoenix18.uuidmethods.listener;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import de.yellowphoenix18.uuidmethods.UUIDMethods;
import de.yellowphoenix18.uuidmethods.database.UUIDDatabase;
import de.yellowphoenix18.uuidmethods.fetcher.OldUsernameFetcher;
import de.yellowphoenix18.uuidmethods.localstorage.LocalStorage;

public class UUIDListener implements Listener {
	
	@EventHandler
	public void on(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		final String Username = p.getName();
		final String UUID = p.getUniqueId().toString();
		try {
			OldUsernameFetcher.storeData(UUID);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if(UUIDMethods.enabled == true) {
			Bukkit.getScheduler().runTaskAsynchronously(UUIDMethods.m, new Runnable() {
				@Override
				public void run() {
					try {
						UUIDDatabase.setData(UUID, Username);
					} catch (SQLException e) {
						e.printStackTrace();
					}				
				}			
			});
		}
		if(UUIDMethods.storelocal == true) {
			LocalStorage.set(Username, UUID);
		}
	}
	
	@EventHandler
	public void on(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String UUID = p.getUniqueId().toString();
		try {
			OldUsernameFetcher.storeData(UUID);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
	}
}
