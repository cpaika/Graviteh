package Gravity;

import java.awt.Color;
import java.awt.Graphics;

public class Display 
{
	int startX, startY;
	int endX, endY;
	Graphics g;
	
	/**
	 * Takes in the width and height of the screen
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
			//TODO: put in draw code
		}
	}
	/*
	 * Returns the finished BufferedImage for the current frame
	 */
	public void getBufferedImage(Graphics g)
	{
		//TODO: Implement
	}
}
