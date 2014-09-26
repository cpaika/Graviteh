package Gravity;
class Body implements Controllable
{
	//Center of the body object
	private final int GLOBAL_SPEED_LIMIT = 30;
	double posX, posY;
	int height, width = 0; //TODO:initialize these
	protected Vector velocity;//velocity vector
	protected Vector accel;//acceleration vector
	Vector force;//force vector
	int mass;
	protected Display display;
	CollisionBox collide;
	
	/**
	Constructor, takes in the x position, y position, velocity vector, mass and reference to display singleton
	*/
	public Body(int x, int y, Vector v, int m, int h, int w)
	{
		posX = x-(w/2);
		posY = y-(h/2);
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
		collide = new CollisionBox(height/2); 
		force = new Vector(0,0);
		accel = new Vector(0,0);
		collide.update(new Vector(getCenterX(), getCenterY()));
	}
	
	public int getMass()
	{
		return mass;
	}
	public int getPosX()
	{
		return (int) (posX + .5);
	}
	public int getPosY()
	{
		return (int) (posY + .5);
	}
	public Vector getVelocity()
	{
		return velocity;
	}
	/**
	 * @return Returns the exact center x component of the Body
	 */
	public int getCenterX()
	{
		return (int)(posX + height*.5);
	}
	/**
	 * @return Returns the exact center y component of the Body
	 */
	public int getCenterY()
	{
		return (int)(posY + width*.5);
	}
	public Vector getCenterVector()
	{
		return new Vector(this.getCenterX(), this.getCenterY());
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
		if(velocity.addition(accel).getMagnitude() <= GLOBAL_SPEED_LIMIT)
		{
			velocity = velocity.addition(accel);
			accel = new Vector(0,0);
		}
		else
		{
			//Here the objects speed should be maxed out TODO
		}
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
		collide.update(new Vector(getCenterX(), getCenterY()));
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
		velocity = velocity.addition(new Vector(0,1));
	}

	
	/**
	 * Increases upward velocity
	 */
	public void upButton() 
	{
		velocity = velocity.addition(new Vector(0,-1));
	}

	/**
	 * Increases leftward velocity
	 */
	public void leftButton() 
	{
		velocity = velocity.addition(new Vector(-1,0));
	}

	/**
	 * Increases rightward velocity
	 */
	public void rightButton() 
	{
		velocity = velocity.addition(new Vector(1,0));
	}
	public CollisionBox getCollisionBox()
	{
		return collide;
	}
	public boolean checkCollision(CollisionBox other)
	{
		return collide.checkCollision(other);
	}
	
	/**
	 * Fixes the Body's orbit into a circular one around the passed in Body.  Only works when the orbiting body is significantly smaller in mass than the central body.
	 * @param center The Body this body is going to orbit around
	 */
	public void fixIntoOrbit(Body center)
	{
		System.out.println("Fixing a body into orbit!");
		int mass = center.getMass();
		Vector distance = this.getCenterVector().subtract(center.getCenterVector());
		double rawVelocity = Math.sqrt((Physics.gravConst*mass)/distance.getMagnitude());
		System.out.println("Raw velocity is: " + rawVelocity);
		velocity = new Vector(Physics.calcForce(this, center).normalize().getYComp(), Physics.calcForce(this, center).normalize().getXComp());//dirty way of finding normal vector
		System.out.println("Velocity vector after normalization: " + velocity);
		velocity = velocity.multiplyBy(rawVelocity);
		return;
	}
}