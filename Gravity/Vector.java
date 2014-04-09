package Gravity;

import java.lang.Math;

class Vector
{
	private double magnitude;
	//The X and Y component of this vector
	private double xComp, yComp;


	public Vector(double x, double y)
	{
		xComp = x;
		yComp = y;
	}
	/*
	Creates a component vector from the x and y distance as well as the force between these two body's
	TODO:  Create a unit test
	*/
	public Vector(int x, int y, double force)
	{
		if(x==0)
		{
			xComp = 0;
			yComp = force;
			return;
		}
		else if(y==0)
		{
			xComp = force;
			yComp = 0;
			return;
		}
		double angle = Math.atan(y/x);
		xComp = (int) ((Math.cos(angle)*force)+.5);
		yComp = (int) ((Math.sin(angle)*force)+.5);
		return;
	}

	public double getXComp()
	{
		return xComp;
	}

	public double getYComp()
	{
		return yComp;
	}

	/*
	Returns a Vector that is divided by the scalar passed in.
	*/
	public Vector divideBy(int scalar)
	{
		return new Vector(xComp/scalar, yComp/scalar);
	}

	/*
	Returns a Vector that is multiplied by the scalar passed in.
	*/
	public Vector multiplyBy(int scalar)
	{
		return new Vector(xComp*scalar, yComp*scalar);
	}
	/*
	Adds two vectors by adding their components, returns the sum vector.  Does not change the state of this vector.
	*/
	public Vector addition(Vector a)
	{
		double x = xComp + a.getXComp();
		double y = yComp + a.getYComp();
		return (new Vector(x,y));
	}
	public Vector getInverse()
	{
		return new Vector(-xComp,-yComp);
	}
	/**
	 * Returns a string representation of the vector
	 */
	public String toString()
	{
		String s = "Vector X: " + xComp + " Vector Y: " + yComp;
		return s;
	}
	/**
	 * Draws a vector to the screen based on its components, with its start point as input
	 */
	public void drawToScreen(int x, int y)
	{
		Display.getDisplay().drawArrow(x, y, (int) (xComp+.5 + x), (int) (yComp+.5 + y));
	}
}