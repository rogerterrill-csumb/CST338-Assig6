/**
 * Title:               Timed High Card Game Phase 1
 * Files:               Assig6_Phase1.java
 * Semester:            Summer A, 2019
 * Date:                June 11, 2019
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
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Assig6_Phase1
{
   public static void main(String[] args)
   {
      GameView gameView = new GameView();

      GameModel gameModel = new GameModel();

      GameController gameController = new GameController(gameView, gameModel);
   }
}

/*****************************************************************************
 * Card - A class that provides a card object and checks to see if the card  *
 * created has valid values.                                                 *
 *****************************************************************************/
class Card
{
   public enum Suit
   {CLUBS, DIAMONDS, HEARTS, SPADES}

   public static char[] valuRanks = {'A', '2', '3', '4', '5', '6', '7', '8',
           '9', 'T', 'J', 'Q', 'K', 'X'};
   private char value;
   private Suit suit;
   private boolean errorFlag;

   /**
    * Purpose: Constructor with no parameters to initialize card.
    * Preconditions: None.
    * Postconditions: Sets default value for a Card object.
    */
   public Card()
   {
      value = 'A';
      suit = Suit.SPADES;
   }

   /**
    * Purpose: Overloaded constructor with two parameters to initialize card.
    * Preconditions: Access to set() method.
    * Postconditions: Utilizes set method to initialize card.
    *
    * @param value The char value of the card.
    * @param suit  The enum suit of the card
    */
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   /**
    * Purpose: To return a String that displays card value and suit.
    * Preconditions: Initialized card object.
    * Postconditions: Sets value for card object based on parameters.
    *
    * @return Returns a String displaying the value and suit of card or illegal
    * if a errorFlag is true
    */
   public String toString()
   {
      if (errorFlag)
      {
         return "** illegal **";
      }
      return value + " of " + suit;
   }

   /**
    * Purpose: Sets card value and suit if the arguments are valid.
    * Preconditions: empty card initialized
    * Postconditions: Sets the errorFlag to true if valid or false otherwise.
    *
    * @return Returns a boolean of true if arguments are valid and false if not
    */
   public boolean set(char value, Suit suit)
   {
      if (isValid(value, suit))
      {
         this.value = value;
         this.suit = suit;
         errorFlag = false;
         return true;
      }
      errorFlag = true;
      return false;
   }

   /**
    * Purpose: Get value of value.
    * Preconditions: card object successfully set
    * Postconditions: none
    *
    * @return Returns char value of card object
    */
   public char getValue()
   {
      return value;
   }

   /**
    * Purpose: Get enum suit of suit.
    * Preconditions: card object successfully set
    * Postconditions: none
    *
    * @return Returns enum suit
    */
   public Suit getSuit()
   {
      return suit;
   }

   /**
    * Purpose: Get value of errorFlag.
    * Preconditions: card object successfully set
    * Postconditions: none
    *
    * @return Returns char value of card object
    */
   public boolean isErrorFlag()
   {
      return errorFlag;
   }

   /**
    * Purpose: Checks the equality of two objects and there members
    * Preconditions: card object successfully set
    * Postconditions: none
    *
    * @return Returns boolean result after checking if both value and suit are equal
    */
   public boolean equals(Card card)
   {

      return (value == card.value && suit == card.suit && errorFlag == card.errorFlag);
   }

   /**
    * Purpose: Checks the validity of the arguments passed into method by
    * checking if they are in our cardValues array
    * Preconditions: card object initialized
    * Postconditions: none
    *
    * @return Returns boolean depending if the value passed into the method is
    * in our approved array list as true and if not in our list,
    * returns false
    */
   private boolean isValid(char value, Suit suit)
   {
      String cardValues = "A23456789TJQKX";
      if (cardValues.indexOf(value) != -1)
      {
         return true;
      }
      return false;
   }

