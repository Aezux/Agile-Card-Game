package main.java.game.backend;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * A singleton object that acts as a single point of
 * reference for each team's points total.
 *
 */
public class PointsKeeperSingleton {
	
	private static PointsKeeperSingleton instance;
	private Map<String, Integer> teams;
	
	static {
		instance = new PointsKeeperSingleton();
	}
	
	private PointsKeeperSingleton() {
		teams = new HashMap<>();
	} 
	
	public static PointsKeeperSingleton getUniqueInstance() {
		return instance;
	}
	
	public void addPointsToScore(String team, int points) {
		int newvalue = teams.get(team).intValue() + points;
		teams.put(team, newvalue);
	}
	
	public void removePointsFromScore(String team, int points) {
		int newvalue = teams.get(team).intValue() - points;
		teams.put(team, newvalue);
	}
	
	public int getTeamScore(String team) {
		return teams.get(team).intValue();
	}
	
	public void addTeam(String team) {
		teams.put(team, 0);
	}
}


