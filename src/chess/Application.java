package chess;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// Import all board classes
import board.Board;

/** 
 * This class holds the main method which creates the board GUI and sets up all the pieces for the chess game.
 * 
 * @author Lewis Self
 * @version 1.0.0
 */
public class Application
{
	// Create a chess board
	private static Board board = new Board();
	
	/**
	 * The main method to run on the program execution.
	 * 
	 * @param args	main method argument
	 */
	public static void main(String[] args) 
    {
		// Create a new runnable object
        Runnable r = new Runnable() 
        {
            /**
             * @see java.lang.Runnable#run()
             */
            @Override
            public void run() 
            {
            	// Create GUI JFrame
                JFrame gui = new JFrame("Master Chess");
                
                // Creates the GUI for the board
                gui.add(board.getGui());
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setLocationByPlatform(true);
                gui.pack();
                gui.setVisible(true);
                gui.setResizable(false);
            }
        };

        SwingUtilities.invokeLater(r);
    }
	
	/* ===================== */
	/* = Getters & Setters = */
	/* ===================== */
	
	/**
	 * Returns a board object based on the current state of the board in the game
	 * 
	 * @return	Current state of the Board
	 */
	public static Board getBoard() 
	{
		return board;
	}
	
	/**
	 * Sets the board state to the new pBoard which is passed into the parameters
	 * 
	 * @param pBoard	The new board state
	 */
	public static void setBoard(Board pBoard) 
	{
		board = pBoard;
	}
}