package paintshop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * PaintShop
 * 
 * Contains the information that relates to the store and
 * its processes, which includes processing the order.
 *
 * @author  Maria Lopez-Latorre
 * @version 1.0
 * @since   2016-05-29 
 */
public class PaintShop {
	private final int numberOfColors;
	private ArrayList<Customer> customers;
	private Solution solution;
	private TreeSet<Solution> possibleSolutions;
	private ArrayList<Color> mustHaveColors;

	/**
	 * Constructor
	 * Creates a Shop with a given number of colors.
	 * It initializes the TreeSet of the possibleSolutions with a comparator
	 * that takes into consideration the cost of each solution so that the first
	 * solution is always the cheapest one.
	 * 
	 * @param numColors Number of paint colors that the store will have.
	 */
	public PaintShop(int numColors) {
		numberOfColors = numColors;
		customers = new ArrayList<Customer>();
		possibleSolutions = new TreeSet<Solution>(new Comparator<Solution>() {
			@Override
			public int compare(Solution s1, Solution s2) {
				return s1.getCost() > s2.getCost() ? 1 : s1.getCost() < s2.getCost() ? -1 :0;
			}
		});
		mustHaveColors = new ArrayList<Color>();
		solution = new Solution(numberOfColors); 
	}

	/** Adds a customer to the shop. */
	public void addCustomer(Customer cust){
		customers.add(cust);
	}

	/**
	 * This method contains the logic to finding a solution.
	 * The algorithm has the following logic:
	 * 
	 * 1. Processes the case of customers with a single preference.
	 * 2. If there's a possible solution after doing so, it generates all possible permutations
	 * 3. Removes all the preferences that don't suit the customers.
	 * 4. When only the solutions that actually would satisfy the costumers exist,
	 * 		it sets as solution the one with the lowest cost.
	 * 
	 * @return Nothing
	 */
	public void processOrder() {
		checkOnePreferenceCustomers();

		ArrayList<Solution> allSolutions = new ArrayList<Solution>();
		if (solution != null) {
			generatePermutations(solution, 0, numberOfColors, allSolutions);

			//Optimize this
			Iterator<Solution> allIterator = allSolutions.iterator();
			while (allIterator.hasNext()) {
				Solution currentSolution = allIterator.next();
				for (Color currentColor : mustHaveColors) {
					if (!currentSolution.contains(currentColor)) {
						allIterator.remove();
					}
				}
			}

			int customersSatisfied = 0;
			for (Solution currentSolution : allSolutions) {
				for (Customer currentCustomer : customers) {
					if (currentCustomer.isSatisfiedBySolution(currentSolution)) {
						customersSatisfied++;
					}
					if (customersSatisfied == customers.size())
						possibleSolutions.add(currentSolution);
				}
			}

			if (possibleSolutions.isEmpty())
				solution = null;
			else {
				solution = possibleSolutions.first();
			}
		}
	}

	/**
	 * This method checks if there's a solution and returns its readable format.
	 * @return String The result of processing the order.
	 */
	public String printOrder() {
		if (solution == null) return "No solution exists";
		else {
			final StringBuilder builder = new StringBuilder();
			for (int i = 0, j = solution.size(); i < j; i++) {
				if (i > 0)
					builder.append(" ");
				builder.append(solution.getColor(i));
			}
			return builder.toString();
		}
		
	}
	
	/**
	 * Helper used to format the solution string properly
	 * */

	/**
	 * Recursively generates permutations (combinations) of possible solutions, 
	 * and adds them to a collection if they didn't exist.
	 * 
	 * Given that order is important and that we don't sort this collection,
	 * solutions is implemented with an ArrayList instead of a HashSet.
	 * This implies though that we have to check whether the solution we're adding is already there or not.
	 * 
	 * @param solution Initial solution.
	 * @param start Left index.
	 * @param end Right index (far-right end of the list).
	 * @param solution Collection used to store the solutions found.
	 * @return Nothing.
	 */
	private void generatePermutations(Solution solution, int start, int end, ArrayList<Solution> solutions) {
		if (start == end) {
			if (!solutions.contains(solution)) solutions.add(new Solution(solution));
		} else {
			for (int i = start; i<end;i++) {
				generatePermutations(solution, i+1, end, solutions);
				solution.getColor(i).switchFinish();
				generatePermutations(solution, i+1, end, solutions);
			}
		}
	}

	/**
	 * This method takes into consideration all the clients 
	 * that have only one preference,checks if those preferences 
	 * are compatible, and adds them to the list of colors that
	 * have to be on the solution.
	 *  
	 * If those single preferences aren't compatible between them, 
	 * it nullifies the solution in order to prevent the processing 
	 * algorithm from generating the permutations.
	 * 
	 * This will help decrease the amount of computed solutions 
	 * on the order processing, 
as we can determine that if there's a client that has only one preference this has to be
	 * on the final solution.
	 * @return Nothing.
	 */
	private void checkOnePreferenceCustomers() {
		for (Customer cust:customers ){
			if (cust.getPreferences().size()==1 && !mustHaveColors.contains(cust.getPreferences().get(0))){
				mustHaveColors.add(cust.getPreferences().get(0));
			}
		}
		if (!mustHaveColors.isEmpty()) {
			if (!colorsCompatible(mustHaveColors)) {
				solution = null;
			}
		}
	}

	/**
	 * This method checks whether a group of colors is fully compatible.
	 * 
	 * @param colors The list of colors.
	 * @return boolean Whether all of those colors are compatible or not
	 */
	private boolean colorsCompatible(ArrayList<Color> colors) {
		boolean compatible = true; 
		for (int i = 0; i<colors.size()-1; i++) {
			if (!colors.get(i).isCompatible(colors.get(i+1))) {
				compatible = false;
			}
		}
		return compatible;
	}
}
