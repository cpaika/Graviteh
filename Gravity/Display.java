package Gravity;

import java.awt.Color;
import java.awt.Graphics;

public class Display 
{
	private static Display window = null;
	private int startX, startY;
	private int endX, endY;
	Graphics g;
	
	/**
	 * Takes in the width and height of the screen, need to be called before any other function call
	 */
	private Display(int width, int height)
	{
		startX = startY = 0;
		endX = width;
		endY = height;
	}
	
	public void draw(Body x,int absX, int absY, int height, int width, Color color)//Fix sprite
	{
		if((((absX + width) > startX) && (absX < endX)) && (((absY + height) > startY) && (absY < endY)))//if the object about to be displayed is inside the screen
		{
			//TODO: Expand on draw code
			
		}
	}
	/*
	 * Returns the finished BufferedImage for the current frame
	 */
	public void getBufferedImage(Graphics g)
	{
		//TODO: Implement
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
			return new Display(w, h);
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
}
