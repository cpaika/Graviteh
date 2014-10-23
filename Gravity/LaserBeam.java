package Gravity;

import java.awt.Color;

public class LaserBeam 
{
	private static final Color c = Color.RED;
	Vector location, velocity;
	public LaserBeam(Vector loc, Vector vel)
	{
		location = loc;
		velocity = vel;
	}
	
	public void update()
	{
		location = location.addition(velocity);
	}
	
	public void draw()
	{
		Display.getDisplay().drawLaser(location, velocity.normalize(), c);
	}

}
