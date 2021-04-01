package rushhour;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class Solver
{
    public static void solveFromFile(String inputPath, String outputPath)
    {
    	GameNode initBoard = null;
    	GameNode target = null;
    	
        try {
            initBoard = initializeBoard(inputPath);
        }
        catch (Exception e) {
            System.out.println("The board has not been initialized.\nPlease, check the board for errors and try again.\n");
            e.printStackTrace();
            return;
        }

        target = BFS(initBoard);

        try {
			writeInstructions(target, outputPath);
		}
        catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static GameNode initializeBoard(String inputPath) throws Exception
    {
        GameNode initBoard = new GameNode();
        char[][] matrix = new char[6][6];
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
        
        return initBoard;
    }
    
    public static boolean isSolved(GameNode node)
    {
        //To check if the X car is on the right position at the right of the board.
        return node.getCars().get('X').getX() == 4;
    }

    public static GameNode BFS (GameNode node)
    {
        LinkedList<GameNode> nodes = new LinkedList<>();
        HashSet<GameNode> visitedNodes = new HashSet<>();
        
        nodes.addLast(node);
        visitedNodes.add(node);
        
        while (!nodes.isEmpty())
        {
            GameNode n = nodes.removeFirst();
            HashSet<GameNode> gameNodes = n.getNeighbors();
            
            for (GameNode u:gameNodes)
            {
                u.setParent(n);
                if (isSolved(u))
                {
                    return u;
                }
                
                if (!visitedNodes.contains(u))
                {
                    nodes.addLast(u);
                    visitedNodes.add(u);
                }
            }
        }
        
        return null;
    }
    
    public static GameNode Astar (GameNode node)
    {
        CustomPriorityQueue openQueue = new CustomPriorityQueue();
        
        HashMap<Integer, GameNode> closedSet = new HashMap<>();
        
        node.setG(0);
        node.calculateHeuristic();
        node.setParent(null);
        openQueue.add(node);
        while (!openQueue.isEmpty()) {
            GameNode n = openQueue.poll();
            HashSet<GameNode> nodes = n.getNeighbors();
            for (GameNode node1: nodes) {
                node1.calculateHeuristic();
                node1.setParent(n);
                node1.setG(n.getG()+1);
                if (isSolved(node1)) {
                    return node1;
                }
                else if (openQueue.contains(node1))
                {
                    if (node1.getF() < openQueue.getNode(node1.hashCode()).getF()){
                        openQueue.update(node1);
                    }
                }
                else if (closedSet.containsKey(node1.hashCode()))
                {
                    if (node1.getF() < closedSet.get(node1.hashCode()).getF()) {
                        closedSet.remove(node1.hashCode());
                        openQueue.add(node1);
                    }
                }
                else {
                    openQueue.add(node1);
                }
            }
            closedSet.put(n.hashCode(), n);
        }
        return null;
    }
    
	public static void writeInstructions(GameNode solution, String outputPath) throws IOException
	{
		Stack<String> instructions = new Stack<>();
		GameNode current = solution;
		
		// To Write Instructions
		// START LOOP
		// 1. Get current Node's Parent
		// 2. Find which car has moved
		// 2.1 Find direction and distance moved
		// 2.2 Push the movement into the instructions Stack
		// 3. Set current = current's parent
		// END LOOP when current does not have a parent
		// Write strings from the instructions Stack into the output file line by line
		
		while(current.getParent() != null)
		{
			GameNode parent = current.getParent();
			
			// 2. Find which car has moved
			for(Character name : current.getCars().keySet())
			{
				if(current.getCars().get(name).getX() != parent.getCars().get(name).getX()
						|| current.getCars().get(name).getY() != parent.getCars().get(name).getY())
				{
					// 2.1 Find direction and distance moved
					// Instruction in format: NameDirectionDistance
					String move = name.toString();
					int dir = current.getCars().get(name).getDir();
					int distance = 0;
					
					if(dir == 1) // car is horizontal
					{
						distance = current.getCars().get(name).getX() - parent.getCars().get(name).getX();
						
						if(distance > 0) // Moved Right
						{
							move += "R" + distance;
						}
						
						else // Moved left
						{
							move += "L" + -distance;
						}
					}
					
					else // car is vertical
					{
						distance = current.getCars().get(name).getY() - parent.getCars().get(name).getY();
						
						if(distance > 0) // Moved Down
						{
							move += "D" + distance;
						}
						
						else // Moved up
						{
							move += "U" + -distance;
						}
					}
					
					// 2.2 Push the movement into the instructions Stack
					instructions.push(move);
				}
				
			}
			
			// 3. Set current = current's parent
			current = parent;
		} // while (current.getParent() != null)
		
		// Write strings from the instructions Stack into the output file line by line
		
		BufferedWriter outFile = new BufferedWriter(new FileWriter(outputPath));
		
	    while(!instructions.isEmpty())
	    {
	    	outFile.write(instructions.pop() + "\n");
	    }
	    
	    outFile.close();
	}
}



