package game.backend;

import java.util.ArrayList;

import java.util.Collections;

public class Deck {
	ArrayList<Card> Cards = new ArrayList<Card>();
	
	Deck(ArrayList<Card> deck) {
		this.Cards = deck;
	}
	
	//shuffles the deck
	public void shuffle() {
		Collections.shuffle(Cards);
	}
	
	public void removeCard(int i) {
		Cards.remove(i);
	}
	
	//getter
	public Card getCard(int i) {
		return Cards.get(i);
	}
	
	public void addCard(Card card) {
		Cards.add(card);
	}
	
	//Draw card from deck and place in another deck (the other deck can be you hand)
	//We will have three decks in the main class: the main deck, the hand your dealt, and discarded deck 
	public void draw(Deck comingFrom) {
		//take card from deck
		Card card = comingFrom.getCard(0);
		//add the card to the new deck
		Cards.add(card);
		//remove card from old deck
		removeCard(0);
	}
	
	
}
