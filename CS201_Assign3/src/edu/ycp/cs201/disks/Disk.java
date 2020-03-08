package edu.ycp.cs201.disks;

/**
 * An instance of the Disk class represents a disk
 * to be placed on the game board.
 */
public class Disk {
	// TODO: add fields.
	// You need to store information about the
	// Disk's x and y coordinate, its radius,
	// and its color.

	private double xCord;
	private double yCord;
	private double rad;
	private DiskColor diskCol;

	
	/**
	 * Constructor.
	 * Store x, y, radius, and Color values in the fields
	 * of the object being initialized.
	 * 
	 * @param x the x coordinate of the new Disk
	 * @param y the y coordinate of the new Disk
	 * @param radius the radius of the new Disk
	 * @param color the color of the new Disk
	 */
	public Disk(double x, double y, double radius, DiskColor color) {
		//throw new UnsupportedOperationException("TODO - implement");
		xCord = x;
		yCord = y;
		rad = radius;
		diskCol = color;
		
	}
	
	/**
	 * @return the Disk's x coordinate
	 */
	public double getX() {
		//throw new UnsupportedOperationException("TODO - implement");
		return xCord;
	}
	
	/**
	 * @return the Disk's y coordinate
	 */
	public double getY() {
		//throw new UnsupportedOperationException("TODO - implement");
		return yCord;
	}
	
	/**
	 * @return the Disk's radius
	 */
	public double getRadius() {
		//throw new UnsupportedOperationException("TODO - implement");
		return rad;
	}
	
	/**
	 * @return the Disk's color
	 */
	public DiskColor getColor() {
		//throw new UnsupportedOperationException("TODO - implement");
		return diskCol;
	}
	
	/**
	 * Return true if this Disk overlaps
	 * the Disk given as the parameter, false otherwise.
	 * 
	 * @param other another Disk
	 * @return true if the two Disks overlap, false if they don't
	 */
	public boolean overlaps(Disk other) {
		//throw new UnsupportedOperationException("TODO - implement");
		
		//double location = (Math.sqrt((x2 - x1)*(x2 - x1)) + ((y2 - y1)*(y2 - y1)));
		//location is the distance between 2 points 
		
		double disBetween = Math.sqrt(
				((other.getX() - getX())*(other.getX() - getX())) + 
				((other.getY() - getY())*(other.getY() - getY())));
		
		//double Xs = Math.pow(((other.getX()-other.getRadius())-(getX()-getRadius())), 2.0);
		//double Ys = Math.pow(((other.getY()-other.getRadius())-(getY()-getRadius())), 2.0);
		//double disBetween = (Math.sqrt(Xs+Ys));
		//double location = (Math.sqrt(((other.getX() + (other.getRadius()*2))- (getX()+ getRadius()*2)))*((other.getX() + (other.getRadius()*2)) - (getX() + (getRadius()*2)))) + 
		//		(((other.getY() + (other.getRadius()*2)) - (getY() + (getRadius()*2)))*((other.getY()+(other.getRadius()*2)) - (getY() + getRadius()*2)));
		//(location <= getRadius() && location <= other.getRadius())
		
		//IF THE DISTANCE IS GREATER THAN THE SUM OF THE RADII IT IS CLEAR
		if(disBetween <= ((getRadius()/2) + (other.getRadius()/2)))
		{
			//System.out.println("DisBetween: " + disBetween + " Sum of radii: " + ((getRadius()) + (other.getRadius())));
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Return true if this Disk is out of bounds, meaning that
	 * it is not entirely enclosed by rectangle whose width and
	 * height are given by the two parameters. 
	 * Assume that the upper-left hand corner of the rectangle
	 * is at (0,0), that x coordinates increase going to
	 * the right, and that y coordinates increase going down.
	 * 
	 * @param width   the width of a rectangle -X
	 * @param height  the height of a rectangle-Y
	 * @return false if the Disk fits entirely within the rectangle,
	 *         true if at least part of the Disk lies outside the
	 *         rectangle
	 */
	public boolean isOutOfBounds(double width, double height) {
		
		if((getX() - (getRadius()/2)) > 0 && (getX() + (getRadius()/2)) < width && (getY() - (getRadius()/2)) > 0 && (getY() + (getRadius()/2) < height))
		{
			return false;
		}
				
		return true;		
		
		
		//throw new UnsupportedOperationException("TODO - implement");
		//X1 Y1 = x y
		//X2 y2 = width height 
		//double test1 = Math.sqrt((0 - getX())*(0 - getX()) + (0 - getY()) + (0 - getY()));
		//double test2 = Math.sqrt((width - getX())*(width - getX()) + (height - getY()) + (height - getY()));
		//IF test1 + getRadius() > 0 && test2 - getRadius() < width && test2 - getRadius() < height
		
		
		//Height Y Width X
		//if(getX() - radius > 0 && getY() - radius > 0  && getY() + radius < height &&  getX() + radius < width)//disk out of bounds
		//{
		//	return true;
		//}
				
		//(10,10) Radius-5 board 20x25
		//10-5>0 && 10-5>0
		//10+5 < 20 && 10+5< 25
		/*if((getX() - radius) > 0 && (getY() - radius) > 0)
		{
			if((getX() + radius) < width && (getY() + radius) < height)
			{
				return true;
			}
		}*/
		//*********************************************************************************************
		//Check bottom and right
		//Is X between the bounds is Y between the bounds
		
		//Is X between the left and right 
		//Is Y between top and bottom 
	}
}
