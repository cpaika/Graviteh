import java.lang.Math;
class Body
{
	//Center of the body object
	int posX, posY;

	Vector velocity;//velocity vector
	Vector accel;//acceleration vector
	Vector force;//force vector
	int mass;

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
	public calcPosition()
	{
		posX = Math.round(this.velocity.getXComp());//might give you errors 
		posY = Math.round(this.velocity.getYComp());
	}
	/*
	Calculates velocity vector.  Clears the acceleration vector when finished
	*/
	public calcVelocity()
	{
		velocity = velocity.addition(accel);
		acceleration = new Vector(0,0);
	}
	/*
	At First all the forces between all the bodies are calulated and summed.  Then the resultant force vector is turned into an acceleration vector.  
	This function clears the force variable once done.
	*/
	public calcAcceleration()
	{
		accel = force.divideBy(mass);
		force = new Vector(0,0);
	}

	/*
	This function takes a force vector and adds it to the current combination of force vectors
	*/
	public sumForce(Vector a)
	{
		force = force.addition(a);
	}
}