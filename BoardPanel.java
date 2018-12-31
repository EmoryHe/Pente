import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * This class shows the game board and holds all its functionality
 */
public class BoardPanel extends JPanel implements MouseListener {

	private int clickedX_ = -1; // Where the user clicks in regards to the x axis
	private int clickedY_ = -1; // Where the user clicks in regards to the y axis
	private ArrayList<Integer[]> placedPieces_; // ArrayList that holds the placed
	                                            // pieces
	private Game game_; // Game instance
	private int stoneDiameter_ = 20;
	private boolean isPlay_ = false;
	private boolean turnOver_ = false;
	private boolean gameEnd_ = false;
	private int type_ = 0;

	public BoardPanel ( int width, int height, Game game ) {
		super.addMouseListener(this);
		this.setBackground(new Color(1,158,46));
		this.setSize(width,height);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		game_ = game;
		placedPieces_ = new ArrayList<Integer[]>();
	}

	public void paintComponent ( Graphics g ) {
		Graphics2D g2d = (Graphics2D) g;

		if(isPlay_ == true) {
		// This is the 2nd most outer layer
		g2d.setColor(new Color(222,161,58));
		g2d.fillRect(5,5,798,798);

		// This is the background for the actual board
		setBackgroundBoard(g2d,new Color(204,158,46),798,798);

		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(new Color(232,181,58));
		boardSize(g2d);
		// Draws all stones contained in the array list
		drawPieces(g2d);
		} else {
	    Image img1 = Toolkit.getDefaultToolkit().getImage("src/PENTE.jpg");
	    g2d.drawImage(img1, 0, 0, this);
	    g2d.finalize();
		}
		repaint();
	}

	public int getCoor () {
		return clickedX_;
	}

	public void resetGame ( Game game ) {
		game_ = game;
		placedPieces_ = new ArrayList<Integer[]>();
		isPlay_ = false;
	}

	/**
	 * Makes the game playable
	 */
	public void setPlay () {
		isPlay_ = true;
	}

	/**
	 * 
	 */
	public void setTurn () {
		turnOver_ = true;
	}

	/**
	 * Sets the game end variable to true
	 */
	public void setGameEnd ( boolean end ) {
		gameEnd_ = end;
	}

	/**
	 * This returns the gameEnd_ variable
	 * 
	 * @return
	 */
	public boolean getEndGame () {

		return gameEnd_;
	}

	/**
	 * Draws the pieces of each player
	 * 
	 * @param g2d
	 */
	public void drawPieces ( Graphics2D g2d ) {
		Integer[] stoneProp = new Integer[3];
		for ( int i = 0 ; i < placedPieces_.size() ; i++ ) {
			stoneProp = placedPieces_.get(i);
			if ( stoneProp[2] == 1 ) {
				g2d.setColor(Color.BLACK);
			} else {
				g2d.setColor(Color.WHITE);
			}
			g2d.fillOval(stoneProp[0],stoneProp[1],stoneDiameter_,stoneDiameter_);
		}
	}

	/**
	 * Draws background for the board
	 */
	public void setBackgroundBoard ( Graphics2D g2d, Color color, int width,
	                                 int height ) {
		g2d.setColor(color);
		g2d.fillRect(0,0,width,height);
		// repaint();
	}

	/**
	 * Draws playing board
	 * 
	 * @param g2d
	 *          Graphics 2D object
	 */
	public void boardSize ( Graphics2D g2d ) {

		for ( int i = 21 ; i <= 777 ; i = i + 42 ) {
			g2d.drawLine(i,21,i,777);
			g2d.drawLine(21,i,777,i);
		}
		g2d.setColor(Color.BLACK);
		g2d.fillOval(395,395,10,10);

		g2d.drawRect(21,21,756,756);
	}

