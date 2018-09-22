package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class API {
	
	// ------------------ Standard for any API ------------------ //
	
	public JSONObject getResponse(String url) throws IOException, JSONException {
		
//		URL obj = new URL(url);
//		HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
		
		URL obj = new URL(url);
		HttpURLConnection conn;
		conn = (HttpURLConnection) obj.openConnection();
		
		int responseCode = conn.getResponseCode();
		
		System.out.println("\nSending 'GET' request to URL :" + url);
		System.out.println("Response Code :" + responseCode);
		
//		System.out.println(conn.getInputStream());
//		InputStreamReader isr = new InputStreamReader(conn.getInputStream());
//		BufferedReader in = new BufferedReader(isr);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();         				// I will need to change this response to a JSON object
		
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		
		JSONObject recievedResponse = new JSONObject(response.toString());   // Here, I'm converting the response to JSON object
		
		return recievedResponse;
		
	}
	
	
	// ------------------ gets URL for fixer API ------------------ //
	
	public String getFixerURL1() {
		
		return "https://api.fixer.io/latest";
		
	}
	
	public String getFixerURL2(String date) {
		
		String url = "https://api.fixer.io/" + date;
		return url;
		
	}
	
	public String getFixerURL3(String base) {
		
		String url = "https://api.fixer.io/latest?base=" + base;
		return url;
		
	}
	
	public String getFixerURL4(String first, String second) {
		
		String url = "https://api.fixer.io/latest?symbols=" + first + "," + second;
		return url;
		
	}
	
	
	// ------------------ prints results from fixer API ------------------ //
	
	public void printFixer(JSONObject recievedResponse) throws JSONException {
		
		System.out.println("\nBase : " + recievedResponse.getString("base"));   // I know the data types of every component of the JSON object from JSON Editor Online
		System.out.println("Date : " + recievedResponse.getString("date"));
		
		JSONObject ratesObject = new JSONObject(recievedResponse.getJSONObject("rates").toString());
		System.out.println("Rates : " + ratesObject);
		
		for (int i = 0; i < ratesObject.length(); i++) {
			
			System.out.println((i+1) + "- " + ratesObject.names().getString(i) + " : " + ratesObject.get(ratesObject.names().getString(i)));
			
		}
		
	}
	
	// ------------------ gets URL for motor racing API ------------------ //
	
	public String getRacingURL(String season) {
			
		String url = "http://ergast.com/api/f1/" + season + "/1/results.json";  // .json is added to obtain a JSON response
		return url;
		
	}
	
	// ------------------ prints results from motor racing API ------------------ //
	
	public void printRacingInfo(JSONObject receivedResponse) throws JSONException {
		
		JSONObject MRData_Object = new JSONObject(receivedResponse.getJSONObject("MRData").toString());
		
		JSONObject RaceTable = MRData_Object.getJSONObject("RaceTable"); 
		
		// Get The Season of the racing
		String season = RaceTable.getString("season");
		System.out.println("\n\nSeason :" + season);
		
		
		String round = RaceTable.getString("round");
		System.out.println("Round : " + round);
		
		JSONArray Races = RaceTable.getJSONArray("Races");
		JSONObject Element_0 = Races.getJSONObject(0);
		String raceName = Element_0.getString("raceName");
		String Date = Element_0.getString("date");
		System.out.println("RaceName : " + raceName);
		System.out.println("Date : " + Date);
		
		JSONObject Circuit = Element_0.getJSONObject("Circuit");
		String circuitName = Circuit.getString("circuitName");
		System.out.println("Circuit_Name : " + circuitName);
		
		JSONObject Location = Circuit.getJSONObject("Location");
		String Long = Location.getString("long");
		String locality = Location.getString("locality");
		String country = Location.getString("country");
		
		System.out.println("Long : " + Long);
		System.out.println("Locality : " + locality);
		System.out.println("country : " + country);
		
		JSONArray Results = Element_0.getJSONArray("Results");
		System.out.println("Position \t" + "Name \t" + "Nationality \t     " + "Num_Of_Laps \t" + "FatestLap \t");
		
		for (int i = 0 ; i < 10 ; i ++) {
			
			// Get the position of the Driver 
			JSONObject Arr_Element = Results.getJSONObject(i);
			String Position = Arr_Element.getString("position");  
			
			// Get the Name And Nationality of the Nationality
			JSONObject Driver = Arr_Element.getJSONObject("Driver");
			String Name = Driver.getString("givenName");
			String Nationality = Driver.getString("nationality");
			
			// Get The Number of Laps in The Racing
			String Laps = Arr_Element.getString("laps");
			
//			// Get FastestLap of the Driver 
//			JSONObject FastestLap = Arr_Element.getJSONObject("FastestLap");
//			String lap = FastestLap.getString("lap");
			
			// fastest lap is not available in all seasons...
//			System.out.println(Position + "\t" +"       " + Name + "\t"+"     " + Nationality + "\t"+ "      " + Laps + "\t" +"        " + lap );
			System.out.println(Position + "\t" +"       " + Name + "\t"+"     " + Nationality + "\t"+ "      " + Laps + "\t" +"        ");

		}
	}

	// ------------------ gets URL for motor Date & Time API ------------------ //
	
	public String getDateAndTime() {
		
		String url = "http://date.jsontest.com";
		return url;
		
	}
	
	// ------------------ prints results from motor racing API ------------------ //
	
	public void printDateAndTime(JSONObject receivedResponse) throws JSONException {
		
//		String[] arr = receivedResponse.getString("time").split(":");         // Another way for splitting the result time
//
//		for (int i = 0; i < 3; i++) {                   // For Debugging -------------------------------
//			System.out.println(arr[i]);
//		}
//		
//		System.out.println("Time : " + (Integer.valueOf(arr[0]) + 2) + ":" + arr[1] + ":" + arr[2]);
		
		String time = receivedResponse.getString("time");
		String hours = time.substring(0, 2);
		String other = time.substring(2, time.length());
	
		System.out.println("Time : " + (Integer.valueOf(hours) + 2) + other);
		
		System.out.println("Date : " + receivedResponse.getString("date"));
		
	}
	
//	public String getOMDbURL() {
//		
//		String url = "http://www.omdbapi.com/?t=Inception&apikey=[fe33282e]";
//		return url;
//	}
//	
//	public void printOMDbInfo() {
//		System.out.println("Movies Done!");
//	}

}
