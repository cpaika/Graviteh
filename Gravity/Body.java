package Gravity;
abstract class Body implements Controllable
{
	protected boolean isDestroyable;
	protected int safeTime = 0;
	//Center of the body object
	private final int GLOBAL_SPEED_LIMIT = 30;
	protected Vector centerLocation;
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
		centerLocation = new Vector(x-(w/2), y-(h/2));
		velocity = v;
		mass = m;
		height = h;
		width = w;
		display = Display.getDisplay();
		setUp();
	}
	
	/**
	Constructor, takes in the x position, y position, mass and reference to display singleton.
	Sets velocity to 0;
	*/
	public Body(int x, int y, int m, int h, int w)
	{
		centerLocation = new Vector(x-(w/2), y-(h/2));
		velocity = new Vector(0,0);
		mass = m;
		height = h;
		width = w;
		display = Display.getDisplay();
		setUp();
	}
	public Body(Vector l,Vector v, int m, int h, int w)
	{
		centerLocation = l.copy();
		velocity = v.copy();
		mass = m;
		height = h;
		width = w;
		display = Display.getDisplay();
		setUp();
	}
	
	public abstract void collisionOccurred();
	protected abstract void collisionDestroy();
	protected abstract void eventOccurred(ControlEvent e);
	
	public void setUp()
	{
		isDestroyable = true;
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
		return (int) ((centerLocation.getXComp()-(width/2)) + .5);
	}
	public int getPosY()
	{
		return (int) ((centerLocation.getYComp()-(height/2)) + .5);
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
		return (int) (centerLocation.getXComp() + .5);
	}
	/**
	 * @return Returns the exact center y component of the Body
	 */
	public int getCenterY()
	{
		return (int) (centerLocation.getYComp() + .5);
	}
	/**
	 * Returns the Vector pointing to the center of this object
	 * @return the center of the object
	 */
	public Vector getCenterVector()
	{
		return centerLocation.copy();
	}
	
	public Vector getTopLeftVector()
	{
		return centerLocation.addition(new Vector(-width/2, -height/2));
	}
	/*
	Calculates a new position based on velocity.
	*/
	public void calcPosition()
	{
		centerLocation = centerLocation.addition(velocity);
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
		if(!isDestroyable)
		{
			safeTime--;
			if(safeTime == 0)//body can now collide with other bodies
			{
				isDestroyable = true;
			}
		}
		//System.out.println("My velocity is " + velocity.getXComp() + " : " + velocity.getYComp());
		calcAcceleration();
		calcVelocity();
		calcPosition();
		collide.update(new Vector(getCenterX(), getCenterY()));
	}
	public void draw()
	{
		//put in display code here
		System.out.println("X: " + centerLocation.getXComp() + " Y: " + centerLocation.getYComp());
	}
	
	/**
	 * Functions for manipulating a Body
	 */
	abstract public void upButton();
	abstract public void leftButton();
	abstract public void rightButton(); 
	abstract public void downButton();
	
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
		int mass = center.getMass();
		Vector distance = this.getCenterVector().subtract(center.getCenterVector());
		double rawVelocity = Math.sqrt((Physics.gravConst*center.mass)/distance.getMagnitude());
		velocity = distance.normalize().rotate(90);
		velocity = velocity.multiplyBy(rawVelocity);
		return;
	}
	/**
	 * Sets the time that this Body is safe from having a collsiion against other Body's registering
	 * @param Time that the object is safe from collisions.  TIme denoted in ticks, with 60 ticks to the second.
	 */
	public void setSafeTime(int t)
	{
		safeTime = t;
		isDestroyable = false;
	}
	
}