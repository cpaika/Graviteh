package Gravity;

import java.lang.Math;
import java.awt.Color;

public class Planet extends Body
{
	Color c;
	public Planet(int x, int y, int m, int h, int w)
	{
		super(x,y,m,h,w);
		generateColor();
	}
	public void generateColor()
	{
		c = new Color((int)(Math.random()*255),(int) (Math.random()*255), (int) (Math.random()*255));
	}
	public void draw()
	{
		//TODO: posX is the center not the top left
		Display.getDisplay().draw(posX, posY, width, height, c);
		//velocity.drawToScreen(getCenterX(), getCenterY());
		force = new Vector(0,0);
	}
}
