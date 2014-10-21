package Gravity;

public class ControlEvent 
{
	private String type;
	public ControlEvent(String type)
	{
		this.type = type;
	}
	
	/**
	 * Returns the String type of this control event.
	 * @return type of event
	 */
	public String getType()
	{
		return type;
	}

}
