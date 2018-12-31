import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Emory
 */
/************************************************************/
/**
 * Contains board and handles all the game rules, gameplay, and player turns
 */
public class Game {

	// Game’s personal board which allows it to manipulate and access the board in
	// the game
	private Board board_;
	// Represents who's turn it is. false means p1 and true is p2.
	private boolean turn_;
	// If this is true reset the game.
	private boolean reset_;
	// Player1's capture count
	private int captureOne_ = 0;
	// Player2's capture count
	private int captureTwo_ = 0;

	/**
	 * Constructor for Game
	 */
	public Game () {
		Board board = new Board(19,19);
		board_ = board;
	}

	/**
	 * To get the type of a dot at designated coords
	 * 
	 * @param x
	 *          coords to check
	 * @param y
	 *          coords to check
	 * @return type of the dot at the designated coords
	 */
	public int getType ( int x, int y ) {
		return board_.getType(x,y);
	}

	/**
	 * Switches the turn to the next player’s
	 */
	public void switchTurn () {
		if ( turn_ == false ) {
			turn_ = true;
		} else {
			turn_ = false;
		}
	}

	/**
	 * This updates the captured pieces for the players if they captured
	 * opponent's pieces.
	 */
	public void updateCaptured () {
		if ( turn_ == false ) { // Player1's capture
			captureOne_++;
		} else if ( turn_ == true ) { // Player2's capture
			captureTwo_++;
		}
		if ( captureOne_ >= 10 || captureTwo_ >= 10 ) { // If any of the player's
		                                                // have won by capture
			endGame();
		}
	}

	/**
	 * Returns player1's captured amount
	 * 
	 * @return number of captures for player 1
	 */
	public int returnCapOne () {
		return captureOne_;
	}

	/**
	 * Returns player2's captured amount
	 * 
	 * @return number of captures for player 2
	 */
	public int returnCapTwo () {
		return captureTwo_;
	}

