package Gravity;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameEngine extends JPanel
{
	private final static int WIDTH = 600;
	private final static int HEIGHT = 560;
	Skeleton sk;
	boolean playing = true;
	Universe theGame;
	Timer time;
	/*
	 * Private ensures that Engine will remain singleton
	 */
	private GameEngine()
	{
		theGame = Universe.getInstance();
		Display.initialize(WIDTH, HEIGHT);
		sk= new Skeleton(this, WIDTH, HEIGHT);
		time = new Timer(60);
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
		
	}

}
