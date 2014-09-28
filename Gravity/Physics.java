package Gravity;

import java.lang.Math;

class Physics
{
	//This is the constant that determines how powerful the force of gravity is, the higher it is the more powerful.
	public static final double gravConst = 1; 
	private Physics()
	{
		//This class can never be implemented
	}

	/*
	Calculates the force acting between the two bodies.  Force is the same for both objects, however it points in opposite directions for both.  
	This returns the vector of the force for Body a.  Assumes that posX and posY are the center of the Body objects.  Body b's vector is the inverse of this resultant vector
	*/
	
	public static Vector calcForce(Body a, Body b)
	{
		Vector force = new Vector(a.getCenterX() - b.getCenterX(), a.getCenterY() - b.getCenterY());
		double distance = force.getMagnitude();
		double m = (gravConst * a.mass * b.mass)/(distance*distance);
		force = force.normalize();
		force = force.multiplyBy(m);
		return force;
	}
}