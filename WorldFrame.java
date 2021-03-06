import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;


// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------
 
/**
 * Emory
 */
/************************************************************/
/**
 * Shows the game, handles the button panel and drawing panel
 */
public class WorldFrame extends JFrame{
	
	public WorldFrame ()
	    throws HeadlessException {
		// Labels JFrame
		super("Pente");

		// These two lines define a Content object as the content pane of the
		// current JFrame then sets the JFrames layout
		Container content = getContentPane();
		content.setLayout(new FlowLayout());
		// Initialized Board
		//Game game = new Game();
		
		// Intialize Button Panel
		ButtonPanel buttonPan = new ButtonPanel(1048, 820);
		
		
		// Adds Panels to the frame
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(buttonPan,BorderLayout.CENTER);
		
		// Initializes the side panel that holds all the Labels, TextFields,
		// Buttons, and Combo Boxes
		this.add(mainPanel);
		
		this.setSize(1048,830);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setVisible(true);
	}
}
