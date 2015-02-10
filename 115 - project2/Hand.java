/**
 *  The Hand class represents a Hand in a card game.
 *  A Hand is a subclass of a Deck, so it inherits
 *  the shuffle(), sort(), add(), pop() methods. It
 *  also inherits the top and bottom instance variables.
 * 
 *  
 * 
 *  @author CPSC 115
 */
public class Hand extends Deck {
    
  /** 
   *  Constructor initializes the array used to store the Hand's cards.
   *  @param n gives the number of cards in the hand
   */
  public Hand(int n) {
    super();
    cards = new Card[n];
  }
}