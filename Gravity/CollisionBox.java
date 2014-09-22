package Gravity;

public class CollisionBox 
{
	private int radius;
	private Vector location;
	public CollisionBox(int r)
	{
		radius = r;
		location = new Vector(0,0);
	}
	public void update(Vector l)
	{
		location = l;
	}
	public boolean checkCollision(CollisionBox other)
	{
		Vector diff = location.subtract(other.getLocation());
		if(diff.getMagnitude() <= (radius + other.getRadius()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int getRadius()
	{
		return radius;
	}
	public Vector getLocation()
	{
		return location;
	}
}
