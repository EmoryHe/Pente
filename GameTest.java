import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

	Game game_ = new Game();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp () throws Exception {
		// adds dots to topleft corner y axis
		for ( int i = 0 ; i < 5 ; i++ ) {
			game_.addToBoard(0,i,1);
		}

		// adds dots to topleft corner x axis
		for ( int i = 0 ; i < 5 ; i++ ) {
			game_.addToBoard(i,0,1);
		}

		// adds dots to topright corner x axis
		for ( int i = 18 ; i > 13 ; i-- ) {
			game_.addToBoard(i,0,1);
		}

		// adds dots to topright corner y axis
		for ( int i = 0 ; i < 5 ; i++ ) {
			game_.addToBoard(18,i,1);
		}

		// adds dots to lowright corner y axis
		for ( int i = 18 ; i > 13 ; i-- ) {
			game_.addToBoard(18,i,1);
		}

		// adds dots to lowright corner x axis
		for ( int i = 18 ; i > 13 ; i-- ) {
			game_.addToBoard(i,18,1);
		}

		// adds dots to lowleft corner x axis
		for ( int i = 0 ; i < 5 ; i++ ) {
			game_.addToBoard(i,18,1);
		}

		// adds dots to lowleft corner y axis
		for ( int i = 18 ; i > 13 ; i-- ) {
			game_.addToBoard(0,i,1);
		}

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception {}

	// testing the 5 in a row method
	
	//testing putting in piece in mid of a 5 row
	@Test
	public void testCheckFiveRowTopLeftYMid () {

		assertTrue(game_.checkFiveRow(0,2,1));

	}
	
	
}
