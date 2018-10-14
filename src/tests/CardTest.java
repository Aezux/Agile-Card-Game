package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.Card;

/*  */
public class CardTest {
	Card emptyCard = new Card();
	Card correctCard = new Card("What city does Batman live in?", "A", "A) Gotham, B) New York, C) Wakanda, D) Asgard", 1, "Pop Culture");
	Card noQuestionCard = new Card("", "A", "A) Gotham, B) New York, C) Wakanda, D) Asgard", 1, "Pop Culture");
	Card noAnswerCard = new Card("What city does Batman live in?", "", "A) Gotham, B) New York, C) Wakanda, D) Asgard", 1, "Pop Culture");
	Card noChoicesCard = new Card("What city does Batman live in?", "A", "", 1, "Pop Culture");
	Card noPointsCard = new Card("What city does Batman live in?", "A", "A) Gotham, B) New York, C) Wakanda, D) Asgard", 0, "Pop Culture");

	@Test
	/* Testing the card that is empty */
	void emptyCard() {
		assertEquals(emptyCard.getQuestion(), null);
		assertEquals(emptyCard.getAnswer(), null);
		assertEquals(emptyCard.getChoices(), null);
		assertEquals(emptyCard.getPoints(), 0);
	}
	
	@Test
	/* Testing the good card */
	void correctCard() {
		assertEquals(correctCard.getQuestion(), "What city does Batman live in?");
		assertFalse(correctCard.getQuestion() == null);
		
		assertEquals(correctCard.getAnswer(), "A");
		assertFalse(correctCard.getAnswer() == null);
		
		assertEquals(correctCard.getChoices(), "A) Gotham, B) New York, C) Wakanda, D) Asgard");
		assertFalse(correctCard.getChoices() == null);
		
		assertEquals(correctCard.getPoints(), 1);
		assertFalse(correctCard.getPoints() == 0);
	}
	
	@Test
	/* Testing the card that has no question */
	void noQuestionCard() {
		assertEquals(noQuestionCard.getQuestion(), null);
		assertTrue(noQuestionCard.getQuestion() == null);
		
		assertEquals(noQuestionCard.getAnswer(), "A");
		assertFalse(noQuestionCard.getAnswer() == null);
		
		assertEquals(noQuestionCard.getChoices(), "A) Gotham, B) New York, C) Wakanda, D) Asgard");
		assertFalse(noQuestionCard.getChoices() == null);
		
		assertEquals(noQuestionCard.getPoints(), 1);
		assertFalse(noQuestionCard.getPoints() == 0);
	}
	
	@Test
	/* Testing the card that has no answer */
	void noAnswerCard() {
		assertEquals(noAnswerCard.getQuestion(), "What city does Batman live in?");
		assertFalse(noAnswerCard.getQuestion() == null);
		
		assertEquals(noAnswerCard.getAnswer(), null);
		assertTrue(noAnswerCard.getAnswer() == null);
		
		assertEquals(noAnswerCard.getChoices(), "A) Gotham, B) New York, C) Wakanda, D) Asgard");
		assertFalse(noAnswerCard.getChoices() == null);
		
		assertEquals(noAnswerCard.getPoints(), 1);
		assertFalse(noAnswerCard.getPoints() == 0);
	}
	
	@Test
	/* Testing the card that has no choices */
	void noChoicesCard() {
		assertEquals(noChoicesCard.getQuestion(), "What city does Batman live in?");
		assertFalse(noChoicesCard.getQuestion() == null);
		
		assertEquals(noChoicesCard.getAnswer(), "A");
		assertFalse(noChoicesCard.getAnswer() == null);
		
		assertEquals(noChoicesCard.getChoices(), null);
		assertTrue(noChoicesCard.getChoices() == null);
		
		assertEquals(noChoicesCard.getPoints(), 1);
		assertFalse(noChoicesCard.getPoints() == 0);
	}
	
	@Test
	/* Testing the card that has no points */
	void noPointsCard() {
		assertEquals(noPointsCard.getQuestion(), "What city does Batman live in?");
		assertFalse(noPointsCard.getQuestion() == null);
		
		assertEquals(noPointsCard.getAnswer(), "A");
		assertFalse(noPointsCard.getAnswer() == null);
		
		assertEquals(noPointsCard.getChoices(), "A) Gotham, B) New York, C) Wakanda, D) Asgard");
		assertFalse(noPointsCard.getChoices() == null);
		
		assertEquals(noPointsCard.getPoints(), 0);
		assertTrue(noPointsCard.getPoints() == 0);
	}

}
