package Gravity;

public class Timer 
{
	public int fps;
	public long lastTime;
	
	/**
	 * Creates a new timer object
	 * @param fps is the frames per second you want the game capped at
	 */
	public Timer(int fps)
	{
		this.fps = fps;
		lastTime = System.currentTimeMillis();
	}
	/*
	 * Should be called every game loop cycle to cap the framerate to the previously set amount of frames per second.
	 */
	public void update()
	{
		long currentTime = System.currentTimeMillis();
		long difference = currentTime - lastTime;
		if(difference < (1000/fps))//if the game is running faster than the fps
		{
			long wait = (1000/fps) - difference;
			try
			{
				Thread.sleep(wait);
			}
			catch(InterruptedException e)
			{
				System.err.println("Error sleeping");
			}
		}
		lastTime = System.currentTimeMillis();
	}

}
