package parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.General;

public class GeneralParser {
	
	public String parse(String oldValue, String[][] file){
		//compute total Time of the session
		double spentTime = (Double.parseDouble(file[file.length-1][2]) - Double.parseDouble(file[5][2]))/3600.0;
		
		General general = loadJSON(oldValue);
		
		general.setTimeCount(general.getTimeCount() + spentTime);
		general.setSessionCount(general.getSessionCount() + 1);
		
		return storeJSON(general);
	}
	
	//Create empty general information json
	public String createStory(String eventName, String eventLocation) {
		General general = new General();
		general.setSessionCount(0);
		general.setTimeCount(0);
		general.setEventName(eventName);
		general.setEventLocation(eventLocation);
		
		return storeJSON(general);
	}
	
	//Write general information to JSON-String
	public String storeJSON(General general) {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		String jsonInString = gson.toJson(general);
		
		return jsonInString;
	}
	
	//load existing JSON-String containing general information into Java Objects
	private General loadJSON(String jsonString){
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	      
	    Gson gson = builder.create(); 
	    
	    General general = gson.fromJson(jsonString, General.class);
		
		return general;
	}
}
