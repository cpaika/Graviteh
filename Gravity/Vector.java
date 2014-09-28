package Gravity;

import java.lang.Math;

class Vector
{
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
	
	/**
	 * Returns the angle between this vector and the unit vector (1,0)
	 * @return An angle in radians
	 */
	public double getAngle()
	{
		double offset = 0;
		if(this.getXComp() < 0 && this.getYComp() > 0)
		{
			offset = Math.PI/2;
		}
		if(this.getXComp() < 0 && this.getYComp() < 0)
		{
			offset = Math.PI;
		}
		if(this.getXComp() > 0 && this.getYComp() < 0)
		{
			offset = Math.PI*1.5;
		}
		double angle = Math.asin(this.getYComp());
		return (angle + offset);
	}

	/*
	Returns a Vector that is divided by the scalar passed in.
	*/
	private Vector divideBy(int scalar)
	{
		if(scalar == 0)
		{
			//TODO throw error here
			return null;
		}
		else
		{
			return new Vector(xComp/scalar, yComp/scalar);
		}
	}
	
	/**
	 * Returns a Vector object that is this current Vector rotated some degrees counter clockwise
	 * @param angle  The angle of rotation, represented in radians
	 * @return The rotated Vector
	 */
	public Vector rotate(double angle)
	{
		Vector working = this.normalize();
		double currentAngle = working.getAngle();
		angle += currentAngle;
		working = new Vector(Math.cos(angle), Math.sin(angle));
		working = working.multiplyBy(this.getMagnitude());
		return working;
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
	
	/**
	 * Addition function used for debugging purposes
	 * @param a
	 * @return
	 */
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
	 * 
	 * @param a
	 * @return Returns a new Vector which is the current vector with the input subtracted from it
	 */
	public Vector subtract(Vector a)
	{
		return new Vector(xComp-a.getXComp(), yComp - a.getYComp());
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
	/**
	 * Returns an exact copy of this vector
	 * @return Exact copy of the vector it is called on
	 */
	public Vector copy()
	{
		return new Vector(this.xComp, this.yComp);
	}
}