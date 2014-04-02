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

	//TODO make a function that makes a vector from a magnitude and angle
	public Vector(double magnitude, double angle)
	{
		
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
	
}