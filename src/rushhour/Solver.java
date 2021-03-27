package rushhour;

import java.io.BufferedReader;
import java.io.FileReader;

public class Solver
{
	private static GameNode initBoard;
	private static char[][] matrix;
	
	public static void solveFromFile(String inputPath, String outputPath) {
		try {
			initializeBoard(inputPath);
		} catch (Exception e) {
			System.out.println("The board has not been initialized.\n Please, check the board for errors and try again.\n");
			e.printStackTrace();
		}
	}

	private static void initializeBoard(String inputPath) throws Exception {
		matrix = new char[6][6];
		BufferedReader input = new BufferedReader(new FileReader(inputPath));

		String str = input.readLine();
		int i = 0;

		while (str != null) {
			if (str.length() == 6) {
				for (int j = 0; j < str.length(); j++) {
					matrix[i][j] = str.charAt(j);
				}
			} else {
				Exception e = new Exception("The board from the txt file is either incomplete or has more than the required cells.\n");
				throw e;
			}
			i++;
			str = input.readLine();
		}
		input.close();
	}

	public static void Astar (GameNode node, ){

	}

	public static void main(String[] args) {
		solveFromFile("/Users/emmanuelokonkwo/Desktop/CMPT225/Final_Project/CMPT225_Rush_Hour_Solver/src/A00.txt",
				"/Users/emmanuelokonkwo/Desktop/CMPT225/Final_Project/CMPT225_Rush_Hour_Solver/src/A00.txt");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}

	}
}
