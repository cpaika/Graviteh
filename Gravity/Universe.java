package Gravity;
import java.util.ArrayList;
import java.util.Arrays;

class Universe
{
	private static final int UNIVERSE_SIZE = 300;
	private static Universe instance = null;
	Body[] bodies;
	private int bodyCount;
	private Universe()//private to ensure singleton
	{
		bodies = new Body[UNIVERSE_SIZE];
		bodyCount = 0;
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
	public void refresh()
	{
		for(int i = 0; i <= bodyCount - 1 ; i++)//check boundary conditions
		{
			if(bodies[i] != null)
			{
				bodies[i].update();
			}
		}
	}
	/**
	Draws the objects to screen
	*/
	public void draw()
	{
		for(int i = 0; i <= bodyCount - 1; i++)//check boundary conditions
		{
			if(bodies[i] != null)
			{
				bodies[i].draw();
			}
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
	/**
	 * @return Returns a reference to the current player controlled body
	 */
	public Body getPlayer()
	{
		return bodies[0];
	}
	
	public Body addBody(Body b)
	{
		if(bodyCount < this.UNIVERSE_SIZE - 1)
		{
			bodies[bodyCount] = b;
			bodyCount++;
			return b;
		}
		else
		{
			return null;
		}
	}
	public Body returnLastAdded()
	{
		return bodies[bodyCount-1];
	}
	
	public Body remove(Body a)
	{
		for(int i = 0;  i < bodies.length; i++)
		{
			if((bodies[i] != null) && (bodies[i].equals(a)))
			{
				Body eject = bodies[i];
				bodies[i] = null;
				return eject;
			}
		}
		return null; //if nothing is found
	}
}