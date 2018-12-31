import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * This class holds all the buttons and their functionality for the game.
 */
public class ButtonPanel extends JPanel {

	private BoardPanel boardPanel_;
	private boolean isPlay_ = false;
	private Game game_;
	private JLabel setPlay_;
	private JLabel identifyPlay_;
	private JButton start_ = new JButton("Start");
	private JButton reset_ = new JButton("Reset");
	private JButton quit_ = new JButton("Quit");
	private JButton help_ = new JButton("Help");
	private JButton endMove_ = new JButton("End Turn");
	private JLabel captureOne_;	
	private JLabel captureTwo_;
	private Color backColor_ = new Color(222,161,58);
	private boolean firstMove_ = true;

	public ButtonPanel ( int width, int height ) {
		// Main panel containing both board and buttons
		this.setLayout(new BorderLayout());

		// Buttons panel
		JPanel buttons = new JPanel();
		buttons.setPreferredSize(new Dimension(250,height));
		buttons.setLayout(new FlowLayout());
		buttons.setBackground(backColor_);
		game_ = new Game();
		boardPanel_ = new BoardPanel(798,800,game_);

		// All of the Panels used for the GUI
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel topText = new JPanel(new FlowLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		JPanel topButton = new JPanel(new FlowLayout());
		JPanel bottomButton = new JPanel(new FlowLayout());
		JPanel capPanelMain = new JPanel(new BorderLayout());
		JPanel capPanel = new JPanel(new GridLayout(0,1));

		TitledBorder turnTitle;
		turnTitle = BorderFactory
		    .createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
		                        "Turn Actions");
		turnTitle.setTitleColor(Color.black);
		turnTitle.setTitleJustification(TitledBorder.LEFT);

		TitledBorder actionTitle;
		actionTitle = BorderFactory
		    .createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
		                        "Main Actions");
		actionTitle.setTitleColor(Color.black);
		actionTitle.setTitleJustification(TitledBorder.LEFT);
		
