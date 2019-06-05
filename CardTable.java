import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*****************************************************************************
 * CardTable  - Class that embodies the JPanels and Layout(s) needed for the
 * application. This is where all the cards and controls will be placed.
 *****************************************************************************/
public class CardTable extends JFrame implements ActionListener
{
   //CardTable static data
   static int MAX_CARDS_PER_HAND = 56;
   static int MAX_PLAYERS = 2;

   //CardTable private data
   private int numCardsPerHand;
   private int numPlayers;

   //CardTable public data
   //3 panels - One Computer player, One Human player, One play area
   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea, pnlGame;

   //Constructor and mutator - Adds panels to the JFrame
   CardTable(String title, int numCardsPerHand, int numPlayers)
   {
      //the string - title - will be displayed on the window frame.
      super(title);

      //Create the menu bar

      JMenuBar menuBar = new JMenuBar();
      JMenu fileMenu = new JMenu("File");
      JMenuItem deal = new JMenuItem("Deal");
      deal.addActionListener(this);
      fileMenu.add(deal);
      JMenuItem exit = new JMenuItem("Exit");
      exit.addActionListener( this);
      fileMenu.addSeparator();
      fileMenu.add(exit);
      menuBar.add(fileMenu);
      JMenu helpMenu = new JMenu("Help");
      JMenuItem about = new JMenuItem("About");
      about.addActionListener(this);
      helpMenu.add(about);
      menuBar.add(helpMenu);
      JMenuBar bar = new JMenuBar( );
      bar.add(menuBar);
      setJMenuBar(bar);

      //BorderLayout manager - BorderLayout(int horizontalGap, int verticalGap)
      setLayout(new BorderLayout());

      //Sets values
      this.numCardsPerHand = numCardsPerHand;
      this.numPlayers = numPlayers;

      //GridLayout(int rows, int columns)
      //defines a panel for each field
      pnlComputerHand = new JPanel(new GridLayout(1, numCardsPerHand));
      pnlHumanHand = new JPanel(new GridLayout(1, numCardsPerHand));
      pnlPlayArea = new JPanel(new GridLayout(2, numPlayers +1));

      //Place panels to their specific location
      add(pnlComputerHand, BorderLayout.NORTH);
      add(pnlHumanHand, BorderLayout.SOUTH);
      add(pnlPlayArea, BorderLayout.CENTER);

      //Names each section and places a border
      pnlComputerHand.setBorder(new TitledBorder("Computer Hand"));
      pnlHumanHand.setBorder(new TitledBorder("Your Hand"));
      pnlPlayArea.setBorder(new TitledBorder("Playing Area"));
   }

   public int getNumCardsPerHand()
   {
      return numCardsPerHand;
   }

   public int getNumPlayers()
   {
      return numPlayers;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      String buttonString = e.getActionCommand( );
      if (buttonString.equals("Deal"))
      {
         this.setVisible(false);
         Assig6_Phase1.main(null);
      }
      else if (buttonString.equals("Exit"))
         System.exit(0);
      else if (buttonString.contentEquals("About"))
         JOptionPane.showMessageDialog(this,
               "GUI Cards\n\n"
                     + "A project by:\n "
                     + " Abby Packham\n"
                     + "  Carlos Orduna\n"
                     + "  Dalia Faria\n"
                     + "  George Blombach\n"
                     + "  Roger Terrill\n\n"
                     + " "
                     + "CSUMB CST338, June 2019");
   }
}

/*****************************************************************************
 *                        End of CardTable                                   *
 *****************************************************************************/
