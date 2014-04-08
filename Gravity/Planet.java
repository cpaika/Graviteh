package Gravity;

import java.lang.Math;
import java.awt.Color;

public class Planet extends Body
{
	Color c;
	public Planet(int x, int y, int m, Display d, int h, int w)
	{
		super(x,y,m,d,h,w);
		generateColor();
	}
	public void generateColor()
	{
		c = new Color((int)Math.random()*255,(int) Math.random()*255, (int) Math.random()*255);
	}
}
