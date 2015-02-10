/**
 * @authors: Yuxuan Li & Daming Xing
 * cpsc115 final project
 * 
 * This class represents the deck of cards used in Blackjack game.
 * It inherits everything from the deck.
 * 
 * A method is added in this class to examine when the deck is low of cards.
 */

public class BlackJackDeck extends Deck {

	//Constructor, inherits everything.
	public BlackJackDeck() {
		super();
	}

	//Examines when the deck is low of cards.
	public boolean isDeckLow(){
		if (bottom - top <= 4) return true;
		return false;
	}
}
