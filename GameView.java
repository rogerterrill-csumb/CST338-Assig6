import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

class GameView extends JFrame
{
   // Constants
   private int NUM_CARDS_PER_HAND = GameModel.NUM_CARDS_PER_HAND;
   private int NUM_PLAYERS = GameModel.NUM_PLAYERS;

   // CardTable private members
   private int numCardsPerHand;
   private int numPlayers;

   // Cards to be displayed
   private Card computerCard;

   // JLabels private members to are the Cards Displayed
   private JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   private JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
   private JButton[] playerCardButtons = new JButton[NUM_CARDS_PER_HAND];

   // JLabels private members that display text
   private JLabel gameText = new JLabel();
   private JLabel gameStatus = new JLabel();
   private JLabel playerCardLabel, computerCardLabel;

   // Timer Display Components
   private JLabel timerDisplay = new JLabel("0");
   private JButton startButton = new JButton("Start");

   // 4 panels - One Computer player, One Human player, One play area, Timer Area
   private JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea, pnlTimerDisplay;

   // CardGameFramework object to pass into from model through controller
   private CardGameFramework highCardGame;

   GameView()
   {
      // Sets the title of the blank table
      super("Card Game Table");

      // Foundational Methods to Setup Frame
      setSize(800, 600);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Show the table
      setVisible(true);
   }

   public void initTable()
   {
      // Container Layout Manager
      setLayout(new BorderLayout());

      // Panels to display both hands and play area
      pnlComputerHand = new JPanel(new GridLayout(1,numCardsPerHand)); //Remember col num is ignored if it goes over adds new column
      pnlHumanHand = new JPanel(new GridLayout(1,numCardsPerHand)); //Remember col num is ignored
      pnlPlayArea = new JPanel(new GridLayout(2,3)); //Remember col num is ignored
      pnlTimerDisplay = new JPanel(new GridLayout(2,1)); // Timer display with Button

      // Place panels to their specific location
      add(pnlComputerHand, BorderLayout.NORTH);
      add(pnlPlayArea, BorderLayout.CENTER);
      add(pnlTimerDisplay, BorderLayout.WEST);
      add(pnlHumanHand, BorderLayout.SOUTH);

      // Add border titles to each section
      pnlComputerHand.setBorder(new TitledBorder("Computer Hand"));
      pnlPlayArea.setBorder(new TitledBorder("Playing Area"));
      pnlTimerDisplay.setBorder(new TitledBorder("Timer Button"));
      pnlHumanHand.setBorder(new TitledBorder("Your Hand"));

      // Set the game text style
      gameText.setText("Welcome to High Card!");
      gameText.setForeground(Color.red);
      gameText.setHorizontalAlignment(JLabel.CENTER);

      gameStatus.setText("Click on card to begin.");
      gameStatus.setForeground(Color.red);
      gameStatus.setHorizontalAlignment(JLabel.CENTER);

      /**
       * Create labels for each card in each panel
       */
      // Load the icons to use on cards
      GUICard.loadCardIcons();

      // Create labels for player and computer
      for( int card = 0; card < NUM_CARDS_PER_HAND; card++)
      {
         // Load Cards Icons into array the cards
         computerLabels[card] = new JLabel(GUICard.getBackcardIcon());

         // Sorts the player hand
         highCardGame.getHand(1).sort();
         playerCardButtons[card] = new JButton(GUICard.getIcon(highCardGame.getHand(1).inspectCard(card)));

      }

      // After creating labels above, you then add them to the specified panel
      for( int card = 0; card < NUM_CARDS_PER_HAND; card++)
      {
         // Load cards from array into panel
         pnlComputerHand.add(computerLabels[card]);
         pnlHumanHand.add(playerCardButtons[card]);
      }

      // Create playing card Icons
      playedCardLabels[0] = new JLabel(GUICard.getBackcardIcon());
      playedCardLabels[1] = new JLabel(GUICard.getIcon(computerCard));

      // Create the text label under each played card
      playerCardLabel = new JLabel("You", JLabel.CENTER);
      computerCardLabel = new JLabel("Computer", JLabel.CENTER);

      /**
       * Add card icons to the play area section in table
       */
      // First Row
      pnlPlayArea.add(playedCardLabels[0]);
      pnlPlayArea.add(playedCardLabels[1]);
      pnlPlayArea.add(gameText);

      // Second Row
      pnlPlayArea.add(playerCardLabel);
      pnlPlayArea.add(computerCardLabel);
      pnlPlayArea.add(gameStatus);

      // Add timer components to display
      pnlTimerDisplay.add(startButton);
      pnlTimerDisplay.add(timerDisplay);

      // Show the table
      setVisible(true);
   }

   // Set action listener for player cards
   public void addCardListenerToCards(ActionListener cardListener, int card)
   {
      // Adds the action listener to specific card
      playerCardButtons[card].addActionListener(cardListener);
   }

   public void addTimerButtonListener(ActionListener timerListener)
   {
      startButton.addActionListener(timerListener);
   }

   // Getter and Setters
   public void setHighCardGame(CardGameFramework highCardGame)
   {
      this.highCardGame = highCardGame;
   }

   public JButton[] getPlayerCardButtons()
   {
      return playerCardButtons;
   }

   public void setComputerCard(Card computerCard)
   {
      this.computerCard = computerCard;
   }

   public JPanel getPnlHumanHand()
   {
      return pnlHumanHand;
   }

   public JLabel[] getPlayedCardLabels()
   {
      return playedCardLabels;
   }

   // Removes card from player and computer panels
   public void removeCard(int index)
   {
      pnlComputerHand.remove(computerLabels[index]);
      pnlHumanHand.remove(playerCardButtons[index]);
      repaint();
   }

   // Updates the text on Game Status
   public void setGameStatus(String str)
   {
      gameStatus.setText(str);
   }

   // Sets the player played card icon
   public void setPlayerPlayedCardLabel(Card card)
   {
      this.playedCardLabels[0].setIcon(GUICard.getIcon(card));
   }

   // Sets the computer played card icon
   public void setComputerPlayedCardLabel(Card card)
   {
      this.playedCardLabels[1].setIcon(GUICard.getIcon(card));
   }

   // Sets the computer played card to back icon
   public void setComputerBackIcon()
   {
      this.playedCardLabels[1].setIcon(GUICard.getBackcardIcon());
   }

   // Set the seconds
   public void setTimerDisplay(int seconds)
   {
      this.timerDisplay.setText(Integer.toString(seconds));
   }

   public JButton getStartButton()
   {
      return startButton;
   }

   public void setStartButtonText(String str)
   {
      this.startButton.setText(str);
   }
}
