package pieces;

import java.util.ArrayList;

/**
 * Creates all the attributes and movements for the Rook
 * 
 * @author Lewis Self
 * @version 1.0.0
 */
public class Rook extends Piece 
{
	final static String name = "Rook";
	final static int value   = 5;
	
	/**
	 * Creates the Rook object.
	 * 
	 * @param pColor	The Rooks colour
	 * @param pY		The y coordinate for the Rook
	 * @param pX		The x coordinate for the Rook
	 */
	public Rook(String pColor, int pY, int pX)
	{
		super(name, pColor, value, pY, pX, "../images/rook-" + pColor + ".png");
	}
	
	/**
	 * @see pieces.Piece#possibleMoves()
	 */
	@Override
	public ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves()
	{
		// Create an array that holds all possible paths
		ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		// Give the rook the ability to move across the ranks and files
		possibleMoves.add(createPathUp());
		possibleMoves.add(createPathRight());
		possibleMoves.add(createPathLeft());
		possibleMoves.add(createPathDown());
		
		return possibleMoves;
	}
}