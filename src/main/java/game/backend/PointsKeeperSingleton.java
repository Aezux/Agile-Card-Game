package game.backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * A singleton object that acts as a single point of
 * reference for each team's points total.
 *
 */
public class PointsKeeperSingleton {
	
	private final int startingPoints = 200;
	private static PointsKeeperSingleton instance;
	private Map<String, Integer> teams;
	private Map<String, Player> teamPlayers = new HashMap<>();
	
	static {
		instance = new PointsKeeperSingleton();
	}
	
	private PointsKeeperSingleton() {
		teams = new HashMap<>();
	} 
	
	public static PointsKeeperSingleton getUniqueInstance() {
		return instance;
	}

	public void addPlayerToTeam(String team, Player player){
		teamPlayers.put(team, player);
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
		if (getWinner().equals("None")) {
			return startingPoints;
		}
		return teams.get(team).intValue();
	}
	
	public void addTeam(String team) {
		teams.put(team, startingPoints);
	}
	
	/**
	 * Returns the team name with the lowest points total
	 * 
	 * @return String
	 */
	public String getWinner() {
		if (teams.isEmpty()) return "None";
		
		Set<String> keyset = teams.keySet();
		String winner = keyset.toArray()[0].toString();
		int min = teams.get(winner).intValue();
		
		for (Entry<String, Integer> entry : teams.entrySet()) {
			if (entry.getValue() < min) {
				min = entry.getValue();
				winner = entry.getKey();
			}
		}
		return winner;
	}
}

