import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *  @authors: Yuxuan Li & Daming Xing
 *  cpsc115 - final project
 *  Lab: Wednesday
 */

/**
 * A GUI for playing Blackjack game.
 * 
 * This class represents the Controller and View elements in a 
 * 	Model-View-Controller design. The Model is the BlackJack class.
 */
public class BlackJackGUI extends JPanel implements ActionListener {
	
	private static final int WIDTH=100,HEIGHT=140;
	
	//reference to the Model, the Blackjack game.
	private BlackJack blackjack; 
	
	// The elements that make up the visible portion of the user interface.
	private JPanel controlPanel = new JPanel();
	private JPanel dealerPanel = new JPanel();
	private JPanel playerPanel = new JPanel();
	private JLabel[] dealerLabel = new JLabel[10];
	private JLabel[] playerLabel = new JLabel[10];
	private JLabel statusLabel = new JLabel("");
	private JButton againButton = new JButton("AGAIN");
	private JButton quitButton = new JButton("QUIT");
	private JButton hitButton = new JButton("HIT");
	private JButton standButton = new JButton("STAND");
	
	//reference to the dealer's hand and player's hand
	private Card dealerTopCard;
	private Hand dealerHand;
	private Hand playerHand;
	
	/**
	 * Creates the GUI's Panel and initializes the game.
	 */
	public BlackJackGUI(){
		blackjack = new BlackJack(this);
		initialize();
	}

	/**
	 * Initializes the game and the GUI. This is called once at start up.
	 */
	private void initialize() {
		//initialize the Controller
		againButton.addActionListener(this);
		quitButton.addActionListener(this);
		hitButton.addActionListener(this);
		standButton.addActionListener(this);
		
		//Remove all the components and reset the display
		removeAll();
		setVisible(false);
		
		//Initialize the images of the dealer's topCard, and the back of the 
		// other card, both are stored in Labels.
		dealerHand = blackjack.getDealerHand();
		dealerTopCard = blackjack.getDealerTopCard();
		dealerLabel[0] = new JLabel(new ImageIcon(resizeImage(dealerTopCard.toImage(), WIDTH, HEIGHT)));
		dealerLabel[1] = new JLabel(new ImageIcon(resizeImage(blackjack.getDeckImage(), WIDTH, HEIGHT)));
		dealerPanel.add(dealerLabel[0]);
		dealerPanel.add(dealerLabel[1]);
		
		//Initialize the images of the player's hand.
		playerHand = blackjack.getPlayerHand();
		displayPlayerHand(playerHand);
		
		//The controlPanel holds the buttons and the statusLabel.
		statusLabel = new JLabel(blackjack.getStatus());
		statusLabel.setForeground(Color.red);
		controlPanel.add(statusLabel);
		controlPanel.add(hitButton);
		controlPanel.add(standButton);
		controlPanel.add(againButton);
		againButton.setEnabled(false);
		controlPanel.add(quitButton);

		//add the handPanel and controlPanel to the main panel.
		setLayout(new GridLayout(3,1,0,0));
		add(dealerPanel,BorderLayout.NORTH);
		add(controlPanel,BorderLayout.CENTER);
		add(playerPanel,BorderLayout.SOUTH);
		setVisible(true);
	}
	
	
	/**
	 * This method takes the dealer's hand and stores the image into 
	 *  an array of labels.
	 * @param Hand dealerHand
	 */
	private void displayDealerHand(Hand dealerHand) {
		dealerPanel.removeAll();
		Card[] cards = dealerHand.toArray();
		int length = 0;
		for(int k=0;k<cards.length;k++)
			if (cards[k] != null) length++;
		dealerLabel = new JLabel[length];
		for(int i=0;i<length;i++) {
			Card card = dealerHand.toArray()[i];
			dealerLabel[i] = new JLabel(new ImageIcon(resizeImage(card.toImage(), WIDTH, HEIGHT)));
			dealerPanel.add(dealerLabel[i]);
		}
	}
	
	/**
	 * This method takes the player's hand and stores the image into 
	 *  an array of labels.
	 * @param Hand playerHand
	 */
	private void displayPlayerHand(Hand playerHand) {
		setVisible(false);
		playerPanel.removeAll();
		Card[] cards = playerHand.toArray();
		int length = 0;
		for(int k=0;k<cards.length;k++)
			if (cards[k] != null) length++;
		playerLabel = new JLabel[length];
		for(int i=0;i<length;i++) {
			Card card = playerHand.toArray()[i];
			playerLabel[i] = new JLabel(new ImageIcon(resizeImage(card.toImage(), WIDTH, HEIGHT)));
			playerPanel.add(playerLabel[i]);
		}
		setVisible(true);
	}
	
	// Needed to size the images properly.
	/** 
	 * This version resizes an image
	 */
	private BufferedImage resizeImage(BufferedImage image, int w, int h) {
	    BufferedImage resizedImage  = new BufferedImage(w, h, image.getType());
	    Graphics2D g = resizedImage.createGraphics();
	    g.drawImage(image, 0, 0, w, h, null);
	    g.dispose();
	    return resizedImage;
	  }
	
