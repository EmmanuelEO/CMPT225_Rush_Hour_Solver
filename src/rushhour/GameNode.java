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
		// loop through all cars
		// find all available moves for each car
		// create a new GameNode for each available move
		
		return null;
	}

	
	// TODO make a method that calculates the h and f
	
	
	// TODO implement hashcode()




}


