package se459GroupProject;

public class Player {
	
	private final String name;
	private String team;
	
	public Player(String name, String team) {
		this.name = name;
		this.team = team;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTeam() {
		return team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
}

