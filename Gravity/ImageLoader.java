package Gravity;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

class ImageLoader 
{
	public static BufferedImage imageLoad(String url)
	{
		BufferedImage img = null;
		try 
		{
		    img = ImageIO.read(new File(url));
		    return img;
		} 
		catch (IOException e) 
		{
			System.err.println("ERROR:  Couldn't open the file " + url);
			return null;
		}
	}
}
