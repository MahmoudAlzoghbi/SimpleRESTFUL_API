package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather_API {

	public static void main(String[] args) throws IOException, JSONException {
		
		String url = "https://www.weatherbit.io/static/swagger.json";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		
		
		int responseCode = con.getResponseCode();
		
		System.out.println("\nSending 'GET' requeste to URL :" + url);
		System.out.println("Response Code :" + con.getResponseCode());
		
		
		BufferedReader in =new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		// print in String 
		//System.out.println(response.toString());
		
		
		JSONObject My_Object = new JSONObject (response.toString());
		JSONObject Info_Object = new JSONObject(My_Object.getJSONObject("info").toString());
		JSONObject Path_Object = new JSONObject(My_Object.getJSONObject("paths").toString());
		JSONObject def_Object = new JSONObject(My_Object.getJSONObject("definitions").toString());
		
		
		
		System.out.println("swagger : " + My_Object.getString("swagger"));
		System.out.println("Info : " + Info_Object);
		System.out.println("Host : " + My_Object.getString("host"));
		System.out.println("Schemes : " + My_Object.getJSONArray("schemes"));
		System.out.println("Base_Path : " + My_Object.getString("basePath"));
		System.out.println("produces : " + My_Object.getJSONArray("produces"));
		System.out.println("Path : " + Path_Object);
		//System.out.println("definitions : " + def_Object);


	}

}
