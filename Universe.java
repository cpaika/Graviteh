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
}