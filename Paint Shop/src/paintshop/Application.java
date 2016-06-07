package paintshop;

import java.io.IOException;

/**
* Application
* 
* Class that holds loop of the application.
*
* @author  Maria Lopez-Latorre
* @version 1.0
* @since   2016-05-29 
*/
public class Application {

	private static PaintShop shop;
	
	/**
	   * This is the main method which creates the shop with the customer preferences, 
	   * processes the order and prints to the standard output the result.
	   * @param args Unused.
	   * @return Nothing.
	   * @exception IOException On file path error.
	   * @see IOException
	   */
	public static void main(String[] args) throws IOException {
		//TODO: Allow relative paths without making the thing dangerous
		String path = args[0];
		shop = FileReader.shopFromFile(path);
		if (shop == null) throw new NullPointerException();
		shop.processOrder();
		System.out.println(shop.printOrder());
		
		}

}
