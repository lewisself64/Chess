package pieces;

import java.util.ArrayList;

/**
 * Creates all the attributes and movements for the Queen
 * 
 * @author Lewis Self
 * @version 1.0.0
 */
public class Queen extends Piece 
{
	final static String name = "Queen";
	final static int value   = 9;
	
	/**
	 * Creates the Queen object.
	 * 
	 * @param pColor	The Queens colour
	 * @param pY		The y coordinate for the Queen
	 * @param pX		The x coordinate for the Queen
	 */
	public Queen(String pColor, int pY, int pX)
	{
		super(name, pColor, value, pY, pX, "/images/queen-" + pColor + ".png");
	}
	
	/**
	 * @see pieces.Piece#possibleMoves()
	 */
	@Override
	public ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves()
	{
		// Create an array that holds all possible paths
		ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		// Give the queen the ability to move along the ranks, files and all diagonals
		possibleMoves.add(createPathBottomLeft());
		possibleMoves.add(createPathTopLeft());
		possibleMoves.add(createPathBottomRight());
		possibleMoves.add(createPathTopRight());
		possibleMoves.add(createPathUp());
		possibleMoves.add(createPathRight());
		possibleMoves.add(createPathLeft());
		possibleMoves.add(createPathDown());
				
		return possibleMoves;
	}
}