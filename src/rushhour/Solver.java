package rushhour;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Solver
{
    public static GameNode initBoard;
    public static char[][] matrix;
    
    public static GameNode solveFromFile(String inputPath, String outputPath)
    {
        try {
            initializeBoard(inputPath);
            return initBoard;
        }
        catch (Exception e)
        {
            System.out.println("The board has not been initialized.\n Please, check the board for errors and try again.\n");
            e.printStackTrace();
            return null;
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
        return node.getCars().get('X').getX() == 4;
    }

    public static GameNode BFS (GameNode node) {
        LinkedList<GameNode> nodes = new LinkedList<>();
        HashSet<GameNode> visitedNodes = new HashSet<>();
        nodes.addLast(node);
        visitedNodes.add(node);
        while (!nodes.isEmpty()) {
            GameNode n = nodes.removeFirst();
            HashSet<GameNode> gameNodes = n.getNeighbors();
            for (GameNode u:gameNodes) {
                if (isSolved(u)) {
                    return u;
                }
                if (!visitedNodes.contains(u)) {
                    nodes.addLast(u);
                    visitedNodes.add(u);
                }
            }
        }
        return null;
    }
    
    public static GameNode Astar (GameNode node){
        CustomPriorityQueue openQueue = new CustomPriorityQueue();
        
        HashMap<Integer, GameNode> closedSet = new HashMap<>();
        
        node.setG(0);
        node.setParent(null);
        openQueue.add(node);
        while (!openQueue.isEmpty()) {
            GameNode n = openQueue.poll();
            HashSet<GameNode> nodes = n.getNeighbors();
            for (GameNode node1: nodes) {
                if (isSolved(node1)) {
                    node1.setG(n.getG() + 1);
                    node1.setParent(n);
                    return node1;
                } else if (openQueue.contains(node1)) {
                    //System.out.println((node1 == null) + " dg ");
                    //System.out.println((openQueue.getNode(node1.hashCode()) == null) + " df ");
                    if (node1.getF() < openQueue.getNode(node1.hashCode()).getF()){
                        openQueue.update(node1);
                    }
                } else if (closedSet.containsKey(node1.hashCode())) {
                    if (node1.getF() < closedSet.get(node1.hashCode()).getF()) {
                        closedSet.remove(node1.hashCode());
                        openQueue.add(node1);
                    }
                }  else {
                    node1.setG(n.getG()+1);
                    node1.setParent(n);
                    openQueue.add(node1);
                }
            }
            closedSet.put(n.hashCode(), n);
        }
        return null;
    }
    
    
    public static void main(String[] args) {
//        GameNode gameNode = new GameNode();
//        gameNode = solveFromFile("/Users/emmanuelokonkwo/Desktop/CMPT225/Final_Project/CMPT225_Rush_Hour_Solver/test_files/A03.txt", "/Users/emmanuelokonkwo/Desktop/CMPT225/Final_Project/CMPT225_Rush_Hour_Solver/test_files/");
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix.length; j++) {
//                System.out.print(matrix[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println(gameNode);
//        GameNode solvedGameNode = BFS(gameNode);
        File folder = new File("/Users/emmanuelokonkwo/Desktop/CMPT225/Final_Project/CMPT225_Rush_Hour_Solver/test_files/");
        File[] listOfFiles = folder.listFiles();
        int i = 0;
        GameNode gameNode;
        GameNode[] solvedGameNode = new GameNode[listOfFiles.length];

        for (File file : listOfFiles) {
            if (file.isFile()) {
                String str = "/Users/emmanuelokonkwo/Desktop/CMPT225/Final_Project/CMPT225_Rush_Hour_Solver/test_files/" + file.getName();
                //System.out.println(file.getName());
                gameNode = solveFromFile(str, str);
                solvedGameNode[i] = BFS(gameNode);
                //System.out.println(file.getName());
                i++;
            }
        }
        for (int j = 0; j < listOfFiles.length; j++) {
            System.out.println(solvedGameNode[j]);
        }
        System.out.println(solvedGameNode.length);
    }
}



