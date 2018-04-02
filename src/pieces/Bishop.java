package pieces;

import java.util.ArrayList;

/**
 * Creates all the attributes and movements for the Bishop
 * 
 * @author Lewis Self
 * @version 1.0.0
 */
public class Bishop extends Piece 
{
	final static String name = "Bishop";
	final static int value   = 3;
	
	/**
	 * Creates the Bishop object
	 * 
	 * @param pColor	The Bishops colour
	 * @param pY		The y coordinate for the Bishop
	 * @param pX		The x coordinate for the Bishop
	 */
	public Bishop(String pColor, int pY, int pX)
	{
		super(name, pColor, value, pY, pX, "/images/bishop-" + pColor + ".png");
	}
	
	/**
	 * @see pieces.Piece#possibleMoves()
	 */
	@Override
	public ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves()
	{
		// Create an array that holds all possible paths
		ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		// Give the Bishop the ability to move across all diagonals
		possibleMoves.add(createPathBottomLeft());
		possibleMoves.add(createPathTopLeft());
		possibleMoves.add(createPathBottomRight());
		possibleMoves.add(createPathTopRight());
		
		// Return an ArrayList containing all paths and moves for the Bishop
		return possibleMoves;
	}
}