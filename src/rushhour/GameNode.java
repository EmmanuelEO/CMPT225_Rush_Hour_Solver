package rushhour;

import java.util.HashMap;
import java.util.HashSet;

public class GameNode
{
	private GameNode parent;
	private int g;
	private int h = 0;
	
	private HashMap<Character, Car> cars;
	
	public GameNode()
	{
		cars = new HashMap<>();
	}
	
	public GameNode(GameNode node)
	{
		this.cars = new HashMap<>();
		
		for(char name : node.getCars().keySet())
		{
			this.cars.put(name, new Car(node.getCars().get(name)));
		}
	}

	public void setParent(GameNode p)
	{
		this.parent = p;
	}

	public int getG() {
		return g;
	}

	public int getF() {
		return g + h;
	}

	public int getH() {
		return h;
	}

	public void setG(int g) {
		this.g = g;
	}

	public void setH(int h) {
		this.h = h;
	}

	@Override
	public String toString() {
		return "GameNode{\n" +
				"cars=\n" + cars + "\n}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (this.hashCode() != o.hashCode()) return false;
		return true;
	}

	@Override
	public int hashCode()
	{
		int res = 0;
		
		int arr[] = {5, 11, 17, 29, 37, 41, 53, 59, 67, 71, 79, 97, 101, 107, 127, 137, 149, 157};
		int i = 0;
		
		/*for (Car car: cars.values()) {
			res = res + ((car.getX()) + (car.getY()) * arr[i]);
			i++;
		}
		res *= 1327;*/
		
		
		for(Character name : cars.keySet())
		{
			res += name.hashCode() * 17 + cars.get(name).getX() * name.hashCode() + cars.get(name).getY() * name.hashCode();
			
			//res = res % 10000;
			
			i++;
		}
		
		return res;
	}

	public GameNode getParent()
	{
		return this.parent;
	}
	
	public HashMap<Character, Car> getCars()
	{
		return cars;
	}

	public HashSet<GameNode> getNeighbors()
	{
		// STEPS:
		// 1. build board from car info
		// 2. loop through all cars
		// 2.1 find all available moves for each car
		// 2.1.1 create a new GameNode for each available move

		HashSet<GameNode> neighbors = new HashSet<>();
		
		// 1. BUILD BOARD by looping through cars
		char board[][] = new char[6][6];
		for(int n = 0; n < 36; n++)
			board[n % 6][n / 6] = ' '; // Initializing board to blank

		for(char name : cars.keySet())
		{
			if(cars.get(name).getDir() == 1) // the car is horizontal
			{
				for(int n = 0; n < cars.get(name).getLength(); n++)
				{
					board[cars.get(name).getX() + n][cars.get(name).getY()] = name;
				}
			}
			
			else if(cars.get(name).getDir() == -1) // the car is vertical
			{
				for(int n = 0; n < cars.get(name).getLength(); n++)
				{
					board[cars.get(name).getX()][cars.get(name).getY() + n] = name;
				}
			}
			
			else
			{
				System.out.println("ERROR: The direction of car " + name + " is invalid");
				return null;
			}
		} // Done building board
		

		// 2. LOOP THROUGH ALL CARS
		for(char name : cars.keySet())
		{
			// 2.1 FIND ALL AVAILABLE MOVES for the current car

			// If car is vertical
			if(cars.get(name).getDir() == -1)
			{
				// Find all moves that can be made going up
				for(int y = cars.get(name).getY() - 1; y >= 0; y--)
				{
					if(board[cars.get(name).getX()][y] == ' ')
					{
						// This space is clear, so this is a valid move; add this as a neighbor
						GameNode neighborNode = new GameNode(this);
						neighborNode.getCars().get(name).setPos(cars.get(name).getX(), y);
						// This makes the neighbor identical to this one except for the current cars position, which will be changed
						neighbors.add(neighborNode);
					}
					else
					{ // There is another car blocking the path
						break;
					}
				}

				// Find all moves that can be made going down
				for(int y = cars.get(name).getY() + 1; y <= 6 - cars.get(name).getLength(); y++)
				{
					if(board[cars.get(name).getX()][y + cars.get(name).getLength() - 1] == ' ')
					{
						// This space is clear, so this is a valid move; add this as a neighbor
						GameNode neighborNode = new GameNode(this);
						neighborNode.getCars().get(name).setPos(cars.get(name).getX(), y);
						// This makes the neighbor identical to this one except for the current cars position, which will be changed
						neighbors.add(neighborNode);
					}
					else // There is another car blocking the path
					{
						break;
					}
				}
			} // end of if car is vertical


			// else if car is horizontal
			else
			{
				// Find all moves that can be made going left
				for(int x = cars.get(name).getX() - 1; x >= 0; x--)
				{
					if(board[x][cars.get(name).getY()] == ' ')
					{
						// This space is clear, so this is a valid move; add this as a neighbor
						GameNode neighborNode = new GameNode(this);
						neighborNode.getCars().get(name).setPos(x, cars.get(name).getY());
						// This makes the neighbor identical to this one except for the current cars position, which will be changed
						neighbors.add(neighborNode);
					}
					else // There is another car blocking the path
					{
						break;
					}
				}

				// Find all moves that can be made going right
				for(int x = cars.get(name).getX() + 1; x <= 6 - cars.get(name).getLength(); x++)
				{
					if(board[x + cars.get(name).getLength() - 1][cars.get(name).getY()] == ' ')
					{
						// This space is clear, so this is a valid move; add this as a neighbor
						GameNode neighborNode = new GameNode(this);
						neighborNode.getCars().get(name).setPos(x, cars.get(name).getY());
						// This makes the neighbor identical to this one except for the current cars position, which will be changed
						neighbors.add(neighborNode);
					}
					else // There is another car blocking the path
					{
						break;
					}
				}
			} // end of else if car is horizontal

			// All possible moves for this car have been added as neighbors

		} // end of loop through all cars

		return neighbors;
	}

		
	// TODO make a method that calculates the h	



}










