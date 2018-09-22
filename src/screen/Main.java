package screen;

// Done by: Ahmed M. Galal 20150033 & Mahmoud Mossad Mohammed 20150244
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import api.API;

public class Main {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, JSONException {
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("\n Hey, What do u want??");
		System.out.println("\n 1- Currency \t 2- Racing \t 3- Current Date and Time");
		
		int choice = in.nextInt();
		
		API test = new API();
		
		if (choice == 1) {
			
			System.out.print("\n Available Services:");
			System.out.print("\n 1- Get the latest foreign exchange reference rates. \n 2- Get historical rates for any day since 1999.");
			System.out.println("\n 3- Quote rates against a different currency. \n 4- Request specific exchange rates.");
			
			int service = 0;
			
			service = in.nextInt();
			
			if (service == 1 ) {
				
				String url = test.getFixerURL1();
				JSONObject receivedResponse = test.getResponse(url);
				test.printFixer(receivedResponse);
				
			}
			
			else if (service == 2) {
				
				System.out.println("\n Please enter a date with that format: yyyy-mm-dd (ex: 2005-02-22) ");
				String date = in.next();
				
				String url = test.getFixerURL2(date);
				JSONObject receivedResponse = test.getResponse(url);
				test.printFixer(receivedResponse);
				
			}
			
			else if (service == 3) {
						
				System.out.print("\n Choose the base currency: ");
				System.out.println("\n 1- AUD \t 2- BGN \n 3- USD \t 4- EUR");       // Ektb USD msln msh 3 ... ektb string y3ny :D
				String base = in.next();
				
				String url = test.getFixerURL3(base);
				JSONObject receivedResponse = test.getResponse(url);
				test.printFixer(receivedResponse);
				
			}
						
			else if (service == 4) {
				
				System.out.print("\n Choose the first currency: ");
				System.out.println("\n 1- AUD \t 2- BGN \n 3- USD \t 4- EUR");       // Ektb USD msln msh 3 ... ektb string y3ny
				String first = in.next();
				
				System.out.print("\n Choose the second currency: ");
				System.out.println("\n 1- AUD \t 2- BGN \n 3- USD \t 4- EUR");       // Ektb USD msln msh 3 ... ektb string y3ny
				String second = in.next();
				
				String url = test.getFixerURL4(first, second);
				JSONObject receivedResponse = test.getResponse(url);
				test.printFixer(receivedResponse);
				
			}
						
			else 
				System.out.println("/nNot available yet..");
			
		}
		
		else if (choice == 2) {
			
			System.out.println("\n Enter a season year (ex: 2008)");
			String season = in.next();
			
			String url = test.getRacingURL(season);
			JSONObject receivedResponse = test.getResponse(url);
			test.printRacingInfo(receivedResponse);
			
		}
		
		else if (choice == 3) {
			
			String url = test.getDateAndTime();
			JSONObject receivedResponse = test.getResponse(url);
			test.printDateAndTime(receivedResponse);
			
		}
		
//		else if (choice == 4) {
//			
//			String url = test.getOMDbURL();
//			JSONObject receivedResponse = test.getResponse(url);
//			test.printOMDbInfo();
//			
//		}
		
		else
			System.out.println("\n Wrong Choice!!");

	}
}
