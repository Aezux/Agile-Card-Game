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
	String token;
	int points;
	
	Random rand = new Random();
	
	/* Constructor */
	public Question(String token) {
		this.token = token;
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
		
		StringBuffer bufferedResult = new StringBuffer(result);
		
		/* Parses for the question */
		int questionStart = bufferedResult.indexOf("question") + 11;
		int questionEnd = bufferedResult.indexOf("correct_answer", questionStart) - 3;
		String question = bufferedResult.substring(questionStart, questionEnd);
		
		/* Parses for the answer */
		int answerStart = bufferedResult.indexOf("correct_answer") + 17;
		int answerEnd = bufferedResult.indexOf("incorrect_answers", answerStart) - 3;
		String answer = bufferedResult.substring(answerStart, answerEnd);
		
		/* Parses for the choices and randomly places the answer within them */
		int choiceStart = bufferedResult.indexOf("incorrect_answers") + 20;
		int choiceEnd = bufferedResult.indexOf("]", choiceStart);

		String[] choices = bufferedResult.substring(choiceStart, choiceEnd).replace("\"", "").split(",");
		
		/* Converts the array to an ArrayList */
		List<String> list = new ArrayList<>();
		list.add(answer);
		for (String x : choices) {
			list.add(x);
		}
		
		/* Shuffles the list */
		Collections.shuffle(list);
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(list.get(0));
		stringBuilder.append(", ");
		stringBuilder.append(list.get(1));
		stringBuilder.append(", ");
		stringBuilder.append(list.get(2));
		stringBuilder.append(", ");
		stringBuilder.append(list.get(3));
		
		String choice = stringBuilder.toString();
		
		return new Card(question, answer, choice, cardPoints, category);
	}
	
	/* Calls the API and gets back a question */
	private String triviaResults(String catagory, String difficulty) {
		String question = null;
		
		try {
			/* Puts together the url */
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("https://opentdb.com/api.php?amount=1&token=");
			stringBuilder.append(token);
			stringBuilder.append("&category=");
			stringBuilder.append(catagory);
			stringBuilder.append("&difficulty=");
			stringBuilder.append(difficulty);
			stringBuilder.append("&type=multiple");
			
			String api_url = stringBuilder.toString();
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