   /**
    * Purpose: Sort an array of cards from smallest to largest
    *
    * @param cardArray Array of cards
    * @param arraySize Number of cards in the array
    */
   public static void arraySort(Card[] cardArray, int arraySize)
   { // cardArray is Hand myCards, arraySize is numCards

      Card temp;

      // Bubble sort algorithm
      for (int card = 0; card < arraySize; card++)
      {
         for (int nextCard = 1; nextCard < (arraySize - card); nextCard++)
         {
            int previousCard = valueOfCard(cardArray[nextCard - 1]);
            int currentCard = valueOfCard(cardArray[nextCard]);

            if (previousCard > currentCard)
            {
               temp = cardArray[nextCard - 1];
               cardArray[nextCard - 1] = cardArray[nextCard];
               cardArray[nextCard] = temp;
            }

         }
      }
   }

   /**
    * Purpose: Return the numerical value of card based on index position
    *
    * @param card Card object whose value we want
    * @return Returns int card value
    */
   public static int valueOfCard(Card card)
   {

      // It traverses the valuRanks and check which matches the card value
      // Then it returns the index position as the value
      for (int value = 0; value < valuRanks.length; value++)
      {
         if (card.getValue() == valuRanks[value])
         {
            return value;
         }
      }
      return -1;
   }
}

/*****************************************************************************
 * Hand - A class that provides a card object and checks to see if the       *
 * card created has valid values.                                            *
 *****************************************************************************/

class Hand
{
   public static final int MAX_CARDS = 100;
   private Card[] myCards;
   private int numCards;

   /**
    * Purpose: Default constructor to initialize hand object
    * Preconditions: card object
    * Postconditions: Creates a hand
    */
   public Hand()
   {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }

   /**
    * Purpose: Empty hand
    * Preconditions: initialized and declared hand
    * Postconditions: Changes numCards back to 0
    */
   /* Fix:You should set numCards to 0 in the resetHand().*/
   public void resetHand()
   {
      numCards = 0;
   }

   /**
    * Purpose: Takes card and adds it to myCards array
    * Preconditions: Valid card must exist
    * Postconditions: Adds card to array and iterates numCards +1
    *
    * @param card A valid card object from the card class
    * @return Returns true if successfully took card
    */
   /* Fix: The takeCard() method should be returning a boolean according to the
   state of the fullness of the hand. Yours returns true all of the time,
   which defeats the purpose.*/
   public boolean takeCard(Card card)
   {
      if (numCards < MAX_CARDS)
      {
         myCards[numCards] = new Card(card.getValue(), card.getSuit());
         numCards++;
         return true;
      }
      return false;
   }


   /**
    * Purpose: Reduces number of cards in hand
    * Preconditions: Valid card must exist
    * Postconditions: Decrements numCards 1
    *
    * @return Returns the top card
    */
   /* Fix: playCard() should  check for no more cards in the hand and then
   do something like return a bad card.
   */
   public Card playCard()
   {
      if (numCards > 0)
      {
         numCards--;
         System.out.println(myCards[numCards]);
         return myCards[numCards];
      }
      else
      {
         Card badCard = new Card('0', Card.Suit.SPADES);
         return badCard;
      }


   }

   /**
    * Purpose: Gives the number of cards in hand
    * Preconditions: hand object must exist
    * Postconditions: None
    *
    * @return Return int of top card
    */
   public int getNumCards()
   {
      return numCards;
   }

   /**
    * Purpose: Displays the cards in the Hand
    * Preconditions: Hand object exist
    * Postconditions: None
    *
    * @return Returns String that displays card in Hand object
    */
   public String toString()
   {
      String str;
      str = "Hand = ( ";
      int card;
      for (card = 0; card < numCards; card++)
      {
         str += (myCards[card].toString());
         if (card < numCards - 1)
         {
            str += ", ";
         }
      }
      str += " )";

      return str;
   }

