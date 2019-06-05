/**
 * Title:               Timed High-Card Game Phase 3
 * Files:               Assig6_Phase1.java
 * Semester:            Summer A, 2019
 * Date:                June 3, 2019
 *
 * Author:              Roger Terrill, George Blombach, Dalia Faria,
 *                      Abby Packham, Carlos Orduna
 * Email:               rchicasterrill@csumb.edu, gblombach@csumb.edu,
 *                      dfaria@csumb.edu, apackham@csumb.edu,
 *                      cordunacorrales@csumb.edu
 * Lecturer's Name:     Jesse Cecil, M.S.
 * TA's Name:           Joseph Appleton
 * Lab Section:         CST 338
 */

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Random;

public class Assig6_Phase1
{
    static int NUM_CARDS_PER_HAND = 7;
    static int NUM_PLAYERS = 2;
    static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
    static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];
    static JLabel gameText = new JLabel();
    static JLabel gameStatus = new JLabel();
    static int playerScore, computerScore = 0;


    public static void main(String[] args)
    {
        // Initializing instance variables for CardGameFramework
        int card;
        Icon tempIcon;
        int numPacksPerDeck = 1;
        int numJokersPerPack = 2;
        int numUnusedCardsPerPack = 0;
        Card[] unusedCardsPerPack = null;
        playLabelText[0] = new JLabel( "Computer", JLabel.CENTER );
        playLabelText[1] = new JLabel( "You", JLabel.CENTER );
        playerScore = 0;
        computerScore = 0;

        //game controls
        gameText = new JLabel("Welcome to High Card!");
        gameStatus = new JLabel("Click on card to begin.");
        gameText.setForeground(Color.red);
        gameStatus.setForeground(Color.red);

        //keep track of computer card array
        int computerCards[] = new int[NUM_CARDS_PER_HAND];

        // Creating highCardGame object
        CardGameFramework highCardGame = new CardGameFramework
              (numPacksPerDeck, numJokersPerPack, numUnusedCardsPerPack,
                    unusedCardsPerPack, NUM_PLAYERS, NUM_CARDS_PER_HAND);

        // Deals cards between the number of players
        highCardGame.deal();
        //DEBUG: System.out.print(highCardGame.getHand(0) + "\n");

        //Load Icons for cards from GUICard class
        GUICard.loadCardIcons();

        // establish main frame in which program will run
        CardTable myCardTable
              = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
        myCardTable.setSize(800, 600);
        myCardTable.setLocationRelativeTo(null);
        myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add mouse adapter
        MouseAdapter mouseAdapter = new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                playGame(myCardTable.pnlHumanHand.getComponentZOrder
                      (e.getComponent()));
            }

            private void playGame(int index)
            {
                //check conditions for game play
                if (!playedCardLabels[1].isVisible())
                {
                    //unhide placeholder card
                    playedCardLabels[1].setVisible(true);

                    //build card computer array in memory
                    for (int count = 0;count < NUM_CARDS_PER_HAND; count++)
                    {
                        computerCards[count] = Card.valueOfCard(highCardGame
                              .getHand(0).inspectCard(count));
                    }
                }

                //hide card just played
                humanLabels[index].setVisible(false);

                //move to playing field
                playedCardLabels[1].setIcon(humanLabels[index].getIcon());

                /* DEBUG System.out.print(Card.valueOfCard(highCardGame.getHand(1)
                 * .inspectCard(index)) + "\n");
                 */
                /* DEBUG System.out.print(Card.valueOfCard(highCardGame.getHand(1)
                 * .inspectCard(index)) + "\n");
                 */

                //get computer hand
                computerPlay(Card.valueOfCard(highCardGame.getHand(1)
                                                          .inspectCard(index)));
            }

            private void computerPlay(int highCard)
            {
                int bestCard = 14;
                int index = 0;
                int minInd = 0;
                int minCard = 14;
                //get available values
                for (int count = 0;count < NUM_CARDS_PER_HAND; count++)
                {
                    int cardValue = computerCards[count];
                    if (cardValue > highCard && cardValue < bestCard)
                    {
                        index = count;
                        bestCard = cardValue;
                    }

                    if (cardValue < minCard && cardValue >= 0)
                    {
                        minInd = count;
                        minCard = cardValue;
                    }
                }
                if (bestCard < 14)
                {
                    //check conditions for game play
                    if (!playedCardLabels[0].isVisible())
                    {
                        //unhide placeholder
                        playedCardLabels[0].setVisible(true);
                    }
                    //hide card just played
                    computerLabels[index].setVisible(false);

                    //move to playing field
                    playedCardLabels[0].setIcon(GUICard.getIcon(highCardGame
                          .getHand(0).inspectCard(index)));

                    computerCards[index] = -1;

                    //set display
                    computerScore ++;
                    updateGame("Computer Wins");
                }
                else
                {
                    //check conditions for game play
                    if (!playedCardLabels[0].isVisible())
                    {
                        //unhide placeholder
                        playedCardLabels[0].setVisible(true);
                    }
                    //hide card just played
                    computerLabels[minInd].setVisible(false);

                    //move to playing field
                    playedCardLabels[0].setIcon(GUICard.getIcon(highCardGame
                          .getHand(0).inspectCard(minInd)));

                    computerCards[minInd] = -1;

                    //set display
                    playerScore ++;
                    updateGame("You win");
                }
            }

            private void updateGame(String message)
            {
                //show score
                gameStatus.setText("Score: " + computerScore + "-" + playerScore);
                gameText.setText(message);
                if (computerScore + playerScore == NUM_CARDS_PER_HAND)
                    if (computerScore > playerScore)
                        gameText.setText("Game Over Computer Wins");
                    else
                        gameText.setText("Game Over You Win!");
            }
        }; //end of mouseAdapter

        // CREATE LABELS ----------------------------------------------------
        for (card = 0; card < NUM_CARDS_PER_HAND; card++)
        {
            //give the Computer a back card Label
            computerLabels[card] = new JLabel(GUICard.getBackcardIcon());

            //give Human a card
            tempIcon = GUICard.getIcon(generateRandomCard());
            humanLabels[card] = new JLabel(tempIcon);
            humanLabels[card].addMouseListener(mouseAdapter);
        }

        // ADD LABELS TO PANELS -----------------------------------------
        for (card = 0; card < NUM_CARDS_PER_HAND; card++)
        {
            //add indexed label to Computer panel
            myCardTable.pnlComputerHand.add(computerLabels[card]);

            //add indexed label to Human panel
            myCardTable.pnlHumanHand.add(humanLabels[card]);
        }

        // add two random cards in the play region (simulating a computer/hum ply)
        //getting random card
        tempIcon = GUICard.getIcon(generateRandomCard());

        //assigning 2 labels to playedCards
        playedCardLabels[0] = new JLabel(tempIcon);
        playedCardLabels[0].setVisible(false);

        tempIcon = GUICard.getIcon(generateRandomCard());

        playedCardLabels[1] = new JLabel(tempIcon);
        playedCardLabels[1].setVisible(false);

        //adding labels to played area
        myCardTable.pnlPlayArea.add(playedCardLabels[0]);
        myCardTable.pnlPlayArea.add(playedCardLabels[1]);
        myCardTable.pnlPlayArea.add(gameText);
        myCardTable.pnlPlayArea.add(playLabelText[0]);
        myCardTable.pnlPlayArea.add(playLabelText[1]);
        myCardTable.pnlPlayArea.add(gameStatus);

        // show everything to the user
        myCardTable.pack();
        myCardTable.setVisible(true);
    }

    //generate a random card to be given to a player
    //-Currently can give repeated cards-
    //-It's OK as this is only for testing purposes-
    static Card generateRandomCard()
    {
        Deck deck = new Deck();
        Random randomGen = new Random();
        return deck.inspectCard(randomGen.nextInt(deck.getNumCards()));
    }
}