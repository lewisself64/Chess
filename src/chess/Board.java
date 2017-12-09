package chess;

import java.awt.Color;
import java.awt.GridLayout;
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
    private Square[][] chessBoardSquares = new Square[8][8];
    private Piece movingPiece = null;
    private JPanel chessBoard = new JPanel(new GridLayout(0, 8));
    
	Board()
	{
        // Add squares to the chess board
        for (int i = 0; i < getChessBoardSquares().length; i++)
        {
            for (int j = 0; j < getChessBoardSquares().length; j++) 
            {
            	chessBoard.add(getChessBoardSquares()[j][i]);
            }
        }
		
        // Create pieces for both colours
        setUpWhite();
        //setUpBlack();
	}
	
	public void setUpWhite()
	{
        chessBoardSquares[0][7].setPiece(new Rook("White", 0, 7));
        chessBoardSquares[7][7].setPiece(new Rook("White", 7, 7));
        
        chessBoardSquares[1][7].setPiece(new Knight("White", 1, 7));
        chessBoardSquares[6][7].setPiece(new Knight("White", 6, 7));
        
        chessBoardSquares[2][7].setPiece(new Bishop("White", 2, 7));
        chessBoardSquares[5][7].setPiece(new Bishop("White", 5, 7));
        
        chessBoardSquares[3][7].setPiece(new Queen("White", 3, 7));
        chessBoardSquares[4][7].setPiece(new King("White", 4, 7));
        
        // Place all pawns on the second rank
        for(int i = 0; i < 8; i++)
        {
            chessBoardSquares[i][6].setPiece(new Pawn("White", i, 6));
        }
	}
	
	public void setUpBlack()
	{
        chessBoardSquares[0][0].setPiece(new Rook("Black", 0, 0));
        chessBoardSquares[7][0].setPiece(new Rook("Black", 7, 0));
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
		
	public Square[][] getChessBoardSquares() {
		return chessBoardSquares;
	}

	public void setChessBoardSquares(Square[][] chessBoardSquares) {
		this.chessBoardSquares = chessBoardSquares;
	}

	public Piece getMovingPiece() {
		return movingPiece;
	}

	public void setMovingPiece(Piece movingPiece) {
		this.movingPiece = movingPiece;
	}

	public void highlightPossibleMoves(ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves)
	{
	  // Loop through each path the piece can take
	  for(int j = 0; j < possibleMoves.size(); j++)
	  {
		  for(int k = 0; k < possibleMoves.get(j).size(); k++)
		  {
			  // Get the current square in the loop
			  Square currentSquare = chessBoardSquares[possibleMoves.get(j).get(k).get(0)][possibleMoves.get(j).get(k).get(1)];
			  
			  // Highlight the square selected (This also sets the canOccupy as true)
			  currentSquare.highlightSquare();
			  
			  // Check if the current square is occupied
			  if(currentSquare.isSquareOccupied())
			  {
				  // If the piece on the current square is the same as the piece that is currently moving, remove the highlight (This also sets the canOccupy as false)
				  if(currentSquare.getPiece().getColor() == movingPiece.getColor())
				  {
					  currentSquare.removeHighlight();
				  }
				  else
				  {
					  // If the piece is not the same colour, change the highlight to red to indicate it can be captured
					  currentSquare.highlightSquare(Color.RED);
				  }
				  
				  // In both cases brake out of the path's loop
				  break;
			  }
		  }
	  }
	}
	
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
	
	public void move(ArrayList<Integer> moveFrom, ArrayList<Integer> moveTo)
	{
		// Get the piece from the moveFrom square
		Piece movingPiece = chessBoardSquares[moveFrom.get(0)][moveFrom.get(1)].getPiece();
		
		// Remove the piece icon
		chessBoardSquares[moveFrom.get(0)][moveFrom.get(1)].removeIcon();
		
		// Change the movingPiece coordinates to the new location
		movingPiece.setY(moveTo.get(0));
		movingPiece.setX(moveTo.get(1));
		
		// Remove the piece from the old location
		chessBoardSquares[moveFrom.get(0)][moveFrom.get(1)].setPiece(null);
		
		// Set the piece on the new location
		chessBoardSquares[moveTo.get(0)][moveTo.get(1)].setPiece(movingPiece);
		
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
}