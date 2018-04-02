package board;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import pieces.Piece;

/**
 * Holds all the attributes and methods for the squares on the chess board.
 * 
 * @author Lewis Self
 * @version 1.0.0
 */
public class Square extends JButton
{
	/**
	 * The serializable class Square must declare a static final serialVersionUID field of type long (All subclasses of the JButton must do this)
	 */
	private static final long serialVersionUID = 1L;
	
	// Location of the square on the board
	private int xCoordinate;
	private int yCoordinate;
	
	// Defaults square to hold no piece
	private Piece piece = null;
	
	// Colour of the square
	private Color color;
	
	// Set square properties as false by default
	private boolean canOccupy       = false;
	private boolean promotionSquare = false;
	private boolean kingsideCastle  = false;
	private boolean queensideCastle = false;
	private boolean enPassant       = false;

	/**
	 * Creates the Square object. The constructor also calculates the squares colour based on the location on the board.
	 * 
	 * @param pXCoordinate	The x coordinate of the squares location
	 * @param pYCoordinate	The y coordinate of the squares location
	 */
	Square(int pXCoordinate, int pYCoordinate)
	{
		// Sets the square coordinates
		this.xCoordinate = pXCoordinate;
		this.yCoordinate = pYCoordinate;
		
		// Sets the size of the square 75px wide and long
		this.setPreferredSize(new Dimension(75, 75));
		
		// Calculate the colour of the square depending on the position on the board (Creates a checkered effect)
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
		
		// Set the square that it can be occupied by a piece
		setCanOccupy(true);
	}
	
	/**
	 * Highlights the square a specific colour instead of the default green.
	 * 
	 * @param pColor	The colour the square should be highlighted
	 */
	public void highlightSquare(Color pColor)
	{
		// Set the background colour
		this.setBackground(pColor);
		
		// Set the square that it can be occupied
		setCanOccupy(true);
	}
	
	/**
	 * Overloads the highlight square method and sets the squares special attribute based on input from board.
	 * 
	 * @param specialMove	Special move such as kingside, queenside castling or en-passant
	 */
	public void highlightSquare(String specialMove)
	{
		// Check if the move is a kingside castling move
		if(specialMove == "kingside")
		{
			// Call the default highlight move to change the squares colour and set the canOccupy as true
			highlightSquare();
			
			// Sets the squares kingside castling property as true
			setKingsideCastle(true);
		}
		
		// Check if the move is a queenside casting move
		if(specialMove == "queenside")
		{
			// Call the default highlight move to change the squares colour and set the canOccupy as true
			highlightSquare();
			
			// Sets the squares queenside castling attribute as true
			setQueensideCastle(true);
		}
		
		// Check if the move is an en-passant move
		if(specialMove == "en-passant")
		{
			// Highlight this square red to indicate taking a piece if moving to this square
			highlightSquare(Color.RED);
			
			// Sets the square en-passant attribute as true
			setEnPassant(true);
		}
	}
	
	/**
	 * Removes the highlight from the current square and sets the canOccupy to false.
	 */
	public void removeHighlight()
	{
		// Set the background colour to the original colour
        this.setBackground(color);
          
        // Set the square to not be occupied
        setCanOccupy(false);
	}	
	
	/**
	 * Gets the current piece's image and set the JButton icon to the image.
	 * 
	 * @param piece		The piece which the icon should resemble.
	 */
	public void setIcon(Piece piece)
	{
        try 
        {
        	String imageResource = piece.getImage();
        	
        	this.setIcon(new ImageIcon(this.getClass().getResource(imageResource)));
        } 
        catch (Exception ex)
        {
        	System.out.println(ex);
        }
	}
	
	/**
	 * Returns true if a piece is currently on the square
	 * 
	 * @return	Returns true if the square is occupied and false if it is not.
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
	
	/**
	 * Removes the icon from the square.
	 */
	public void removeIcon()
	{
        this.setIcon(new ImageIcon());
	}
		
	/* ===================== */
	/* = Getters & Setters = */
	/* ===================== */
	
