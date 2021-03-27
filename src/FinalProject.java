
import rushhour.*;

import java.awt.Point;
import java.lang.Math.*;
import java.util.*;

public class FinalProject
{
	public static void main(String[] args)
	{
		
	}
	
	public static void testGameNodeDeepCopy()
	{
		GameNode a = new GameNode();
		
		a.getCars().put('A', new Car(1, 2));
				
		GameNode b = new GameNode(a);
		
		a.getCars().get('A').setPos(3, 4);
		
		System.out.print("GameNode Deep Copy: ");
		
		if(a.getCars().get('A').getX() != b.getCars().get('A').getX())
			System.out.println("PASSED");
		else
			System.out.println("FAILED");
	}

	public static void testGameNodeGetNeighbors()
	{
		GameNode A = new GameNode();
		
		Car c = new Car(2, 2);
		c.setLength(2);
		c.setDir(1);
		
		Car d = new Car(5, 0);
		d.setLength(4);
		d.setDir(-1);
		
		Car e = new Car(0, 5);
		e.setLength(2);
		e.setDir(1);
		
		
		A.getCars().put('C', c);
		A.getCars().put('D', d);
		A.getCars().put('E', e);
		
		HashSet<GameNode> neighbors = A.getNeighbors();
		
		System.out.println("Size: " + neighbors.size() + "\n");
		
		for(GameNode N : neighbors)
		{
			for(char name : N.getCars().keySet())
			{
				System.out.println(name + ": " + N.getCars().get(name).getX() + " " + N.getCars().get(name).getY());
			}
			
			System.out.println("");
		}
	}
	
	public static void testHashMapOrdering()
	{
		HashMap<Character, Car> A = new HashMap<>();
		HashMap<Character, Car> B = new HashMap<>();
		
		Car a = new Car(1, 2);
		a.setLength(2);
		a.setDir(1);
		
		Car b = new Car(3, 4);
		b.setLength(3);
		b.setDir(-1);
		
		Car c = new Car(0, 1);
		c.setLength(1);
		c.setDir(1);
		
		Car d = new Car(1, 4);
		d.setLength(2);
		d.setDir(1);
		
		A.put('D', d);
		A.put('B', b);
		A.put('C', c);
		A.put('A', a);
		
		B.put('B', b);
		B.put('C', c);
		B.put('A', a);
		B.put('D', d);
		
		for(char name : A.keySet())
		{
			System.out.println(name);
		}
		
		System.out.println("");
		
		for(char name : B.keySet())
		{
			System.out.println(name);
		}
		
	}
}