		TitledBorder capturedTitle;
		capturedTitle = BorderFactory
		    .createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
		                        "Number of Stones Captured");
		capturedTitle.setTitleColor(Color.black);
		capturedTitle.setTitleJustification(TitledBorder.LEFT);

		// Initializes JLabels
		setPlay_ = new JLabel("Game Set Up: ");
		identifyPlay_ = new JLabel("Click Start to Play");
		captureOne_ = new JLabel("Player1 : ");
		captureTwo_ = new JLabel("Player2 : ");

		// Tool Tips for Buttons
		start_.setToolTipText("Starts the game.");
		reset_.setToolTipText("Resets the game.");
		quit_.setToolTipText("Quits the game.");
		help_.setToolTipText("Click this to read the rules of Pente!");
		endMove_.setToolTipText("Click this to end your move.");

		// Adding Action Listeners
		mainActions mainAction = new mainActions();
		start_.addActionListener(mainAction);
		reset_.addActionListener(mainAction);
		quit_.addActionListener(mainAction);

		turnActions turnAction = new turnActions();
		help_.addActionListener(turnAction);
		endMove_.addActionListener(turnAction);

		// Adds JButtons to the panels
		topText.add(setPlay_);
		topText.add(identifyPlay_);
		topText.setBackground(backColor_);
		topText.setBorder(BorderFactory.createLineBorder(Color.black));

		topButton.add(start_);
		topButton.add(reset_);
		topButton.add(quit_);
		topButton.setBackground(backColor_);
		topButton.setBorder(actionTitle);

		bottomButton.add(help_);
		bottomButton.add(endMove_);
		bottomButton.setBackground(backColor_);
		bottomButton.setBorder(turnTitle);

		capPanel.add(captureOne_);
		capPanel.add(captureTwo_);
		capPanel.setBackground(backColor_);
		
		capPanelMain.add(capPanel, BorderLayout.WEST);
		capPanelMain.setPreferredSize(new Dimension(100,650));
		capPanelMain.setBackground(backColor_);
		capPanelMain.setBorder(capturedTitle);
		

		// Adding the Panels for the other main Panels
		topPanel.add(topText,BorderLayout.NORTH);
		topPanel.add(topButton,BorderLayout.SOUTH);

		mainPanel.add(topPanel,BorderLayout.NORTH);
		mainPanel.add(capPanelMain,BorderLayout.CENTER);
		mainPanel.add(bottomButton,BorderLayout.SOUTH);

		mainPanel.setBackground(new Color(232,171,58));
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		buttons.add(mainPanel);

		boardPanel_.setVisible(true);
		buttons.setVisible(true);

		this.add(boardPanel_,BorderLayout.CENTER);
		this.add(buttons,BorderLayout.EAST);
		this.setBackground(new Color(232,181,58));
		this.setPreferredSize(new Dimension(width,height));
	}

	/**
	 * This is the action listener used for the main actions the user can click.
	 * 
	 * @author ab5001
	 */
	public class mainActions implements ActionListener {

		public void actionPerformed ( ActionEvent e ) {

			if ( e.getActionCommand().equals("Start") ) {
				setPlay_.setText("Current Player: ");
				identifyPlay_.setText("Player 1");
				boardPanel_.setPlay();
			} else if ( e.getActionCommand().equals("Reset") ) {
				setPlay_.setText("Game Set Up: ");
				identifyPlay_.setText("Click Start to Play");
				game_ = new Game();
				boardPanel_.resetGame(game_);
				firstMove_ = true;
				repaint();
			} else if ( e.getActionCommand().equals("Quit") ) {
				game_.quit();
			}
		}
	}

	/**
	 * This is the action listener used for the actions the player can make while
	 * making a move.
	 */
	public class turnActions implements ActionListener {

		public void actionPerformed ( ActionEvent e ) {

			if ( e.getActionCommand().equals("Help") ) {

				// rules of the game
				String message = "\"OBJECT OF THE GAME:\"\n" + " \n"
				    + "Win by placing five (or more) of your stones in a row, vertically, \n"
				    + "horizontally, or diagonally, with no empty points between them. Or, \n"
				    + "win by capturing five (or more) pairs of your opponent's stones. \n"
				    + " \n"

				    + "\"HOW TO PLAY:\"\n" + " \n"
				    + "Play starts with the board completely clear of stones.\n"
				    + "The first player (white) begins the game by playing\n"
				    + "one stone on the center point. Thereafter the players\n"
				    + "take turns placing their stones, one at a time, on any\n"
				    + "empty intersection. The stones are placed on the intersections \n"
				    + "of the lines (including the very edge of the board), rather \n"
				    + "than in the squares. Once played, a stone cannot be moved \n"
				    + "again, except when removed by a capture. Players alternate \n"
				    + "turns adding new stones to the board, building up their \n"
				    + "positions, until one player wins." + " \n" + " \n"
				    + "\"CAPTURES:\"\n" + " \n"

				    + "Whenever your opponent has two stones (and only two) which are \n"
				    + "adjacent,those stones are vulnerable to capture. The pair can be \n"
				    + "captured by bracketing its two ends with your own stones. \n"
				    + "The captured stones are removed from the board.\n"
				    + "Captures can be made vertically, horizontally, or\n"
				    + "diagonally, and multiple captures can occur on a \n"
				    + "single move.\n" + " \n" + "\"WINNING THE GAME:\"\n" + " \n"
				    + "The game ends immediately when one player captures five \n"
				    + "pairs, or places five stones in a row. The opposing player \n"
				    + "has no “last chance” to make a final move.\n"
				    + "When a player obtains an unblocked row of four stones, called a \n"
				    + "tessera, a win is imminent. Therefore, an unblocked row of three stones, \n"
				    + "called a tria, is a serious threat that should be blocked unless a stronger o\n"
				    + "ffensive move exists. An unblocked row of three stones, if it contains one \n"
				    + "gap, is still considered a tria. In the example to the right, black has formed\n"
				    + "both a horizontal and a vertical tria, while white has formed a tessera and\n"
				    + "will win with the next move. \n" + " \n" + "\"ETIQUETTE:\"\n"
				    + " \n"
				    + "It is a customary, but not mandatory, refinement of this game to announce \n"
				    + "“three” or “tria” when moving to make an open three, and also to\n"
				    + "call “four” or “tessera” when making four in a row. This is so that \n"
				    + "one's opponent does not forget to stop the formation of an open four,\n"
				    + "or five—because there is no fun in winning a game owing to the\n"
				    + "blunder of the adversary; at least there shouldn't be. The idea is \n"
				    + "to win in spite of one's opponent seeing every threat. Pointing out\n"
				    + "a player's errant move also demonstrates one's own confidence \n"
				    + "and mastery of play. \n" + "\n"
				    + "Pente Net - How to play the game of Pente, www.pente.net/instructions.html."

				;

				JTextArea textArea = new JTextArea(message);
				JScrollPane scrollPane = new JScrollPane(textArea);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				scrollPane.setPreferredSize(new Dimension(500,500));
				JOptionPane.showMessageDialog(null,scrollPane,"Help",
				                              JOptionPane.INFORMATION_MESSAGE);

			} else if ( e.getActionCommand().equals("End Turn") ) {
				if (firstMove_) {
					if (boardPanel_.getCoor() == 9) {
						if ( game_.getTurn() == true ) {
							identifyPlay_.setText("Player 1");
						} else {
							identifyPlay_.setText("Player 2");
						}
						boardPanel_.SwitchTurns();
						firstMove_ = false;
					}
					else {
						JOptionPane.showMessageDialog(null,"First move is placed in the middle","Warning",
						                              JOptionPane.INFORMATION_MESSAGE);
					}
				}
			else if ( boardPanel_.getEndGame() == true ) {
					boardPanel_.setGameEnd(false);
					Object[] options = { "New Game", "Exit" };
					int response =
					    JOptionPane.showOptionDialog(null,"You Win! New game?","Winner!",
					                                 JOptionPane.YES_NO_OPTION,
					                                 JOptionPane.QUESTION_MESSAGE,null,
					                                 options,options[0]);
					if ( response == 0 ) {
						setPlay_.setText("Game Set Up: ");
						identifyPlay_.setText("Click Start to Play");
						game_ = new Game();
						boardPanel_.resetGame(game_);
						repaint();
					} else if ( response == 1 ) {
						System.exit(0);

					}
				} else if ( game_.getTurn() == true ) {
					identifyPlay_.setText("Player 1");
					boardPanel_.SwitchTurns();
				} else if ( game_.getTurn() == false ) {
					identifyPlay_.setText("Player 2");
					boardPanel_.SwitchTurns();
				}
				captureOne_.setText("Player1: "+game_.returnCapTwo());
				captureTwo_.setText("Player2: "+game_.returnCapOne());
				boardPanel_.endOfTurn();
			}
		}
	}
}
