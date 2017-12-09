package pieces;

import java.util.ArrayList;

public class Bishop extends Piece 
{
	final static String name  = "Bishop";
	final static String image = "../images/bishop-white.png";
	final static int value    = 3;
	
	public Bishop(String pColor, int pY, int pX)
	{
		super(name, pColor, value, pY, pX, image);
	}
	
	public ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves()
	{
		// Create an array that holds all possible paths
		ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		// Give the bishop the ability to move across all diagonals
		possibleMoves.add(createPathBottomLeft());
		possibleMoves.add(createPathTopLeft());
		possibleMoves.add(createPathBottomRight());
		possibleMoves.add(createPathTopRight());
		
		return possibleMoves;
	}
}