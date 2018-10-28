package game.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Question {
	String catagory;
	int points;
	
	Random rand = new Random();
	
	/* Constructor */
	public Question() {
		points = 10;
	}
	
	/* Gives the user a card */
	public Card getCard(String catagory) {
		if (catagory.length() == 0) return new Card();
		String results = triviaResults(catagory, generateDifficulty());
		return makeCard(results, catagory);
	}
	
	private Card makeCard(String result, String category) {
		
		/* Store the points for the card and set the global variable back to the original value */
		int cardPoints = points;
		points = 10;
		
		/* Parses for the question */
		int questionStart = result.indexOf("question") + 11;
		int questionEnd = result.indexOf("correct_answer", questionStart) - 3;
		String question = result.substring(questionStart, questionEnd);
		
		/* Parses for the answer */
		int answerStart = result.indexOf("correct_answer") + 17;
		int answerEnd = result.indexOf("incorrect_answers", answerStart) - 3;
		String answer = result.substring(answerStart, answerEnd);
		
		/* Parses for the choices and randomly places the answer within them */
		int choiceStart = result.indexOf("incorrect_answers") + 20;
		int choiceEnd = result.indexOf("]", choiceStart);
		String[] choices = ((result.substring(choiceStart, choiceEnd)).replace("\"", "")).split(",");
		
		/* Converts the array to an Arraylist */
		List<String> list = new ArrayList<>();
		list.add(answer);
		for (String x : choices) {
			list.add(x);
		}
		
		/* Shuffles the list */
		Collections.shuffle(list);
		String choice = "" + list.get(0) + ", " + list.get(1) + ", " + list.get(2) + ", " + list.get(3);
		
		return new Card(question, answer, choice, cardPoints, category);
	}
	
	
	/* Calls the API and gets back a question */
	private String triviaResults(String catagory, String difficulty) {
		String question = null;
		
		try {
			/* Puts together the url */
			String api_url = "https://opentdb.com/api.php?amount=1&category=" + catagory + "&difficulty=" + difficulty + "&type=multiple";
			URL url = new URL(api_url);

			/* Get the data from the url */
	        URLConnection connection = url.openConnection();
	        InputStream inputStream = connection.getInputStream();
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	        question = bufferedReader.readLine();

		} catch (IOException e) {
			question = null;
		}
		
		return question;		
	}
	
	/* Randomly assigns a difficulty */
	private String generateDifficulty() {
		String difficulty = null;
		
		/* Generate a number from 1 to 3 and assign difficulty */
		int num = getRandomNumber(3) + 1;
		switch(num) {
			case 1: difficulty = "easy"; break;
			case 2: difficulty = "medium"; break;
			case 3: difficulty = "hard"; break;
		}
		points *= num;
		return difficulty;
	}
	
	/* Generates a number from 0 to maxRandom */
	private int getRandomNumber(int maxRandom) {
		return rand.nextInt(maxRandom);
	}
	
}
