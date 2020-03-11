package parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Role;

public class RoleParser {
	
	public String parse(String oldValue, String role) {
				
		Role[] roles = loadJSON(oldValue);
		
		//check if role already exists in file
		int sum = 0;
		boolean found = false;
		int numberRoles;
		
		if (roles == null) {
			numberRoles = 0;
		} else {
			numberRoles = roles.length;
		}
		
		for(int j = 0; j < numberRoles; j++){
			if (found == false && roles[j].getRole().toLowerCase().equals(role.toLowerCase())){
				roles[j].setCount( roles[j].getCount() + 1 );
				found = true;
			}
			sum+= roles[j].getCount();
		}
		
		Role[] newRoles;
		
		//if not already there create new entry in file
		if(found == false){
			newRoles = new Role[numberRoles+1];
			for(int i = 0; i < numberRoles; i++){
				newRoles[i] = roles[i];
			}
			Role newRole = new Role();
			newRole.setRole(role);
			newRole.setCount(1);
			newRoles[newRoles.length-1] = newRole;
			sum ++;
		} else{
			newRoles = roles;
		}
		
		//Update all percentage values
		double tmp = 0;
		for(int j = 0; j < newRoles.length; j++){
			tmp = 100.0 * newRoles[j].getCount()/sum;
			newRoles[j].setPercentage(Math.round(100.0 * tmp)/100.0);
		}

		return storeJSON(newRoles);
	}
	
	//Create empty general information json
	public String createStory() {
		
		Role[] role = new Role[0];
		
		return storeJSON(role);
	}
	
	//Write role information to JSON file
	public String storeJSON(Role[] roles) {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		String jsonInString = gson.toJson(roles);
		
		return jsonInString;
	}
	
	//load existing JSON file containing information about how often which role appeared into Java Objects
	private Role[] loadJSON(String jsonString){
		
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	      
	    Gson gson = builder.create(); 
	    
	    Role[] roles = gson.fromJson(jsonString, Role[].class);
		
		return roles;
	}

}
