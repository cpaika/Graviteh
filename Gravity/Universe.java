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
			bodies[i].update();
		}
	}
	/**
	Draws the objects to screen
	*/
	public void draw()
	{
		for(int i = 0; i <= bodyCount - 1; i++)//check boundary conditions
		{
			bodies[i].draw();
		}
	}
	public void calcGrav()
	{
		ArrayList<Body> collection = new ArrayList<Body>(Arrays.asList(bodies));
		while(collection.size() > 1)
		{
			Body a = collection.get(0);
			collection.remove(0);
			for(Body b : collection)
			{
				if(b == null)//this is a hack TODO
				{
					break;
				}
				if(a.checkCollision(b.getCollisionBox()))
				{
					System.out.println("Collision occured");
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
		addBody(new Planet(40,400,1000,20,20));
		addBody(new Planet(300,300,1,20,20));
		//bodies[2] = new Planet(700,100,190,5,5);
		//bodies[2] = new Planet(21,100,100,10,10);
	}
	/**
	 * @return Returns a reference to the current player controlled body
	 */
	public Body getPlayer()
	{
		return bodies[0];
	}
	
	public boolean addBody(Body b)
	{
		if(bodyCount < this.UNIVERSE_SIZE - 1)
		{
			bodies[bodyCount] = b;
			bodyCount++;
			return true;
		}
		else
		{
			return false;
		}
	}
	public Body returnLastAdded()
	{
		return bodies[bodyCount-1];
	}
}