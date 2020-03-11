package parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Experience;
import hana.JSONStringConnector;

public class ExperienceParser {
	JSONStringConnector jsonStringConnector;
	
	public String parse(String oldValue, String sessionName, String years){
		//get existing experiences
		Experience[] oldExperiences = loadJSON(oldValue);
		Experience[] newExperiences;
		
		//enhance or initialize experience information array
		if(oldExperiences != null){
			newExperiences = new Experience[oldExperiences.length+1];
			for(int i=0; i<oldExperiences.length;i++){
				newExperiences[i] = oldExperiences[i];
			}
		}else{
			newExperiences = new Experience[1];
		}
		
		//create new object for the current session
		Experience newExperience = new Experience();
		newExperience.setSession(sessionName);
		newExperience.setYears(Integer.valueOf(years));
		newExperiences[newExperiences.length - 1] = newExperience;
		
		return storeJSON(newExperiences);
		
	}
	
	//Create empty general information JSON
	public String createStory() {
		Experience[] exp = new Experience[0];
		return storeJSON(exp);
	}
	
	//Write experience information to JSON-String
	private String storeJSON(Experience[] experiences) {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		String jsonInString = gson.toJson(experiences);
		
		return jsonInString;
	}
	
	//load existing JSON-String containing information about participants experience into Java Objects
	private Experience[] loadJSON(String jsonString){
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	      
	    Gson gson = builder.create(); 
	    
	    Experience[] experiences = gson.fromJson(jsonString, Experience[].class);
		
		return experiences;
	}
}
