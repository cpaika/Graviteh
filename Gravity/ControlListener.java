package Gravity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlListener implements KeyListener
{
	@Override
	public void keyPressed(KeyEvent e) 
	{
		Universe main = Universe.getInstance();
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_DOWN:
				main.sendEvent(new ControlEvent("DOWN"));
				break;
				
			case KeyEvent.VK_UP:
				main.sendEvent(new ControlEvent("UP"));
				break;
				
			case KeyEvent.VK_LEFT:
				main.sendEvent(new ControlEvent("LEFT"));
				break;
			
			case KeyEvent.VK_RIGHT:
				main.sendEvent(new ControlEvent("RIGHT"));
				break;
			case KeyEvent.VK_SPACE:
				main.sendEvent(new ControlEvent("SPACE"));
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
