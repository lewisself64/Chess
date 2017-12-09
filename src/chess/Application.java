package chess;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Application 
{
    private final static JPanel gui = new JPanel();
    
	// Create a chess board
	static Board board = new Board();
	
    public static void main(String[] args) 
    {
        //gui.add(board.getChessBoard());
    	
        Runnable r = new Runnable() 
        {
            @Override
            public void run() 
            {
                // set up the main GUI
                createChessGUI();
            	
                JFrame gui = new JFrame("Lewis' Chess Game");
                gui.add(gui);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setLocationByPlatform(true);
                gui.pack();
                gui.setMinimumSize(gui.getSize()); // Prevent the user resizing less than the minimum width
                gui.setVisible(true);
            }
        };
        
        SwingUtilities.invokeLater(r);
    }
    
	public static void createChessGUI()
	{		
        // Loop through all rows
        for (int i = 0; i < board.getChessBoardSquares().length; i++) 
        {
        	// Loop through all columns
            for (int j = 0; j < board.getChessBoardSquares()[i].length; j++) 
            {
            	// Create a new Square and set the margin to 0 and set the colour of the square
                Square square = new Square(i, j);
                
                square.setMargin(new Insets(0,0,0,0));                
                square.setBackground(square.getColor());

                // Put the square inside the board array
                board.getChessBoardSquares()[j][i] = square;
                
                // Check if the square is on the first or last rank.
                if(i == 0 || i == 7)
                {
                	// This square can promote pawns
                	square.promotionSquare = true;
                }

                // Create ActionListener for when the square has been clicked
                square.addActionListener(new ActionListener()
        		{
				  public void actionPerformed(ActionEvent e) 
				  {
					  // Check if the selected square is occupied by a piece
					  if(square.isSquareOccupied())
					  {
						  // Get the currently moving piece
						  board.setMovingPiece(square.getPiece());
						  
						  // Check if the piece is not active active
						  if(!square.getPiece().isActive())
						  {
							  // Remove all highlights on board and set the piece as active
							  board.removeAllHighlights();
							  square.getPiece().setActive(true);
							  
							  // Highlight all legal moves for the current piece
							  board.highlightPossibleMoves(square.getPiece().possibleMoves());
						  }
						  else
						  {
							// Remove all highlights from the board
							board.removeAllHighlights();
							
							// Set the piece as not active and remove the currently moving piece
							square.getPiece().setActive(false);
							board.setMovingPiece(null);
						  }
					  }
					  
					  // Check if no piece has been selected
					  if(board.getMovingPiece() != null)
					  {
						  // Check that the square can be occupied
						  if(square.getCanOccupy())
						  {
							  // Get the coordinates from the move from and to
							  ArrayList<Integer> moveFrom = new ArrayList<Integer>();
							  ArrayList<Integer> moveTo = new ArrayList<Integer>();
							  
							  // Add the move to coordinates
							  moveTo.add(square.getYCoordinate());
							  moveTo.add(square.getXCoordinate());
							  
							  // Add the move from coordinates
							  moveFrom.add(board.getMovingPiece().getY());
							  moveFrom.add(board.getMovingPiece().getX());
							  
							  // Run the move function from the moveFrom to the moveTo coordinates
							  board.move(moveFrom, moveTo);
							  
							  // Once the piece has moved remove all the highlights from the board
							  board.removeAllHighlights();
						  }
					  }
				  }
        		});
            }
        }
	}
	
    public final static JComponent getGui() 
    {
        return gui;
    }
}