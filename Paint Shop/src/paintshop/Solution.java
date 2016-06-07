package paintshop;

import java.util.ArrayList;

/**
* Solution model object
* 
* Contains the colors that belong to a particular solution
* and the logic to evaluate how expensive it will be.
*
* @author  Maria Lopez-Latorre
* @version 1.0
* @since   2016-05-29 
*/
public class Solution {
	
	private ArrayList<Color> solutionColors;
	
	/**
	 * Constructor by expliciting colors.
	 * 
	 * Creates a solution with a set of specific colors
	 * @param colors Array of colors
	 */
	public Solution(Color...colors) {
		solutionColors = new ArrayList<Color>();
		for(Color c:colors) {
			solutionColors.add(new Color(c));
		}
	}
	
	/**
	 * Constructor by copy
	 * 
	 * Creates a solution by copying another solution's colors.
	 * @param otherSolution The solution we wish to copy
	 */
	public Solution(Solution otherSolution) {
		solutionColors = new ArrayList<Color>();
		for (int i = 0; i<otherSolution.size(); i++) {
			solutionColors.add(new Color(otherSolution.getColor(i)));
		}
	}
	
	/**
	 * Constructor by size.
	 * 
	 * Creates a solution of a determined size with all its colors
	 * initialized with the cheapest paint finish.
	 * @param size Size of the desired constructor.
	 */
	public Solution(int size) {
		solutionColors = new ArrayList<Color>();
		for (int i = 0; i<size; i++){
			solutionColors.add(new Color(i+1));
		}
	}
	
	/**
	 * Retrieves a specific color within the solution's colors.
	 * @param i index of the desired color.
	 * @return Color Color stored in the provided index.
	 */
	public Color getColor(int i) {
		return solutionColors.get(i);
	}
	
	/**
	 * @return ArrayList<Color> Returns the colors of the solution.
	 */
	public ArrayList<Color> getColors() {
		return solutionColors;
	}
	
	/**
	 * This method gets the cost of the solution by aggregating the cost of its colors
	 * given the understanding that matte finishes are more expensive.
	 * @return int Total cost of solution.
	 */
	public int getCost() {
		int cost = 0;
		for(Color c:solutionColors) {
			cost += c.getCost();
		}
		return cost;
	}

	/**
	 * This method reads a file line by line and generates a PaintShop from it.
	 * @return PaintShop Shop generated from file.
	 */
	public int size() {
		return solutionColors.size();
	}
	
	/**
	 * This method checks if an object (which we could assert to be a color) is present
	 * in the current Solution.
	 * @param obj Object that we want to check if present in the solution's colors.
	 * @return boolean Returns whether the colors in the solution contain the object or not.
	 */
	public boolean contains(Object obj) {
		return solutionColors.contains(obj);
	}
	
	/**
	 * This method overrides toString() in order to provide a human readable
	 * output when casting a Solution to string.
	 * @return String Human readable string with the current solution.
	 */
	@Override
	public String toString() {
		return solutionColors.toString();
	}
	
	/**
	 * Custom equal override that returns whether 
	 * a Solution equals another object by comparing its colors.
	 * If the other object isn't a solution it will never be equal.
	 * @param obj Object we want to compare to this instance of Solution.
	 * @return boolean Returns whether the current solution equals obj.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Solution))
			return false;
		else if (obj == this)
			return true;
		
		Solution otherSolution = (Solution) obj;
		return solutionColors.equals(otherSolution.solutionColors);
	}
}
