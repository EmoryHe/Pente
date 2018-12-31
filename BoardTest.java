import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Alex
 * Emory
 * Kemal
 * Terrance
 */
public class BoardTest {

	/**
	 * @throws java.lang.Exception
	 */

	Board board_;

	@Before
	public void setUp () throws Exception {
		board_ = new Board(10,10);
		board_.setDot(0,0,1);// dot of type 1 at coords (0,0)
		board_.setDot(0,3,1);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception {}

	// testing setDot

	@Test
	public void testSetDotSameValue () {
		board_.setDot(0,2,1);
		int type = board_.getType(0,2);
		assertTrue(type == board_.getType(0,2));
	}

	@Test
	public void testSetDotCorrectValue () {
		board_.setDot(0,2,1);
		int type = 0;
		assertFalse(type == 1);
	}

	// testing getType

	@Test
	public void testGetDotInit () {
		int correct = 0;// the value the dot's type should be
		int type = board_.getType(0,1);
		assertTrue(correct == type);

	}

	@Test
	public void testGetDotSetCorrect () {
		int correct = 1;// the value the dot's type should be
		int type = board_.getType(0,0);// dot at (0,0) has had value set to 1
		assertTrue(correct == type);

	}

	@Test
	public void testGetDotSetFalse () {
		int value = 0;// the value the dot's type should be
		int type = board_.getType(0,0);// dot at (0,0) has had value set to 1
		assertFalse(value == type);
	}
	
//getBoard

	@Test
	public void testGetBoard () {
		Board other = new Board(10,10);
		System.out.print("Original Board's values:");
		System.out.print(" (0,0):" + board_.getType(0,0));
		System.out.print(" (0,1):" + board_.getType(0,1));
		System.out.print(" (0,2):" + board_.getType(0,2));
		System.out.println(" (0,3):" + board_.getType(0,3));

		System.out.print("New Board's values:");
		System.out.print(" (0,0):" + other.getType(0,0));
		System.out.print(" (0,1):" + other.getType(0,1));
		System.out.print(" (0,2):" + other.getType(0,2));
		System.out.println(" (0,3):" + other.getType(0,3));

		System.out
		    .println("After using getBoard to give New Board Original Board's values.");
		other = board_;

		System.out.print("New Board's values:");
		System.out.print(" (0,0):" + other.getType(0,0));
		System.out.print(" (0,1):" + other.getType(0,1));
		System.out.print(" (0,2):" + other.getType(0,2));
		System.out.println(" (0,3):" + other.getType(0,3));

		assertTrue(board_.getType(0,0) == other.getType(0,0)
		    && board_.getType(0,1) == other.getType(0,1)
		    && board_.getType(0,2) == other.getType(0,2)
		    && board_.getType(0,3) == other.getType(0,3));
	}

	

}
