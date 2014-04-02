package Gravity;

public class GameEngine 
{
	
	boolean playing = true;
	Universe theGame;
	Timer time;
	/*
	 * Private ensures that Engine will remain singleton
	 */
	private GameEngine()
	{
		theGame = Universe.getInstance();
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
		}
	}

}
