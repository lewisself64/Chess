package pieces;

import java.util.ArrayList;

public class Pawn extends Piece 
{
	private final static String name  = "Pawn";
	private final static String image = "../images/pawn-white.png";
	private final static int value    = 1;
	
	public Pawn(String pColor, int pY, int pX)
	{
		super(name, pColor, value, pY, pX, image);
	}
	
	public ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves()
	{
		// Create an array that holds all possible paths, moves and coordinates
		ArrayList<ArrayList<ArrayList<Integer>>> possibleMoves = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		ArrayList<ArrayList<Integer>> path = new ArrayList<ArrayList<Integer>>();
		
		// Get current piece location from piece class
		int x = getX();
		int y = getY();
		
		// Check the pieces colour, white moves upwards, black moves downwards
		if(getColor() == "White")
		{			
			// Check if this is the first time a pawn has moved.
			if(isFirstMove() == true)
			{
				// Loop through twice (Pawns can move 2 squares on their first move)
				for(int i = x - 1; i > x - 3; i--)
				{			
					ArrayList<Integer> moves = new ArrayList<Integer>();
					
					moves.add(y);
					moves.add(i);
					path.add(moves);
				}
				
				possibleMoves.add(path);
			}
			else
			{
				// If pawn has already moved, only let it move one square
				possibleMoves.add(singlePath(y, x - 1));
			}
		}
		else
		{
			// Check if this is the first time a pawn has moved.
			if(isFirstMove() == true)
			{
				// Loop through twice (Pawns can move 2 squares on their first move)
				for(int i = x + 1; i < x + 3; i++)
				{			
					ArrayList<Integer> moves = new ArrayList<Integer>();
					
					moves.add(y);
					moves.add(i);
					path.add(moves);
				}
				
				possibleMoves.add(path);
			}
			else
			{
				// If pawn has already moved, only let it move one square
				possibleMoves.add(singlePath(y, x + 1));
			}
		}
		
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