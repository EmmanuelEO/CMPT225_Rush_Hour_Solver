
import rushhour.Solver;
import rushhour.Car;

import java.awt.Point;
import java.lang.Math.*;
import java.util.*;

public class FinalProject
{
	public static void main(String[] args)
	{
		HashMap<Character, Car> a = new HashMap<Character, Car>();
		
		a.put('A', new Car(1, 2));
		
		System.out.println(a.get('A').getPos().x);
		
		for(Map.Entry<Character, Car> c : a.entrySet())
		{
			System.out.println(c.getValue().getPos().y);
		}
		
		a.forEach((ch, ca) -> 
		{
			System.out.println(ch.toString() + ca.getPos().x + ca.getPos().y);
		});
		
		for(char c : a.keySet())
		{
			System.out.println(7);
		}
		
		
		
	}

}
