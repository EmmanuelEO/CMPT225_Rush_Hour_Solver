
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
		
		if(a.getCars().get('A').getPos().x != b.getCars().get('A').getPos().x)
			System.out.println("PASSED");
		else
			System.out.println("FAILED");
	}

}
