package hana;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class JSONStringConnector {
	
	private PrintWriter writer = null;
	private BufferedReader br = null;
	
	//Read JSON File to String
	public String readString(String path){
        String line = "";
        String file = "";
        
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
            	file += line;
            }
        } catch (Exception e) {
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        //remove all spaces
        file = file.replaceAll("\\s+","");
        
		return file;
	}
	
	//Write String to a JSON  file at specified path
	public void writeString(String string, String path){
		
        try {
			writer = new PrintWriter(path, "UTF-8");
			writer.println(string);
			writer.close();
		} catch (Exception e) {
			System.out.println("File: "+path+" couldn't be updated! (Is it maybe opened?)");
		} 
	}
	
}
