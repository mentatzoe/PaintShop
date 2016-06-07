package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import paintshop.Color;
import paintshop.Solution;

public class SolutionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCost() {
		Solution testSolutionZero = new Solution(3);
		
		Solution testSolutionThree = 
				new Solution(new Color(1,'M'), new Color(2, 'M'), new Color(3,'M'));
		assertEquals(""+testSolutionZero.getCost(), "0");
		assertEquals(""+testSolutionThree.getCost(), "3");
	}

}
