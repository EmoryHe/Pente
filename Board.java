import java.util.ArrayList;

/**
 * Emory
 */
/************************************************************/
/**
 * Handles all Dots on the board and represents the playing board.
 */
public class Board {
	/**
	 * A double array of type DOT which represents the positions on the board
	 */
	private Dot[][] dots_;

	private int boardWidth_;

	/**
	 * Initializes a 2D array full of Dot (Stone) objects relative to the size of
	 * the board
	 */
	public Board ( int width, int height ) {
		boardWidth_ = width;
		dots_ = new Dot[width][height];
		for ( int i = 0 ; i < width ; i++ ) {
			for ( int j = 0 ; j < height ; j++ ) {
				dots_[i][j] = new Dot();
			}
		}
	}

	/**
	 * sets the type of one of the dots in the array
	 * 
	 * @param x
	 *          x coord/row to set chip at
	 * @param y
	 *          y coord/column to set chip at
	 */
	public void setDot ( int x, int y, int type ) {
		dots_[x][y].setType(type);
	}

	/**
	 * gets the type of one of the dots in the array
	 * 
	 * @param x
	 *          x coord/row to set chip at
	 * @param y
	 *          y coord/column to set chip at
	 * @return
	 */
	public int getType ( int x, int y ) {
		return dots_[x][y].getType();
	}

	/**
	 * Returns the board's 2d array of Dots
	 * 
	 * @return Board's 2d array of Dots
	 */
	public Dot[][] getBoard () {
		return dots_;
	}

}