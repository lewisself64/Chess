package pieces;

import java.util.ArrayList;

public class Queen extends Piece 
{
	final static String name  = "Queen";
	final static String image = "../images/queen-white.png";
	final static int value    = 9;
	
	public Queen(String pColor, int pY, int pX)
	{
		super(name, pColor, value, pY, pX, image);
	}
	
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
	
	public String getName()
	{
		return name;
	}
	
	public String getImage()
	{
		return image;
	}
	
	public int getValue()
	{
		return value;
	}
}