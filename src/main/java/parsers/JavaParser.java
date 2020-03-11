package parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Java;

public class JavaParser {
	
	public String parse(String oldValue, String[][] file) {
		
		//find out if java was enabled in session
		String bool = "notFound";
		for (int i = 0; i < file.length; i++){
			if (file[i][0].equals("Browser Details")){
				for (int j = 1; j < file[i].length; j++){
					if (file[i][j].equals("Java enabled")){
						bool = file[i+1][j].toLowerCase();
						break;
					}
				}
				break;
			}
		}
		
		//Load existing java information from JSON file
		Java[] java = loadJSON(oldValue);
		
		//update particular boolean value by adding 1
		int sum = 0;
		for(int j = 0; j < java.length; j++){
			if (java[j].getJava().equals(bool)){
				java[j].setCount( java[j].getCount() + 1 );
			}
			sum+= java[j].getCount();
		}
		
		//Update all percentage values
		double tmp = 0;
		for(int j = 0; j < java.length; j++){
			tmp = 100.0 * java[j].getCount()/sum;
			java[j].setPercentage(Math.round(100.0 * tmp)/100.0);
		}
		
		return storeJSON(java);
	}
	
	//Create empty general information json
	public String createStory() {
		Java trueJava = new Java();
		trueJava.setJava("true");
		trueJava.setCount(0);
		trueJava.setPercentage(0);
		
		Java falseJava = new Java();
		falseJava.setJava("false");
		falseJava.setCount(0);
		falseJava.setPercentage(0);
		
		Java[] java = {trueJava, falseJava};
		
		return storeJSON(java);
	}
	
	//Write java information to JSON-String
	public String storeJSON(Java[] java) {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		String jsonInString = gson.toJson(java);
		
		return jsonInString;
	}
	
	//load existing JSON-String containing information about if java was enabled into Java Objects
	private Java[] loadJSON(String jsonString){
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	      
	    Gson gson = builder.create(); 
	    
	    Java[] java = gson.fromJson(jsonString, Java[].class);
		
		return java;
	}
}
