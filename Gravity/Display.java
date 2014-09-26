package Gravity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Display 
{
	private static Display window = null;
	private int startX, startY;
	private int endX, endY;
	private Graphics g;
	private Image offscreen;
	private int width, height;
	private double scale;
	
	/**
	 * Takes in the width and height of the screen, need to be called before any other function call
	 */
	private Display(int width, int height)
	{
		scale = 1;
		startX = startY = 0;
		endX = width;
		endY = height;
		this.width = width;
		this.height = height;
	}
	
	//TODO: implement support for moving the screen
	public void draw(int absX, int absY, int width, int height, Color color)//Fix sprite
	{
		if(((((absX/scale) + (width/scale)) > (startX/scale)) && ((absX/scale) < endX*scale)) && ((((absY/scale) + (height/scale)) > (startY/scale)) && ((absY/scale) < (endY*scale))))//if the object about to be displayed is inside the screen
		{
			g.setColor(color);
			g.fillOval((int)Math.round(absX/scale), (int)Math.round(absY/scale), (int)Math.round(width/scale), (int)Math.round(height/scale));
		}
	}
	/*
	 * Returns the finished BufferedImage for the current frame
	 */
	private void getBufferedImage(Graphics g)
	{
		//TODO: Implement/ Do we even need this
	}
	
	/*
	 * Returns a Display with specified width and height.  If already initialized returns the current Display
	 */
	public static Display initialize(int w, int h)
	{
		if(window != null)
		{
			return window;
		}
		else
		{
			window = new Display(w, h);
			return window;
		}
	}
	/**
	 * Returns a reference to the display object to work with
	 * @return
	 */
	public static Display getDisplay()
	{
		return window;
	}
	private void clearScreen()
	{
		if(g != null)
		{
			g.setColor(Color.black);
			g.clearRect(0, 0, endX, endY);
		}
	}
	public void prime(Graphics graphics)
	{
		g = graphics;
		clearScreen();
	}
	public void stopDrawing()
	{
		g = null;//making sure no errors arise from using g from now on
	}
	
	public void drawArrow(int originX, int originY, int destX, int destY)
	{
		g.drawLine(originX, originY, destX, destY);
	}
	/**
	 * Zooms the screen in for positive numbers and out for negatives
	 * @param scrollAmount
	 */
	public void scroll(int scrollAmount)
	{
		if(scrollAmount > 0)//zoom in
		{
			scale = scale/1.1;
		}
		else if(scrollAmount < 0)//zoom out
		{
			scale = scale*1.1;
		}
	}
	
	public Vector fromScreen(int x, int y)
	{
		int xLoc, yLoc = 0;
		xLoc = (int) Math.round(x*scale - width);
		yLoc = (int) Math.round(y*scale - height);
		return new Vector(xLoc,yLoc);
	}
}
