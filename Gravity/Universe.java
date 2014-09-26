package Gravity;
import java.util.ArrayList;
import java.util.Arrays;

class Universe
{
	private static Universe instance = null;
	BodySet bodies = null;
	private Universe()//private to ensure singleton
	{
		bodies = new BodySet();
		generateTest();
		//generateSmallTest();
	}
	
	/**
		Returns the singleton instance of the Universe
	*/
	public static Universe getInstance()
	{
		if(instance==null)
		{
			instance = new Universe();
		}
		return instance;
	}
	/**
		Updates the acceleration and velocity and position of all the bodies in the universe.
	*/
	public void refresh()//TODO check how objects remove themselves
	{
		for(Body b: bodies)
		{
			b.update();
		}
	}
	/**
	Draws the objects to screen
	*/
	public void draw()
	{
		for(Body b: bodies)
		{
			b.draw();
		}
	}
	public void calcGrav()
	{
		ArrayList<Body> collection = new ArrayList<Body>(Arrays.asList(bodies));
		while(collection.size() > 1)
		{
			Body a = collection.get(0);
			collection.remove(0);
			while(a == null && collection.size() > 1)//TODO THIS IS A HACK
			{
				a = collection.get(0);
				collection.remove(0);
			}
			for(Body b : collection)
			{
				if(b == null)//this is a hack TODO
				{
					break;
				}
				if(a.checkCollision(b.getCollisionBox()))
				{
					System.out.println("Collision occured");
					if(a.getMass() > b.getMass())
					{
						b.collisionOccurred();
					}
					else if (a.getMass() < b.getMass())
					{
						a.collisionOccurred();
					}
					else//they are equal sizes
					{
						a.collisionOccurred();
						b.collisionOccurred();
					}
				}
				Vector forceA = Physics.calcForce(a, b);
				a.sumForce(forceA.getInverse());
				b.sumForce(forceA);
			}
		}
	}
	public void update()
	{
		calcGrav();
		refresh();
	}
	/**
	 * Generates Body's for a test universes
	 */
	public void generateTest()
	{
		int smallBodies = 5;
		addBody(new Planet(900,500,5,20,20));
		addBody(new Planet(800,500,5,20,20));
		addBody(new Planet(700,500,5,20,20));
		addBody(new Planet(600,500,5,20,20));
		addBody(new Planet(400,500,5,20,20));
		//addBody(new Planet(500,600,1,20,20));
		//addBody(new Planet(500,400,1,20,20));
		Body center = addBody(new Planet(500,500,1000,20,20));
		for(int i = 0; i < smallBodies; i++)
		{
			bodies[i].fixIntoOrbit(center);
		}
	}
	public void generateSmallTest()
	{
		addBody(new Planet(600,500,1,20,20));
		addBody(new Planet(500,500,1000,20,20));
		bodies[0].fixIntoOrbit(bodies[1]);
	}
	/** Returns a reference to the current player controlled body
	 * @return Returns the player controlled Body if it exists.  If there is no current Player controlled Body this returns null.
	 */
	public Body getPlayer()
	{
		return bodies.getPlayer();
	}
	
	/**
	 * Adds a Body to the Universe.  This Body will then be drawn to screen, affected by forces, etc.
	 * @param b The Body to be added to the Universe.
	 * @return Will return the Body if successfully added.  Will return null if there was a problem adding the Body to the Universe.
	 */
	public Body addBody(Body b)
	{
		if(bodies.addBody(b))
		{
			return b;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Removes the Body from the Universe.  No longer displays on screen, affected by forces, or affects any other Body.  
	 * @param a Body to be removed
	 * @return If the Body successfully was removed it returns the Body.  If the Body was not successfully removed it returns null.
	 */
	public Body remove(Body a)
	{
		if(bodies.removeBody(a))
		{
			return a;
		}
		else//couldn't remove body
		{
			return null;
		}
	}
}