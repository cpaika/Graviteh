package Gravity;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class UserMouse implements MouseListener, MouseWheelListener
{
	boolean currentlyDragging;
	public UserMouse()
	{
		currentlyDragging = false;
	}
	
	@Override
	/**
	 * Creates a stationary planet when the mouse is clicked
	 */
	public void mouseClicked(MouseEvent e) 
	{
		//Makes a standard planet where the user clicked
		Planet temp = new Planet(e.getX(), e.getY(), 100, 20, 20);
		if(!Universe.getInstance().addBody(temp))
		{
			System.out.println("Problem adding a planet.");
		}
		else
		{
			System.out.println("Planet successfully added");
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

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		Display.getDisplay().scroll(e.getWheelRotation());
	}

}
