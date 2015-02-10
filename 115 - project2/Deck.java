import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *  The Deck class represents a deck of cards. An array  is
 *  used to store Card objects.  
 *
 *  Card images are in PNG formate are stored at PATH.
 *  PNG card images are from https://code.google.com/p/vector-playing-cards/
 * 
 *  @author CPSC 115
 *  @license Lesser GPL
 */
public class Deck {

  private static final String PATH = "png/";
  private static final String EXT = ".png";

  /** 
   *  Static strings for image file names
   */
  public static String suitNames[] = {"clubs", "diamonds", "hearts", "spades"};
  public static String rankNames[] = {"", "ace", "2", "3","4","5","6","7",
				      "8","9","10","jack","queen", "king"};
  /**
   *   Cards are stored in an array with top pointing to the top of the deck
   *    and bottom pointing to the bottom of the deck.
   */
  protected Card cards[];      // Cards are stored in an array
  protected int top;           // For dealing, location of the first card, initially 0
  protected int bottom =  -1;  // For dealing, location of the last  card in deck
  protected BufferedImage cardBack; // Image for face-down cards. 

  /**
   *  Constructor creates the standard 52 card deck.  Card images are
   *   input from png/<rank>_of_<suit>.png.
   */
  public Deck() {
  }

  /**
   * Populates deck with actual cards
   */
  public void populate(int n) {
    cards = new Card[n];
    int indx = 0;
    BufferedImage image = null;
    String path = "";
    try {
      cardBack = ImageIO.read(new File(PATH + "cardBack.png"));
      for (int suit = 0; suit < 4; suit++) 
	for (int rank = 1; rank <= 13; rank++) {
          path = PATH + Deck.rankNames[rank] + "_of_" + Deck.suitNames[suit] + EXT;
          image = ImageIO.read(new File(path));
	  cards[indx] = new Card(suit,rank,image);
	  indx++;
	}
    } catch (IOException e) {
      System.out.println("IOException -- probably wrong path to card image");
    }

    // Initialize the top and the bottom of the deck
    top = 0;
    bottom = cards.length - 1;
  }

  /**
   * Returns the cardBack as the deck's image.
   */
  public BufferedImage toImage() {
    return cardBack;
  }

  /**
   *  Returns a string representation of the deck by concatenation
   *   of each card from top to bottom into a string.
   */
  public String toString() {
    if (cards == null)
      return "Empty deck";
    String deck = "";
    for (int k = top; k <= bottom; k++) 
      deck += cards[k] + " ";
    return deck;
  }

  /**
   *  Returns the deck, from top to bottom, as an array. The new array
   *   is a deep copy of the cards array. That is, the cards are
   *   cloned.
   */
  public Card[] toArray() {
    Card[] newcards = new Card[cards.length];
    for (int i = top; i <= bottom; i++)
      newcards[i] = new Card(cards[i]);    // Clone each card
    return newcards;
  }

  /**
   * Shuffle mixes up the deck by repeatedly swapping cards.
   *  NOTE: shuffles the whole array, not just top to bottom.
   * 
   */
  public void shuffle() {
    System.out.println("Shuffle");
    int nCards = cards.length;
    for (int k = 0; k < nCards; k++){
      int random = (int)(Math.random() * nCards); // Random number between 0..nCards
      swapCards(cards, 0, random);
    }
  }

  /**
   * Swaps the cards and indices i and j in the array cards.
   */
  private void swapCards(Card cards[], int i, int j) {
    Card temp = cards[i];
    cards[i] = cards[j];
    cards[j] = temp;
  }

  /**
   *  Performs a selection sort on the whole array of Cards,
   *   independent of top and botom
   */
  public void sort() {
    System.out.println("Sort");
    int ncards = cards.length;
    for (int i = 0; i < ncards; i++) {
      int minIndx  =  i;
      for (int j = i; j < ncards; j++) {    // Find min starting at the ith card
        int cmp = cards[j].compareTo(cards[minIndx]);
        if (cmp < 0)
          minIndx = j;
      }
      swapCards(cards, i, minIndx);
    }
  }

  /**
   *  Deals the top card.  Cards are not removed from the 
   *   array. We just keep track of the top. 
   *  NOTE: This doesn't check whether all the cards
   *   have been dealt -- i.e., when top == bottom.
   */
  public Card pop() {
    top += 1;
    return cards[top-1];
  }

  /**
   * Inserts a new card at the bottom of the deck if there is room.
   *  Note: Initially bottom = -1 for an empty deck
   */
  public void add(Card card) {
    if (bottom < cards.length) {
      bottom++;
      cards[bottom] = card;
    }
  }

  /**
   *  Deals n cards from the top of the deck to the designated hand.
   * @param n gives the number of cards to deal
   */
  public Hand deal(int n) {
    System.out.println("Dealing  = " + n);
    Hand hand = new Hand(n);
    for (int k = 0; k < cards.length &&  k < n; k++) {
      hand.add( this.pop() );
    }
    return hand;
  }

  /**
   * Prints an array of cards.
   */
  public void print(Card[] arr) {
    for (int i = 0; i < arr.length; i++)
      System.out.print(arr[i] + " ");
    System.out.println();
  }


  /**
   *  Test the Deck methods.
   */
  public static void main(String args[]) {
    Deck deck = new Deck();
    System.out.println(deck + "\n");
    deck.populate(52);
    System.out.println(deck + "\n");
    deck.shuffle();
    System.out.println("Shuffled Deck: " + deck + "\n");
    deck.sort();
    System.out.println("Sorted deck: " + deck + "\n");

    Hand hand = deck.deal(5);

    System.out.println("Hand: " + hand + "\n");
    System.out.println("Deck after Dealing: " + deck + "\n");

    System.out.println("Printing hand as an array");
    Card[] cards = hand.toArray();
    deck.print(cards);
   }
}