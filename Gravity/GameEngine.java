package Gravity;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameEngine extends JPanel
{
	private final static int FRAMERATE = 60;
	private final static int WIDTH = 1000;
	private final static int HEIGHT = 1000;
	Skeleton sk;
	boolean playing = true;
	Universe theGame;
	Timer time;
	Display disp;
	ControlListener control;
	UserMouse mouseControl;
	
	/*
	 * Private ensures that Engine will remain singleton
	 */
	private GameEngine()
	{
		disp = Display.initialize(WIDTH, HEIGHT);
		theGame = Universe.getInstance();
		sk= new Skeleton(this, WIDTH, HEIGHT);
		time = new Timer(FRAMERATE);
		control = new ControlListener(theGame.getPlayer());
		mouseControl = new UserMouse();
		sk.addMouseListener(mouseControl);
		sk.addMouseWheelListener(mouseControl);
		sk.addMouseMotionListener(mouseControl);
		sk.addKeyListener(control);
		setDoubleBuffered(true);
	}
	public static void main(String args[])
	{
		GameEngine current = new GameEngine();
		current.go();
	}
	public void go()
	{
		while(playing)
		{
			time.update();
			theGame.update();
			repaint();
		}
	}
	public void paintComponent(Graphics g)
	{
		disp.prime(g);
		theGame.draw();
	}

}
