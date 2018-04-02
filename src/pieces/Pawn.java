package pieces;

import java.util.ArrayList;

/**
 * Creates all the attributes and movements for the Pawn
 * 
 * @author Lewis Self
 * @version 1.0.0
 */
public class Pawn extends Piece 
{
	private boolean enPassant = false;
	
	/**
	 * Creates the Pawn object.
	 * 
	 * @param pColor	The Pawns colour
	 * @param pY		The y coordinate for the Pawn
	 * @param pX		The x coordinate for the Pawn 
	 */
	public Pawn(String pColor, int pY, int pX)
	{
		super("Pawn", pColor, 1, pY, pX, "/images/pawn-" + pColor + ".png");
	}
	
	/**
	 * @see pieces.Piece#possibleMoves()
	 */
	@Override
	public ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves()
	{
		// Create an array that holds all possible paths, moves and coordinates
		ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		ArrayList<ArrayList<Integer>> path = new ArrayList<ArrayList<Integer>>();
		
		// Get current piece location from piece class
		int x = getX();
		int y = getY();
		
		// Check the pieces colour, white moves upwards, black moves downwards
		if(getColor() == "white")
		{			
			// Check if this is the first time a pawn has moved.
			if(isFirstMove() == true)
			{
				// Loop through twice (Pawns can move 2 squares on their first move)
				for(int i = x - 1; i > x - 3; i--)
				{			
					ArrayList<Integer> moves = new ArrayList<Integer>();
					
					moves.add(y);
					moves.add(i);
					path.add(moves);
				}
				
				possibleMoves.add(path);
			}
			else
			{
				// If pawn has already moved, only let it move one square
				possibleMoves.add(singlePath(y, x - 1));
			}
		}
		else
		{
			// Check if this is the first time a pawn has moved.
			if(isFirstMove() == true)
			{
				// Loop through twice (Pawns can move 2 squares on their first move)
				for(int i = x + 1; i < x + 3; i++)
				{			
					ArrayList<Integer> moves = new ArrayList<Integer>();
					
					moves.add(y);
					moves.add(i);
					path.add(moves);
				}
				
				possibleMoves.add(path);
			}
			else
			{
				// If pawn has already moved, only let it move one square
				possibleMoves.add(singlePath(y, x + 1));
			}
		}
		
		return possibleMoves;
	}
	
	/**
	 * @return	Returns true if the move is an en-passant move and false if it's not
	 */
	public boolean isEnPassant() 
	{
		return enPassant;
	}

	/**
	 * @param enPassant	Sets the en-passant property for the pawn
	 */
	public void setEnPassant(boolean pEnPassant) 
	{
		enPassant = pEnPassant;
	}
}