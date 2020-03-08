package edu.ycp.cs201.disks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DisksPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	
	private Timer timer;
	// TODO: add any other fields you need to represent the state of the game
	private int timeLeft = 150;
	
	private int xPos;
	private int yPos;
	private int width;
	
	private Disk[] disks;
	private int diskCount;
	private int fastTime = 1;
	private boolean diskPlaced = true;
	private boolean gameOn = true;
	
	
	//0-6
    private static final Color[] colors = { Color.RED, Color.GREEN, Color.BLUE, Color.WHITE, Color.BLACK, Color.ORANGE, Color.GRAY };//0-6
    private static final DiskColor[] color = { DiskColor.RED, DiskColor.GREEN, DiskColor.BLUE, DiskColor.WHITE, DiskColor.YELLOW, DiskColor.CYAN, DiskColor.MAGENTA};//0-6

    
	public DisksPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.GRAY);
		
		//Disk d = new Disk();
		
		disks = new Disk[500];
		diskCount = 0;
		
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				handleMouseClick(e);
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				handleMouseMove(e);
			}
		});
		
		// Schedule timer events to fire every 100 milliseconds (0.1 seconds)
		this.timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTimerEvent(e);
			}
		});
		
		timer.start();
	}

	// You can call this method to convert a DiskColor value into
	// a java.awt.Color object.
	//DiskColor.RED, DiskColor.GREEN, DiskColor.BLUE, DiskColor.WHITE, DiskColor.YELLOW, DiskColor.CYAN, DiskColor.MAGENTA
	public Color colorOf(DiskColor dc) {
		return new Color(dc.red(), dc.green(), dc.blue());
	}

	// This method is called whenever the mouse is moved
	protected void handleMouseMove(MouseEvent e) {
		xPos = e.getX();
		yPos = e.getY();
		
		//if a disk has been placed get a new circle size else keep the same size
		if(diskPlaced)
		{
			cirSize();
			//System.out.println(width);
		}
		else
		{
			repaint();
		}
	}
	
	// This method is called whenever the mouse is clicked
	protected void handleMouseClick(MouseEvent e) {
		double x, y;
		e.getButton();
		x = e.getX();
		y = e.getY();

		//FIND A RANDOM NUMBER FOR A COLOR
		Random rand = new Random();
		int number = rand.nextInt(color.length);
		
		diskPlaced = true; 
		
		//IF YOU LOST RESET THE GAME
		//I ADDED THE ABILITY FOR A GAME TO RESTART ON CLICK
		if(!gameOn)
		{
			resetGame();
		}
		
		//SET THE NEW DISK IN THE ARRAY
		disks[diskCount] = new Disk(x, y, width, color[number]);
		//System.out.println("------\nCount: " + diskCount + " (X,Y):" + disks[diskCount].getX() + "," + disks[diskCount].getY() + "Width: " + disks[diskCount].getRadius());
		
		//CHECK NEW DISKS AGAINST ALL DISKS IF THERE IS AN OVERLAP 
		for(int i = 0; i < diskCount; i++)
		{
			if((disks[diskCount].overlaps(disks[i])))
			{
				//System.out.println("overlap");
				gameOn = false;
				}
			}
		
		//IF IT IS OUT OF BOUNDS
		if(disks[diskCount].isOutOfBounds(WIDTH, HEIGHT))
		{
			//System.out.println("Bounds");
			gameOn = false;
		}

		//IF YOU HAVE NOT LOST YET
		if(gameOn)
		{
			diskCount++;
			if(diskCount % 5 == 0)
			{
				fastTime+=3;
			}
			timer.stop();
			timeLeft = 150 - fastTime;
			timer.start();
			repaint();
		}
		
		
	}
	
	// This method is called whenever a timer event fires
	protected void handleTimerEvent(ActionEvent e) {
		// TODO
		timeLeft -= fastTime;
		if(timeLeft <= 0)
		{
			gameOn = false;
		}
		//System.out.println(timeLeft);
		repaint();
	}
	
	//int r = (int) (Math.random() * (upper - lower)) + lower;
	public void cirSize()
	{
		//Random rand = new Random();
		int number = (int) (Math.random() * (88 - 20) + 20);
		width = number;
		diskPlaced = false;
	}
	
	private static final Font FONT = new Font("Dialog", Font.BOLD, 60);
	

	// This method is called automatically whenever the contents of
	// the window need to be redrawned.
	@Override
	public void paintComponent(Graphics g) {
		// Paint the window background
		super.paintComponent(g);
		FONT.isBold();
		g.setFont(FONT);
		
		//DRAW CIRCLE AROUND CURSOR 
		g.setColor(colors[1]);
		g.drawOval(xPos - (width/2), yPos - (width/2), width, width);
		

		//DRAW DISK
		for(int i = 0; i < diskCount; i++)
		{
			double r = disks[i].getRadius();
			int rad = (int) r;
			g.setColor(colorOf(disks[i].getColor()));
			int x = (int)disks[i].getX();
			int y = (int)disks[i].getY();
			//System.out.println("Count: " + i + " (X,Y):" + x + "," + y + "Width: " + width);
			g.fillOval(x -(rad/2), y- (rad/2), rad, rad);
		}
		
		//DISKS PLACED
		g.setColor(colors[4]);
		g.drawString(""+diskCount, 335, 275);
		
		//DRAW TIME BAR
		Color barColor = new Color(255, 23, 23, 63);
		g.setColor(barColor);
		g.fillRect(0, 280, timeLeft, 10);
		
		//IF YOU LOST;
		if(!gameOn)
		{
			g.setColor(colors[0]);
			g.drawString("GAME OVER", 12, 150);
		}
	}
	
	
	public void resetGame()
	{
		for(int i = 0; i < disks.length; i++)
		{
			disks[i] = new Disk(0, 0, 0, color[0]);
		}
		
		//RESET VALUES
		timeLeft = 150;
		diskCount = 0;
		fastTime = 1;
		gameOn = true;
	}
}











