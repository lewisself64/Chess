package chess;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Board 
{
	// Create panels for GUI
    private JPanel gui = new JPanel();
    private JPanel chessBoard;
    
    // Create an 8 by 8 array of squares (The chess board)
    private Square[][] chessBoardSquares = new Square[8][8];
    
    // Default no movingPiece
    private Piece movingPiece = null;
    
    // Default the current turn to white from start of the game
    private String currentTurn = "white"; 
        
	Board()
	{
        // set up the main GUI
        chessBoard = new JPanel(new GridLayout(0, 8));
        gui.add(chessBoard);
        
        createChessGUI();

        // Create pieces for both colours
        setUpWhite();
        setUpBlack();
	}
	
	/**
	 * Set's up all starting positions for whites pieces
	 */
	public void setUpWhite()
	{
		// Create the white rooks
        chessBoardSquares[0][7].setPiece(new Rook("white", 0, 7));
        chessBoardSquares[7][7].setPiece(new Rook("white", 7, 7));
        
        // Create the white knights
        chessBoardSquares[1][7].setPiece(new Knight("white", 1, 7));
        chessBoardSquares[6][7].setPiece(new Knight("white", 6, 7));
        
        // Create white bishops
        chessBoardSquares[2][7].setPiece(new Bishop("white", 2, 7));
        chessBoardSquares[5][7].setPiece(new Bishop("white", 5, 7));
        
        // Create white queen and king
        chessBoardSquares[3][7].setPiece(new Queen("white", 3, 7));
        chessBoardSquares[4][7].setPiece(new King("white", 4, 7));
        
        // Create the pawns on the 2nd rank
        for(int i = 0; i < 8; i++)
        {
            chessBoardSquares[i][6].setPiece(new Pawn("white", i, 6));
        }
	}
	
	/**
	 * Set's up all starting positions for blacks pieces
	 */
	public void setUpBlack()
	{
		// Create black rooks
        chessBoardSquares[0][0].setPiece(new Rook("black", 0, 0));
        chessBoardSquares[7][0].setPiece(new Rook("black", 7, 0));
        
        // Create black knights
        chessBoardSquares[1][0].setPiece(new Knight("black", 1, 0));
        chessBoardSquares[6][0].setPiece(new Knight("black", 6, 0));
        
        // Create black bishops
        chessBoardSquares[2][0].setPiece(new Bishop("black", 2, 0));
        chessBoardSquares[5][0].setPiece(new Bishop("black", 5, 0));
        
        // Create black king and queen
        chessBoardSquares[3][0].setPiece(new Queen("black", 3, 0));
        chessBoardSquares[4][0].setPiece(new King("black", 4, 0));
        
        // Place all pawns on the 7th rank
        for(int i = 0; i < 8; i++)
        {
            chessBoardSquares[i][1].setPiece(new Pawn("black", i, 1));
        }
	}
	
	/**
	 * Removes every piece on the board
	 */
	public void clearBoard()
	{
		// Loop through all rows
		for (int i = 0; i < chessBoardSquares.length; i++) 
		{
			// Loop through all columns
		    for (int j = 0; j < chessBoardSquares[i].length; j++) 
		    {
		    	// Remove the pieces from the square
		    	chessBoardSquares[j][i].setPiece(null);
		    }
		}
	}
	
	/**
	 * @param square - The square the pawn is promoting on
	 * @param piece - The pawn that is being replaced
	 * 
	 * Removes the pawn from the board and replaces it with another piece of the users choice.
	 */
	public void promotePawn(Square square, Piece piece)
	{
		// Remove the pawn from the board
		square.setPiece(null);
		
		// Get the coordinates for the square the pawn is promoting on
		int x = square.getXCoordinate();
		int y = square.getYCoordinate();
		
		// Add radio buttons to give the option to choose the piece they wish to promote too
	    final JRadioButton queenButton  = new JRadioButton("Queen");
	    final JRadioButton rookButton   = new JRadioButton("Rook");
	    final JRadioButton knightButton = new JRadioButton("Knight");
	    final JRadioButton bishopButton = new JRadioButton("Bishop");
	    
	    // Create a panel to display the options in
	    final JPanel promotionOptions = new JPanel();

	    // Add the game to the JPanel
	    promotionOptions.add(new JLabel("Promote too: "));
	    promotionOptions.add(queenButton);
	    promotionOptions.add(rookButton);
	    promotionOptions.add(knightButton);
	    promotionOptions.add(bishopButton);

	    // Display options to user
	    JOptionPane.showMessageDialog(null, promotionOptions);
	    
	    if(queenButton.isSelected())
	    {
	    	// Create Queen object in place of pawn
	    	square.setPiece(new Queen(piece.getColor(), y, x));
	    }
	    
	    if(rookButton.isSelected())
	    {
	    	// Create Rook object in place of pawn
	    	square.setPiece(new Rook(piece.getColor(), y, x));
	    }
	    
	    if(knightButton.isSelected())
	    {
	    	// Create Knight object in place of pawn
	    	square.setPiece(new Knight(piece.getColor(), y, x));
	    }
	    
	    if(bishopButton.isSelected())
	    {
	    	// Create Bishop object in place of pawn
	    	square.setPiece(new Bishop(piece.getColor(), y, x));
	    }
	    
	    // If nothing is selected default to queen (If the user hits the close button instead of choosing a piece)
	    if(!(queenButton.isSelected()) && !(rookButton.isSelected()) && !(knightButton.isSelected()) && !(bishopButton.isSelected()))
	    {
	    	square.setPiece(new Queen(piece.getColor(), y, x));
	    }
	}
	
	/**
	 * Implements all castling moves in the game
	 */
	public void castling()
	{
		// Check if the current moving piece is a king
		if(movingPiece instanceof King)
		{
			// Get the king side and queen side rooks
			Piece kingsideRook = chessBoardSquares[7][movingPiece.getX()].getPiece();
			
			// Check the king side rook is present and the king and rook haven't moved in the game
			if((kingsideRook != null) && (kingsideRook.isFirstMove()) && movingPiece.isFirstMove())
			{
				// Check the squares between the king and rook to make sure they are empty
				if(!(chessBoardSquares[6][movingPiece.getX()]).isSquareOccupied() && !(chessBoardSquares[5][movingPiece.getX()]).isSquareOccupied())
				{
					// Highlight the kingside castling square. Use method overriding to change the "kingsideCastling" boolean in the square
					chessBoardSquares[6][movingPiece.getX()].highlightSquare("kingside");
				}
			}
		}
	}
	
	public void createChessGUI()
	{        
        // Loop through all rows
        for (int i = 0; i < chessBoardSquares.length; i++) 
        {
        	// Loop through all columns
            for (int j = 0; j < chessBoardSquares[i].length; j++) 
            {
            	// Create a new Square based on the current coordinates
                Square square = new Square(i, j);
                
                // Set the margin to 0 and set the colour of the square
                square.setMargin(new Insets(0, 0, 0, 0));
                square.setBackground(square.getColor());

                // Place the square inside the board array
                chessBoardSquares[j][i] = square;
                
                // Check if the square is on the first or last rank.
                if(i == 0 || i == 7)
                {
                	// Give these squares the ability to promote pawns
                	square.setPromotionSquare(true);
                }

                // This code runs once a square on the board has been clicked
                square.addActionListener(new ActionListener()
        		{
                	public void actionPerformed(ActionEvent e) 
                	{
                		// Check if the selected square is occupied by a piece
                		if(square.isSquareOccupied())
                		{
                			// Check if the current piece is the same colour as the colours current move
                			if(square.getPiece().getColor() == currentTurn)
                			{
                				// Get the currently moving piece
                				movingPiece = square.getPiece();
							  							  
                				// Check if the piece is not active
                				if(!square.getPiece().isActive())
                				{
                					// Remove all highlights on board and set the piece as active
                					removeAllHighlights();
                					square.getPiece().setActive(true);
								  
                					// Highlight all legal moves for the new piece
                					highlightLegalMoves(square.getPiece().possibleMoves());
                				}
                				else
                				{
                					// Remove all highlights from the board
                					removeAllHighlights();
								
                					// Set the piece as not active and remove the currently moving piece
                					square.getPiece().setActive(false);
                					movingPiece = null;
                				}
                			}
                		}
					  
                		// Check if no piece has been selected
                		if(movingPiece != null)
                		{
                			// Check that the square can be occupied
                			if(square.getCanOccupy())
                			{
                				// Get the coordinates from the move from and to
                				ArrayList<Integer> moveFrom = new ArrayList<Integer>();
                				ArrayList<Integer> moveTo   = new ArrayList<Integer>();
							  
                				// Add the move to coordinates
                				moveTo.add(square.getYCoordinate());
							  	moveTo.add(square.getXCoordinate());
							  
							  	// Add the move from coordinates
							  	moveFrom.add(movingPiece.getY());
							  	moveFrom.add(movingPiece.getX());
							  
							  	// Run the move function from the moveFrom to the moveTo coordinates
							  	move(moveFrom, moveTo);
							  
							  	// Check if the move is a kingside castling move
							  	if(square.isKingsideCastle())							  
							  	{
							  		// Get the coordinates from the move from and to
							  		ArrayList<Integer> rookFrom = new ArrayList<Integer>();
							  		ArrayList<Integer> rookTo   = new ArrayList<Integer>();
								  
							  		// Add the move to coordinates
							  		rookTo.add(5);
							  		rookTo.add(square.getXCoordinate());
								  
							  		// Add the move from coordinates
							  		rookFrom.add(7);
							  		rookFrom.add(movingPiece.getX());
								  
							  		// Move the rook form the original square to the square next to the king
							  		move(rookFrom, rookTo);
							  	}
							  
							  	// Check if the piece moving is a pawn moved to a promotion square
							  	if((movingPiece instanceof Pawn) && square.isPromotionSquare())
							  	{
							  		// Promote the pawn on the current square
							  		promotePawn(square, movingPiece);
							  	}
							  
							  	// Toggle the current turn between white and black
							  	if(currentTurn == "white")
							  	{
							  		currentTurn = "black";
							  	}
							  	else
							  	{
							  		currentTurn = "white";
							  	}
							  
							  	// Once the piece has moved remove all the highlights from the board
							  	removeAllHighlights();
                			}
                		}
                	}
        		});
            }
        }

        // Loop through each row
        for (int i = 0; i < chessBoardSquares.length; i++)
        {
        	// Loop through each column
            for (int j = 0; j < chessBoardSquares.length; j++) 
            {
            	// add the chess board squares to the JPanel board
            	chessBoard.add(chessBoardSquares[j][i]);
            }
        }
	}
	
	/**
	 * Takes the possible moves from the type of piece being moved and highlights the legal moves it can make based on the current state of the board
	 * 
	 * @param possibleMoves
	 */
	public void highlightLegalMoves(ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves)
	{
		// Checks if castling moves are possible
		castling();
		
	  // Loop through each path the piece can take
	  for(int j = 0; j < possibleMoves.size(); j++)
	  {
		  // Loop through each move in the path
		  for(int k = 0; k < possibleMoves.get(j).size(); k++)
		  {
			  // Get the current square in the loop
			  Square currentSquare = chessBoardSquares[possibleMoves.get(j).get(k).get(0)][possibleMoves.get(j).get(k).get(1)];
			  
			  // Check if the moving piece is a pawn to add capture moves if possible
			  if(movingPiece instanceof Pawn)
			  {
				  // Check if the colour of the piece is white (x - 1)
				  if(movingPiece.getColor() == "white")
				  {
					  // Check if x - 1 is on the board
					  if(movingPiece.getX() - 1 > -1)
					  {
						  // Check if the y + 1 is on the board
						  if(movingPiece.getY() + 1 < 8)
						  {
							  // Get the right attacking square
							  Square rightAttackWhite = chessBoardSquares[movingPiece.getY() + 1][movingPiece.getX() - 1];
							  
							  // Check if the square is occupied (Pawns can only move this direction if they can take an enemy piece)
							  if(rightAttackWhite.isSquareOccupied() && (rightAttackWhite.getPiece().getColor() != movingPiece.getColor()))
							  {
								  // Highlight square and notify player that they can capture the piece with a red colour
								  rightAttackWhite.highlightSquare(Color.RED);
							  }
						  }
						  
						  // Check if the y coordinate is on the board
						  if(movingPiece.getY() - 1 > -1)
						  {
							  // Get the square next top left of the pawn
							  Square leftAttackWhite = chessBoardSquares[movingPiece.getY() - 1][movingPiece.getX() - 1];
							  
							  // Check if the square is occupied (Pawns can only move this direction if they can take an enemy piece)
							  if(leftAttackWhite.isSquareOccupied() && (leftAttackWhite.getPiece().getColor() != movingPiece.getColor()))
							  {
								// Highlight square and notify player that they can capture the piece with a red colour
								  leftAttackWhite.highlightSquare(Color.RED);
							  }
						  }
					  }
				  }
				  else
				  {
					  // Check if the x + 1 is on the board
					  if(movingPiece.getX() + 1 < 8)
					  {
						// Check if the y + 1 is on the board
						  if(movingPiece.getY() + 1 < 8)
						  {
							  // Get the right attack square for a black pawn
							  Square rightAttackBlack = chessBoardSquares[movingPiece.getY() + 1][movingPiece.getX() + 1];
							  
							  // Check if the square is occupied and is an enemy
							  if(rightAttackBlack.isSquareOccupied() && (rightAttackBlack.getPiece().getColor() != movingPiece.getColor()))
							  {
								  rightAttackBlack.highlightSquare(Color.RED);
							  }
						  }
						  
						  if(movingPiece.getY() - 1 > -1)
						  {
							  // Get the left attack square for a black pawn
							  Square leftAttackBlack = chessBoardSquares[movingPiece.getY() - 1][movingPiece.getX() + 1];
							  
							  // Check if the square is occupied and is an enemy
							  if(leftAttackBlack.isSquareOccupied() && (leftAttackBlack.getPiece().getColor() != movingPiece.getColor()))
							  {
								  leftAttackBlack.highlightSquare(Color.RED);
							  }
						  }
					  }
				  }
			  }
			  
			  // Highlight the square selected (This also sets the canOccupy as true)
			  currentSquare.highlightSquare();
			  
			  // Check if the current square is occupied
			  if(currentSquare.isSquareOccupied())
			  {
				  // If the piece on the current square is the same colour as the piece that is currently moving, remove the highlight (This also sets the canOccupy as false)
				  if(currentSquare.getPiece().getColor() == movingPiece.getColor())
				  {
					  currentSquare.removeHighlight();
				  }
				  else
				  {
					  // Check if the piece not a pawn (They can't capture in their normal movement direction
					  if(!(movingPiece instanceof Pawn))
					  {
						  // Change the highlight to red to indicate it can be captured
						  currentSquare.highlightSquare(Color.RED);
					  }
					  else
					  {
						  // If the piece is a pawn, remove the highlight for it's forward path even if the piece is an enemy
						  currentSquare.removeHighlight();
					  }
				  }
				  
				  // In both cases brake out of the path's loop
				  break;
			  }
		  }
	  }	  
	}
	
	/**
	 * Removes all highlights from the board
	 */
	public void removeAllHighlights()
	{
		// Loop through chess board squares
		for (int i = 0; i < chessBoardSquares.length; i++) 
		{
		    for (int j = 0; j < chessBoardSquares[i].length; j++) 
		    {
		    	// Remove highlight
		    	chessBoardSquares[j][i].removeHighlight();
		    }
		}
	}
	
	/**
	 * @param moveFrom - The location the piece is moving from on the board
	 * @param moveTo - The location the piece is moving too on the board
	 * 
	 * The move method takes the piece on the moveFrom square and places it on the moveTo square. This method also runs the takePiece method if the square moving too is occupied
	 */
	public void move(ArrayList<Integer> moveFrom, ArrayList<Integer> moveTo)
	{
		// Get the piece from the moveFrom and moveTo square's
		Square moveFromSquare = chessBoardSquares[moveFrom.get(0)][moveFrom.get(1)];
		Square moveToSquare   = chessBoardSquares[moveTo.get(0)][moveTo.get(1)];
		
		// Get the piece on the move from square
		Piece movingPiece = moveFromSquare.getPiece();
		
		// Remove the piece icon from the old square
		moveFromSquare.removeIcon();
		
		// Change the movingPiece coordinates to the new location
		movingPiece.setY(moveTo.get(0));
		movingPiece.setX(moveTo.get(1));
		
		// Remove the piece from the old location
		moveFromSquare.setPiece(null);
		
		// Check if the square the piece is moving too is occupied
		if(moveToSquare.isSquareOccupied())
		{
			// Run method to take piece off the square
			moveToSquare.setPiece(null);
		}
		
		// Set the piece on the new location
		moveToSquare.setPiece(movingPiece);
		
		// If this is the first time a piece has moved, set firstMove to false
		if(movingPiece.isFirstMove() == true)
		{
			movingPiece.setFirstMove(false);
		}
	}
	
	/* Getters & Setters */
	
    public final JComponent getChessBoard() 
    {
        return chessBoard;
    }

    public Square[][] getChessBoardSquares() 
    {
		return chessBoardSquares;
	}

	public void setChessBoardSquares(Square[][] pChessBoardSquares) 
	{
		chessBoardSquares = pChessBoardSquares;
	}

	public Piece getMovingPiece() 
	{
		return movingPiece;
	}

	public void setMovingPiece(Piece pMovingPiece) 
	{
		movingPiece = pMovingPiece;
	}

	public String getCurrentTurn() 
	{
		return currentTurn;
	}

	public void setCurrentTurn(String pCurrentTurn) 
	{
		currentTurn = pCurrentTurn;
	}

	public void setGui(JPanel pGui) 
	{
		gui = pGui;
	}

	public void setChessBoard(JPanel pChessBoard) 
	{
		chessBoard = pChessBoard;
	}

	public final JComponent getGui() 
    {
        return gui;
    }
}
