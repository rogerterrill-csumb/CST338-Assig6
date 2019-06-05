import javax.swing.*;

/*****************************************************************************
 * GUICard - A class that manages the reading and building of the card image
 * Icons                                                 *
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
         for (int cardSuit = 0; cardSuit < iconCards[cardValue].length;
              cardSuit++)
         {
            //numCard will return string at index cardValue
            //numSuit will return suit at index cardSuit
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
      String[] cardValues =
            {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "X"};
      return cardValues[cardNum];
   }

   // return string of suit name
   static String numSuit(int suitNum)
   {
      if (suitNum < 0 || suitNum > 3)
         return "invalid";

      return Card.Suit.values()[suitNum]
            .toString().toUpperCase().substring(0, 1);

   }

   // return integer of card value
   public static int valueToInt(Card card)
   {
      return Card.valueOfCard(card);
   }

   //Converts suit to number
   public static int suitToNum(Card card)
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
 *                        End of GUICard                                     *
 *****************************************************************************/