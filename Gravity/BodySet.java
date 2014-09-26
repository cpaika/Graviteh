package Gravity;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * This class is a wrapper for an ArrayList, used as the Set of Body objects in the Universe.  Helps manipulate the ArrayList
 * and prevent any corruption/errors from affecting the Universe state.
 * @author Christopher Paika
 *
 */
public class BodySet implements Iterable
{
	private ArrayList<Body> bodies;
	public BodySet()
	{
		bodies = new ArrayList<Body>();
	}
	
	/**
	 * Adds a Body to the set of Body's
	 * @param a Body to be added
	 * @return True if Body is added successfully, false if there is an error adding the Body and it was not added to the set
	 */
	public boolean addBody(Body a)
	{
		bodies.add(a);
		return true;
	}
	/**
	 * Removes a body from the set of Body's
	 * @param r The body to be removed
	 * @return True if the Body was successfully removed, false if there was an error and the Body was not removed
	 */
	public boolean removeBody(Body r)
	{
		return bodies.remove(r);
	}

	/**
	 * Returns an iterator of the BodySet
	 */
	public Iterator iterator() 
	{
		return (Iterator<Body>)bodies.iterator();
	}
}
