package game;

/* 
 * mythology - 20
 * geography - 22
 * animals - 27
 * sports - 21
 * 
 * */

public class Main {
	public static void main(String args[]) {
		Question question = new Question();
		Card card = question.getCard("27");
		System.out.println("Question: " + card.getQuestion());
		System.out.println("Choices: " + card.getChoices());
		System.out.println("Answer: " + card.getAnswer());
		System.out.println("Points: " + card.getPoints());
	}
}
