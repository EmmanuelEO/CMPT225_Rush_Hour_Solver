package rushhour;

import java.awt.Point;
import java.lang.Math.*;
import java.util.*;

public class GameNode
{
	private GameNode parent;
	private byte f = 63; // f + h
	private byte g;
	private byte h;
	
	private HashMap<Character, Car> cars;
	//private HashSet<GameNode> neighbors;
	
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
		
		//neighbors = new HashSet<>();
	}

	public void setParent(GameNode p)
	{
		this.parent = p;
	}
	
	public GameNode getParent()
	{
		return this.parent;
	}
	
	public HashMap<Character, Car> getCars()
	{
		return cars;
	}

	// TODO make a method that finds all neighbors of a given node
	public HashSet<GameNode> getNeighbors()
	{
		// build board from car info
		// loop through all cars
		// find all available moves for each car
		// create a new GameNode for each available move
		
		HashSet<GameNode> neighbors = new HashSet<>();
		
		// build board by looping through cars
		char board[][] = new char[6][6];
		
		for(char name : cars.keySet())
		{
			if(cars.get(name).getDir() == 1) // the car is horizontal
			{
				for(int n = 0; n < cars.get(name).getLength(); n++)
				{
					board[cars.get(name).getPos().x + n][cars.get(name).getPos().y] = name;
				}
			}
			
			else if(cars.get(name).getDir() == -1) // the car is vertical
			{
				for(int n = 0; n < cars.get(name).getLength(); n++)
				{
					board[cars.get(name).getPos().x][cars.get(name).getPos().y + n] = name;
				}
			}
			
			else
			{
				System.out.println("ERROR: The direction of car " + name + " is invalid");
				return null;
			}
		} // Done building board
		
		// Loop through all cars to find all available moves for each car
		for(char name : cars.keySet())
		{
			
		}
		
		
		return neighbors;
	}

	
	// TODO make a method that calculates the h and f
	
	
	// TODO implement hashcode()




}


