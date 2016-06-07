/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import paintshop.Customer;
import paintshop.PaintShop;

/**
 * @author Maria Lopez-Latorre
 *
 */
public class PaintShopTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testOrderProcessing() {
		
		char[] testCust1Prefs = {'1','M','3','G','5','G'}; 
		char[] testCust2Prefs = {'2','G', '3', 'M', '4', 'G'}; 
		char[] testCust3Prefs = {'5','M'}; 
		Customer testCust1 = new Customer(testCust1Prefs);
		Customer testCust2 = new Customer(testCust2Prefs);
		Customer testCust3 = new Customer(testCust3Prefs);
		
		PaintShop testShop1 = new PaintShop(5);
		testShop1.addCustomer(testCust1);
		testShop1.addCustomer(testCust2);
		testShop1.addCustomer(testCust3);
		
		String testCase1 = "G G G G M";
		
		testShop1.processOrder();
		String result = testShop1.printOrder();
		
		assertEquals(result, testCase1);
	}

}
