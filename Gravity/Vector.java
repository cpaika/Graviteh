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
	
	/**
	 * 
	 * @param a
	 * @return Return's a normalized version of Vector a
	 */
	public Vector normalize()
	{
		return this.divideBy(Math.sqrt(xComp*xComp + yComp*yComp));
	}
	
	public double getMagnitude()
	{
		return Math.sqrt((xComp*xComp) + (yComp*yComp));
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
	
	public Vector divideBy(double scalar)
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
	
	public Vector multiplyBy(double scalar)
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
	public Vector specialAddition(Vector a)
	{
		double x = xComp + a.getXComp();
		double y = yComp + a.getYComp();
		System.out.println("Old Vector x: " + this.getXComp() + " and y: " + this.getYComp());
		System.out.println("Addition vector x:" + a.getXComp() + " and y: " + a.getYComp());
		System.out.println("New vector x:" + x + " and y: " + y);
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
		Display.getDisplay().drawArrow(x, y, (int) (xComp*10+.5 + x), (int) (yComp*10+.5 + y));
	}
}