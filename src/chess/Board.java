package chess;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Board 
{
    private final JPanel gui = new JPanel();
    private Square[][] chessBoardSquares = new Square[8][8];
    private JPanel chessBoard;
    private Piece movingPiece = null;
    private String currentTurn = "white"; // Default currentTurn to white (White moves first)
    
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
        
        // Create the pawns on the second rank
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
        chessBoardSquares[0][0].setPiece(new Rook("black", 0, 0));
        chessBoardSquares[7][0].setPiece(new Rook("black", 7, 0));
        
        chessBoardSquares[1][0].setPiece(new Knight("black", 1, 0));
        chessBoardSquares[6][0].setPiece(new Knight("black", 6, 0));
        
        chessBoardSquares[2][0].setPiece(new Bishop("black", 2, 0));
        chessBoardSquares[5][0].setPiece(new Bishop("black", 5, 0));
        
        chessBoardSquares[3][0].setPiece(new Queen("black", 3, 0));
        chessBoardSquares[4][0].setPiece(new King("black", 4, 0));
        
        // Place all pawns on the second rank
        for(int i = 0; i < 8; i++)
        {
            chessBoardSquares[i][1].setPiece(new Pawn("black", i, 1));
        }
	}
	
	public void clearBoard()
	{
		for (int i = 0; i < chessBoardSquares.length; i++) 
		{
		    for (int j = 0; j < chessBoardSquares[i].length; j++) 
		    {
		    	chessBoardSquares[j][i].setPiece(null);
		    }
		}
	}
	
	public void promotePawn(Square square, Piece piece)
	{
		square.setPiece(null);
		
		int x = square.getXCoordinate();
		int y = square.getYCoordinate();
		
		square.setPiece(new Queen(piece.getColor(), y, x));
	}
	
	public void castling()
	{
		if(movingPiece instanceof King)
		{
			Piece kingsideRook = chessBoardSquares[7][movingPiece.getX()].getPiece();
			
			if((kingsideRook != null) && (kingsideRook.isFirstMove()) && movingPiece.isFirstMove())
			{
				if(!(chessBoardSquares[6][movingPiece.getX()]).isSquareOccupied() && !(chessBoardSquares[5][movingPiece.getX()]).isSquareOccupied())
				{
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
            	// Create a new Square and set the margin to 0 and set the colour of the square
                Square square = new Square(i, j);
                
                square.setMargin(new Insets(0, 0, 0, 0));
                square.setBackground(square.getColor());

                // Put the square inside the board array
                chessBoardSquares[j][i] = square;
                
                // Check if the square is on the first or last rank.
                if(i == 0 || i == 7)
                {
                	// This square can promote pawns
                	square.setPromotionSquare(true);
                }

                // Create ActionListener for when the square has been clicked
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
								  
								  move(rookFrom, rookTo);
							  }
							  
							  // Check if the piece moving is a pawn moved to a promotion square
							  if((movingPiece instanceof Pawn) && square.isPromotionSquare())
							  {
								  promotePawn(square, movingPiece);
							  }
							  
							  // Switch the current move
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

        // Add squares to the chess board
        for (int i = 0; i < chessBoardSquares.length; i++)
        {
            for (int j = 0; j < chessBoardSquares.length; j++) 
            {
            	chessBoard.add(chessBoardSquares[j][i]);
            }
        }
	}
	
	public void highlightLegalMoves(ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves)
	{
		castling();
		
	  // Loop through each path the piece can take
	  for(int j = 0; j < possibleMoves.size(); j++)
	  {
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
					  
					  // Check if x - 1 is not out of the chess board
					  if(movingPiece.getX() - 1 > -1)
					  {
						  // Check if the y coordinate is on the board
						  if(movingPiece.getY() + 1 < 8)
						  {
							  Square rightAttackWhite = chessBoardSquares[movingPiece.getY() + 1][movingPiece.getX() - 1];
							  
							  // Check if the square is occupied (Pawns can only move this direction if they can take an enemy piece)
							  if(rightAttackWhite.isSquareOccupied() && (rightAttackWhite.getPiece().getColor() != movingPiece.getColor()))
							  {
								  rightAttackWhite.highlightSquare(Color.RED);
							  }
						  }
						  
						  // Check if the y coordinate is on the board
						  if(movingPiece.getY() - 1 > -1)
						  {
							  Square leftAttackWhite = chessBoardSquares[movingPiece.getY() - 1][movingPiece.getX() - 1];
							  
							  // Check if the square is occupied (Pawns can only move this direction if they can take an enemy piece)
							  if(leftAttackWhite.isSquareOccupied() && (leftAttackWhite.getPiece().getColor() != movingPiece.getColor()))
							  {
								  leftAttackWhite.highlightSquare(Color.RED);
							  }
						  }
					  }
				  }
				  else
				  {
					  // Check if x - 1 is not out of the chess board
					  if(movingPiece.getX() + 1 < 8)
					  {
						  if(movingPiece.getY() + 1 < 8)
						  {
							  Square rightAttackBlack = chessBoardSquares[movingPiece.getY() + 1][movingPiece.getX() + 1];
							  
							  if(rightAttackBlack.isSquareOccupied() && (rightAttackBlack.getPiece().getColor() != movingPiece.getColor()))
							  {
								  rightAttackBlack.highlightSquare(Color.RED);
							  }
						  }
						  
						  if(movingPiece.getY() - 1 > -1)
						  {
							  Square leftAttackBlack = chessBoardSquares[movingPiece.getY() - 1][movingPiece.getX() + 1];
							  
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
	 * Remove all highlights from the board
	 */
	public void removeAllHighlights()
	{
		for (int i = 0; i < chessBoardSquares.length; i++) 
		{
		    for (int j = 0; j < chessBoardSquares[i].length; j++) 
		    {
		    	chessBoardSquares[j][i].removeHighlight();
		    }
		}
	}
	
	/**
	 * @param moveFrom
	 * @param moveTo
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
	
    public final JComponent getChessBoard() 
    {
        return chessBoard;
    }

    public final JComponent getGui() 
    {
        return gui;
    }
}
