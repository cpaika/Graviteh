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
		System.out.println("Entering collision occurred function");
		if(!this.isDestroyable)//if planet cannot be destroyed
		{
			System.out.println("Planet is not destroyable yet");
			return;
		}
		else
		{
			if(mass <= AMOUNT_SPLIT*ATOMIC_SIZE)//planet can't be split anymore
			{
				System.out.println("Planet needs to be destroyed");
				this.collisionDestroy();
				//destroy this planet
				//todo add in an animation
			}
			else//split the planet into AMOUNT_SPLIT units
			{
				System.out.println("SPLITTING PLANET");
				int newMass = (int) Math.ceil(this.mass/AMOUNT_SPLIT);
				ArrayList<Planet> planets = new ArrayList<Planet>();
				double angleStep = 90/AMOUNT_SPLIT;
				double currentAngle = 45;
				double mag = this.velocity.getMagnitude();
				for(int i = 0; i < AMOUNT_SPLIT; i++)
				{
					System.out.println("Creating a planet");
					Vector newVelocity = this.getVelocity();//TODO this is the hard part
					Planet p = new Planet(centerLocation, newVelocity, newMass, 10, 10);
					p.setSafeTime(360);
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
}
