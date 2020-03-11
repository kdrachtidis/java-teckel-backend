package parsers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.OS;

public class OsParser {
	public String parse(String oldValue, String[][] file) {

		//Find os in CSV file
		String platform = "notFound";
		for (int i = 0; i < file.length; i++){
			if (file[i][0].equals("Browser Details")){
				for (int j = 1; j < file[i].length; j++){
					if (file[i][j].equals("Browser Platform")){
						platform = file[i+1][j];
						if (platform.indexOf("Mac") != -1){
							platform = "Mac";
						} else if (platform.indexOf("Win") != -1){
							platform = "Windows";
						} else {
							//TODO: Add other Operating Systems like Android, ...
							platform = "Other";
						}
						break;
					}
				}
				break;
			}
		}
		
		//Load existing os information from the JSON file
		OS[] os = loadJSON(oldValue);
		
		//Update particular os value by adding 1
		int sum = 0;
		for(int j = 0; j < os.length; j++){
			if (os[j].getOs().equals(platform)){
				os[j].setCount( os[j].getCount() + 1 );
			}
			sum+= os[j].getCount();
		}
		
		//Update all percentage values
		double tmp = 0;
		for(int j = 0; j < os.length; j++){
			tmp = 100.0 * os[j].getCount()/sum;
			os[j].setPercentage(Math.round(100.0 * tmp)/100.0);
		}
		
		return storeJSON(os);
	}
	
	//Create empty general information json
	public String createStory() {
		OS windows = new OS();
		windows.setOs("Windows");
		windows.setCount(0);
		windows.setPercentage(0);
		
		OS mac = new OS();
		mac.setOs("Mac");
		mac.setCount(0);
		mac.setPercentage(0);
		
		OS iOS = new OS();
		iOS.setOs("iOS");
		iOS.setCount(0);
		iOS.setPercentage(0);
		
		OS android = new OS();
		android.setOs("Android");
		android.setCount(0);
		android.setPercentage(0);
		
		OS[] os = {windows,mac,iOS,android};
		
		return storeJSON(os);
	}
	
	//Write os information to JSON file
	public String storeJSON(OS[] os) {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		String jsonInString = gson.toJson(os);
		
		return jsonInString;
	}
	
	//load existing JSON file containing information about how often which os was used into Java Objects
	private OS[] loadJSON(String jsonString){
		
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	      
	    Gson gson = builder.create(); 
	    
	    OS[] os = gson.fromJson(jsonString, OS[].class);
		
		return os;
	}

}