	/**
	 * This is the validity checker for when the player places a dot. Returns true
	 * if the spot is available for placement.
	 * 
	 * @param x
	 *          coord to check
	 * @param y
	 *          coord to check
	 * @return boolean represent is piece can be placed at coords. true if valid.
	 */
	public boolean isValid ( int x, int y ) {
		boolean turn = getTurn();

		if ( turn == false ) { // Player1's turn
			if ( board_.getType(x,y) == 0 ) {
				return true;
			} else {
				return false;
			}
		} else if ( turn == true ) { // Player2's turn
			if ( board_.getType(x,y) == 0 ) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Returns a boolean value representing who's turn it is.
	 * 
	 * @return boolean representing who's turn it is. false is player 1.
	 */
	public boolean getTurn () {
		return turn_;
	}

	/**
	 * adds a dot with a specified type to the board at the specified coords
	 * 
	 * @param x
	 *          coord of dot to be added
	 * @param y
	 *          coord of dot to be added
	 * @param type
	 *          of dot to be added
	 */
	public void addToBoard ( int x, int y, int type ) {
		board_.setDot(x,y,type);
	}

	/**
	 * Checks from the dot placed if there are adjacent opposing pieces to be
	 * captured and captures and removes them from board
	 * 
	 * @param x
	 *          coord of dot check originates from
	 * @param y
	 *          coord of dot check originates from
	 * @param type
	 *          of dot at x,y coords
	 * @return true if captured has been made, false if not.
	 */
	public ArrayList<Integer> captureCheck ( int x, int y, int type ) {
		int oppType = 0; // Represents type of other player
		ArrayList<Integer> coors = new ArrayList<Integer>();
		if ( type == 1 ) {
			oppType = 2;
		} else {
			oppType = 1;
		}

		// Checks up
		if ( y - 3 > -1 ) {
			if ( board_.getType(x,y - 1) == oppType ) {
				if ( board_.getType(x,y - 2) == oppType ) {
					if ( board_.getType(x,y - 3) == type ) {
						coors.add(x);
						coors.add(y - 1);
						coors.add(x);
						coors.add(y - 2);
						board_.setDot(x,y - 1,0);
						board_.setDot(x,y - 2,0);
					}
				}
			}
		}

		// Checks down
		if ( y + 3 < 19 ) {
			if ( board_.getType(x,y + 1) == oppType ) {
				if ( board_.getType(x,y + 2) == oppType ) {
					if ( board_.getType(x,y + 3) == type ) {
						coors.add(x);
						coors.add(y + 1);
						coors.add(x);
						coors.add(y + 2);
						board_.setDot(x,y + 1,0);
						board_.setDot(x,y + 2,0);
					}
				}
			}
		}

		// Checks right
		if ( x + 3 < 19 ) {
			if ( board_.getType(x + 1,y) == oppType ) {
				if ( board_.getType(x + 2,y) == oppType ) {
					if ( board_.getType(x + 3,y) == type ) {
						coors.add(x + 1);
						coors.add(y);
						coors.add(x + 2);
						coors.add(y);
						board_.setDot(x + 1,y,0);
						board_.setDot(x + 2,y,0);
					}
				}
			}
		}

		// Checks left
		if ( x - 3 > -1 ) {
			if ( board_.getType(x - 1,y) == oppType ) {
				if ( board_.getType(x - 2,y) == oppType ) {
					if ( board_.getType(x - 3,y) == type ) {
						coors.add(x - 1);
						coors.add(y);
						coors.add(x - 2);
						coors.add(y);
						board_.setDot(x - 1,y,0);
						board_.setDot(x - 2,y,0);
					}
				}
			}
		}

		// Checks top right
		if ( x + 3 < 19 && y - 3 > -1 ) {
			if ( board_.getType(x + 1,y - 1) == oppType ) {
				if ( board_.getType(x + 2,y - 2) == oppType ) {
					if ( board_.getType(x + 3,y - 3) == type ) {
						coors.add(x + 1);
						coors.add(y - 1);
						coors.add(x + 2);
						coors.add(y - 2);
						board_.setDot(x + 1,y - 1,0);
						board_.setDot(x + 2,y - 2,0);
					}
				}
			}
		}

		// Checks bottom right
		if ( x + 3 < 19 && y + 3 < 19 ) {
			if ( board_.getType(x + 1,y + 1) == oppType ) {
				if ( board_.getType(x + 2,y + 2) == oppType ) {
					if ( board_.getType(x + 3,y + 3) == type ) {
						coors.add(x + 1);
						coors.add(y + 1);
						coors.add(x + 2);
						coors.add(y + 2);
						board_.setDot(x + 1,y + 1,0);
						board_.setDot(x + 2,y + 2,0);
					}
				}
			}
		}

		// Checks top left
		if ( x - 3 > -1 && y - 3 > -1 ) {
			if ( board_.getType(x - 1,y - 1) == oppType ) {
				if ( board_.getType(x - 2,y - 2) == oppType ) {
					if ( board_.getType(x - 3,y - 3) == type ) {
						coors.add(x - 1);
						coors.add(y - 1);
						coors.add(x - 2);
						coors.add(y - 2);
						board_.setDot(x - 1,y - 1,0);
						board_.setDot(x - 2,y - 2,0);
					}
				}
			}
		}

		// Checks bottom left
		if ( x - 3 > -1 && y + 3 < 19 ) {
			if ( board_.getType(x - 1,y + 1) == oppType ) {
				if ( board_.getType(x - 2,y + 2) == oppType ) {
					if ( board_.getType(x - 3,y + 3) == type ) {
						coors.add(x - 1);
						coors.add(y + 1);
						coors.add(x - 2);
						coors.add(y + 2);
						board_.setDot(x - 1,y + 1,0);
						board_.setDot(x - 2,y + 2,0);
					}
				}
			}
		}
		return coors;
	}

	/**
	 * Checks if there are 5 pieces of the same type in a row on the board, going
	 * in all directions. If so, returns true, else, false.
	 * 
	 * @param x
	 *          coord of dot the check starts from
	 * @param y
	 *          coord of dot the check starts from
	 * @param type
	 *          of the dot the check starts from
	 * @return true if there are 5 ina row, false if not
	 */
	public boolean checkFiveRow ( int x, int y, int type ) {
		int fiveRow = 1; // fiveRow will be the condition which determines whether
		// the game is won or not
		int breakC = 0; // break will be the condition used to determine if the
		// array needs to look in a certain direction
		boolean leftDone = false; // used to determine if the program needs to check
		// in the left direction
		boolean rightDone = false; // used to determine if the program needs to
		// check in the right direction
		// This for loop checks in the x direction around the current placed stone

		for ( int r = x + 1, l = x - 1 ; r < (x + 5) && l > (x - 5) && breakC < 2
		    && r < 19 && l > -1 ; ) {
			if ( board_.getType(r,y) == type ) {
				fiveRow++;
				r++;
			} else {
				if ( !rightDone ) {
					breakC++;
					rightDone = true;
				}
			}
			if ( board_.getType(l,y) == type ) {
				fiveRow++;
				l--;
			} else {
				if ( !leftDone ) {
					breakC++;
					leftDone = true;
				}
			}
			if ( fiveRow > 4 ) {
				return true;
			}
		}
		// Resets values
		fiveRow = 1;
		breakC = 0;
		leftDone = false;
		rightDone = false;
		// This for loop checks in the y direction around the current placed stone
		for ( int d = y + 1, u = y - 1 ; d < (y + 5) && u > (y - 5) && breakC < 2
		    && d < 19 && u > -1 ; ) {
			if ( board_.getType(x,d) == type ) {
				fiveRow++;
				d++;
			} else {
				if ( !rightDone ) {
					breakC++;
					rightDone = true;
				}
			}
			if ( board_.getType(x,u) == type ) {
				fiveRow++;
				u--;
			} else {
				if ( !leftDone ) {
					breakC++;
					leftDone = true;
				}
			}
			if ( fiveRow > 4 ) {
				return true;
			}
		}

		fiveRow = 1;
		breakC = 0;
		leftDone = false;
		rightDone = false;
		// This for loop checks in the diagonal direction around the current placed
		// stone
		for ( int d = y + 1, u = y - 1, r = x + 1, l = x - 1 ; d < (y + 5)
		    && u > (y - 5) && r < (x + 5) && l > (x - 5) && u > -1 && l > -1
		    && d < 19 && r < 19 && breakC < 2 ; ) {
			if ( board_.getType(r,d) == type ) {
				fiveRow++;
				d++;
				r++;
			} else {
				if ( !rightDone ) {

					breakC++;
					rightDone = true;
				}
			}
			if ( board_.getType(l,u) == type ) {
				fiveRow++;
				u--;
				l--;
			} else {
				if ( !leftDone ) {
					breakC++;
					leftDone = true;
				}
			}
			if ( fiveRow > 4 ) {
				return true;
			}
		}
		// Resets values
		fiveRow = 1;
		breakC = 0;
		leftDone = false;
		rightDone = false;
		// This for loop checks in the diagonal direction around the current placed
		// stone
		for ( int d = y + 1, u = y - 1, r = x + 1, l = x - 1 ; d < (y + 5)
		    && u > (y - 5) && r < (x + 5) && l > (x - 5) && u > -1 && l > -1
		    && d < 19 && r < 19 && breakC < 2 ; ) {
			if ( board_.getType(l,d) == type ) {
				fiveRow++;
				d++;
				l--;
			} else {
				if ( !rightDone ) {
					breakC++;
					rightDone = true;
				}
			}
			if ( board_.getType(r,u) == type ) {
				fiveRow++;
				u--;
				r++;
			} else {
				if ( !leftDone ) {
					breakC++;
					leftDone = true;
				}
			}
			if ( fiveRow > 4 ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Stops the game from running
	 */
	public void endGame () {

		Object[] options = { "New Game", "Exit" };
		int response = JOptionPane
		    .showOptionDialog(null,"You Win! New game?","Winner!",
		                      JOptionPane.YES_NO_OPTION,
		                      JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		if ( response == 0 ) {} else if ( response == 1 ) {
			System.exit(0);

		}
	}

	/**
	 * This resets the game
	 */

	/**
	 * Returns the reset_ global variable
	 * 
	 * @return value of reset_
	 */
	public boolean getReset () {
		return reset_;
	}

	/**
	 * Quit the game from running
	 */
	public void quit () {
		System.exit(0);
	}

	/**
	 * For testing in GameTest class. returns game's board object
	 * 
	 * @return Game's board_ object
	 */
	public Board getBoard () {
		return board_;
	}
}
