package Gravity;

public class Display 
{
	int startX, startY;
	int endX, endY;
	
	/**
	 * Takes in the width and height of the screen
	 */
	private Display(int width, int height)
	{
		startX = startY = 0;
		endX = width;
		endY = height;
	}
	
	public void draw(Body x,int absX, int absY)//Fix sprite
	{
		//TODO: Rework this math to make sure that it includes all of the image
		if(((absX > startX) && (absX < endX)) || ((absY > startY) && (absY < endY)))//if the object about to be displayed is inside the screen
		{
			
		}
	}
}
