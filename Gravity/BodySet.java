package Gravity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class is a wrapper for an ArrayList, used as the Set of Body objects in the Universe.  Helps manipulate the ArrayList
 * and prevent any corruption/errors from affecting the Universe state.  Follow the decorator pattern.
 * @author Christopher Paika
 *
 */
public class BodySet implements Iterable<Body>
{
	private static final int NONEXISTANT = -1;
	private ArrayList<Body> bodies;
	private int playerIndex;
	public BodySet()
	{
		bodies = new ArrayList<Body>();
		playerIndex = NONEXISTANT;
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
		if((playerIndex != NONEXISTANT) && (r == bodies.get(playerIndex)))
		{
			playerIndex = NONEXISTANT;
		}
		return bodies.remove(r);
	}

	/**
	 * Returns an iterator of the BodySet
	 */
	public Iterator<Body> iterator() 
	{
		return bodies.iterator();
	}

	/**
	 * Returns the current player controlled Body 
	 * @return Returns the player controlled Body if it exists.  If there is no current Player controlled Body this returns null.
	 */
	public Body getPlayer() 
	{
		if(playerIndex == NONEXISTANT)
		{
			return null;
		}
		else
		{
			return bodies.get(playerIndex);
		}
	}
	
	/**
	 * Set the current Player
	 * @param The Body object to set as the player
	 */
	public void addSetPlayer(Body player)
	{
		bodies.add(0, player);
		playerIndex = 0;
	}

	/**  Returns this Set of Objects with collisions between this and the parameter removed
	 * @param The Set to subtract from this set of Body's
	 * @return Returns the set of Body objects in this object with collisions between this and the removes set removed.
	 */
	public Collection<Body> subtractCollection(Collection<Body> removes)
	{
		ArrayList<Body> newBodies = (ArrayList<Body>) bodies.clone();
		newBodies.removeAll(removes);
		return newBodies;
	}
	
	/**
	 * 
	 * @return Returns the amount of body objects in the universe
	 */
	public int getSize()
	{
		return this.bodies.size();
	}
}
