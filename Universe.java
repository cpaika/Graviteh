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
	Updates the positions of all the bodies in the universe.
	*/
	public void update()
	{
		for(int i = 0; i <= array.length - 1, i++)//check boundary conditions
		{
			Body[i].update();
		}
	}
	
	public void draw()
	{
		for(int i = 0; i <= array.length - 1, i++)//check boundary conditions
		{
			Body[i].draw();
		}
	}
	//TODO:  Calculate all the gravity forces
	public void calcGrav()
	{
		ArrayList<Body> collection = new ArrayList<Body>(bodies);//not sure if an array is a collection
		while(!collection.isEmpty())
		{
			Body a = collection.get(0);
			collection.remove(0);
			for(Body b in collection)
			{
				Vector force = calcForce(a, b);
				//TODO need to figure out how the directions work
			}
		}
	}
}