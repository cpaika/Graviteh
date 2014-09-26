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
		//generateTest();
		generateSmallTest();
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
		ArrayList<Body> finished = new ArrayList<Body>();
		for(Body a: bodies)
		{
			for(Body b: bodies.subtractCollection(finished))
			{
				if(a.checkCollision(b.getCollisionBox()))//check for collisions among the two
				{
					collisionOccurred(a, b);
				}
				Vector forceA = Physics.calcForce(a, b);
				a.sumForce(forceA.getInverse());
				b.sumForce(forceA);
			}
			finished.add(a);
		}
	}
	/**
	 * Called on two Body objects when a collision occurs between them.  Decides what to do based on both Body's state
	 * @param a First Body that is colliding	
	 * @param b Second Body that is colliding
	 */
	private void collisionOccurred(Body a, Body b)
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
		Body center = addBody(new Planet(500,500,1000,20,20));
		
		Planet p1 = new Planet(900,500,5,20,20);
		p1.fixIntoOrbit(center);
		addBody(p1);
		
		Planet p2 = new Planet(800,500,5,20,20);
		p2.fixIntoOrbit(center);
		addBody(p2);
		
		Planet p3 = new Planet(700,500,5,20,20);
		p3.fixIntoOrbit(center);
		addBody(p3);
		
		Planet p4 = new Planet(600,500,5,20,20);
		p4.fixIntoOrbit(center);
		addBody(p4);
		
		Planet p5 = new Planet(400,500,5,20,20);
		p5.fixIntoOrbit(center);
		addBody(p5);
	}
	public void generateSmallTest()
	{
		Body center = addBody(new Planet(500,500,1000,20,20));
		Planet p = new Planet(600,500,1,20,20);
		p.fixIntoOrbit(center);
		addBody(p);
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