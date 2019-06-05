/*****************************************************************************
 * Card - A class that provides a card object and checks to see if the card  *
 * created has valid values.                                                 *
 *****************************************************************************/

public class Card
{
   public enum Suit{CLUBS, DIAMONDS, HEARTS, SPADES};
   //public static char[] valuRanks =
   //{'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'X'};
   public static String valuRanks = "A23456789TJQKX";
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
      return this.value;
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
    * @return Returns boolean result after checking if both value and suit are
    * equal
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
   static int valueOfCard(Card card)
   {

      // It traverses the valuRanks and check which matches the card value
      // Then it returns the index position as the value
      //System.out.print("Card Value: " + card.getValue() + "\n");
      if(valuRanks.indexOf(card.getValue()) > -1)
         return valuRanks.indexOf(card.getValue());
      else
         return -11;

   }
}
/*****************************************************************************
 *                        End of Card                                        *
 *****************************************************************************/