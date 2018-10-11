package se459GroupProject;

import java.util.HashMap;
import java.util.Map;

public class ScoreKeeperSingleton {
	
	private static ScoreKeeperSingleton instance;
	private Map<String, Integer> teams = new HashMap<>();
	
	private ScoreKeeperSingleton() {} 
	
	private ScoreKeeperSingleton getUniqueInstance() {
		if (instance == null) {
			instance = new ScoreKeeperSingleton();
		}
		return instance;
	}
	
	public void updateScore(String team, int points) {
		int newvalue = getUniqueInstance().teams.get(team).intValue();
		newvalue += points;
		getUniqueInstance().teams.put(team, newvalue);
	}
	
	public int getTeamScore(String team) {
		return getUniqueInstance().teams.get(team).intValue();
	}
	
	public void addTeam(String team) {
		getUniqueInstance().teams.put(team, 0);
	}
}