	/**
	 * Implements the Controller.
	 * 
	 * This method handles button clicks on the four buttons. In each case it 
	 *  calls the Model's guiMove() method, passing the appropriate information.
	 *  
	 * The Controller enables and disables buttons to control the flow.
	 * 
	 * The Model(BlackJack) makes all the decisions for the game and passes back
	 *  a BlackJack.BlackJackResult object, which contains an updated status,
	 *  the dealer's hand, and the player's hand, and the scores.
	 */
	public void actionPerformed(ActionEvent e) {
		BlackJack.BlackJackResult result = null;		
		
		//Examines initially if anyone got a blackjack.
		if (blackjack.isBlackjack(dealerHand, playerHand)) {
			statusLabel.setText(blackjack.getStatus()+"  Dealer score: "+ blackjack.getDealerScore() +"  Your score: "+ blackjack.getPlayerScore());
			displayDealerHand(dealerHand);
			hitButton.setEnabled(false);
			standButton.setEnabled(false);
			againButton.setEnabled(true);
		}
		
		//When the user clicks AGAIN, the Controller passes 2=AGAIN to the Model,
		// the Model returns new dealer's hand, player's hand, new scores and
		// new status in the result object.
		if (e.getSource() == againButton) {
			result = blackjack.guiMove(blackjack.AGAIN);
			// Set the dealer's hand
			dealerTopCard = blackjack.showTopCard(result.dealerHand);
			dealerLabel[0].setIcon(new ImageIcon(resizeImage(dealerTopCard.toImage(),WIDTH,HEIGHT)));
			dealerLabel[1].setIcon(new ImageIcon(resizeImage(blackjack.getDeckImage(), WIDTH, HEIGHT)));
			dealerPanel.removeAll();
			dealerPanel.add(dealerLabel[0]);
			dealerPanel.add(dealerLabel[1]);
			// Set the player's hand
			displayPlayerHand(result.playerHand);
			//Ends the round if whoever gets blackjack.
			if (result.dealerScore ==21 || result.playerScore ==21) {
				statusLabel.setText(result.status+"  Dealer score: "+result.dealerScore+"  Your score: "+ result.playerScore);
				displayDealerHand(result.dealerHand);
				statusLabel.setText(result.status);
				hitButton.setEnabled(false);
				standButton.setEnabled(false);
				againButton.setEnabled(true);
			} else {
				hitButton.setEnabled(true);
				standButton.setEnabled(true);
				againButton.setEnabled(false);
			}
			// Set the status and buttons
			statusLabel.setText(result.status);
			hitButton.setEnabled(true);
			standButton.setEnabled(true);
			againButton.setEnabled(false);
		}
		
		//When the user clicks HIT, the Controller passes 0=HIT to the Model, 
		// which adds a card to the player's hand.
		else if (e.getSource() == hitButton) {
			result =blackjack.guiMove(blackjack.HIT);
			
			// Update the player's hand and the buttons
			displayPlayerHand(result.playerHand);
			if (result.dealerScore >=21 || result.playerScore >=21) {
				statusLabel.setText(result.status+"  Dealer score: "+result.dealerScore+"  Your score: "+ result.playerScore);
				displayDealerHand(result.dealerHand);
				statusLabel.setText(result.status);
				hitButton.setEnabled(false);
				standButton.setEnabled(false);
				againButton.setEnabled(true);
			} else {
				hitButton.setEnabled(true);
				standButton.setEnabled(true);
				againButton.setEnabled(false);
			}
		}
		
		//When the user clicks STAND, the Controller passes 1=STAND to the
		// Model, which let's the dealer now draw cards, then determines
		// the winner and updates the status.
		else if (e.getSource() == standButton) {
			result = blackjack.guiMove(blackjack.STAND);
			//reveal the dealer's hand and the player's hand
			displayDealerHand(result.dealerHand);
			displayPlayerHand(result.playerHand);
			//Set the status
			statusLabel.setText(result.status+"  Dealer score: "+result.dealerScore+"  Your score: "+ result.playerScore);
			//Set the buttons
			hitButton.setEnabled(false);
			standButton.setEnabled(false);
			againButton.setEnabled(true);
		}
		
		//When the user clicks QUIT, the Controller just quits.
		else {
			statusLabel.setText("Bye!");
			hitButton.setEnabled(false);
			standButton.setEnabled(false);
		    againButton.setEnabled(false);
		    quitButton.setEnabled(false);
		    System.exit(0);
		}
	}
	
	/**
	 * Creates a top-level window (JFrame) and adds the BlackJackGUI to it.
	 */
	public static void main(String argv[]) {
		BlackJackGUI gui = new BlackJackGUI();
		JFrame window = new JFrame("");
	    window.setLocation(0,0);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // Add the GUI to the top-level window and display it.
	    Container contentPane = window.getContentPane();
	    contentPane.removeAll();
	    contentPane.add((JPanel) gui);
	    //window.pack();
	    window.setSize(8*WIDTH, HEIGHT*4);
	    window.setVisible(true);
	}
}
