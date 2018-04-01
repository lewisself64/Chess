package pieces;

import java.util.ArrayList;

/**
 * Creates all the attributes and movements for the King
 * 
 * @author Lewis Self
 * @version 1.0.0
 */
public class King extends Piece 
{
	final static String name = "King";
	final static int value   = 999999999; // The king is priceless
	
	/**
	 * Creates the King object.
	 * 
	 * @param pColor	The Kings colour
	 * @param pY		The y coordinate for the King
	 * @param pX		The x coordinate for the King
	 */
	public King(String pColor, int pY, int pX)
	{
		super(name, pColor, value, pY, pX, "../images/king-" + pColor + ".png");
	}
	
	/**
	 * @see pieces.Piece#possibleMoves()
	 */
	@Override
	public ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves()
	{
		// Create an array that holds all possible paths
		ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		// Get current piece location from piece class
		int x = getX();
		int y = getY();
							
		// Creates all possible single space moves for the king
		possibleMoves.add(singlePath(y - 1, x - 1));
		possibleMoves.add(singlePath(y - 1, x));
		possibleMoves.add(singlePath(y, x - 1));
		possibleMoves.add(singlePath(y + 1, x + 1));
		possibleMoves.add(singlePath(y - 1, x + 1));
		possibleMoves.add(singlePath(y + 1, x - 1));
		possibleMoves.add(singlePath(y - 1, x));
		possibleMoves.add(singlePath(y + 1, x));
		possibleMoves.add(singlePath(y, x + 1));

		return possibleMoves;
	}
}