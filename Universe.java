import java.util.ArrayList<E>

class Universe
{
	private static final UNIVERSE_SIZE = 100;
	private static Universe instance = null;
	Body[] bodies;
	private Universe()//private to ensure singleton
	{
		bodies = new Body[UNIVERSE_SIZE]
	}
	
	/**
	Returns the singleton instance of the Universe
	*/
	public static getInstance()
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
	public void update()
	{
		for(int i = 0; i <= array.length - 1, i++)//check boundary conditions
		{
			Body[i].update();
		}
	}
	/**
	Draws the objects to screen
	*/
	public void draw()
	{
		for(int i = 0; i <= array.length - 1, i++)//check boundary conditions
		{
			Body[i].draw();
		}
	}
	public void calcGrav()
	{
		ArrayList<Body> collection = new ArrayList<Body>(Arrays.asList(bodies));//not sure if an array is a collection
		while(collection.size() > 1)
		{
			Body a = collection.get(0);
			collection.remove(0);
			for(Body b in collection)
			{
				Vector forceA = calcForce(a, b);
				a.sumForce(forceA);
				b.sumForce(forceA.getInverse());
			}
		}
	}
}