   /**
    * Purpose: Checks to see if card is still valid and enables errorFlag if not
    * Preconditions: Cards in Hand
    * Postconditions: Changes card errorFlag to true if card is invalid
    *
    * @return Returns Card with error flag True or False
    */
   /* Fix: inspectCard() should validate k according to how many cards are in
   the myCards array.
   */
   public Card inspectCard(int k)
   {
      if (k > numCards || k < 0)
      {
         return new Card('0', Card.Suit.SPADES);
      }
      return myCards[k];
   }

   /**
    * Purpose: Sort the card in Hand object
    */
   public void sort()
   {
      Card.arraySort(myCards, numCards);
   }

   /**
    * Purpose: Plays Cards
    *
    * @param cardIndex The index of the card in the array
    * @return Returns a card that was played
    */
   public Card playCard(int cardIndex)
   {
      if (numCards == 0) //error
      {
         //Creates a card that does not work
         return new Card('M', Card.Suit.SPADES);
      }
      //Decreases numCards.
      Card card = myCards[cardIndex];

      numCards--;
      for (int cardPosition = cardIndex; cardPosition < numCards; cardPosition++)
      {
         myCards[cardPosition] = myCards[cardPosition + 1];
      }

      myCards[numCards] = null;

      return card;
   }
}

/*****************************************************************************
 * Deck - A class that provides a card object and checks to see if the card  *
 * created has valid values.                                                 *
 *****************************************************************************/
class Deck
{
   public static final int DECK_SIZE = 52;
   public static final int MAX_CARDS = 6 * DECK_SIZE;
   public static final int NUM_OF_VALUES = 13;

   private static Card[] masterPack = new Card[DECK_SIZE];
   private Card[] cards = new Card[MAX_CARDS];
   private int topCard = 0;

   /**
    * Purpose: Constructor to build single deck
    * Preconditions: None
    * Postconditions: Creates a single deck of cards
    */
   public Deck()
   {
      int card;
      allocateMasterPack();
      for (card = 0; card < DECK_SIZE; card++)
      {
         cards[card] = masterPack[card % DECK_SIZE];
         topCard++;
      }
   }

   /**
    * Purpose: Constructor to build multiple decks
    * Preconditions: None
    * Postconditions: Creates a multiple deck of cards
    *
    * @param numPacks The number of pack of cards
    */
   public Deck(int numPacks)
   {
      int card;
      allocateMasterPack();
      for (card = 0; card < numPacks * DECK_SIZE; card++)
      {
         cards[card] = masterPack[card % DECK_SIZE];
         topCard++;
      }

   }

   /**
    * Purpose: Constructor to build multiple decks
    * Preconditions: None
    * Postconditions: Creates a multiple deck of cards
    *
    * @param numPacks The number of pack of cards
    */
   /* Fix: init() should validate numPacks. */
   public void init(int numPacks)
   {
      if (numPacks <= 6)
      {
         int card;
         topCard = 0;

         for (card = 0; card < numPacks * DECK_SIZE; card++)
         {
            cards[card] = masterPack[card % DECK_SIZE];
            topCard++;
         }
      }
   }

   /**
    * Purpose: Shuffles the deck of cards
    * Preconditions: Need a full deck of cards
    * Postconditions: Shuffled deck
    */
   public void shuffle()
   {
      for (int card = 0; card < topCard; card++)
      {
         int second = (int) (Math.random() * topCard);
         Card temp = cards[card];
         cards[card] = cards[second];
         cards[second] = temp;
      }
   }

   /**
    * Purpose: Deals the card from the deck
    * Preconditions: Initialized deck
    * Postconditions: Decrements to rid of top card and returns top card
    */
   public Card dealCard()
   {
      if (topCard > 0)
      {
         topCard--;
         Card tempCard = cards[topCard];
         return tempCard;
      }
      return new Card('-', Card.Suit.SPADES);
   }

   /**
    * Purpose: Get the top card int
    * Preconditions: Cards in the deck
    * Postconditions: The int of the top card position
    */
   public int getTopCard()
   {
      return topCard;
   }

