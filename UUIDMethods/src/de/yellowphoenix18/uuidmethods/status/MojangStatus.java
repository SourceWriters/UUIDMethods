package de.yellowphoenix18.uuidmethods.status;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MojangStatus {
	
	public static JSONParser jsonParser = new JSONParser();
	
    public static String getStatus() throws Exception {
    	HttpURLConnection connection = createConnection();
        JSONObject jsonProfile = (JSONObject) jsonParser.parse(new InputStreamReader(connection.getInputStream()));
        String status = (String) jsonProfile.get("Status");
		return status; 	
    }

    public static HttpURLConnection createConnection() throws Exception {
        URL url = new URL("https://api.mojang.com/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        return connection;
    }

}
