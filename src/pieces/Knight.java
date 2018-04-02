package pieces;

import java.util.ArrayList;

/**
 * Creates all the attributes and movements for the Knight
 * 
 * @author Lewis Self
 * @version 1.0.0
 */
public class Knight extends Piece 
{
	private final static String name = "Knight";
	private final static int value   = 3;
	
	/**
	 * Creates the Knight object.
	 * 
	 * @param pColor	The Knights colour
	 * @param pY		The y coordinate for the Knight
	 * @param pX		The x coordinate for the Knight
	 */
	public Knight(String pColor, int pY, int pX)
	{
		super(name, pColor, value, pY, pX, "/images/knight-" + pColor + ".png");
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
							
		// Creates all possible single space moves for the knight
		possibleMoves.add(singlePath(y + 2, x - 1));
		possibleMoves.add(singlePath(y + 2, x + 1));
		possibleMoves.add(singlePath(y - 2, x - 1));
		possibleMoves.add(singlePath(y - 2, x + 1));
		possibleMoves.add(singlePath(y + 1, x - 2));
		possibleMoves.add(singlePath(y + 1, x + 2));
		possibleMoves.add(singlePath(y - 1, x - 2));
		possibleMoves.add(singlePath(y - 1, x + 2));

		return possibleMoves;
	}
}