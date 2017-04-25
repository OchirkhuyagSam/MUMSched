package mum.sched;

public enum UserRole {
	ADMIN("admin"),
	FACULTY("faculty"),
	STUDENT("student");
	
	private final String role;
	
	private UserRole(final String role) {
		this.role = role;
	}
	
	public String toString() {
		return role;
	}
}
