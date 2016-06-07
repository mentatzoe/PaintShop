package paintshop;

/**
* Color 
* 
* Contains the attributes and logic needed to represent 
* and work with colors on the PaintStore. 
*
* @author  Maria Lopez-Latorre
* @version 1.0
* @since   2016-05-29 
*/
public class Color {

	private char finish;
	private final int position;
	
	/**
	 * Constructor by specs
	 * 
	 * Creates a color by explicitely indicating the value of its attributes
	 * @param pos The position of the color
	 * @param paint The finish that the paint will have
	 */
	public Color(int pos, char paint) {
		position = pos;
		finish = paint;
	}
	
	/**
	 * Constructor by position
	 * 
	 * Creates a color with the given position and the standard finish (Glossy)
	 * @param pos The position of the color
	 */
	public Color(int pos) {
		position = pos;
		finish = 'G';
	}
	
	/**
	 * Constructor by copy
	 * 
	 * Creates a color with the specification of another color.
	 * @param c The color that we want to get a copy of.
	 */
	public Color(Color c) {
		position = c.getPosition();
		finish = c.getFinish();
	}

	/** Accessor for the color's position*/
	public int getPosition() {
		return position;
	}

	/** Accessor for the color's finish*/
	public char getFinish() {
		return finish;
	}
	
	/**
	 * This method switches the finish of the current color 
	 * from matte to glossy and vice-versa.
	 * @return Nothing
	 */
	public void switchFinish() {
		if (finish == 'G') {
			finish = 'M';
		} else {
			finish = 'G';
		}
	}
	
	/**
	 * This method returns the cost associated to a finish for the current color.
	 * Matte is more expensive.
	 * @return int The cost of making that color.
	 */
	public int getCost() {
		if (finish == 'G') {
			return 0;
		} else {
			return 1;
		}
	}
	
	/**
	 * This method returns whether two colors can exist at the same time in a store.
	 * @param c The color that is susceptible of being compatible
	 * @return boolean Whether the colors are compatible or not.
	 */
	public boolean isCompatible(Color c) {
		if(position == c.position && finish != c.finish) {
			return false;
		}
		return true;
	}
	
	/**
	 * This method overrides toString() in order to provide a human readable
	 * output when casting a Color to string. 
	 * In this case, this means the finish of the color. 
	 * @return String Human readable string with the current Color.
	 */
	@Override
	public String toString(){
		return "" + finish;
	}
	
	/**
	 * Custom equal override that returns whether 
	 * a Color equals another object by comparing its attributes.
	 * We consider two colors equal if their position and finish are the same.
	 * @param obj Object we want to compare to this instance of Color.
	 * @return boolean Returns whether the current color equals obj.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Color))
			return false;
		else if (obj == this)
			return true;

		Color otherColor = (Color) obj;
		return otherColor.getPosition() == position && otherColor.getFinish() == finish;
	}
}
