package pieces;

import java.util.ArrayList;

public class Rook extends Piece 
{
	final static String name  = "Rook";
	final static int value    = 5;
	
	public Rook(String pColor, int pY, int pX)
	{
		super(name, pColor, value, pY, pX, "../images/rook-" + pColor + ".png");
	}
	
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
	
	public String getName()
	{
		return name;
	}
		
	public int getValue()
	{
		return value;
	}
}
