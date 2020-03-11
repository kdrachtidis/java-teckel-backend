package parsers;

public class TimelineParser {
	
	//parse timeline information - not yet done
	public String parse() {
		//TODO: Load Data and refresh it
		
		return "date,close\r\n" + 
				"10,00\r\n" + 
				"9,02\r\n" + 
				"8,05\r\n" + 
				"7,00\r\n" + 
				"6,00\r\n" + 
				"5,07\r\n" + 
				"4,00\r\n" + 
				"3,01\r\n" + 
				"2,05\r\n" + 
				"1,00";
	}
	
	//Create empty timeline information
	public String createStory() {
		
		return "date,close\r\n" + 
				"10,00\r\n" + 
				"9,00\r\n" + 
				"8,00\r\n" + 
				"7,00\r\n" + 
				"6,00\r\n" + 
				"5,00\r\n" + 
				"4,00\r\n" + 
				"3,00\r\n" + 
				"2,00\r\n" + 
				"1,00";
	}
}
