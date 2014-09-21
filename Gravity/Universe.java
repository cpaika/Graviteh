package Gravity;
import java.util.ArrayList;
import java.util.Arrays;

class Universe
{
	private static final int UNIVERSE_SIZE = 1;
	private static Universe instance = null;
	Body[] bodies;
	private Universe()//private to ensure singleton
	{
		bodies = new Body[UNIVERSE_SIZE];
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
		for(int i = 0; i <= bodies.length - 1; i++)//check boundary conditions
		{
			bodies[i].update();
		}
	}
	/**
	Draws the objects to screen
	*/
	public void draw()
	{
		for(int i = 0; i <= bodies.length - 1; i++)//check boundary conditions
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
				Vector forceA = Physics.calcForce(a, b);
				a.sumForce(forceA);
				b.sumForce(forceA.getInverse());
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
		bodies[0] = new Planet(500,150,1000,5,5);
		//bodies[1] = new Planet(300,100,190,5,5);
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
}