package Gravity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpaceShip extends Body
{
	private static final double ROTCONSTANT = .025;
	private static final double SPEED_CONST = 2;
	private static final Vector rotationOffset = new Vector(0,-1);
	Vector direction;
	Color c;
	BufferedImage self;
	public SpaceShip(Vector l, Vector v, int m, Vector dim) 
	{
		super(l, v, m, dim.getYInt(), dim.getXInt());
		c = Color.BLUE;
		self = ImageLoader.imageLoad("assets/spaceship.png");
		if(self == null)
		{
			System.err.println("spaceship image didnt load");
		}
		direction = new Vector(0,-1);
	}

	@Override
	public void collisionOccurred() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void collisionDestroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * This function handles keyboard events when this object is being controlled by the user
	 */
	protected void eventOccurred(ControlEvent e) 
	{
		switch (e.getType())
		{
			case "UP":
				upButton();
				break;
			case "DOWN":
				downButton();
				break;
			case "LEFT":
				leftButton();
				break;
			case "RIGHT":
				rightButton();
				break;
		}
	}
	
	@Override
	public void draw()
	{
		Display.getDisplay().drawImage(getTopLeftVector(), self, c, direction.getAngle() - this.rotationOffset.getAngle());
		force = new Vector(0,0);
	}

	@Override
	/**
	 * Increments the spaceships velocity
	 */
	public void upButton() 
	{
		this.velocity = this.velocity.addition(direction.divideBy(this.SPEED_CONST));
	}
	
	@Override
	/**
	 * Decrements the Spaceships velocity
	 */
	public void downButton() 
	{
		this.velocity = this.velocity.subtract(direction.divideBy(this.SPEED_CONST));
	}
	
	@Override
	public void leftButton() 
	{
		direction = direction.rotate(-2*Math.PI*ROTCONSTANT);
		direction.normalize();
		System.out.println("LEFT angle: " + direction.getAngle());
	}

	@Override
	public void rightButton() 
	{
		direction = direction.rotate(2*Math.PI*ROTCONSTANT);
		direction.normalize();
		System.out.println("RIGHT angle: " + direction.getAngle());
	}
}
