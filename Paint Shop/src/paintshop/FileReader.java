package paintshop;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Class description
 *
 * @author  Maria Lopez-Latorre
 * @version 1.0
 * @since   2016-05-29 
 */
public class FileReader {

	/**
	 * This method reads a file line by line and generates a PaintShop from it.
	 * @param path The path to the file with the store specifications.
	 * @return PaintShop Shop generated from file.
	 */
	public static PaintShop shopFromFile(String path) throws IOException{
		
		//TODO: Exception handling
		//try {
		FileInputStream fstream = new FileInputStream(path);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String numColorsLine = br.readLine();
		PaintShop shop = new PaintShop(Integer.parseInt(numColorsLine));

		String customerLine;
		while ((customerLine = br.readLine()) != null)   {
			shop.addCustomer(new Customer(customerLine.replaceAll("\\s", "").toCharArray()));
		}
		//Close the input stream
		in.close();

		return shop;
		/*} catch (Exception e){
			System.err.println("Error: " + e.getMessage());
			return null;
		}*/
	}

}