	/**
	 * Gets the serial version id
	 * 
	 * @return	Serial version ID
	 */
	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}
	
	/**
	 * Get the color.
	 * 
	 * @return	The colour of the square
	 */
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * Set the color.
	 * 
	 * @param pColor	The colour the square will be changed too
	 */
	public void setColor(Color pColor)
	{
		color = pColor;
	}
	
	/**
	 * Get the Piece.
	 * 
	 * @return	The piece which currently occupies the square. If no Piece occupies the square, return null
	 */
	public Piece getPiece()
	{
		return piece;
	}
	
	/**
	 * Set a piece to occupy the square and piece icon on the square. If null is passed, remove icon and set Piece as null.
	 * 
	 * @param pPiece	The piece to occupy the square
	 */
	public void setPiece(Piece pPiece)
	{		
		// Occupies the square with a new Piece
		this.piece = pPiece;
		
		// Check if the piece is not null
		if(pPiece != null)
		{
			// Set's the pieces icon on the square
			setIcon(pPiece);
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		else
		{
			// Remove the piece icon
			removeIcon();
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	/**
	 * Get the X coordinate.
	 * 
	 * @return	The squares X coordinate.
	 */
	public int getXCoordinate()
	{
		return xCoordinate;
	}
	
	/**
	 * Set the X coordinate.
	 * 
	 * @param pXCoordinate	The X coordinate for the square to be changed too
	 */
	public void setXCoordinate(int pXCoordinate)
	{
		xCoordinate = pXCoordinate;
	}
	
	/**
	 * Get the Y coordinate.
	 * 
	 * @return	The squares Y coordinate.
	 */
	public int getYCoordinate()
	{
		return yCoordinate;
	}
	
	/**
	 * Set the Y coordinate.
	 * 
	 * @param pYCoordinate	The Y coordinate for the square to be changed too
	 */
	public void setYCoordinate(int pYCoordinate)
	{
		yCoordinate = pYCoordinate;
	}

	/**
	 * Check if the square can be occupied.
	 *  
	 * @return	Returns true if the square can be occupied, false if it cannot
	 */
	public boolean getCanOccupy() 
	{
		return canOccupy;
	}

	/**
	 * Set the canOccupy.
	 * 
	 * @param canOccupy	The new state of the canOccupy variable
	 */
	public void setCanOccupy(boolean pCanOccupy) 
	{
		canOccupy = pCanOccupy;
	}

	/**
	 * Checks if the current square is a promotion square for pawns
	 * 
	 * @return	Returns true if the square can promote pawns, false if it cannot
	 */
	public boolean isPromotionSquare() 
	{
		return promotionSquare;
	}

	/**
	 * Set the promotion square.
	 * 
	 * @param promotionSquare	The new state of the promotionSquare variable
	 */
	public void setPromotionSquare(boolean pPromotionSquare) 
	{
		promotionSquare = pPromotionSquare;
	}

	/**
	 * Checks if the move is a kingside castle move.
	 * 
	 * @return	The state of kingsideCastle
	 */
	public boolean isKingsideCastle() 
	{
		return kingsideCastle;
	}

	/**
	 * Sets the square the property of a kingsideCastling move.
	 * 
	 * @param kingsideCastle	Kingside castling move
	 */
	public void setKingsideCastle(boolean pKingsideCastle) 
	{
		kingsideCastle = pKingsideCastle;
	}

	/**
	 * Checks if the move is a queenside castle move.
	 * 
	 * @return	The state of queensideCastle
	 */
	public boolean isQueensideCastle() 
	{
		return queensideCastle;
	}

	/**
	 *  Sets the square the property of a queensideCastle move.
	 * 
	 * @param queensideCastle	Queenside castling move
	 */
	public void setQueensideCastle(boolean pQueensideCastle) 
	{
		queensideCastle = pQueensideCastle;
	}

	/**
	 * Checks if the move is a en-passant castle move.
	 * 
	 * @return	The state of enPassant
	 */
	public boolean isEnPassant() 
	{
		return enPassant;
	}

	/**
	 * Sets the square the property of a en-passant move.
	 * 
	 * @param enPassant	If the move is an enPassant move, set as true
	 */
	public void setEnPassant(boolean pEnPassant) 
	{
		enPassant = pEnPassant;
	}
}