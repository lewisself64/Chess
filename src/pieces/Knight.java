package pieces;

import java.util.ArrayList;

public class Knight extends Piece 
{
	private final static String name = "Knight";
	private final static int value   = 3;
	
	public Knight(String pColor, int pY, int pX)
	{
		super(name, pColor, value, pY, pX, "../images/knight-" + pColor + ".png");
	}
	
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
	
	public String getName()
	{
		return name;
	}
		
	public int getValue()
	{
		return value;
	}
}
