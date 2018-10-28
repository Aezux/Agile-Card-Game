package game;

import game.backend.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Prototype {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many sprints do you want to play: ");
		int rounds = scanner.nextInt();
		
		System.out.print("How many team members are on this team: ");
		int teamNumber = scanner.nextInt();
		
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i=0; i<teamNumber; ++i) {
			System.out.print("Enter your name: ");
			String name = scanner.next();
			players.add(new Player(name, "team1"));
		}
		
		PointsKeeperSingleton teams = PointsKeeperSingleton.getUniqueInstance();
		teams.addTeam("team1");
		
		System.out.println("Team1's current score is: " + teams.getTeamScore("team1"));
		
		System.out.print("Enter what topic you want to do [animals, sports, mythology]: ");
		String topic = scanner.next();
		
		String catagory = null;
		switch (topic) {
			case "animals": catagory = "27"; break;
			case "sports": catagory = "21"; break;
			case "mythology": catagory = "20"; break;
			default: catagory = null; break;
		}
		
		Question question = new Question();
		Card card = question.getCard(catagory);
		
		System.out.println("question: " + card.getQuestion() + ", worth: " + card.getPoints());
		System.out.println("choices: " + card.getChoices());
		System.out.print("Enter your answer: ");
		String answer = scanner.next();
		
		if (card.getAnswer().equals(answer)) {
			teams.addPointsToScore("team1", card.getPoints());
			System.out.println("Correct, your team earns " + card.getPoints() + " points");
		}
		else {
			System.out.println("Wrong, the correct answer is: " + card.getAnswer() + ", no points are awarded");
		}
		
		System.out.println("The sprint is over, the product owner adds 5 points");
		
		teams.addPointsToScore("team1", 5);
		System.out.println("Your teams total is now: " + teams.getTeamScore("team1"));
		scanner.close();
	}

}
