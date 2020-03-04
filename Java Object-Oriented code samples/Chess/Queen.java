package pieces;

import board.Square;
import enums.Color;

public class Queen extends Piece{

	public Queen(int playerNum, int row, int col) {
		super(playerNum, row, col);
	}

	@Override
	public String toString() {
		String x = (this.color == Color.White) ? "w" : "b";
		return x+"Q";
	}

	@Override
	public boolean canMove(Square endSquare, Square[][] board) {
		int num = (this.color == Color.White) ? 1 : 2;
		int row = currentSquare.getRowInt();
		int col = currentSquare.getColInt();
		//recycle bishop and rook move()
		Piece p;
		if (row == endSquare.getRowInt() || col == endSquare.getColInt()) {
			p = new Rook(num, row, col);
			if(p.canMove(endSquare, board)) return true;
		} else {
			p = new Bishop(num, row, col);
			if(p.canMove(endSquare, board)) return true;
		}
		return false;
	}

}
