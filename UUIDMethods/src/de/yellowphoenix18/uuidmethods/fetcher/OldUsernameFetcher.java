package de.yellowphoenix18.uuidmethods.fetcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import de.yellowphoenix18.uuidmethods.config.MainConfig;

public class OldUsernameFetcher {
	
	public static String getUUID(String username) throws Exception {
		
        URL url = new URL("https://uuidmethods.yellowphoenix18.de/?type=read&name=" + username);
        URLConnection con = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine = in.readLine();
        String uuid = null; 
        if(inputLine != null) { 
            if(!inputLine.equals("")) {
                String part1 = inputLine.substring(0, 8);         
                String part2 = inputLine.substring(8, 12);         
                String part3 = inputLine.substring(12, 16);         
                String part4 = inputLine.substring(16, 20);         
                String part5 = inputLine.substring(20, 32);
                
                uuid = part1 + "-" + part2 + "-" + part3 + "-" + part4 + "-" + part5;
            }
        }
        in.close();
		return uuid;
	}
	
	public static void storeData(String uuid) throws Exception {
		uuid = uuid.replace("-", "");
        URL url = new URL("https://uuidmethods.yellowphoenix18.de/?type=write&name=" + uuid);
        URLConnection con = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		
		while ((inputLine = in.readLine()) != null) {
			if(MainConfig.debug == true) {
				System.out.println("[Debug] Data stored, return: " + inputLine);
			}
		}
		in.close();
	}
	

}
