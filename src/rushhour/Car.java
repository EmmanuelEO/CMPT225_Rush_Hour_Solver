package rushhour;

public class Car
{
	private byte lengthAndDir = 1; // if negative, then the car is vertical, else if positive, then horizontal
	private byte pos = 0; // is a number from 0 to 35 (there are 36 board spaces) -> position 0 is the Top left
					// is always the leftmost or upper most point on the car
	
	public Car(int x, int y)
	{	
		this.setPos(x, y);
	}

	@Override
	public String toString() {
		return "Car{" +
				"pos=" + "(" + getX() + ", " + getY() + ")" +
				", length="+ getLength() +
				", direction="+ getDir() +
				"}\n";
	}

	public Car(int x, int y, int length, int direction)
	{
		this.setPos(x, y);
		this.setDir(direction);
		this.setLength(length);
	}
	
	public Car(Car c)
	{
		this.setPos(c.getX(), c.getY());
		this.setDir(c.getDir());
		this.setLength(c.getLength());
	}
	
	public int getLength()
	{
		return Math.abs(lengthAndDir);
	}
	
	public boolean setLength(int L)
	{
		if(Math.abs(L) <= 6)
		{
			lengthAndDir = (byte)(L * lengthAndDir / Math.abs(lengthAndDir));
			return true;
		}
		
		else return false;
	}
	
	public Integer getX()
	{
		return pos % 6;
	}
	
	public Integer getY()
	{
		return pos / 6;
	}

	public boolean setPos(int x, int y)
	{		
		if(Math.abs(y) <= 5 && Math.abs(x)<= 5)
		{
			pos = (byte)(y * 6 + x);
			return true;
		}
		
		else return false;
	}

	public int getDir() // -1 if car is vertical, else if horizontal, +1
	{
		return lengthAndDir / Math.abs(lengthAndDir);
	}

	public boolean setDir(int orientation)
	{
		if(orientation == 1 || orientation == -1)
		{
			lengthAndDir = (byte)(Math.abs(lengthAndDir) * orientation);
			return true;
		}
		
		else return false;
	}

}
