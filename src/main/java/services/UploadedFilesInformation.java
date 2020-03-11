package services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import hana.HANAConnector;
import parsers.ActionParser;
import parsers.BrowserParser;
import parsers.CookieParser;
import parsers.ExperienceParser;
import parsers.GeneralParser;
import parsers.JavaParser;
import parsers.OsParser;
import parsers.RoleParser;
import parsers.ScreenSizeParser;
import parsers.SessionParser;
import parsers.UploadedFilesParser;

@Path("uploadedFiles")
public class UploadedFilesInformation extends Application{
	
	private HANAConnector hanaConnector;
	
	public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(MultiPartFeature.class);
        return resources;
    }
	
	@GET
    @Path("{story}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getFiles(@PathParam("story") String story) {
    	
    	hanaConnector = new HANAConnector();
    	String value = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'UploadedFiles' AND STORY = '" + story + "'");
    	if (value != null) {
    		return Response.ok(value).header("Access-Control-Allow-Origin", "*").build();
    	}else {
    		return Response.noContent().header("Acces-Control-Allow-Origin", "*").build();
    	}
    	
    }
	
	@POST 
	@Path("{story}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)  
    public Response uploadFile(  
    	@PathParam("story") String story,
        @FormDataParam("__xmlview0--fileUploader") InputStream uploadedInputStream0,
        @FormDataParam("__xmlview1--fileUploader") InputStream uploadedInputStream1,
        @FormDataParam("__xmlview2--fileUploader") InputStream uploadedInputStream2,
        @FormDataParam("__xmlview3--fileUploader") InputStream uploadedInputStream3,
        @FormDataParam("__xmlview4--fileUploader") InputStream uploadedInputStream4,
        @FormDataParam("__xmlview5--fileUploader") InputStream uploadedInputStream5,
        @FormDataParam("__xmlview6--fileUploader") InputStream uploadedInputStream6,
        @FormDataParam("__xmlview7--fileUploader") InputStream uploadedInputStream7,
        @FormDataParam("__xmlview8--fileUploader") InputStream uploadedInputStream8,
        @FormDataParam("__xmlview9--fileUploader") InputStream uploadedInputStream9,
        @FormDataParam("__xmlview10--fileUploader") InputStream uploadedInputStream10,
        @FormDataParam("__xmlview11--fileUploader") InputStream uploadedInputStream11,
        @FormDataParam("name") String name,
        @FormDataParam("role") String role,
        @FormDataParam("experience") String experience) {  
		
		String newSession;
		if(uploadedInputStream0 != null) {
    		newSession = readPost(uploadedInputStream0);
    	}else if (uploadedInputStream1 != null) {
    		newSession = readPost(uploadedInputStream1);
    	}else if (uploadedInputStream2 != null) {
    		newSession = readPost(uploadedInputStream2);
    	}else if (uploadedInputStream3 != null) {
    		newSession = readPost(uploadedInputStream3);
    	}else if (uploadedInputStream4 != null) {
    		newSession = readPost(uploadedInputStream4);
    	}else if (uploadedInputStream5 != null) {
    		newSession = readPost(uploadedInputStream5);
    	}else if (uploadedInputStream6 != null) {
    		newSession = readPost(uploadedInputStream6);
    	}else if (uploadedInputStream7 != null) {
    		newSession = readPost(uploadedInputStream7);
    	}else if (uploadedInputStream8 != null) {
    		newSession = readPost(uploadedInputStream8);
    	}else if (uploadedInputStream9 != null) {
    		newSession = readPost(uploadedInputStream9);
    	}else if (uploadedInputStream10 != null) {
    		newSession = readPost(uploadedInputStream10);
    	}else if (uploadedInputStream11 != null) {
    		newSession = readPost(uploadedInputStream11);
    	}else {
    		return Response.status(400).entity("no data found").header("Access-Control-Allow-Origin", "*").build();
    	}
		
		try {
		
		//Update UploadedFiles Information
		hanaConnector = new HANAConnector();
		String oldValue = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'UploadedFiles' AND STORY = '" + story + "'");
		UploadedFilesParser uploadedFilesParser = new UploadedFilesParser();
    	String newValue = uploadedFilesParser.parse(oldValue,name,role,experience);
    	hanaConnector.executeQuery("UPDATE " + HANAConnector.tableName + " SET VALUE = '" + newValue + "' WHERE ID = 'UploadedFiles' AND STORY = '" + story + "'");
    	
    	//Read CSV into String[][]
    	String[][]file = readCSV(newSession,";");
    	
    	//Update Browser Information
    	oldValue = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'Browser' AND STORY = '" + story + "'");
    	BrowserParser browserParser = new BrowserParser();
    	newValue = browserParser.parse(oldValue, file);
    	hanaConnector.executeQuery("UPDATE " + HANAConnector.tableName + " SET VALUE = '" + newValue + "' WHERE ID = 'Browser' AND STORY = '" + story + "'");
    	
    	//Update Cookie Information
    	oldValue = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'Cookie' AND STORY = '" + story + "'");
    	CookieParser cookieParser = new CookieParser();
    	newValue = cookieParser.parse(oldValue, file);
    	hanaConnector.executeQuery("UPDATE " + HANAConnector.tableName + " SET VALUE = '" + newValue + "' WHERE ID = 'Cookie' AND STORY = '" + story + "'");
    	
    	//Update Experience Information
    	oldValue = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'Experience' AND STORY = '" + story + "'");
    	ExperienceParser experienceParser = new ExperienceParser();
    	newValue = experienceParser.parse(oldValue, name, experience);
    	hanaConnector.executeQuery("UPDATE " + HANAConnector.tableName + " SET VALUE = '" + newValue + "' WHERE ID = 'Experience' AND STORY = '" + story + "'");
    	
    	//Update General Information
    	oldValue = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'General' AND STORY = '" + story + "'");
    	GeneralParser generalParser = new GeneralParser();
    	newValue = generalParser.parse(oldValue, file);
    	hanaConnector.executeQuery("UPDATE " + HANAConnector.tableName + " SET VALUE = '" + newValue + "' WHERE ID = 'General' AND STORY = '" + story + "'");
    	
    	//Update Java Information
    	oldValue = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'Java' AND STORY = '" + story + "'");
    	JavaParser javaParser = new JavaParser();
    	newValue = javaParser.parse(oldValue, file);
    	hanaConnector.executeQuery("UPDATE " + HANAConnector.tableName + " SET VALUE = '" + newValue + "' WHERE ID = 'Java' AND STORY = '" + story + "'");
    	
    	//Update Os Information
    	oldValue = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'OS' AND STORY = '" + story + "'");
    	OsParser osParser = new OsParser();
    	newValue = osParser.parse(oldValue, file);
    	hanaConnector.executeQuery("UPDATE " + HANAConnector.tableName + " SET VALUE = '" + newValue + "' WHERE ID = 'OS' AND STORY = '" + story + "'");
    	
		//Update Session Information
		oldValue = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'Sessions' AND STORY = '" + story + "'");
		SessionParser sessionParser = new SessionParser();
		newValue = sessionParser.parse(oldValue, name, file);
		hanaConnector.executeQuery("UPDATE " + HANAConnector.tableName + " SET VALUE = '" + newValue + "' WHERE ID = 'Sessions' AND STORY = '" + story + "'");
    	
    	//Update Role Information
    	oldValue = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'Role' AND STORY = '" + story + "'");
    	RoleParser roleParser = new RoleParser();
    	newValue = roleParser.parse(oldValue, role);
    	hanaConnector.executeQuery("UPDATE " + HANAConnector.tableName + " SET VALUE = '" + newValue + "' WHERE ID = 'Role' AND STORY = '" + story + "'");
    	
    	//Update ScreenSize Information
    	oldValue = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'ScreenSize' AND STORY = '" + story + "'");
    	ScreenSizeParser screenSizeParser = new ScreenSizeParser();
    	newValue = screenSizeParser.parse(oldValue, file);
    	hanaConnector.executeQuery("UPDATE " + HANAConnector.tableName + " SET VALUE = '" + newValue + "' WHERE ID = 'ScreenSize' AND STORY = '" + story + "'");
    	
    	//Update Action Information
    	oldValue = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'Actions' AND STORY = '" + story + "'");
	    ActionParser actionParser = new ActionParser();
	    newValue = actionParser.parse(oldValue, name, file);
	    hanaConnector.executeQuery("UPDATE " + HANAConnector.tableName + " SET VALUE = '" + newValue + "' WHERE ID = 'Actions' AND STORY = '" + story + "'");
    	
    	//Update Timeline Information
    	//TODO: Implement
    	
		} catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).header("Access-Control-Allow-Origin", "*").build(); 
		}
		
		return Response.status(200).entity(newSession).header("Access-Control-Allow-Origin", "*").build(); 
	}
    
	@OPTIONS
	@Path("{story}")
	public Response getOptions() {
      return Response.ok()
    		  .header("Access-Control-Allow-Origin","*")
    		  .header("Access-Control-Allow-Methods","GET, POST, DELETE, OPTIONS")
    		  .header("Access-Control-Allow-Headers","Origin, Content-Type, X-Auth-Token").build();
    }
    
    private String readPost(InputStream stream) {
    	String result = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));
        return result;
    }
    
    public String[][] readCSV(String file, String split){
        
        //Split String into 2D String Array
        String[] temp = file.split("\n");
        String[][] fileArray = new String[temp.length][];
        
		for (int i = 0; i < temp.length; i++){
			fileArray[i] = temp[i].split(split);
		}
		
		return fileArray;
	}
}
