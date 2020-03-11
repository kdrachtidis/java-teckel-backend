package parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Cookie;

public class CookieParser {

	public String parse(String oldValue, String[][] file) {
		
		//Read from csv file if cookies were enabled 
		String bool = "notFound";
		for (int i = 0; i < file.length; i++){
			if (file[i][0].equals("Browser Details")){
				for (int j = 1; j < file[i].length; j++){
					if (file[i][j].equals("Browser Cookies")){
						bool = file[i+1][j].toLowerCase();
						break;
					}
				}
				break;
			}
		}
		
		//Load existing cookie information from the JSON-String
		Cookie[] cookie = loadJSON(oldValue);
		
		//Update particular boolean value by adding 1
		int sum = 0;
		for(int j = 0; j < cookie.length; j++){
			if (cookie[j].getCookie().equals(bool)){
				cookie[j].setCount( cookie[j].getCount() + 1 );
			}
			sum+= cookie[j].getCount();
		}
		
		//Update all percentage values
		double tmp = 0;
		for(int j = 0; j < cookie.length; j++){
			tmp = 100.0 * cookie[j].getCount()/sum;
			cookie[j].setPercentage(Math.round(100.0 * tmp)/100.0);
		}
		
		return storeJSON(cookie);
	}

	//Create empty general information json
	public String createStory() {
		Cookie trueCookie = new Cookie();
		trueCookie.setCookie("true");
		trueCookie.setCount(0);
		trueCookie.setPercentage(0);
		
		Cookie falseCookie = new Cookie();
		falseCookie.setCookie("false");
		falseCookie.setCount(0);
		falseCookie.setPercentage(0);
		
		Cookie[] cookies = {trueCookie, falseCookie};
	
		return storeJSON(cookies);
	}
	
	//Write cookie information to String
	public String storeJSON(Cookie[] cookie) {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		String jsonInString = gson.toJson(cookie);
		
		return jsonInString;
	}
	
	//load JSON-String containing information about how often cookies were enabled into Java Objects
	private Cookie[] loadJSON(String jsonString){
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	      
	    Gson gson = builder.create(); 
	    
	    Cookie[] cookie = gson.fromJson(jsonString, Cookie[].class);
		
		return cookie;
	}
}
