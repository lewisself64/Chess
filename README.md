# Chess Application

This program creates a chess board and lets two players play a game of chess against each other. Once a player clicks on a piece it will  highlight the legal squares the piece selected can move too green. If an enemy piece can be captured it will highlight red instead of  green.

The game currently supports special moves in chess such as castling king or queenside and en-passant. Once a pawn has reached the end of  the board. A popup will appear and let the user select the piece they wish the pawn to turn into.

## Instructions

If it is your turn to move, click on the piece you wish to move. The board will highlight the avalible squares for the piece to be placed. Click on the square you want the piece to move too and it will move to that location. If the square is highlighted red it will capture the piece currently on the square.

### Castling

In order to castle you must select the king. If castling is alowed in the current possition you should see the king should be alowed to move to the g or c square. Once you click on the move the rook will automatically move to the new position.

### En Passant

If a pawn moves two squares next to an enemy pawn, you will be given the option to capture it as if it only moved one. The square will be highlighted as red even though there is no piece occupying this square. When you select this move, your pawn will move to the new location and the pawn which has moved 2 squares will be removed from the board.

**Note:** The capture can only be made on the move immediately after the opposing pawn makes the two square move.

### Promoting

Once a pawn reaches the end of the board it can be turned into any piece the player wishes. A popup will appear with a list of radio options. You can select an option and click "promote" and the piece selected will appear in place of the pawn.

**Note:** If the user closes the popup instead of choosing a piece, the game will default the new piece to a Queen.

**Useful links**: 
[En Passant](https://en.wikipedia.org/wiki/En_passant) |
[Castling](https://en.wikipedia.org/wiki/Castling) |
[Promotion](https://en.wikipedia.org/wiki/Promotion_(chess))
