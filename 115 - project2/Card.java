import java.awt.image.BufferedImage;

/**
 *  The Card class represents a playing card.
 *  Its Comparable interface implements to compareTo() method,
 *   which defines the order to be used in sorting Cards.
 *
 *  @author CPSC 115
 */
public class Card implements Comparable {

  // Static arrays used for the card and suit names
  public static String suitNames[] = {"C", "D", "H", "S"};
  public static String rankNames[] = {"", "A", "2", "3","4","5","6","7",
				      "8","9","10","J","Q", "K"};

  protected int suit;  // Cards are represented by a suit (0-4) and rank (1-13)
  protected int rank;
  protected BufferedImage face;  // Image for the card's face

  /**
   *  Constructor creates a Card given its suit and rank
   */
  public Card(int suit, int rank) {
    this.suit = suit;
    this.rank = rank;
  }

  /**
   *  Constructor creates a Card given its suit,  rank, and image
   */
  public Card(int suit, int rank, BufferedImage img) {
    this.suit = suit;
    this.rank = rank;
    this.face = img;
  }

  public Card (Card c) {
    this.suit = c.suit;
    this.rank = c.rank;
    this.face = c.face;
  }

 public void setSuit(int suit) {
    this.suit = suit;
  }

  public int getSuit() {
    return suit;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public int getRank() {
    return rank;
  }

  /**
   * Returns the card's image.
   */
  public BufferedImage toImage() {
    return face;
  }

  /**
   * Setter method for the card's image.
   */
  public void setImage(BufferedImage img) {
    face = img;
  }

  /**
   *  Returns a string representation of the Card
   */
  @Override
  public String toString() {
    return  rankNames[rank] + suitNames[suit];
  }

  /**
   *  Determins when tow Card objects are equal (vs. identical)
   */
  public boolean equals(Card other) {
    return this.suit == other.suit && this.rank == other.rank;
  }

  /**
   *  Method used to define the order between cards. 
   *  Cards are ordered by lower to higher rank:
   *     this < other, return a negative int
   *     this > other, return a positive int
   *     this == other, return 0
   *  Used automatically by sort methods.
   */
  @Override  // Means this method overrides Object.compareTo()
  public int compareTo(Object other) {
    return this.rank - ((Card) other).rank;
  }

  /**
   *  Run simple tests of this class.
   */
  public static void main(String args[]) {
   Card card1 = new Card(3,11);
    Card card2 = new Card(2,1);
    Card card3 = new Card(2,1);
    Card card4 = card3;
    System.out.println ("Two cards: " + card1 + " and " + card2);
    if (card1.compareTo(card2) > 0) 
      System.out.println(card1 + " is greater than " + card2);
    else
      System.out.println(card1 + " is greater than " + card2);

    System.out.println("card2 = " + card2);
    System.out.println("card3 = " + card3);
    System.out.println("card4 = " + card4);

    System.out.println("card3 == card2: "  + (card3 == card2));
    System.out.println("card3 != card2: "  + (card3 != card2));
    System.out.println("card3.equals(card2): "  + card3.equals(card2));

    System.out.println("card3 == card4: "  + (card3 == card4));
    System.out.println("card3 != card4: "  + (card3 != card4));
    System.out.println("card3.equals(card4): "  + card3.equals(card4));
 
  }
}