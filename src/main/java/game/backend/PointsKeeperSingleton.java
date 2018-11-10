package game.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javafx.stage.Stage;

/**
 * 
 * A singleton object that acts as a single point of
 * reference for each team's points total.
 *
 */
public class PointsKeeperSingleton {
	
	private final int startingPoints1 = 200;
	private static PointsKeeperSingleton instance;
	private Map<String, Integer> teams;
	private boolean team1Turn = true;
	
	private int playersPerTeam;
	//An arrayList of string arrayLists, so that the first array is team1 and the second is team 2
	private ArrayList<ArrayList<String>> teamPlayers = new ArrayList<ArrayList<String>>();
	
	static {
		instance = new PointsKeeperSingleton();
	}
	
	private PointsKeeperSingleton() {
		teams = new HashMap<>();
	} 
	
	public static PointsKeeperSingleton getUniqueInstance() {
		return instance;
	}

	public void addPlayerToTeam(int team, String player){
		teamPlayers.get(team).add(player);
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
			return startingPoints1;
		}
		return teams.get(team).intValue();
	}
	
	public void addTeam(String team) {
		teams.put(team, startingPoints1);
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
	
	public void setPlayersPerTeam(int num) {
		this.playersPerTeam = num;
	}
	
	//Get the number of players per team
	public int getPlayersPerTeam() {
		return playersPerTeam;
	}
	
	public ArrayList<ArrayList<String>> getTeams(){
		return this.teamPlayers;
	}
	
	public int getCurrentSizeOfTeam(int teamIndex) {
		return teamPlayers.get(teamIndex).size();
	}
	
	public boolean checkBothTeamsEmpty() {
//		if (teamPlayers.isEmpty()) {
//			return true;
//		}
		if (teamPlayers.get(0).isEmpty() && teamPlayers.get(1).isEmpty()) {
			return true;
		}
		else return false;
	}
	
	//checks to see if it is team1's turn
	public boolean checkIfTeam1Turn() {
		return this.team1Turn;
	}
	
	public void changeTurns() {
		this.team1Turn = !team1Turn;
	}
	
}


