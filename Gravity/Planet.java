package Gravity;

import java.lang.Math;
import java.util.ArrayList;
import java.awt.Color;

public class Planet extends Body
{
	private final static double ATOMIC_SIZE = 1;
	private final static int AMOUNT_SPLIT = 3;
	Color c;
	public Planet(int x, int y, int m, int h, int w)
	{
		super(x,y,m,h,w);
		generateColor();
	}
	public Planet(int x, int y, Vector v, int m, int h, int w)
	{
		super(x,y,v,m,h,w);
		generateColor();
	}
	public Planet(Vector l, Vector v, int m, int h, int w)
	{
		super(l,v,m,h,w);
		generateColor();
	}
	public void generateColor()
	{
		c = new Color((int)(Math.random()*255),(int) (Math.random()*255), (int) (Math.random()*255));
	}
	public void draw()
	{
		//TODO: posX is the center not the top left
		Display.getDisplay().draw(getPosX(), getPosY(), width, height, c);
		//velocity.drawToScreen(getCenterX(), getCenterY());
		force = new Vector(0,0);
	}
	/**
	 * Called when the Planet collides with another Body.  Planet is responsible for its collision behavior.
	 */
	public void collisionOccurred()
	{
		if(!this.isDestroyable)//if planet cannot be destroyed
		{
			return;
		}
		else
		{
			if(this.mass <= AMOUNT_SPLIT*ATOMIC_SIZE)//planet can't be split anymore
			{
				this.collisionDestroy();
				//destroy this planet
				//todo add in an animation
			}
			else//split the planet into AMOUNT_SPLIT units
			{
				int newMass = (int) Math.ceil(this.mass/AMOUNT_SPLIT);
				ArrayList<Planet> planets = new ArrayList<Planet>();
				double angleStep = 90/(AMOUNT_SPLIT-1);
				double currentAngle = 45;
				for(int i = 0; i < AMOUNT_SPLIT; i++)
				{
					Vector newVelocity = this.getVelocity();//TODO this is the hard part
					newVelocity = newVelocity.rotate(currentAngle);
					Planet p = new Planet(centerLocation, newVelocity, newMass, 10, 10);
					p.setSafeTime(30);
					Universe.getInstance().addBody(p);//TODO fix how height and width works
					//TODO make the add planet function in universe better
					currentAngle = currentAngle - angleStep;//TODO
				}
				this.collisionDestroy();
			}
		}
	}

	/**
	 * Function called when the Planet has collided with another Body and needs to be destroyed.  Implements Body abstract function.
	 */
	protected void collisionDestroy()
	{
		Body ejected = Universe.getInstance().remove(this);
		if(ejected == null)
		{
			System.out.println("ERROR removing this planet from the array");
		}
		return;
		
	}
	/**
	 * @Override
	 * This take a ControlEvent and moves the planet accordingly
	 * @param the ControlEvent that occurred
	 */
	protected void eventOccurred(ControlEvent e)
	{
		switch (e.getType())
		{
			case "UP":
				velocity = velocity.addition(velocity.normalize());
			case "DOWN":
				velocity = velocity.subtract(velocity.normalize());
			case "LEFT":
				velocity = velocity.addition(velocity.normalize().rotate(90));
			case "RIGHT":
				velocity = velocity.addition(velocity.normalize().rotate(270));
		}
	}
	
	/**
	 * This functions are unimplemented currently 
	 */
	@Override
	public void upButton() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void leftButton() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rightButton() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void downButton() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void spaceButton() {
		// TODO Auto-generated method stub
		
	}
}
