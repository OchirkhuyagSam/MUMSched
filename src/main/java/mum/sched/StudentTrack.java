package mum.sched;

public enum StudentTrack {
	
	MPP("mpp"), FPP("fpp");
	private final String type;
	
	private StudentTrack(final String type) {
		this.type = type;
	}
	
	public String toString() {
		return type;
	}
}
