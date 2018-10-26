package tests.java.game.backend;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.java.game.backend.PointsKeeperSingleton;

class PointsKeeperSingletonTest {

	@Test
	void testInitialization() {
		PointsKeeperSingleton.getUniqueInstance().addTeam("team1");
		assertTrue(PointsKeeperSingleton.getUniqueInstance().getTeamScore("team1") == 0);
	}
	
	@Test
	void testHashMap() {
		PointsKeeperSingleton.getUniqueInstance().addTeam("team1");
		PointsKeeperSingleton.getUniqueInstance().addTeam("team2");
		PointsKeeperSingleton.getUniqueInstance().addTeam("team3");
		PointsKeeperSingleton.getUniqueInstance().addTeam("team4");
		
		PointsKeeperSingleton.getUniqueInstance().addPointsToScore("team1", 20);
		PointsKeeperSingleton.getUniqueInstance().addPointsToScore("team2", 60);
		PointsKeeperSingleton.getUniqueInstance().addPointsToScore("team3", 30);
		PointsKeeperSingleton.getUniqueInstance().addPointsToScore("team4", 90);
		PointsKeeperSingleton.getUniqueInstance().addPointsToScore("team1", 20);
		PointsKeeperSingleton.getUniqueInstance().removePointsFromScore("team1", 10);
		PointsKeeperSingleton.getUniqueInstance().removePointsFromScore("team4", 10);
		
		assertTrue(PointsKeeperSingleton.getUniqueInstance().getTeamScore("team1") == 30);
		assertTrue(PointsKeeperSingleton.getUniqueInstance().getTeamScore("team2") == 60);
		assertTrue(PointsKeeperSingleton.getUniqueInstance().getTeamScore("team3") == 30);
		assertTrue(PointsKeeperSingleton.getUniqueInstance().getTeamScore("team4") == 80);
	}
	
	@Test
	void testWinner() {
		PointsKeeperSingleton.getUniqueInstance().addTeam("team1");
		PointsKeeperSingleton.getUniqueInstance().addTeam("team2");
		PointsKeeperSingleton.getUniqueInstance().addTeam("team3");
		PointsKeeperSingleton.getUniqueInstance().addTeam("team4");
		
		PointsKeeperSingleton.getUniqueInstance().addPointsToScore("team1", 20);
		PointsKeeperSingleton.getUniqueInstance().addPointsToScore("team2", 60);
		PointsKeeperSingleton.getUniqueInstance().addPointsToScore("team3", 30);
		PointsKeeperSingleton.getUniqueInstance().addPointsToScore("team4", 90);
		
		assertTrue(PointsKeeperSingleton.getUniqueInstance().getWinner().equals("team1"));
		
		PointsKeeperSingleton.getUniqueInstance().removePointsFromScore("team4", 85);
		
		assertTrue(PointsKeeperSingleton.getUniqueInstance().getWinner().equals("team4"));
	}
}

