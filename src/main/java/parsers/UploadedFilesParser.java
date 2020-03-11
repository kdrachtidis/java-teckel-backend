package parsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.File;
import beans.UploadedFile;

public class UploadedFilesParser {
	
	public String parse (String oldSessionsString, String name, String role, String experience) {
		File oldSessions = loadJSON(oldSessionsString);
		File newSessions = new File();
		UploadedFile[] newFiles;
		//append information about current session to existing json file
		
		if(oldSessions.getFiles() != null) {
			newFiles = new UploadedFile[oldSessions.getFiles().length + 1];
			for (int i = 0; i < oldSessions.getFiles().length; i++){
				newFiles[i] = oldSessions.getFiles()[i];
			}
		}else {
			newFiles = new UploadedFile[1];
		}
		
		newFiles[newFiles.length-1] = createNew(newFiles.length,name,role,experience);
		
		newSessions.setFiles(newFiles);
		
		String newSessionsString = storeJSON(newSessions);
		return newSessionsString;
	}
	
	//Create empty general information json
	public String createStory() {
		
		File files = new File();
		UploadedFile[] file = new UploadedFile[0];
		files.setFiles(file);
		
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		String jsonInString = gson.toJson(files);
		
		return jsonInString;
	}
	
	//Write uploaded files to JSON file
	public String storeJSON(File newFiles) {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		String jsonInString = gson.toJson(newFiles);
		
		return jsonInString;
	}
	
	//load existing JSON file containing information about uploaded files into Java Objects
	private File loadJSON(String jsonString){
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	    
	    Gson gson = builder.create(); 
	    File files = gson.fromJson(jsonString, File.class);
		
		return files;
	}
	
	private UploadedFile createNew(int documentId, String fileName, String role, String experience){
		
		UploadedFile file = new UploadedFile();
		
		file.setSessionId(Integer.toString(documentId));
		file.setSessionName(fileName);
		file.setSessionRole(role);
		file.setSessionExperience(experience);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		file.setUploadedOn(dtf.format(localDate));
		
		return file;
	}

}
