package beans;

public class Detail {
	private String label;
	private int totalProcessingTime;
	private int timeForAction1;
	private int timeForAction2;
	private int timeForAction3;
	private int timeForAction4;
	private int timeForAction5;
	private int timeForAction6;
	private int timeForAction7;
	private int timeForAction8;
	private int timeForAction9;
	private int timeForAction10;
	
	public Detail() {}
	
	public Detail(Detail uc) {
		this.label = uc.label;
		this.totalProcessingTime = uc.totalProcessingTime;
		this.timeForAction1 = uc.timeForAction1;
		this.timeForAction2 = uc.timeForAction2;
		this.timeForAction3 = uc.timeForAction3;
		this.timeForAction4 = uc.timeForAction4;
		this.timeForAction5 = uc.timeForAction5;
		this.timeForAction6 = uc.timeForAction6;
		this.timeForAction7 = uc.timeForAction7;
		this.timeForAction8 = uc.timeForAction8;
		this.timeForAction9 = uc.timeForAction9;
		this.timeForAction10 = uc.timeForAction10;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getTotalProcessingTime() {
		return totalProcessingTime;
	}
	public void setTotalProcessingTime(int totalProcessingTime) {
		this.totalProcessingTime = totalProcessingTime;
	}
	public int getTimeForAction1() {
		return timeForAction1;
	}
	public void setTimeForAction1(int timeForAction1) {
		this.timeForAction1 = timeForAction1;
	}
	public int getTimeForAction2() {
		return timeForAction2;
	}
	public void setTimeForAction2(int timeForAction2) {
		this.timeForAction2 = timeForAction2;
	}
	public int getTimeForAction3() {
		return timeForAction3;
	}
	public void setTimeForAction3(int timeForAction3) {
		this.timeForAction3 = timeForAction3;
	}
	public int getTimeForAction4() {
		return timeForAction4;
	}
	public void setTimeForAction4(int timeForAction4) {
		this.timeForAction4 = timeForAction4;
	}
	public int getTimeForAction5() {
		return timeForAction5;
	}
	public void setTimeForAction5(int timeForAction5) {
		this.timeForAction5 = timeForAction5;
	}
	public int getTimeForAction6() {
		return timeForAction6;
	}
	public void setTimeForAction6(int timeForAction6) {
		this.timeForAction6 = timeForAction6;
	}
	public int getTimeForAction7() {
		return timeForAction7;
	}
	public void setTimeForAction7(int timeForAction7) {
		this.timeForAction7 = timeForAction7;
	}
	public int getTimeForAction8() {
		return timeForAction8;
	}
	public void setTimeForAction8(int timeForAction8) {
		this.timeForAction8 = timeForAction8;
	}
	public int getTimeForAction9() {
		return timeForAction9;
	}
	public void setTimeForAction9(int timeForAction9) {
		this.timeForAction9 = timeForAction9;
	}
	public int getTimeForAction10() {
		return timeForAction10;
	}
	public void setTimeForAction10(int timeForAction10) {
		this.timeForAction10 = timeForAction10;
	}
	
	public void setToNull() {
		timeForAction1 = 0;
		timeForAction2 = 0;
		timeForAction3 = 0;
		timeForAction4 = 0;
		timeForAction5 = 0;
		timeForAction6 = 0;
		timeForAction7 = 0;
		timeForAction8 = 0;
		timeForAction9 = 0;
		timeForAction10 = 0;
	}
		
}
