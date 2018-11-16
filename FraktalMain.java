import java.awt.*;

import javax.swing.*;


public class FraktalMain
{
	public static void main(String[] args)
	{
		int size=800;
		try
		{
			size=Integer.parseInt(args[0]);
		}
		catch(Exception e)
		{
			System.out.println("Du kan ange storlek som argument om du vill.");
		}
		JFrame rutan= new JFrame("Fraktal");
		new FraktalControl(size, rutan);
	}
}
