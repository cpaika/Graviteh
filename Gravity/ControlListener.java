package Gravity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlListener implements KeyListener
{
	Controllable thePlayer;
	public ControlListener(Controllable theP)
	{
		thePlayer = theP;
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_DOWN:
				thePlayer.downButton();
				break;
				
			case KeyEvent.VK_UP:
				thePlayer.upButton();
				break;
				
			case KeyEvent.VK_LEFT:
				thePlayer.leftButton();
				break;
			
			case KeyEvent.VK_RIGHT:
				thePlayer.rightButton();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		//unimplemented
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		//unimplemented
	}
}