   /**
    * Purpose: Checks the validity of card
    * Preconditions: Cards in list
    * Postconditions: Changes the card error attribute to true if valid and
    * false if not valid
    *
    * @param k The value of the index position of card
    */
   public Card inspectCard(int k)
   {
      if (k > topCard)
      {
         return new Card('0', Card.Suit.SPADES);
      }

      return cards[k];
   }

   /**
    * Purpose: Creates the initial pack all other packs reference
    * Preconditions: none
    * Postconditions: Masterpack created
    */
   private static void allocateMasterPack()
   {
      int masterPackIndex;

      String cardValues = "A23456789TJQKX";

      if (masterPack[0] == null)
      {
         for (masterPackIndex = 0; masterPackIndex < DECK_SIZE; masterPackIndex++)
         {
            if (masterPackIndex / NUM_OF_VALUES == 0)
            {
               masterPack[masterPackIndex] =
                       new Card(cardValues.charAt(masterPackIndex % NUM_OF_VALUES),
                               Card.Suit.SPADES);
            }
            if (masterPackIndex / NUM_OF_VALUES == 1)
            {
               masterPack[masterPackIndex] =
                       new Card(cardValues.charAt(masterPackIndex % NUM_OF_VALUES),
                               Card.Suit.CLUBS);
            }
            if (masterPackIndex / NUM_OF_VALUES == 2)
            {
               masterPack[masterPackIndex] =
                       new Card(cardValues.charAt(masterPackIndex % NUM_OF_VALUES),
                               Card.Suit.HEARTS);
            }
            if (masterPackIndex / NUM_OF_VALUES == 3)
            {
               masterPack[masterPackIndex] =
                       new Card(cardValues.charAt(masterPackIndex % NUM_OF_VALUES),
                               Card.Suit.DIAMONDS);
            }
         }
      }
   }

   /**
    * Get the number of cards in Deck
    *
    * @return Returns int that is the number of cards
    */
   public int getNumCards()
   {
      return topCard;
   }

   /**
    * Purpose: Adds a card to the deck and makes sure each card only has the
    * number of instances equal to or less than number of packs
    *
    * @param card The card to be inserted
    * @return Returns true if successfully added, false if not
    */
   public boolean addCard(Card card)
   {
      // The number of decks
      int deckNum = topCard / DECK_SIZE;

      // Keep track on the number of instances per card
      int cardInstances = 0;

      // If the card matches, it adds to the instance count
      for (int cardPosition = 0; cardPosition < topCard; cardPosition++)
      {
         if (card.equals(cards[cardPosition]))
         {
            cardInstances++;
         }
      }

//      System.out.println("Card instances is: " + cardInstances);

      // If card instance is equal or more than the number of decks ,it fails.
      if (cardInstances >= deckNum)
      {
//         System.out.println("Did not add card" + card.toString());
         return false;
      }
//      System.out.println("Added the card to the deck");

      // Take added card and assign it to the top card.
      cards[topCard] = card;

      // Increase the topCard counter since we added a card
      topCard++;
//      System.out.println("The topCard Value is: " + topCard);
      return true;
   }

   /**
    * Purpose: Removes a card for the Deck
    *
    * @param card Card to be removed
    * @return Returns true if successfully removed, false if not
    */
   public boolean removeCard(Card card)
   {
      // Traverses array of cards to see if card exists
      for (int cardsIndex = 0; cardsIndex < topCard; cardsIndex++)
      {
         // If card equals a card in deck, it removes it
         if (cards[cardsIndex].equals(card))
         {
            System.out.println("Removed Card Successfully");

            // Sets card to value of topCard
            cards[cardsIndex] = cards[topCard - 1];

            // Decrements topCard
            topCard--;
            return true;
         }
      }

      System.out.println("Did not remove card, none left");
      System.out.println(topCard);
      return false;
   }

