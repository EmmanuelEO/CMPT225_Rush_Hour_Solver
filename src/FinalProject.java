import rushhour.Car;
import rushhour.CustomPriorityQueue;
import rushhour.GameNode;
import rushhour.Solver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class FinalProject
{
    public static void main(String[] args)
    {
        testSolveTimeAstar();
    }

    public static void testSolveFromFile()
    {
        String fileName = "A09";

        String inputPath = "C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\CMPT225_Rush_Hour_Solver\\test_files\\" + fileName + ".txt";
        String outputPath = "C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\solutionFiles\\" + fileName + ".sol";


        double start = System.nanoTime();

        Solver.solveFromFile(inputPath, outputPath);

        double end = System.nanoTime();


        System.out.println("Solved Board " + fileName + " in " + (end - start) / 1000000000 + " seconds.");
    }

    public static void testSolveTimeAstar()
    {
        double start = System.nanoTime();

        testAstar();

        double end = System.nanoTime();

        System.out.println("Time to solve all boards: " + (end - start) / 1000000000 + " seconds");
    }

    public static void testSolveTimeDijsktra()
    {
        double start = System.nanoTime();

        testDijsktra();

        double end = System.nanoTime();

        System.out.println("Time to solve all boards: " + (end - start) / 1000000000 + " seconds");
    }

    public static void testSolveTimeBFS()
    {
        double start = System.nanoTime();

        testBFS();

        double end = System.nanoTime();

        System.out.println("Time to solve all boards: " + (end - start) / 1000000000 + " seconds");
    }

    public static void testSolveTimeDFS()
    {
        double start = System.nanoTime();

        testDFS();

        double end = System.nanoTime();

        System.out.println("Time to solve all boards: " + (end - start) / 1000000000 + " seconds");
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

    public static void testHashCodes()
    {
        String inputPath = "/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/test_files/D35.txt";
        GameNode initBoard = null;

        try
        {
            initBoard = Solver.initializeBoard(inputPath);
        }
        catch(Exception e)
        {
            System.out.println("Bad Board");
        }


        HashSet<GameNode> S = initBoard.getNeighbors();

        System.out.println("\n" + S.size() + "\n");

        for(GameNode N : S)
        {
            System.out.println(N.hashCode());
        }
    }

    public static void testCustomPriorityQueue()
    {
        GameNode A = new GameNode();
        A.getCars().put('A', new Car(0, 0, 2, 1));
        A.getCars().put('O', new Car(5, 0, 3, -1));
        A.getCars().put('X', new Car(0, 2, 2, 1));
        A.getCars().put('Q', new Car(3, 3, 3, 1));
        A.getCars().put('C', new Car(4, 4, 2, 1));
        A.getCars().put('R', new Car(2, 5, 3, 1));
        A.getCars().put('B', new Car(4, 1, 2, -1));

        GameNode B = new GameNode(A);

        GameNode C = new GameNode(A);
        C.getCars().get('A').setPos(1, 0);


        A.setF(6);
        B.setF(1);
        C.setF(5);

        CustomPriorityQueue Q = new CustomPriorityQueue();

        Q.add(A);
        Q.add(C);

        System.out.println(Q.peek().getF());

        Q.update(B);

        System.out.println(Q.peek().getF());
    }

    public static void testCalculateHeuristic()
    {
        // AA...O
        // ....BO
        // XX..BO
        // ...QQQ
        // ....CC
        // ..RRR.


        GameNode A = new GameNode();

        A.getCars().put('A', new Car(0, 0, 2, 1));
        A.getCars().put('O', new Car(5, 0, 3, -1));
        A.getCars().put('X', new Car(0, 2, 2, 1));
        A.getCars().put('Q', new Car(3, 3, 3, 1));
        A.getCars().put('C', new Car(4, 4, 2, 1));
        A.getCars().put('R', new Car(2, 5, 3, 1));
        A.getCars().put('B', new Car(4, 1, 2, -1));

        A.calculateHeuristic();

        System.out.println(A.getH());
    }

    public static void testWriteInstructions()
    {
        GameNode A = new GameNode();

        A.getCars().put('A', new Car(5, 2, 3, -1));
        A.getCars().put('X', new Car(0, 3, 2, 1));

        GameNode B = new GameNode(A);
        B.setParent(A);
        B.getCars().get('A').setPos(5, 0);

        GameNode C = new GameNode(B);
        C.setParent(B);
        C.getCars().get('X').setPos(4, 3);

        try {
            //Solver.writeInstructions(C, "/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/testWriteInstructions.txt");
            Solver.writeInstructions(C, "C:\\Users\\Ian\\Desktop\\testWriteInstructions.sol");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testBFS()
    {
        File folder = new File("/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/test_files/");
        //File folder = new File("C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\CMPT225_Rush_Hour_Solver\\test_files\\");

        File[] listOfFiles = folder.listFiles();

        GameNode initBoard;
        GameNode[] solutionGameNodes = new GameNode[listOfFiles.length];
        int i = 0;

        for (File file:listOfFiles)
        {
            if (file.isFile())
            {
                String inputPath = "/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/test_files/" + file.getName();
                //String inputPath = "C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\CMPT225_Rush_Hour_Solver\\test_files\\" + file.getName();

                String outputPath = "/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/sol_files/";
                //String outputPath = "C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\solutionFiles\\";
                try
                {
                    initBoard = Solver.initializeBoard(inputPath);
                    GameNode node = Solver.BFS(initBoard);
                    try {
                        Solver.writeInstructions(node, outputPath + file.getName());
                    } catch (IOException e) {
                        System.out.println("This is an exception thrown when writing the solution files.");
                        e.printStackTrace();
                    }
                    solutionGameNodes[i] = node;
                    i++;
                }
                catch(Exception e)
                {
                    System.out.println("Bad Board");
                }
            }
        }

        for (int j = 0; j < listOfFiles.length; j++) {
            System.out.println(solutionGameNodes[j]);
        }
    }

    public static void testDFS()
    {
        File folder = new File("/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/test_files/");
        //File folder = new File("C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\CMPT225_Rush_Hour_Solver\\test_files\\");

        File[] listOfFiles = folder.listFiles();

        GameNode initBoard;
        GameNode[] solutionGameNodes = new GameNode[listOfFiles.length];
        int i = 0;

        for (File file:listOfFiles)
        {
            if (file.isFile())
            {
                String inputPath = "/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/test_files/" + file.getName();
                //String inputPath = "C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\CMPT225_Rush_Hour_Solver\\test_files\\" + file.getName();

                String outputPath = "/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/sol_files/";
                //String outputPath = "C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\solutionFiles\\";
                try
                {
                    initBoard = Solver.initializeBoard(inputPath);
                    GameNode node = Solver.DFS(initBoard);
                    try {
                        Solver.writeInstructions(node, outputPath + file.getName());
                    } catch (IOException e) {
                        System.out.println("This is an exception thrown when writing the solution files.");
                        e.printStackTrace();
                    }
                    solutionGameNodes[i] = node;
                    i++;
                }
                catch(Exception e)
                {
                    System.out.println("Bad Board");
                }
            }
        }

        for (int j = 0; j < listOfFiles.length; j++) {
            System.out.println(solutionGameNodes[j]);
        }
    }

    public static void testDijsktra()
    {
            File folder = new File("/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/test_files/");
            //File folder = new File("C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\CMPT225_Rush_Hour_Solver\\test_files\\");

            File[] listOfFiles = folder.listFiles();

            GameNode initBoard;
            GameNode solutionGameNodes[] = new GameNode[listOfFiles.length];
            int i = 0;

            for (File file:listOfFiles)
            {
                if (file.isFile())
                {
                    String inputPath = "/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/test_files/" + file.getName();
                    //String inputPath = "C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\CMPT225_Rush_Hour_Solver\\test_files\\" + file.getName();

                    String outputPath = "/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/sol_files/";
                    //String outputPath = "C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\solutionFiles\\";
                    try
                    {
                        initBoard = Solver.initializeBoard(inputPath);
                        GameNode node = Solver.Dijsktra(initBoard);
                        try {
                            Solver.writeInstructions(node, outputPath + file.getName());
                        } catch (IOException e) {
                            System.out.println("This is an exception thrown when writing the solution files.");
                            e.printStackTrace();
                        }
                        solutionGameNodes[i] = node;
                        i++;
                    }
                    catch(Exception e)
                    {
                        System.out.println("Bad Board");
                    }
                }
            }

            //System.out.println(solutionGameNodes);

            for (int j = 0; j < listOfFiles.length; j++) {
                System.out.println(solutionGameNodes[j]);
            }
    }

    public static boolean solutionIsCorrect(String boardName, GameNode target) throws IOException// solutions need to be in .txt files
    {
        String inputPath = "/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/test_files/" + boardName;
        String outputPath = "/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/sol_files/" + boardName;

        BufferedReader solutionFile = new BufferedReader(new FileReader(outputPath));
        GameNode initBoard = null;

        try {
            initBoard = Solver.initializeBoard(inputPath);
        } catch (Exception e) {
            System.out.println("Bad Board");
            e.printStackTrace();
        }

        String instruction = solutionFile.readLine();

        while(instruction != null)
        {
            char name = instruction.charAt(0);
            char cDir = instruction.charAt(1);
            int nDir = 1;
            int distance = Character.getNumericValue(instruction.charAt(2));

            if(cDir == 'L' || cDir == 'U')
                nDir = -1;

            if(initBoard.getCars().get(name).getDir() == 1) // if the car is horizontal
            {
                initBoard.getCars().get(name).setPos(initBoard.getCars().get(name).getX() + nDir * distance, initBoard.getCars().get(name).getY());
            }

            else if(initBoard.getCars().get(name).getDir() == -1) // if the car is vertical
            {
                initBoard.getCars().get(name).setPos(initBoard.getCars().get(name).getX(), initBoard.getCars().get(name).getY() + nDir * distance);
            }

            instruction = solutionFile.readLine();
        }

        solutionFile.close();

        return target.equals(initBoard);
    }

    public static void testAstar()
    {
        File folder = new File("/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/test_files/");
        //File folder = new File("C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\CMPT225_Rush_Hour_Solver\\test_files\\");

        File[] listOfFiles = folder.listFiles();

        GameNode initBoard;
        GameNode[] solutionGameNodes = new GameNode[listOfFiles.length];
        int i = 0;

        for (File file:listOfFiles)
        {
            if (file.isFile())
            {
                String inputPath = "/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/test_files/" + file.getName();
                //String inputPath = "C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\CMPT225_Rush_Hour_Solver\\test_files\\" + file.getName();

                String outputPath = "/Users/emmanuelokonkwo/Desktop/CMPT225/FinalProject/CMPT225_Rush_Hour_Solver/sol_files/";
                //String outputPath = "C:\\Users\\Ian\\Desktop\\CMPT_Programs\\CMPT_225\\FinalProject\\solutionFiles\\";
                try
                {
                    initBoard = Solver.initializeBoard(inputPath);
                    GameNode node = Solver.Astar(initBoard);
                    try {
                        Solver.writeInstructions(node, outputPath + file.getName());
                        System.out.println(solutionIsCorrect(file.getName(), node));
                    } catch (IOException e) {
                        System.out.println("This is an exception thrown when writing the solution files.");
                        e.printStackTrace();
                    }
                    solutionGameNodes[i] = node;
                    i++;
                }
                catch(Exception e)
                {
                    System.out.println("Bad Board");
                }
            }
        }

//        for (int j = 0; j < listOfFiles.length; j++) {
//            System.out.println(solutionGameNodes[j]);
//        }

    }
}