package Gravity;
class Body
{
	//Center of the body object
	int posX, posY;
	int height, width = 0; //TODO:initialize these
	Vector velocity;//velocity vector
	Vector accel;//acceleration vector
	Vector force;//force vector
	int mass;
	private Display display;
	
	/**
	Constructor, takes in the x position, y position, velocity vector, mass and reference to display singleton
	*/
	public Body(int x, int y, Vector v, int m, Display d, int h, int w)
	{
		posX = x;
		posY = y;
		velocity = v;
		mass = m;
		display = d;
		height = h;
		width = w;
	}
	
	/**
	Constructor, takes in the x position, y position, mass and reference to display singleton.
	Sets velocity to 0;
	*/
	public Body(int x, int y, int m, Display d, int h, int w)
	{
		posX = x;
		posY = y;
		velocity = new Vector(0,0);
		mass = m;
		display = d;
		height = h;
		width = w;
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
	Calculates a new positon based on velocity.
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
	At First all the forces between all the bodies are calulated and summed.  Then the resultant force vector is turned into an acceleration vector.  
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
		calcAcceleration();
		calcVelocity();
	}
	public void draw()
	{
		//put in display code here
	}
}