   /**
    * Purpose: Sorts the array of cards in deck
    */
   public void sort()
   {
      Card.arraySort(cards, topCard);
   }

   /**
    * Purpose: String to display to console the deck of cards
    *
    * @return String that holds the cards in hand
    */
   public String toString()
   {
      String str;
      str = "Deck = ( ";
      for (int card = 0; card < getNumCards(); card++)
      {
         str += (cards[card].toString());
         if (card < getNumCards() - 1)
         {
            str += ", ";
         }
      }
      str += " )";

      System.out.println(str);
      return str;
   }
}

/*****************************************************************************
 * GUICard - A class that provides a card object icons                       *
 *****************************************************************************/
class GUICard
{
   //private static GUICard data
   private static Icon[][] iconCards = new ImageIcon[14][4];
   private static Icon iconBack;

   static boolean iconsLoaded = false;

   static void loadCardIcons()
   {
      if (iconsLoaded)
         return;
      for (int cardValue = 0; cardValue < iconCards.length; cardValue++)
      {
         for (int cardSuit = 0; cardSuit < iconCards[cardValue].length; cardSuit++)
         {
            //numCard will return string at i value
            //numSuit will return suit at ii value
            String filename = numCard(cardValue) + numSuit(cardSuit) + ".gif";
            ImageIcon cardImage = new ImageIcon("images/" + filename);
            iconCards[cardValue][cardSuit] = cardImage;
         }
      }
      //create final back card
      iconBack = new ImageIcon("images/BK.gif");
      iconsLoaded = true;
   }

   //  Changes integer to the card value
   static String numCard(int cardNum)
   {
      String[] cardValues = {"A", "2", "3", "4", "5", "6",
              "7", "8", "9", "T", "J", "Q", "K", "X"};
      return cardValues[cardNum];
   }

   //Check
   static String numSuit(int suitNum)
   {
      if (suitNum < 0 || suitNum > 3)
         return "invalid";
      return Card.Suit.values()[suitNum]
              .toString().toUpperCase().substring(0, 1);
   }

   //Check
   public static int valueToInt(Card card)
   {
      return Card.valueOfCard(card);
   }

   //Converts suit to number
   private static int suitToNum(Card card)
   {
      Card.Suit cardSuit = card.getSuit();

      switch (cardSuit)
      {
         case CLUBS:
            return 0;
         case DIAMONDS:
            return 1;
         case HEARTS:
            return 2;
         case SPADES:
            return 3;
         default:
            return -1;
      }
   }

   public static Icon getIcon(Card card)
   {
      return iconCards[valueToInt(card)][suitToNum(card)];
   }

   public static Icon getBackcardIcon()
   {
      return iconBack;
   }
}

/*****************************************************************************
 * CardGameFramework - Provides a framework to start a card game             *
 *****************************************************************************/
class CardGameFramework
{
   private static final int MAX_PLAYERS = 50;

   private int numPlayers;
   private int numPacks;            // # standard 52-card packs per deck
   // ignoring jokers or unused cards
   private int numJokersPerPack;    // if 2 per pack & 3 packs per deck, get 6
   private int numUnusedCardsPerPack;  // # cards removed from each pack
   private int numCardsPerHand;        // # cards to deal each player
   private Deck deck;               // holds the initial full deck and gets
   // smaller (usually) during play
   private Hand[] hand;             // one Hand for each player
   private Card[] unusedCardsPerPack;   // an array holding the cards not used
   // in the game.  e.g. pinochle does not
   // use cards 2-8 of any suit

