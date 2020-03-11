package beans;

public class Overview {
	private String useCase;
	private int duration;
	
	public Overview() {}
	
	public Overview(Overview uc) {
		this.useCase = uc.useCase;
		this.duration = uc.duration;
	}
	
	public String getUseCase() {
		return useCase;
	}
	public void setUseCase(String useCase) {
		this.useCase = useCase;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
}
