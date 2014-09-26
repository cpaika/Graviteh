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
		Display d = Display.getDisplay();
		Vector location = d.fromScreen(e.getX(), e.getY());
		Planet temp = new Planet((int)Math.round(location.getXComp()), (int)Math.round(location.getYComp()), 1, 20, 20);
		if(Universe.getInstance().addBody(temp) == null)
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
