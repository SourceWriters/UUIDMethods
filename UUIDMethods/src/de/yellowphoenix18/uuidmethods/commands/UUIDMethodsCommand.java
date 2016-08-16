package de.yellowphoenix18.uuidmethods.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.yellowphoenix18.uuidmethods.UUIDMethods;
import de.yellowphoenix18.uuidmethods.methods.UUIDAPI;

public class UUIDMethodsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("uuidmethods.admin")) {
				if(args.length == 0) {
					
				} else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("getUUID")) {
						String username = args[1];
						Bukkit.getScheduler().runTaskAsynchronously(UUIDMethods.m, new Runnable() {
							@Override
							public void run() {
								if(UUIDAPI.getUUID(username) != null) {
									p.sendMessage(UUIDAPI.getUUID(username));
								}
							}						
						});
					} else if(args[0].equalsIgnoreCase("getUsername")) {
						String uuid = args[1];
						Bukkit.getScheduler().runTaskAsynchronously(UUIDMethods.m, new Runnable() {
							@Override
							public void run() {
								if(UUIDAPI.getUUID(uuid) != null) {
									p.sendMessage(UUIDAPI.getUsername(uuid));
								}
							}						
						});
					}
				}
			}
		}
		
		
		return true;
	}

}
