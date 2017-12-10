package pieces;

import java.util.ArrayList;

public class King extends Piece 
{
	final static String name  = "King";
	final static int value    = 999999999;
	
	public King(String pColor, int pY, int pX)
	{
		super(name, pColor, value, pY, pX, "../images/king-" + pColor + ".png");
	}
	
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
	
	public String getName()
	{
		return name;
	}
		
	public int getValue()
	{
		return value;
	}
}
