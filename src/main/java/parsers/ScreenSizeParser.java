package parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Screen;

public class ScreenSizeParser {
	
	public String parse(String oldValue, String[][] file) {
		//Find screen size in CSV file
		String screenWidth = "notFound";
		for (int i = 0; i < file.length; i++){
			if (file[i][0].equals("Screen details")){
				for (int j = 1; j < file[i].length; j++){
					if (file[i][j].equals("Screen width")){
						Integer tmp = Integer.parseInt(file[i+1][j]);
						if (tmp < 600){
							screenWidth = "Phone";
						} else if (tmp < 1024){
							screenWidth = "Tablet";
						} else if (tmp < 1440){
							screenWidth = "Desktop";
						} else {
							screenWidth = "LargeDesktop";
						}
						break;
					}
				}
				break;
			}
		}
		
		//Load existing screen size information from the JSON-String
		Screen[] screen = loadJSON(oldValue);
		
		//Update particular browser value by adding 1
		int sum = 0;
		for(int j = 0; j < screen.length; j++){
			if (screen[j].getScreenWidth().equals(screenWidth)){
				screen[j].setCount( screen[j].getCount() + 1 );
			}
			sum+= screen[j].getCount();
		}
		
		//Update all percentage values
		double tmp = 0;
		for(int j = 0; j < screen.length; j++){
			tmp = 100.0 * screen[j].getCount()/sum;
			screen[j].setPercentage(Math.round(100.0 * tmp)/100.0);
		}
		
		return storeJSON(screen);
	}
	
	//Create empty general information json
	public String createStory() {
		Screen phone = new Screen();
		phone.setScreenWidth("Phone");
		phone.setCount(0);
		phone.setPercentage(0);
		
		Screen tablet = new Screen();
		tablet.setScreenWidth("Tablet");
		tablet.setCount(0);
		tablet.setPercentage(0);
		
		Screen desktop = new Screen();
		desktop.setScreenWidth("Desktop");
		desktop.setCount(0);
		desktop.setPercentage(0);
		
		Screen large = new Screen();
		large.setScreenWidth("LargeDesktop");
		large.setCount(0);
		large.setPercentage(0);
		
		Screen[] screens = {phone,tablet,desktop,large};
		
		return storeJSON(screens);
	}
	
	//Write screen size information to JSON-String
	public String storeJSON(Screen[] screen) {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		String jsonInString = gson.toJson(screen);
		
		return jsonInString;
	}
	
	//load existing JSON-String containing information about how often which screen size appeared into Java Objects
	private Screen[] loadJSON(String jsonString){
		
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	      
	    Gson gson = builder.create(); 
	    
	    Screen[] screen = gson.fromJson(jsonString, Screen[].class);
		
		return screen;
	}
}
