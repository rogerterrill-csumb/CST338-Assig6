/*****************************************************************************
 * Deck - A class that provides a card object and checks to see if the card  *
 * created has valid values.                                                 *
 *****************************************************************************/
public class Deck
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
      int i;
      allocateMasterPack();
      for (i = 0; i < DECK_SIZE; i++)
      {
         cards[i] = masterPack[i % DECK_SIZE];
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
      int i;
      allocateMasterPack();
      for (i = 0; i < numPacks * DECK_SIZE; i++)
      {
         cards[i] = masterPack[i % DECK_SIZE];
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
         int i;
         topCard = 0;

         for (i = 0; i < numPacks * DECK_SIZE; i++)
         {
            cards[i] = masterPack[i % DECK_SIZE];
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
      for (int i = 0; i < topCard; i++)
      {
         int second = (int) (Math.random() * topCard);
         Card temp = cards[i];
         cards[i] = cards[second];
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
         for (masterPackIndex = 0; masterPackIndex < DECK_SIZE;
              masterPackIndex++)
         {
            if (masterPackIndex / NUM_OF_VALUES == 0)
            {
               masterPack[masterPackIndex] =
                     new Card(cardValues.charAt(masterPackIndex %
                           NUM_OF_VALUES), Card.Suit.SPADES);
            }
            if (masterPackIndex / NUM_OF_VALUES == 1)
            {
               masterPack[masterPackIndex] =
                     new Card(cardValues.charAt(masterPackIndex %
                           NUM_OF_VALUES), Card.Suit.CLUBS);
            }
            if (masterPackIndex / NUM_OF_VALUES == 2)
            {
               masterPack[masterPackIndex] =
                     new Card(cardValues.charAt(masterPackIndex %
                           NUM_OF_VALUES), Card.Suit.HEARTS);
            }
            if (masterPackIndex / NUM_OF_VALUES == 3)
            {
               masterPack[masterPackIndex] =
                     new Card(cardValues.charAt(masterPackIndex %
                           NUM_OF_VALUES), Card.Suit.DIAMONDS);
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
      for (int i = 0; i < topCard; i++)
      {
         if (card.equals(cards[i]))
         {
            cardInstances++;
         }
      }

      //System.out.println("Card instances is: " + cardInstances);

      // If card instance is equal or more than the number of decks ,it fails.
      if (cardInstances >= deckNum)
      {
         //System.out.println("Did not add card");
         return false;
      }
      System.out.println("Added the card to the deck");

      // Take added card and assign it to the top card.
      cards[topCard] = card;

      // Increase the topCard counter since we added a card
      topCard++;
      System.out.println("The topCard Value is: " + topCard);
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
 *                        End of Deck                                        *
 *****************************************************************************/