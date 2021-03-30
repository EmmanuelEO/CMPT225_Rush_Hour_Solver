
import rushhour.*;


import java.util.HashMap;
import java.util.HashSet;

public class FinalProject
{
	public static void main(String[] args)
	{
		testHashCodes();
		//testGameNodeGetNeighbors2();
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
	
	public static void testGameNodeGetNeighbors2()
	{
		Car a = new Car(0, 0, 2, 1);
		Car o = new Car(5, 0, 2, -1);
		Car x = new Car(4, 2, 2, 1);
		Car q = new Car(3, 3, 3, 1);
		Car c = new Car(4, 4, 2, 1);
		Car r = new Car(2, 5, 3, 1);
		
		GameNode A = new GameNode();
		
		A.getCars().put('A', a);
		A.getCars().put('O', o);
		A.getCars().put('X', x);
		A.getCars().put('Q', q);
		A.getCars().put('C', c);
		A.getCars().put('R', r);
		
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

	public static void tempTest()
	{
		Solver.solveFromFile("/Users/emmanuelokonkwo/Desktop/CMPT225/Final_Project/CMPT225_Rush_Hour_Solver/src/A00.txt",
				"/Users/emmanuelokonkwo/Desktop/CMPT225/Final_Project/CMPT225_Rush_Hour_Solver/src/A00.txt");
		
		for (int i = 0; i < Solver.matrix.length; i++) {
			for (int j = 0; j < Solver.matrix.length; j++) {
				System.out.print(Solver.matrix[i][j]);
			}
			System.out.println();
		}
		System.out.println(Solver.initBoard + " "  + Solver.isSolved(Solver.initBoard));
		HashSet<GameNode> nodes = new HashSet<GameNode>();
		nodes = Solver.initBoard.getNeighbors();
		for (GameNode node:nodes) {
			System.out.println(node + "  " + node.hashCode());
		}
	}

	public static void testHashCodes()
	{
		String inputPath = "C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\TestFiles\\A00.txt";
		
		try
		{
			Solver.initializeBoard(inputPath);
		}
		catch(Exception e)
		{
			System.out.println("Bad Board");
		}
		
		
		HashSet<GameNode> S = Solver.initBoard.getNeighbors();
		
		System.out.println("\n" + S.size() + "\n");
		
		for(GameNode N : S)
		{
			System.out.println(N.hashCode());
		}
		
		System.out.println(Solver.initBoard);
	}
}
