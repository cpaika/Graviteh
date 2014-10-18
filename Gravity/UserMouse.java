package Gravity;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class UserMouse implements MouseListener, MouseWheelListener, MouseMotionListener
{
	Vector dragOrigin;
	public UserMouse()
	{
		dragOrigin = null;
	}
	
	@Override
	/**
	 * Creates a stationary planet when the mouse is clicked
	 */
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getButton() == MouseEvent.BUTTON3)//if right mouse button clicked
		{
			Display d = Display.getDisplay();
			Vector location = d.getAbsoluteVector(new Vector(e.getX(), e.getY()));
			Planet temp = new Planet((int)(location.getXComp() + .5), (int)(location.getYComp() + .5),200, 20, 20);
			temp.fixIntoOrbit(Universe.getInstance().getCenterBody());
			if(Universe.getInstance().addBody(temp) == null)
			{
				System.out.println("Problem adding a planet.");
			}
			else
			{
				System.out.println("Planet successfully added");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * If the right mouse button is pressed it records the coordinates to be used to tell how much the screen has been dragged if the user drags the mouse
	 */
	public void mousePressed(MouseEvent e) 
	{
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			dragOrigin = new Vector(e.getX(), e.getY());
		}
	}

	/**
	 * Resets the point at which the user clicked to null if Button1 is released
	 */
	public void mouseReleased(MouseEvent e) 
	{
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			dragOrigin = null;
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		Display.getDisplay().scroll(e.getWheelRotation());
	}

	/**
	 * This function measures how much the mouse has dragged the display and notifies the Display object about the drag so it can properly move the screen
	 */
	public void mouseDragged(MouseEvent e) 
	{
		if(dragOrigin != null) //If the user is dragging Button1
		{
			Vector current = new Vector(e.getX(), e.getY());
			Display d = Display.getDisplay();
			d.pan((current.subtract(dragOrigin)).getInverse());//gives it the inverse as thats how users excpect panning to work
			dragOrigin = current;//this ensures that the next pan command only sends as much difference
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
