package chess;

import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import pieces.Piece;

public class Square extends JButton
{
	/**
	 * No idea what the hell this does but it makes eclipse stop crying
	 */
	private static final long serialVersionUID = 1L;
	
	private int xCoordinate;
	private int yCoordinate;
	private Piece piece = null;
	private Color color;
	private boolean canOccupy = false;
	boolean promotionSquare = false;

	Square(int pXCoordinate, int pYCoordinate)
	{
		this.xCoordinate = pXCoordinate;
		this.yCoordinate = pYCoordinate;
		
		// Calculate the colour of the square depending on the position on the board
		if((xCoordinate % 2) == 0)
		{
			if((yCoordinate % 2) == 0)
			{
				color = Color.WHITE;
			}
			else
			{
				color = Color.GRAY;
			}
		}
		else
		{
			if((yCoordinate % 2) == 0)
			{
				color = Color.GRAY;
			}
			else
			{
				color = Color.WHITE;
			}
		}
	}
	
	/**
	 * Highlights current square and sets the canOccupy as true so pieces can move to it.
	 */
	public void highlightSquare()
	{
		// Depending on the square's colour, determine if the highlight should be light or dark green.
		if(color == Color.GRAY)
		{
	        this.setBackground(Color.GREEN.darker());
		}
		else
		{
			this.setBackground(Color.GREEN);
		}
		
		// Set the square that it can be occupied
		setCanOccupy(true);
	}
	
	public void highlightSquare(Color pColor)
	{
		// Set the background colour
		this.setBackground(pColor);
		
		// Set the square that it can be occupied
		setCanOccupy(true);
	}
	
	/**
	 * Removes the highlight from the current square and sets the canOccupy to false
	 */
	public void removeHighlight()
	{
		// Set the background colour to the original colour
        this.setBackground(color);
          
        // Set the square to not be occupied
        setCanOccupy(false);
	}	
	
	/**
	 * Gets the current piece's image and set the JButton icon to the image
	 */
	public void setIcon(Piece piece)
	{
        try 
        {
        	// Get the image from the piece and set it as the JButton icon
            Image img = ImageIO.read(getClass().getResource(piece.getImage()));
            this.setIcon(new ImageIcon(img));
        } 
        catch (Exception ex)
        {
        	System.out.println(ex);
        }
	}
	
	/**
	 * Check if the square is occupied
	 */
	public boolean isSquareOccupied()
	{
		// if there's no piece on the square, return false. If there is, return true.
		if(piece == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/* Getters and setters */
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color pColor)
	{
		color = pColor;
	}
	
	public Piece getPiece()
	{
		return piece;
	}
	
	public void setPiece(Piece pPiece)
	{
		this.piece = pPiece;
		
		if(pPiece != null)
		{
			// Set's the pieces icon on the square
			setIcon(pPiece);
		}
	}
	
	public void removeIcon()
	{
        this.setIcon(new ImageIcon());
	}
	
	public int getXCoordinate()
	{
		return xCoordinate;
	}
	
	public void setXCoordinate(int pXCoordinate)
	{
		this.xCoordinate = pXCoordinate;
	}
	
	public int getYCoordinate()
	{
		return yCoordinate;
	}
	
	public void setYCoordinate(int pYCoordinate)
	{
		this.yCoordinate = pYCoordinate;
	}

	public boolean getCanOccupy() {
		return canOccupy;
	}

	public void setCanOccupy(boolean canOccupy) {
		this.canOccupy = canOccupy;
	}
}