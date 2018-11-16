import java.awt.*;
import javax.swing.*;

public class FraktalView extends JPanel
{
	private int size,color,red,blue,green;
	private FraktalControl control;
	private double zoom=0.003;
	private double moveX,moveY;
	
	public void paintComponent(Graphics g)
	{
		//System.out.println(c.getMode());
		Complex z0;
		super.paintComponent(g);
		for(int x=0; x<size; x++)
		{
			for(int y=0; y<size; y++)
			{
				z0=new Complex(((x-(size/2))+moveX*0.003/zoom)*zoom,((y-(size/2))+moveY*0.003/zoom)*zoom);
				if(control.getMode().equals("julia"))
					color=FraktalControl.getIterations() - Formler.isEscape(control.getC(), z0, FraktalControl.getIterations());
				else
					color=FraktalControl.getIterations() - Formler.isEscape(z0, control.getC(), FraktalControl.getIterations());
				if(color==FraktalControl.getIterations() + 1)
					g.setColor(Color.black);
				else
				{
					//red=(color*(FraktalControl.getBlue()%FraktalControl.getIterations()));
					//green=(color*(FraktalControl.getGreen()%FraktalControl.getIterations()));
					//blue=(color*(FraktalControl.getRed()%FraktalControl.getIterations()));
					red=0;
					green=0;
					blue=0;
					if(0.70<((double)color)/((double)FraktalControl.getIterations()))
						blue=(255-(int)(((double)color)/((double)FraktalControl.getIterations())*255.0*0.5));
					else if(0.0<((double)color)/((double)FraktalControl.getIterations()))
						red=255-(int)(((double)color)/((double)FraktalControl.getIterations())*255.0/0.7*0.7);
					Color d=new Color(red, green, blue);
					g.setColor(d);
				}
				g.drawRect(x, y, 1, 1);
			}
		}


	}
	public double getZoom(){return zoom;}
	public void setZoom(double zoom){this.zoom = zoom;}
	public void setMoveX(double moveX){this.moveX = moveX;}
	public void setMoveY(double moveY){this.moveY = moveY;}
	public double getMoveY(){return moveY;}
	public double getMoveX(){return moveX;}
	public FraktalView(int size, FraktalControl control)
	{
		this.size=size;
		this.control=control;
		//Complex z0=new Complex(0, 0);
	}
}
