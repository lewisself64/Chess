package chess;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Application 
{	
	// Create a chess board
	static Board board = new Board();
	
	public static void main(String[] args) 
    {
        Runnable r = new Runnable() 
        {
            @Override
            public void run() 
            {
                JFrame gui = new JFrame("Lewis' Chess Game");
                gui.add(board.getGui());
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setLocationByPlatform(true);
                gui.setMinimumSize(gui.getSize()); // Prevent the user resizing less than the minimum width
                gui.pack();
                gui.setVisible(true);
            }
        };

        SwingUtilities.invokeLater(r);
    }
	
	/* Getters & Setters */
	
    public static Board getBoard() 
    {
		return board;
	}

	public static void setBoard(Board pBoard) 
	{
		board = pBoard;
	}
}
