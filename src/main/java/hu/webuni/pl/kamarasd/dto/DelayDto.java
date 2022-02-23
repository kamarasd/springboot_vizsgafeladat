package hu.webuni.pl.kamarasd.dto;

public class DelayDto {
	
	private long milestoneId;
	private int delay;
	
	public DelayDto(long milestoneId, int delay) {
		this.milestoneId = milestoneId;
		this.delay = delay;
	}
	
	public long getMilestoneId() {
		return milestoneId;
	}
	
	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}
	
	public int getDelay() {
		return delay;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	
	

}
