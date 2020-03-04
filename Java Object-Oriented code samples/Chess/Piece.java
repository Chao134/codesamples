package pieces;

import board.Chessboard;
import board.Square;
import enums.Color;

/**
 * Abstract class for a chess piece. Template Method design pattern.
 * @author Cliff Chao
 */
public abstract class Piece {
	
	protected Square currentSquare;
	protected Color color;
	
	
	/**
	 * 
	 * @param playerNum 1 for white, 2 for black
	 * @param row Row index
	 * @param col Column index
	 */
	public Piece(int playerNum, int row, int col) {
		this.color = Color.White;
		if(playerNum == 2) {
			this.color = Color.Black;
		}
		currentSquare = Chessboard.board[row][col];
	}
	
	
	public abstract String toString();
	
	public Square getCurrentSquare() {
		return currentSquare;
	}
	
	public void setCurrentSquare(Square newSquare) {
		currentSquare = newSquare;
	}
	
	/**
	 * Returns whether the Piece can move to the given square.
	 * @param endSquare that the piece is moving to
	 * @param board 2d array board
	 * @return true if legal move, else false if illegal
	 */
	public abstract boolean canMove(Square endSquare, Square[][] board);


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Checks if the given piece can move.
	 * @return false if piece cannot move
	 */
//	public abstract boolean canMove();
	
	
}