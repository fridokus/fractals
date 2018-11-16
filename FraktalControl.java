import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class FraktalControl implements ActionListener
{
	private FraktalView fraktal;
	private static int iterations,blue,red,green;
	private String mode="julia";
	Complex c;
	Double cim;
	Double cre;
	public FraktalControl(int size,JFrame rutan)
	{
		iterations=30;
		red=(int)(Math.random()*256);
		green=(int)(Math.random()*256);
		blue=(int)(Math.random()*256);
		
		rutan.setSize(size+200, size);
		rutan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c=new Complex(-1,0); // c, som ändrar hur den ser ut -1 0
		cre=-1.0;
		cim=0.0;
		
		FraktalView fraktal=new FraktalView(size, this);
		rutan.add(fraktal);
		fraktal.setPreferredSize(new Dimension(size,size));
		// 
		JPanel rightPanel=new JPanel(new GridLayout(13,1));
		rutan.add(rightPanel, BorderLayout.EAST);
		
		// repaint
		JButton repaint=new JButton("repaint");
		rightPanel.add(repaint);
		repaint.setActionCommand("repaint");
		repaint.addActionListener(this);
		
		
		// Zoom-knappar
		JButton zoomIn=new JButton("Zoom in");
		rightPanel.add(zoomIn);
		zoomIn.setActionCommand("zoomIn");
		zoomIn.addActionListener(this);
		
		JButton zoomOut=new JButton("Zoom out");
		zoomOut.setActionCommand("zoomOut");
		rightPanel.add(zoomOut);
		zoomOut.addActionListener(this);
		
		// Set colors
		
		JButton setRed=new JButton("Set red");
		rightPanel.add(setRed);
		setRed.setActionCommand("setRed");
		setRed.addActionListener(this);
		
		JButton setGreen=new JButton("Set green");
		rightPanel.add(setGreen);
		setGreen.setActionCommand("setGreen");
		setGreen.addActionListener(this);
		
		JButton setBlue=new JButton("Set blue");
		rightPanel.add(setBlue);
		setBlue.setActionCommand("setBlue");
		setBlue.addActionListener(this);
		
		// Set iterations
		JButton setIterations=new JButton("Set iterations");
		rightPanel.add(setIterations);
		setIterations.setActionCommand("setIterations");
		setIterations.addActionListener(this);
		
		// Move-knappar
		JButton moveRight=new JButton("→");
		JButton moveUp=new JButton("^");
		JButton moveDown=new JButton("↓");
		JButton moveLeft=new JButton("←");
		
		moveDown.setActionCommand("moveDown");
		moveLeft.setActionCommand("moveLeft");
		moveRight.setActionCommand("moveRight");
		moveUp.setActionCommand("moveUp");
		
		moveLeft.addActionListener(this);
		moveRight.addActionListener(this);
		moveUp.addActionListener(this);
		moveDown.addActionListener(this);
		
		rightPanel.add(moveRight);
		rightPanel.add(moveLeft);
		rightPanel.add(moveUp);
		rightPanel.add(moveDown);
		
		// change complex constant
		JButton setC=new JButton("Set constant");
		rightPanel.add(setC);
		setC.setActionCommand("setC");
		setC.addActionListener(this);
		
		// set mode
		JButton setMode=new JButton("Set mode");
		rightPanel.add(setMode);
		setMode.setActionCommand("setMode");
		setMode.addActionListener(this);
		
		rutan.pack();
		rutan.setVisible(true);
		
		
		this.fraktal=fraktal;
	}
	public static int getIterations(){return iterations;}
	public static int getRed(){return red;}
	public static int getGreen(){return green;}
	public static int getBlue(){return blue;}
	public Complex getC(){return c;}
	public String getMode(){return mode;}
	public void setIterations(int iterations){FraktalControl.iterations=iterations;}

	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();
		if(e.getActionCommand()=="setMode")
		{
			String[] options={"julia", "mandelbrot"};
			String choice=(String)JOptionPane.showInputDialog(null,"Välj","Välj ett läge",JOptionPane.QUESTION_MESSAGE,null, options,"julia");
			if(choice!=null)
				mode=choice;
		}
		if(e.getActionCommand()=="setC")
		{
			boolean correct=false;
			// double cre=-1;
			// double cim=0;
			String input = null;
			while(!correct) // Medan programmet inte fått ett korrekt input. 
			{
				try
				{
					input= JOptionPane.showInputDialog(null,"Enter the real value of the constant, which right now is " + cre,"Enter constant", JOptionPane.QUESTION_MESSAGE);
					cre=Double.parseDouble(input);
					correct=true;
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,"Skriv ett reellt tal","Invalid input",JOptionPane.ERROR_MESSAGE);
				}
			}
			while(correct) // Medan programmet inte fått ett korrekt input. 
			{
				try
				{
					input= JOptionPane.showInputDialog(null,"Enter the imaginary value of the constant, which right now is " + cim,"Enter constant", JOptionPane.QUESTION_MESSAGE);
					cim=Double.parseDouble(input);
					correct=false;
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,"Skriv ett reellt tal","Invalid input",JOptionPane.ERROR_MESSAGE);
				}
			}
			c=new Complex(cre,cim);
			
		}
		if(e.getActionCommand()=="repaint")
		{
			fraktal.repaint();
			
		}
		if(e.getActionCommand()=="setMode")
		{
			
			
		}
		if(e.getActionCommand()=="zoomOut")
		{
			fraktal.setZoom(fraktal.getZoom()*2);
		}
		if(e.getActionCommand()=="zoomIn")
		{
			fraktal.setZoom(fraktal.getZoom()*0.5);
		}
		
		
		if(e.getActionCommand()=="moveRight")
		{
			fraktal.setMoveX(fraktal.getMoveX()+130*fraktal.getZoom()/0.003);
		}
		if(e.getActionCommand()=="moveLeft")
		{
			fraktal.setMoveX(fraktal.getMoveX()-130*fraktal.getZoom()/0.003);
		}
		if(e.getActionCommand()=="moveUp")
		{
			fraktal.setMoveY(fraktal.getMoveY()-130*fraktal.getZoom()/0.003);
		}
		if(e.getActionCommand()=="moveDown")
		{
			fraktal.setMoveY(fraktal.getMoveY()+130*fraktal.getZoom()/0.003);
		}
		if(e.getActionCommand()=="setBlue"||e.getActionCommand()=="setGreen"||e.getActionCommand()=="setRed")
		{
			boolean correct=false;
			String input = null;
			while(!correct) // Medan programmet inte fått ett korrekt input. 
			{
				try
				{
					if(e.getActionCommand()=="setBlue")
					{
					input= JOptionPane.showInputDialog(null,"Sätt blå (0-255), just nu " + blue,"Enter blue", JOptionPane.QUESTION_MESSAGE);
					blue=Integer.parseInt(input);
					}
					if(e.getActionCommand()=="setGreen")
					{
					input= JOptionPane.showInputDialog(null,"Sätt grön (0-255), just nu " + green,"Enter green", JOptionPane.QUESTION_MESSAGE);
					green=Integer.parseInt(input);
					}
					if(e.getActionCommand()=="setRed")
					{
					input= JOptionPane.showInputDialog(null,"Sätt röd (0-255), just nu " + red,"Enter red", JOptionPane.QUESTION_MESSAGE);
					red=Integer.parseInt(input);
					}
					correct=true;
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,"Skriv ett heltal","Invalid input",JOptionPane.ERROR_MESSAGE);
				}
			} 
			if(blue<0)
				blue=0;
			if(blue>255) // får inte vara för exakt
				blue=255;
			if(red<0)
				red=0;
			if(red>255) // får inte vara för exakt
				red=255;
			if(green<0)
				green=0;
			if(green>255) // får inte vara för exakt
				green=255;
		}
		if(e.getActionCommand()=="setIterations")
		{
			boolean correct=false;
			String input = null;
			while(!correct) // Medan programmet inte fått ett korrekt input. 
			{
				try
				{
					input= JOptionPane.showInputDialog(null,"Enter number of iterations, which right now is set to " + iterations,"Enter speed", JOptionPane.QUESTION_MESSAGE);
					iterations=Integer.parseInt(input);
					correct=true;
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,"Skriv ett heltal","Invalid input",JOptionPane.ERROR_MESSAGE);
				}
			} 
			if(iterations<1)
				iterations=1;
			if(iterations>500) // får inte vara för exakt
				iterations=500;
		}

	}

}
