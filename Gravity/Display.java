package Gravity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Display 
{
	private static final double SCALE_FACTOR = 3;
	private static final int ZOOM_SPEED = 30;
	private static final int PANNING_SPEED = 60;
	private static Display window = null;
	private Vector origin;
	private Vector end;
	private Vector screenDimensions;
	private Graphics g;
	private Image offscreen;
	private double scale;
	private boolean changingScale;
	private double targetScale;
	private boolean changingOrigin;
	private Vector targetOrigin;
	private Vector startOrigin;
	private int changingCount;
	private String output;
	
	/**
	 * Takes in the width and height of the screen, need to be called before any other function call
	 */
	private Display(int width, int height)
	{
		targetScale = scale = 1;
		origin = new Vector(0,0);
		end = new Vector(width,height);
		screenDimensions = new Vector(width, height);
		changingScale = false;
		changingOrigin = false;
		targetOrigin = startOrigin = null;
		output = null;
	}
	
	//TODO: implement support for moving the screen
	public void draw(int absX, int absY, int width, int height, Color color)//Fix sprite
	{
		if(this.withinScreen(absX, absY, height, width))
		{	
			Vector sp = this.getScreenVector(new Vector(absX, absY));
			g.setColor(color);
			int startX = (int)(sp.getXComp() + .5);
			int startY = (int)(sp.getYComp() + .5);
			g.fillOval(startX, startY, (int)Math.round(width/scale), (int)Math.round(height/scale));
		}
	}
	
	public void drawImage(Vector location, BufferedImage img, Color c, double direction)
	{
		if(this.withinScreen(location.getXInt(), location.getYInt(), img.getHeight(), img.getWidth()))
		{
			location = this.getScreenVector(location);
			AffineTransform at = new AffineTransform();
			at.rotate(direction, img.getWidth()/2, img.getHeight()/2);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			img = op.filter(img,  null);
			//g.drawImage(img, location.getXInt(), location.getYInt(), null);
			g.drawImage(img, location.getXInt(), location.getYInt(), (int) (img.getWidth()/scale + .5), (int)(img.getHeight()/scale + .5), null);
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
			g.fillRect(0, 0, screenDimensions.getXInt(), screenDimensions.getYInt());
		}
	}
	public void prime(Graphics graphics)
	{
		if(changingScale)//if the screen is currently zooming
		{
			scale = scale + (targetScale - scale)/(ZOOM_SPEED);
			if(Math.abs(targetScale - scale) < .01)
			{
				changingScale = false;
				scale = targetScale;
			}
		}
		if(changingOrigin)
		{
			this.changingCount--;
			Vector increment = targetOrigin.subtract(startOrigin);
			increment = increment.divideBy(PANNING_SPEED);
			this.origin = origin.addition(increment);
			if(changingCount <= 0)
			{
				//origin = targetOrigin;
				changingOrigin = false;
				changingCount = 0;
				targetOrigin = startOrigin = null;
			}
		}
		g = graphics;
		clearScreen();
		output = "";
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
			zoomToScale(scale/SCALE_FACTOR);
		}
		else if(scrollAmount < 0)//zoom out
		{
			zoomToScale(scale*SCALE_FACTOR);
		}
	}
	
	public Vector fromScreen(int x, int y)
	{
		int xLoc, yLoc;
		xLoc = (int) Math.round(x*scale);
		yLoc = (int) Math.round(y*scale);
		return new Vector(xLoc,yLoc);
		//return new Vector(x,y);
	}
	
	/**
	 * This function zooms the screen smoothly to the new scaling factor
	 * @param newScale The scaling factor desired
	 */
	
	public void zoomToScale(double newScale)
	{
		changingScale = true;
		targetScale = newScale;
	}
	
	/**
	 * This function takes in a distance vector and pans the screen equivalent to the direction and distance of the vector given
	 * @param distance The vector to pan the screen
	 */
	public void pan(Vector distance)
	{
		if(changingOrigin)
		{
			targetOrigin = targetOrigin.addition(distance);
			changingCount = this.PANNING_SPEED;
		}
		else
		{
			changingCount = this.PANNING_SPEED;
			changingOrigin = true;
			startOrigin = this.origin;
			targetOrigin = origin.addition(distance);
		}
	}
	
	/**
	 * Checks if the passed in bounds are within the current screen
	 * @param absX
	 * @param absY
	 * @param height
	 * @param width
	 * @return
	 */
	private boolean withinScreen(int absX, int absY, int height, int width)
	{
		Vector sp = getScreenVector(new Vector(absX, absY));
		double newWidth = (width/scale);
		double newHeight = (height/scale);
	
		if(sp.getXComp() + newWidth < 0)
		{
			return false;
		}
		if(sp.getXComp() > screenDimensions.getXComp())
		{
			return false;
		}
		if(sp.getYComp() + newHeight < 0)
		{
			return false;
		}
		if(sp.getYComp() > screenDimensions.getYComp())
		{
			return false;
		}
		return true;
	}
	/**
	 * Takes an absolute position vector and returns the vector converted in respect to the screen
	 * @return Vector with the objects coordinates on the screen
	 */
	public Vector getScreenVector(Vector v)
	{
		double newX = ((v.getXComp() - origin.getXComp())/scale);
		double newY = ((v.getYComp() - origin.getYComp())/scale);
		return new Vector(newX, newY);
	}
	
	public Vector getAbsoluteVector(Vector sp)
	{
		sp = sp.multiplyBy(scale);
		sp = sp.addition(origin);
		return sp;
	}
	
	/**
	 * This function takes a string and draws it in the upper left corner of the screen, as well as properly formatting it to some degree
	 * @param draw The text being drawn to the screen
	 */
	public void drawText(String draw)
	{
		if(output == null)
		{
			output = "";
		}
		Color temp = g.getColor();
		g.setColor(Color.WHITE);
		output = output + draw ;
		g.drawString(output, 10, 25);
		g.setColor(temp);
	}
	/**
	 * Zooms/pans the screen to the new location
	 * @param scrollAmount mousewheel movement
	 * @param newCenter new center of the screen to pan to
	 */
	public void scrollPan(int scrollAmount, Vector newCenter)
	{
		newCenter = getAbsoluteVector(newCenter);
		Vector distance = newCenter.subtract((origin.addition(screenDimensions.divideBy(2))));
		pan(distance);
		scroll(scrollAmount);
	}
	/**
	 * Draws a laser beam to the screen
	 * @param location location of the laser beam
	 * @param normalize Direction the beam is pointed
	 */
	public void drawLaser(Vector location, Vector direction, Color c) 
	{
		location = this.getScreenVector(location);
		g.setColor(c);
		g.drawRect(location.getXInt(), location.getYInt(), (int)(5/scale), (int)(5/scale));
	}
}