   public CardGameFramework( int numPacks, int numJokersPerPack,
                             int numUnusedCardsPerPack,  Card[] unusedCardsPerPack,
                             int numPlayers, int numCardsPerHand)
   {
      int k;

      // filter bad values
      if (numPacks < 1 || numPacks > 6)
         numPacks = 1;
      if (numJokersPerPack < 0 || numJokersPerPack > 4)
         numJokersPerPack = 0;
      if (numUnusedCardsPerPack < 0 || numUnusedCardsPerPack > 50) //  > 1 card
         numUnusedCardsPerPack = 0;
      if (numPlayers < 1 || numPlayers > MAX_PLAYERS)
         numPlayers = 4;
      // one of many ways to assure at least one full deal to all players
      if  (numCardsPerHand < 1 ||
              numCardsPerHand >  numPacks * (52 - numUnusedCardsPerPack)
                      / numPlayers )
         numCardsPerHand = numPacks * (52 - numUnusedCardsPerPack) / numPlayers;

      // allocate
      this.unusedCardsPerPack = new Card[numUnusedCardsPerPack];
      this.hand = new Hand[numPlayers];
      for (k = 0; k < numPlayers; k++)
         this.hand[k] = new Hand();
      deck = new Deck(numPacks);

      // assign to members
      this.numPacks = numPacks;
      this.numJokersPerPack = numJokersPerPack;
      this.numUnusedCardsPerPack = numUnusedCardsPerPack;
      this.numPlayers = numPlayers;
      this.numCardsPerHand = numCardsPerHand;
      for (k = 0; k < numUnusedCardsPerPack; k++)
         this.unusedCardsPerPack[k] = unusedCardsPerPack[k];

      // prepare deck and shuffle
      newGame();
   }

   // constructor overload/default for game like bridge
   public CardGameFramework()
   {
      this(1, 0, 0, null, 4, 13);
   }

   public Hand getHand(int k)
   {
      // hands start from 0 like arrays

      // on error return automatic empty hand
      if (k < 0 || k >= numPlayers)
         return new Hand();

      return hand[k];
   }

   public Card getCardFromDeck() { return deck.dealCard(); }

   public int getNumCardsRemainingInDeck() { return deck.getNumCards(); }

   public void newGame()
   {
      int k, j;

      // clear the hands
      for (k = 0; k < numPlayers; k++)
         hand[k].resetHand();

      // restock the deck
      deck.init(numPacks);

      // remove unused cards
      for (k = 0; k < numUnusedCardsPerPack; k++)
         deck.removeCard( unusedCardsPerPack[k] );

      // add jokers
      for (k = 0; k < numPacks; k++)
         for ( j = 0; j < numJokersPerPack; j++)
            deck.addCard( new Card('X', Card.Suit.values()[j]) );

      // shuffle the cards
      deck.shuffle();
   }

   public boolean deal()
   {
      // returns false if not enough cards, but deals what it can
      int k, j;
      boolean enoughCards;

      // clear all hands
      for (j = 0; j < numPlayers; j++)
         hand[j].resetHand();

      enoughCards = true;
      for (k = 0; k < numCardsPerHand && enoughCards ; k++)
      {
         for (j = 0; j < numPlayers; j++)
            if (deck.getNumCards() > 0)
               hand[j].takeCard( deck.dealCard() );
            else
            {
               enoughCards = false;
               break;
            }
      }

      return enoughCards;
   }

   void sortHands()
   {
      int k;

      for (k = 0; k < numPlayers; k++)
         hand[k].sort();
   }

   Card playCard(int playerIndex, int cardIndex)
   {
      // returns bad card if either argument is bad
      if (playerIndex < 0 ||  playerIndex > numPlayers - 1 ||
              cardIndex < 0 || cardIndex > numCardsPerHand - 1)
      {
         //Creates a card that does not work
         return new Card('M', Card.Suit.SPADES);
      }

      // return the card played
      return hand[playerIndex].playCard(cardIndex);

   }


   boolean takeCard(int playerIndex)
   {
      // returns false if either argument is bad
      if (playerIndex < 0 || playerIndex > numPlayers - 1)
         return false;

      // Are there enough Cards?
      if (deck.getNumCards() <= 0)
         return false;

      return hand[playerIndex].takeCard(deck.dealCard());
   }
}



