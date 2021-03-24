package rushhour;

import java.awt.Point;
import java.lang.Math.*;
import java.util.*;

public class Car
{
	private char name;
	private byte lengthAndDir; // if negative, then the car is vertical, else if positive, then horizontal
	private byte pos; // is a number from 0 to 35 (there are 36 board spaces) -> position 0 is the bottom left
					// is always the leftmost or lower most point on the car
	
	public Car(char n, int x, int y)
	{	
		name = n;
		this.pos = 0;
		this.setPos(x, y);
	}

	public void setName(char n)
	{
		name = n;
	}

	public char getName()
	{
		return name;
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

	public Point getPos()
	{
		int x = pos % 6;
		int y = pos / 6;
		
		return new Point(x, y);
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
