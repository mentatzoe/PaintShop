package paintshop;

import java.util.ArrayList;
import java.util.Collections;

/**
* Class description
*
* @author  Maria Lopez-Latorre
* @version 1.0
* @since   2016-05-29 
*/
public class Customer {

	private ArrayList<Color> preferences;
	
	/**
	 * Constructor
	 * 
	 * Creates a client and their color preferences from the an array of chars
	 * generated with the non-space characters of their corresponding line.
	 * @param prefs Array of chars with the preferences of the clients. 
	 */
	public Customer(char[] prefs){
		preferences = new ArrayList<Color>();
		for (int i=0; i<=prefs.length-1; i= i+2) {
			preferences.add(new Color(Character.getNumericValue(prefs[i]),prefs[i+1]));
		}
	}
	
	/** Accessor for the customer's preferences */
	public ArrayList<Color> getPreferences() {
		return preferences;
	}
	
	/**
	 * Returns whether a Customer is satisfied with a possible solution.
	 * This implies that at least one of their preferences is included in the solution.
	 * If there's no intersection in the collections of the solution's colors 
	 * and the customer's color preferences, the customer won't be satisfied.
	 * @param possibleSolution The potential solution.
	 * @return boolean Whether the customer is satisfied.
	 */
	public boolean isSatisfiedBySolution(Solution possibleSolution){
		return !Collections.disjoint(possibleSolution.getColors(), preferences);
	}
	
}
