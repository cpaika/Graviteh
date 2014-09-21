package Gravity;
class Body implements Controllable
{
	//Center of the body object
	int posX, posY;
	int height, width = 0; //TODO:initialize these
	protected Vector velocity;//velocity vector
	protected Vector accel;//acceleration vector
	Vector force;//force vector
	int mass;
	protected Display display;
	
	/**
	Constructor, takes in the x position, y position, velocity vector, mass and reference to display singleton
	*/
	public Body(int x, int y, Vector v, int m, int h, int w)
	{
		posX = x;
		posY = y;
		velocity = v;
		mass = m;
		height = h;
		width = w;
		display = null;
		setUp();
	}
	
	/**
	Constructor, takes in the x position, y position, mass and reference to display singleton.
	Sets velocity to 0;
	*/
	public Body(int x, int y, int m, int h, int w)
	{
		posX = x;
		posY = y;
		velocity = new Vector(0,0);
		mass = m;
		height = h;
		width = w;
		display = Display.getDisplay();
		setUp();
	}
	
	public void setUp()
	{
		force = new Vector(0,0);
		accel = new Vector(0,0);
	}
	
	public int getMass()
	{
		return mass;
	}
	public int getPosX()
	{
		return posX;
	}
	public int getPosY()
	{
		return posY;
	}
	public Vector getVelocity()
	{
		return velocity;
	}
	/*
	Calculates a new position based on velocity.
	*/
	public void calcPosition()
	{
		posX = (int)(posX + this.velocity.getXComp() + .5);//.5 rounds to the nearest int
		posY = (int)(posY + this.velocity.getYComp() + .5);
	}
	/*
	Calculates velocity vector.  Clears the acceleration vector when finished
	*/
	public void calcVelocity()
	{
		velocity = velocity.addition(accel);
		accel = new Vector(0,0);
	}
	/*
	At First all the forces between all the bodies are calculated and summed.  Then the resultant force vector is turned into an acceleration vector.  
	This function clears the force variable once done.
	*/
	public void calcAcceleration()
	{
		accel = force.divideBy(mass);
		force = new Vector(0,0);
	}

	/*
	This function takes a force vector and adds it to the current combination of force vectors
	*/
	public void sumForce(Vector a)
	{
		force = force.addition(a);
	}
	/*
	Updates the acceleration and velocity of the Body
	*/
	public void update()
	{
		//System.out.println("My velocity is " + velocity.getXComp() + " : " + velocity.getYComp());
		calcAcceleration();
		calcVelocity();
		calcPosition();
	}
	public void draw()
	{
		//put in display code here
		System.out.println("X: " + posX + " Y: " + posY);
	}
	

	
	/**
	 * Calling this increases the downward velocity
	 */
	public void downButton() 
	{
		velocity = velocity.specialAddition(new Vector(0,1));
	}

	
	/**
	 * Increases upward velocity
	 */
	public void upButton() 
	{
		System.out.println("Up button pressed!");
		velocity = velocity.specialAddition(new Vector(0,-1));
	}

	/**
	 * Increases leftward velocity
	 */
	public void leftButton() 
	{
		System.out.println("left button pressed!");
		velocity = velocity.specialAddition(new Vector(-1,0));
	}

	/**
	 * Increases rightward velocity
	 */
	public void rightButton() 
	{
		velocity = velocity.specialAddition(new Vector(1,0));
	}
}