	/**
	 * Algebra used to place a stone on the point its closest to
	 * 
	 * @param coor
	 *          the coor where the mouse was clicked
	 * @return the coordinate of the point it was closest to
	 */
	private int matchPoint ( double coor ) {
		coor = coor - 21;
		double numBox = coor / 42;
		double value = 0;
		value = numBox - (int) numBox;
		if ( value > 0.5 ) {
			coor = (int) numBox + 1;
		} else {
			coor = (int) numBox;
		}
		coor = coor * 42;
		coor = coor + 21;
		return (int) coor;
	}

	/**
	 * Adds a stone and its properties to a list
	 * 
	 * @param type
	 */
	public void addToList ( int type ) {
		Integer[] ele = new Integer[3];
		ele[0] = clickedX_;
		ele[1] = clickedY_;
		ele[2] = type;
		placedPieces_.add(ele);
	}

	/**
	 * Removes the last placed stone
	 */
	public void removeFromList () {
		placedPieces_.remove(placedPieces_.size() - 1);
	}

	/**
	 * Switches the turn
	 */
	public void SwitchTurns () {

		game_.addToBoard(clickedX_,clickedY_,type_);
		game_.switchTurn();
		turnOver_ = true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 * Carries out all the action of the gameplay
	 */
	@Override
	public void mousePressed ( MouseEvent e ) {
		if ( isPlay_ == true ) { // The game is only playable if start is clicked

			// Gets the place where user clicks and matches to a specific point on the
			// board

			clickedX_ = matchPoint(e.getX());
			clickedY_ = matchPoint(e.getY());
			clickedX_ = clickedX_ - (stoneDiameter_ / 2);
			clickedY_ = clickedY_ - (stoneDiameter_ / 2);
			int tempX = (clickedX_ - 11) / 42;
			int tempY = (clickedY_ - 11) / 42;

			if ( game_.isValid(tempX,tempY) ) {
				if ( game_.getTurn() ) {
					type_ = 1;
				} else {
					type_ = 2;
				}

				if ( placedPieces_.size() == 0 || turnOver_ == true ) {
					addToList(type_);
					turnOver_ = false;
				} else {
					removeFromList();
					addToList(type_);
					game_.addToBoard(tempX,tempY,0); // removes dot
				}

				clickedX_ = (clickedX_ - 11) / 42;
				clickedY_ = (clickedY_ - 11) / 42;
				repaint();
			}
		}
	}

	/**
	 * Checks all of the capture and winning
	 */
	public void endOfTurn () {
		if ( game_.checkFiveRow(clickedX_,clickedY_,type_) ) {
			setGameEnd(true);
		}
		ArrayList<Integer> removeDots =
		    game_.captureCheck(clickedX_,clickedY_,type_);
		if ( removeDots.size() > 0 ) {
			iterateThroughList(removeDots);
			adjustList();
			repaint();
		}
	}

	private void iterateThroughList ( ArrayList<Integer> removeDots ) {
		Integer[] coor = { 0, 0, 0 };
		for ( int i = 0 ; i < placedPieces_.size() ; i++ ) {
			coor = placedPieces_.get(i);
			coor[0] = (coor[0] - 11) / 42;
			coor[1] = (coor[1] - 11) / 42;
			for ( int j = 0 ; j < removeDots.size() ; j = j + 2 ) {
				if ( coor[0] == removeDots.get(j)
				    && coor[1] == removeDots.get(j + 1) ) {
					
					placedPieces_.remove(i);
					game_.updateCaptured();
					i--;
					break;
				}
			}
		}

	}

	private void adjustList () {
		ArrayList<Integer[]> temp = new ArrayList<Integer[]>();
		Integer[] coor = { 0, 0, 0 };
		for ( int i = placedPieces_.size() - 1 ; i > -1 ; i-- ) {
			coor = placedPieces_.get(i);
			coor[0] = (coor[0] * 42) + 11;
			coor[1] = (coor[1] * 42) + 11;
			placedPieces_.remove(i);
			temp.add(coor);
		}
		placedPieces_ = temp;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked ( MouseEvent e ) {}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased ( MouseEvent e ) {}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered ( MouseEvent e ) {}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited ( MouseEvent e ) {}
}
