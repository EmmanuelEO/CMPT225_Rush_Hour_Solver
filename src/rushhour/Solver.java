package rushhour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Stack;

public class Solver
{
	public static GameNode initBoard;
	public static char[][] matrix;

	public static void solveFromFile(String inputPath, String outputPath)
	{
		try
		{
			initializeBoard(inputPath);
		}
		catch (Exception e)
		{
			System.out.println("The board has not been initialized.\n Please, check the board for errors and try again.\n");
			e.printStackTrace();
		}
	}

	public static void initializeBoard(String inputPath) throws Exception
	{
		initBoard = new GameNode();
		matrix = new char[6][6];
		BufferedReader input = new BufferedReader(new FileReader(inputPath));

		String str = input.readLine();
		int l = 0;

		while (str != null)
		{
			if (str.length() == 6)
			{
				for (int j = 0; j < str.length(); j++)
				{
					matrix[l][j] = str.charAt(j);
				}
			}
			
			else
			{
				Exception e = new Exception("The board from the txt file is either incomplete or has more than the required cells.\n");
				throw e;
			}
			l++;
			str = input.readLine();
		}
		input.close();

		GameNode gameNode = new GameNode();
		int length_of_car, direction = 0;
		HashSet<Character> chars = new HashSet<>();

		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 6; j++)
			{
				// Checking to see if the entry is a right entry and if the car has not already been visited.
				if (matrix[i][j] != '.' && !chars.contains(matrix[i][j]))
				{
					l = j;
					int m = i;
					// Adding the cars to a hashset that tracks if each car has been visited.
					chars.add(matrix[i][j]);
					length_of_car = 1;
					// Checking to see if the entry (car character) to the left if the same as the entry (car character) to the right and if there's not
					// an invalid at that position.
					if (j <= 4 && matrix[i][j + 1] != '.' && matrix[i][j + 1] == matrix[i][j])
					{
						direction = 1;
						// Incrementing the horizontal position of the car in question
						l++;
						// Looping through to figure if the incremented position is the same as the previous horizontal position
						while (l <= 5 && matrix[i][l] == matrix[i][l - 1])
						{
							length_of_car++;
							l++;
						}
						// Setting a new car object to be the (horizontal, vertical) position of the car.
						Car car = new Car(j, i, length_of_car, direction);
						initBoard.getCars().put(matrix[i][j], car);
					}
					
					else if (i <= 4 && matrix[i + 1][j] != '.' && matrix[i + 1][j] == matrix[i][j])
					{
						direction = -1;
						// Incrementing the vertical position of the car in question
						m++;
						// Looping through to figure if the incremented position is the same as the previous horizontal position
						while (m <= 5 && matrix[m][j] == matrix[m - 1][j])
						{
							length_of_car++;
							m++;
						}
						Car car = new Car(j, i, length_of_car, direction);
						initBoard.getCars().put(matrix[i][j], car);
					}
				}
			}
		}
	}

	public static boolean isSolved(GameNode node) {
		//To check if the X car is on the right position at the right of the board.
		return node.getCars().get('X').getY() == 4;
	}

//	public static GameNode Astar (GameNode node){
//		PriorityQueue<GameNode> openQueue = new PriorityQueue<>(
//				new Comparator<>() {
//					@Override
//					public int compare(GameNode o1, GameNode o2) {
//						return o1.getF() - o2.getF();
//					}
//				}
//			);
//
//		HashMap<GameNode, Integer> closedSet = new HashMap<>();
//
//		node.setG(0);
//		node.setParent(null);
//		openQueue.add(node);
//		while (!openQueue.isEmpty()) {
//			GameNode n = openQueue.remove();
//			HashSet<GameNode> nodes = n.getNeighbors();
//			for (GameNode node1: nodes) {
//				if (isSolved(node1)) {
//					node.setG(n.getG() + 1);
//					node1.setParent(n);
//					printPath(node1);
//					return node1;
//				} else if (!openQueue.contains(node1) && !closedSet.containsKey(node1)) {
//					node1.setG(n.getG()+1);
//					node1.setParent(n);
//					openQueue.add(node1);
//				}
//				else if (node1.computeHashCode() == openQueue.element()) {
//					node1.setG(n.getG()+1);
//					node1.setParent(n);
//					openQueue.add(node1);
//				}
//			}
//		}
//	}


	private static void writeInstructions(GameNode solution, String outputPath)
	{
		Stack<String> instructions = new Stack<>();
		
		
		
		
	}
}



