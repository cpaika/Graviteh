package Gravity;

import java.lang.Math;
//TODO Look into using the Java vector class

class Physics
{
	//This is the constant that determines how pwerful the force of gravity is, the higher it is the more powerful.
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
		double totalMass = a.getMass()*b.getMass();
		double xSquared = (a.posX-b.posX)*(a.posX-b.posX);
		double ySquared = (a.posY-b.posY)*(a.posY-b.posY);
		double distance = Math.abs(Math.sqrt(xSquared+ySquared));
		//TODO instead of returning a scalar return a vector
		float angle = 0;
		Vector result = new Vector((gravConst*totalMass)/(distance*distance), angle);
		return result;
	}
}