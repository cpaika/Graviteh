package Gravity;
import java.util.ArrayList;
import java.util.LinkedList;

public class Universe
{
	private static Universe instance = null;
	BodySet bodies = null;
	ArrayList<Body> collisions;
	ArrayList<Body> added;
	ArrayList<LaserBeam> laserBeams;
	ArrayList<ControlEvent> events;
	Body center;
	
	
	private Universe()//private to ensure singleton
	{
		bodies = new BodySet();
		collisions = new ArrayList<Body>();
		added = new ArrayList<Body>();
		events = new ArrayList<ControlEvent>();
		laserBeams = new ArrayList<LaserBeam>();
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
		for(LaserBeam l: laserBeams)
		{
			l.update();
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
		for(LaserBeam l: laserBeams)
		{
			l.draw();
		}
		Display.getDisplay().drawText("Bodies: " + String.valueOf(bodies.getSize()));
	}
	private void calcGravAndCollisions()
	{
		ArrayList<Body> finished = new ArrayList<Body>();
		for(Body a: bodies)
		{
			finished.add(a);
			for(Body b: bodies.subtractCollection(finished))
			{
				if(a.checkCollision(b.getCollisionBox()))//check for collisions among the two
				{
					collisionOccurred(a, b);
				}
				if(b.isDestroyable)
				{
					Vector forceA = Physics.calcForce(a, b);
					a.sumForce(forceA.getInverse());
					b.sumForce(forceA);
				}
			}
		}
		//Below empties the collision event stack
		while(collisions.size() > 0)
		{
			Body c = collisions.remove(0);
			c.collisionOccurred();
		}
		//Below empties the added body event stack
		while(added.size() > 0)
		{
			Body b = added.remove(0);
			this.bodies.addBody(b);
		}
		//Below empties the event stack
		while(events.size() > 0)
		{
			ControlEvent e = events.remove(0);
			if(this.getPlayer() != null)
			{
				this.getPlayer().eventOccurred(e);
			}
		}
	}
	/**
	 * Called on two Body objects when a collision occurs between them.  Decides what to do based on both Body's state
	 * @param a First Body that is colliding	
	 * @param b Second Body that is colliding
	 */
	private void collisionOccurred(Body a, Body b)
	{
		if(a.getMass() > b.getMass())
		{
			addToCollisions(b);
		}
		else if (a.getMass() < b.getMass())
		{
			addToCollisions(a);
		}
		else//they are equal sizes
		{
			addToCollisions(a);
			addToCollisions(b);
		}
	}
	public void update()
	{
		calcGravAndCollisions();
		refresh();
	}
	/**
	 * Generates Body's for a test universes
	 */
	public void generateTest()
	{
		center = addBody(new Planet(500,500,5000,20,20));
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
		
		SpaceShip first = new SpaceShip(new Vector(50,50), new Vector(0,0), 5, new Vector(20, 20));
		bodies.addSetPlayer(first);
	}
	public void generateSmallTest()
	{
		center = addBody(new Planet(500,500,100,20,20));
		Planet p = new Planet(600,500,1,20,20);
		p.fixIntoOrbit(center);
		addBody(p);
		
		SpaceShip first = new SpaceShip(new Vector(50,50), new Vector(0,0), 5, new Vector(20, 20));
		bodies.addSetPlayer(first);
	}
	/** Returns a reference to the current player controlled body
	 * @return Returns the player controlled Body if it exists.  If there is no current Player controlled Body this returns null.
	 */
	private Body getPlayer()
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
		if(this.added.add(b))
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
	
	/**
	 * Adds a Body to the list of bodies that need to be notified of a collision
	 * @param a The body that has to be notified of a collision
	 */
	private void addToCollisions(Body a)
	{
		if(!collisions.contains(a))
		{
			collisions.add(a);
		}
	}
	
	public Body getCenterBody()
	{
		return center;
	}
	/**
	 * Receives a ControlEvent and adds it to the Event Queue
	 * @param e The ControlEvent to be added
	 */
	public void sendEvent(ControlEvent e)
	{
		events.add(e);
	}
	/**
	 * Adds a LaserBeam to the Universe and begins displaying/moving it
	 * @param start The location of the LaserBeam
	 * @param velocity The velocity of the laser beam
	 */
	public void newLaserBeam(Vector start, Vector velocity) 
	{
		LaserBeam temp = new LaserBeam(start, velocity);
		laserBeams.add(temp);
	}
}