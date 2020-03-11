package beans;

public class TaskDetails {
	private String label;
	private int TotalProcessingTime;
	private int TimeForStep1;
	private int TimeForStep2;
	private int TimeForStep3;
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public int getTotalProcessingTime() {
		return TotalProcessingTime;
	}
	
	public void setTotalProcessingTime(int totalProcessingTime) {
		TotalProcessingTime = totalProcessingTime;
	}
	
	public int getTimeForStep1() {
		return TimeForStep1;
	}
	
	public void setTimeForStep1(int timeForStep1) {
		TimeForStep1 = timeForStep1;
	}
	
	public int getTimeForStep2() {
		return TimeForStep2;
	}
	
	public void setTimeForStep2(int timeForStep2) {
		TimeForStep2 = timeForStep2;
	}
	
	public int getTimeForStep3() {
		return TimeForStep3;
	}
	
	public void setTimeForStep3(int timeForStep3) {
		TimeForStep3 = timeForStep3;
	}
	
}
